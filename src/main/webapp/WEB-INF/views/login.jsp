<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" >
    <link href="assets/css/style.css" rel="stylesheet" >
    <title>Pediatrics Diabetic | Login</title>
    <style>
      .card{
        box-shadow: 10px 10px 15px #444A4F;
      }
      .card:hover{
        box-shadow: 25px 25px 30px #444A4F;
      }
    </style>
  </head>
  <body class="bg-secondary">
    <div class="container">
      <div class="row mt-5 justify-content-center">
        <div class="col-lg-5 text-center py-5 card">
          <h3><img src="assets/images/logo.png" style="max-width: 120px;"> <br/>Pediatrics Diabetic</h3>
          <h6 class="text-muted">All India Institute of Medical Sciences,<br/>Ansari Nagar New Delhi 29</h6><br/>
          <form action="loginProcess" id="loginForm" method="post">
            <div class="row justify-content-center">
             <div class="col-8">
	             <label for="userid" class="form-label">Employee Code</label>
	             <input type="text" name="username" required="required" id="userid" onkeypress='return (event.charCode >= 65 && event.charCode <= 90)||(event.charCode >= 97 && event.charCode <= 122)||(event.charCode >= 48 && event.charCode <= 57)' 
	              class="required form-control form-control-lg" maxlength="8" placeholder="E0000000 (length 8)"
	              value="<%= (session.getAttribute("invalid_username")!=null)?session.getAttribute("invalid_username"):"" %>"/>
             </div>
            </div><br/>
            <div class="row justify-content-center">
             <div class="col-8">
	             <label for="password" class="form-label">Password</label>
	             <input type="password" name="password" required="required" id="password" class="form-control-lg form-control" placeholder="Password ..."
	             value="<%= (session.getAttribute("invalid_password")!=null)?session.getAttribute("invalid_password"):"" %>"/>
             </div>
            </div><br/>
            <div class="row justify-content-center">
            <div class="col-8">
                 <label for="password" class="form-label require">Security Code</label>
                  <div class="input-group mb-3">
					  <div class="input-group-prepend" onclick="createCaptcha()">
					    <span class="input-group-text" id="captcha" style="max-height: 50px;"></span>
					  </div>
					  <input type="text" id="codeValue" class="form-control form-control-lg" placeholder="Code" aria-label="Username" aria-describedby="basic-addon1">
					</div>
             </div>
            </div><br/>
            <div class="row justify-content-center">
             <div class="col-8">
	             <button type="button" onclick="submitForm()" class="w-100 btn-lg btn btn-secondary"> Login </button>
             </div>
             <p class="mt-1"><a href="forgot-password">Forgot password</a></p>
             <p id="err" class="text-danger"></p>
             <p class="text-center mt-3">If New User? <a href="registration">Create Account</a></p>
            </div>
            <p class="text-danger"><%= (session.getAttribute("errorLoginMessage")!=null)?session.getAttribute("errorLoginMessage"):"" %></p>
            <p class="text-danger"><%= (session.getAttribute("password_reset_msg")!=null)?session.getAttribute("password_reset_msg"):"" %></p>
          </form>
          <br/><p class="text-center text-muted">Designed and Developed by Computer Facility AIIMS Delhi</p>
        </div>
      </div>
    </div>
    <!-- Optional JavaScript; choose one of the two! -->

    <!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.bundle.min.js"></script>
     <script src="assets/js/angular.min.js"></script>
     <script src="assets/js/angular-route.min.js"></script>
     <script src="assets/js/ngStorage.min.js"></script>
     <script src="assets/js/newApp.js"></script>
     <!-- <script src="assets/js/authService.js"></script>
     <script src="assets/js/loginController.js"></script> -->
     
    <% session.removeAttribute("invalid_username"); session.removeAttribute("invalid_password"); session.removeAttribute("errorLoginMessage"); session.removeAttribute("password_reset_msg"); %>
    <script type="text/javascript">
        var code;
	    function createCaptcha() {
	      document.getElementById('captcha').innerHTML = "";
	      var charsArray = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@!#$%^&*";
	      var lengthOtp = 6;
	      var captcha = [];
	      for (var i = 0; i < lengthOtp; i++) {
	        //below code will not allow Repetition of Characters
	        var index = Math.floor(Math.random() * charsArray.length + 1); //get the next character from the array
	        if (captcha.indexOf(charsArray[index]) == -1)
	          captcha.push(charsArray[index]);
	        else i--;
	      }
	      var canv = document.createElement("canvas");
	      canv.id = "captcha";
	      canv.width = 100;
	      canv.height = 70;
	      var ctx = canv.getContext("2d");
	      ctx.font = "25px Georgia";
	      ctx.strokeText(captcha.join(""), 0, 40);
	      //storing captcha so that can validate you can save it somewhere else according to your specific requirements
	      code = captcha.join("");
	      document.getElementById("captcha").appendChild(canv); // adds the canvas to the body element
	    }
	    function submitForm(){
	    	     event.preventDefault();
	    	     var regexEmpCode = /([E][0-9][0-9][0-9][0-9][0-9][0-9][0-9])/;
	    	     if(!regexEmpCode.test($("#userid").val())){
					    $("#err").html("Please enter valid Employee Code: example E1500099 "); 
					    $("#userid").focus();
					    createCaptcha();
					    return false;
				 }
	    	     else if($("#password").val() == "" || $("#password").val() == undefined){
	    	    	    $("#err").html("Please enter password "); 
					    $("#password").focus();
					    createCaptcha();
					    return false;
	    	     }
	    	     else if($("#codeValue").val() == "" || $("#codeValue").val() == undefined || $("#codeValue").val() != code){
	    	    	    $("#err").html("Please enter valid Security code. "); 
					    $("#codeValue").focus();
					    createCaptcha();
					    return false;
	    	     }
	    	     else{
	    	    	   $("#loginForm").submit();
	    	     }
	    }
	    createCaptcha();
    </script>
    
</body>
</html>