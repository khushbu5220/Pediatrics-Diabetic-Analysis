var app = angular.module('myApp', []).directive('loading',   ['$http' ,function ($http)    
	 {    
    return {    
        restrict: 'A',    
        template: '<div class="loading-spiner" style="width:100%; margin-top:300px !important; margin:auto;"><center><img style="max-width:80px;" src="https://cfapplication.aiims.edu/research-directory/assets/images/spinner.gif" /></center></div>',    
        link: function (scope, elm, attrs)    
        {    
            scope.isLoading = function () {    
                return $http.pendingRequests.length > 0;    
            };    
   
            scope.$watch(scope.isLoading, function (v)    
            {    
                if(v){    
                    $(".loading-spiner").show();    
                }else{    
                     $(".loading-spiner").hide();    
                }    
            });    
        }    
    };    
}]);
;
app.controller('homeCtrl', function($scope,$http,$compile) {	
	$http({
    	url:'get-all-profiles',
    	method:'GET'
    }).then(
    function(response){
    	console.log(response);
    	for(var i=0; i< response.data.length; i++){
    		let row = "<tr>"+
    		              "<td>"+(i+1)+"</td>" +
    		              "<td>"+response.data[i].user.fullname+"</td>" +
    		              "<td>"+response.data[i].user.designationName+"</td>" +
    		              "<td>"+response.data[i].user.departmentName+"</td>" +
    		              "<td>"+response.data[i].profile.researchInterest+"</td>" +
    		              "<td><a href='view-profile?id="+btoa(response.data[i].user.employeeCode)+"'>view</a></td>" +
    		          "</tr>";
                $("#tbody").append(row);
                if(i==response.data.length-1){
                   new DataTable('#example');
                }
    	}
    	
    },function(error){
    	console.log(error);
    	alert("Error: "+error.data.message);
    });
});
app.controller('profileCtrl', function($scope,$http) {
	var id = window.atob(location.search.split('id=')[1]);
	if( id != undefined && id != ""){
		$http({
            url: 'get-profile?empId='+id,
            method: "GET"
        })
        .then(function(response) {
        	console.log(response);
        	$scope.employee = response.data;
        },function(response) {
        	console.log(response);
        });
	}
});
app.controller('registrationCtrl', function($scope,$http,$compile) {	
	 $scope.showInput = false;
	 $scope.weightLoss = false;
	 
	$scope.baseLine = true;
	$scope.familyHistory = false;
	$scope.socioeconomicHistory = false;
	$scope.investigations = false;
            $scope.nextStep = function () {
				
                $scope.familyHistory =  true;
                $scope.baseLine =  false;
                $scope.socioeconomicHistory = false;
            }
            $scope.nextStep1 = function () {
				$scope.familyHistory =  false;
                $scope.baseLine =  false;
                $scope.socioeconomicHistory = true;
            }
             $scope.nextStep2 = function () {
				$scope.familyHistory =  false;
                $scope.baseLine =  false;
                $scope.socioeconomicHistory = false;
                $scope.investigations = true;
            }
	
	/*$scope.code  = undefined;

	 $scope.createCaptcha = function() {
	  
	   document.getElementById('captcha').innerHTML = "";
	   var charsArray = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@!#$%^&*";
	   var lengthOtp =4;
	   var captcha = [];
	   for (var i = 0; i < lengthOtp; i++) {
	     var index = Math.floor(Math.random() * charsArray.length + 1); //get the next character from the array
	     if (captcha.indexOf(charsArray[index]) == -1)
	       captcha.push(charsArray[index]);
	     else i--;
	   }
	   var canv = document.createElement("canvas");
	   canv.id = "captcha";
	   canv.width = 70;
	   canv.height = 50;
	   var ctx = canv.getContext("2d");
	   ctx.font = "25px Georgia";
	   ctx.strokeText(captcha.join(""), 0, 30);
	   //storing captcha so that can validate you can save it somewhere else according to your specific requirements
	   $scope.code = captcha.join("");
	   document.getElementById("captcha").appendChild(canv); // adds the canvas to the body element
	 }
	 $scope.validateCaptcha = function() {
	   event.preventDefault();
	   debugger
	   if (document.getElementById("cpatchaTextBox").value == code) {
	     alert("Valid Captcha")
	   }else{
	     alert("Invalid Captcha. try Again");
	     $scope.createCaptcha();
	   }
	 }
	 $scope.createCaptcha();*/
	$scope.registration = function(){
		var regex2 = /^[0-9]*$/;
		var regexEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
		var regexEmpCode = /([E][0-9][0-9][0-9][0-9][0-9][0-9][0-9])/;
		var regexPassword1 = /(?=.*[a-z])/;
		var regexPassword2 = /(?=.*[A-Z])/;
		var regexPassword3 = /(?=.*[0-9])/;
		var regexPassword4 = /(?=.*[-+_!@#$%^&*.,?])/;
		
		$("#err").html("");
		
		$(".required").each(function(index){
			event.preventDefault();
			if($(this).val()==""){
			   $(this).focus();
			   $("#err").html("Please fill all required fields!");
			   return false;
			}
			else if(index == $(".required").length-1){
				 if($("#contactNo").val().length != 10 || !regex2.test($("#contactNo").val())){
				    $("#err").html("Please enter valid contact number!"); 
				    $("#contactNo").focus();
				    return false;
				 }
				 else if(!regexEmail.test($("#emailId").val())){
					    $("#err").html("Please enter valid email ID"); 
					    $("#emailId").focus();
					    return false;
				 }
				 else if(!regexEmpCode.test($("#employee_code").val())){
					    $("#err").html("Please enter valid Employee Code: example E1500099 "); 
					    $("#employee_code").focus();
					    return false;
				 }
				 else if($("#password").val().length < 8){
					    $("#err").html("Minimum password length must be 8."); 
					    $("#password").focus();
					    return false;
				 }
				 else if(!regexPassword1.test($("#password").val()) || !regexPassword2.test($("#password").val()) || !regexPassword3.test($("#password").val()) || !regexPassword4.test($("#password").val())){
					    $("#err").html("Must contain atleast 1 uppercase, 1 lowercase, 1 number and 1 special character -+_!@#$%^&*.,?"); 
					    $("#password").focus();
					    return false;
				 }
				 else if($("#password").val() != $("#confirm_password").val()){
					    $("#err").html("Password not matched!"); 
					    $("#confirm_password").focus();
					    return false;
				 }
				 else if($("#codeValue").val() != $scope.code) {
					     $("#err").html("Invalid captcha code!");
					     $scope.createCaptcha();
					     $("#codeValue").focus(); 
				 }
				 else{
					    var profileFile = document.getElementById("profile").files[0];
					    var idCardFile = document.getElementById("idCard").files[0];
					    var idCardFileBack = document.getElementById("idCardBack").files[0];
					    
					    if(profileFile.size > 2324530 || profileFile.type.substring(0,5)!='image'){
					       $("#err").html("Must be an image upto 2MB only"); 
					       $("#profile").focus();
					       return false;
					    }
					    else if(idCardFile.size > 2324530 || idCardFile.type.substring(0,5)!='image'){
						       $("#err").html("Id Card front must be an image upto 2MB only"); 
						       $("#idCard").focus();
						       return false;
						 }
					    else if(idCardFileBack.size > 2324530 || idCardFileBack.type.substring(0,5)!='image'){
						       $("#err").html("Id Card Back must be an image upto 2MB only"); 
						       $("#idCardFileBack").focus();
						       return false;
						 }
					    
					    if(profileFile){
					    	  var reader = new FileReader();
							  reader.onloadend = function() {
							    $scope.user.profile = reader.result;
								$scope.$apply();
							  }
							  reader.readAsDataURL(profileFile);
					    }
					    if(idCardFile){
					    	const reader1 = new FileReader();
					    	reader1.onloadend = function(){
					    		$scope.user.idCard = reader1.result;
					    		$scope.$apply();
					    	}
					    	reader1.readAsDataURL(idCardFile);
					    }
					    if(idCardFileBack){
					    	const reader2 = new FileReader();
					    	reader2.onloadend = function(){
					    		$scope.user.idCardBack = reader2.result;
					    		$scope.$apply();
					    	}
					    	reader2.readAsDataURL(idCardFileBack);
					    }
					    $("#registration_btn").prop("disabled", true);
					    setTimeout(() => {
					    	
					    	$http({
						    	url:'new_registration',
						    	method:'POST',
						    	data:$scope.user
						    }).then(
						    function(response){
						    	console.log(response);
						    	Swal.fire(
							      'Registered!',
							      'Your account has been created successfully, and is currently under review for approval.'+ 
							       'You may contact us at aiims.research.directory[@]gmail[.]com for any queries. You will be notified by SMS when the account is Approved.',
							      'success'
							    ).then(()=>{window.location.href="login"});
						    },function(error){
						    	console.log(error);
						    	alert("Error: "+error.data.message);
						    })
						    .finally(function(){$("#registration_btn").prop("disabled", false);});
						}, 500);
					    
				 }
			}
		});
	}
});
