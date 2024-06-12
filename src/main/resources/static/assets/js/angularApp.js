var app = angular.module('myApp', []);

app.controller('signupCtrl', function($scope,$http) {	
	
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	
	const signup_captcha = new Captcha($('#signup_canvas'),{
        length: 4
    });
    $scope.refreshCaptcha = function(){
		  signup_captcha.refresh();
	}
	$scope.signup = function(){
		console.log($scope.user);
		$("#msg").html("&nbsp;");
		if($scope.user.fullname == undefined || $scope.user.fullname == "" || 
		   $scope.user.username == undefined || $scope.user.username == "" ||
		   $scope.user.password == undefined || $scope.user.password == "" ||
		   $("#captcha").val() == undefined || $("#captcha").val() == ""){
			$("#msg").html("<span style='color:red;'>Please fill all required fields!</span>");
			return false;
		}
		else if(!emailReg.test($scope.user.username)){
			$("#msg").html("<span style='color:red;'>Please enter valid email id!</span>");
			return false;
		}
		else if($scope.user.password != $("#confirmpassword").val()){
			$("#msg").html("<span style='color:red;'>Passsword and confirm password are not matching!</span>");
			return false;
		}
		else if(!signup_captcha.valid($("#captcha").val())){
			$("#msg").html("<span style='color:red;'>Captcha code didn't match!</span>");
			return false;
		}
		else{
			$http({
		        url: 'new_registration',
		        method: "POST",
		        data: $scope.user,
		        
		    })
		    .then(function(response) {
		        console.log(response);
		        Swal.fire(
				  'Good job!',
				  'Welcome to Assessment Portal. Try to login now !',
				  'success'
				).then(()=>{
					window.location.href = "login";
				});   
		    }, 
		    function(error) { 
		          console.log(error);
		          var errors = "";
		          for(var i=0; i<error.data.errors.length; i++){
					  errors = errors+error.data.errors[i]+', ';
				  }
		           Swal.fire(
					  'Oops!',
					  'Exception: '+errors,
					  'error'
					).then(()=>{
					  window.location.reload();
				    });
		    });
		}
	}
 });
 
 app.controller('loginCtrl', function($scope) {
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	const login_captcha = new Captcha($('#login_captcha'),{
        length: 4
    });
    $scope.refreshCaptcha = function(){
		  login_captcha.refresh();
	}
	$scope.login = function(){
		$("#msg").html("&nbsp;");
		if($scope.user.username == undefined || $scope.user.username == "" ||
		   $scope.user.password == undefined || $scope.user.password == "" ||
		   $("#captcha").val() == undefined || $("#captcha").val() == ""){
			$("#msg").html("<span style='color:red;'>Please fill all required fields!</span>");
			return false;
		}
		else if(!login_captcha.valid($("#captcha").val())){
			$("#msg").html("<span style='color:red;'>Captcha code didn't match!</span>");
			return false;
		}
		else{
			$("#loginForm").submit();
		}
	}
 });
 