let chatConnected = false;
const path = '/ShortChat';
const url = 'http://localhost:8090'+path;
let stompClient;
let selectedUser;
let newMessages = new Map();
let sessionId = "";
let recipient = "";
let tempRecipient = "";

let $chatHistory;
let $button;
let $textarea;
let $chatHistoryList;

function init() { alert("okk");
    cacheDOM();
    bindEvents();
}
function bindEvents() { alert("ok2");
    $button.on('click', addMessage.bind(this));
    $textarea.on('keyup', addMessageEnter.bind(this));
}
function cacheDOM() {
    $chatHistory = $('.chat-history');
    $button = $('#send-message-btn');
    $textarea = $('#message');
    $chatHistoryList = $chatHistory.find('ul');
}
function addMessage() {
    sendMessage($textarea.val());
}
function addMessageEnter(event) { 
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage();
    }
}
function registration(userId) {
    $.get(url + "/registration/" + userId, function (response) {
        console.log(response);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!");
        }
    });
}
function connectToChat() {
	console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        setConnected(true);
        var url = stompClient.ws._transport.url; console.log(url);
	    url = url.replace("ws://localhost:8090"+path+"/chat/",  "");
	    url = url.replace("/websocket", "");
	    url = url.replace(/^[0-9]+\//, "");
	    sessionId = url;
	    $("#sessionId").html(sessionId);
	    registration(sessionId);
	    $("#chatting-list").html("");
        stompClient.subscribe("/topic/messages/" + sessionId, function (response) {
			console.log("recieved msg"); alert("got");
            let data = JSON.parse(response.body);
            if (recipient === data.fromLogin) { 
                render(data.message, data.fromName);
            } else {
                newMessages.set(data.fromLogin, data.message);
                $('#userNameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">+1</span>');
            }
            console.log("new messages***");
            console.log(newMessages);
        });
    });
}
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}
function setConnected(connected) {
	chatConnected = connected;
    if (connected) {
        $("#chat-section").show();
        $("#user_name").prop({"disabled":true});
        $("#connect-disconnect-btn").html(" DISCONNECT ");
    }else {
		$("#select-recipient-btn").prop({"disabled":false});
		$("#select-recipient-btn").html(" CONNECT ");
	    $("#recipient-code").prop({"disabled":false});
	    $("#recipient-code").val("");
        $("#chat-section").hide();
        $("#user_name").prop({"disabled":false});
        $("#connect-disconnect-btn").html(" CONNECT ");
    }
}
function sendMsg(from, fromName, text) {
    stompClient.send("/app/chat/" + recipient, {}, JSON.stringify({
        fromName: fromName,
        fromLogin: from,
        message: text
    }));
}
/*function sendMessage(message) {
    let username = $('#user_name').val();
    console.log(username);
    sendMsg(sessionId, username, message);   
}*/
function sendMessage(message) { 
    let username = $('#user_name').val();
    console.log(username);
    sendMsg(sessionId, username, message);  
    scrollToBottom();
    if (message.trim() !== '') {
        var template = Handlebars.compile($("#message-template").html());
        var context = {
            messageOutput: message,
            time: getCurrentTime(),
            toUserName: selectedUser
        };
        $chatHistoryList.append(template(context));
        scrollToBottom();
        $textarea.val('');
    }
}
function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0].scrollHeight);
}
function connectReciepient(recipientId){
	if(recipientId === sessionId){
		alert("You can't connect to your self");
		return;
	}
	$.get(url + "/isExist/" + recipientId, function (response) {
          if( response === true ){
			   recipient = recipientId;
			   $("#select-recipient-btn").prop({"disabled":true});
			   $("#recipient-code").prop({"disabled":true});
			   $("#select-recipient-btn").html(" CONNECTED ");
			   $("#reciepient-change-btn").show();
			   $("#chatting-list").html();
			   $("#chatting-list").html("");
		  }else{
			  alert("No user exist with code: "+recipientId);
		  }
         console.log(response);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Login is already busy!");
        }
    });
}
$(document).ready(function(){
    if(!chatConnected){
		$("#chat-section").hide();
		$("#connect-disconnect-btn").html(" CONNECT ");
	}else{
		$("#chat-section").show();
		$("#connect-disconnect-btn").html(" DISCONNECT ");
	}
	$("#connect-disconnect-btn").click(function(){
		if(chatConnected){ disconnect(); } else { connectToChat(); }
	});
	$("#select-recipient-btn").click(function(){
	   connectReciepient($("#recipient-code").val());
	});
	$("#reciepient-change-btn").click(function(){
	   $("#select-recipient-btn").prop({"disabled":false});
	   $("#recipient-code").prop({"disabled":false});
	   $("#select-recipient-btn").html(" CONNECT ");
	   $("#reciepient-change-btn").hide();
	});
	init();
});