var app = angular.module('myApp', []).directive('loading',   ['$http' ,function ($http)    
	 {    
    return {    
        restrict: 'A',    
        template: '<div class="loading-spiner" style="width:100%; margin:auto;"><center><img src="https://cfapplication.aiims.edu/research-directory/assets/images/spinner.gif" /></center></div>',    
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
app.controller('topBarCtrl', function($scope,$http) {
	 $scope.url = window.location.href.split('/admin/')[1];	
});
app.controller('userVerficationCtrl', function($scope,$http,$compile) {	
	    var userRecord = null; 
	    $scope.newUserList = [];
	    $http({
	        url: '../common/getDepartmentAndDesignationList',
	        method: "GET"
	     })
	    .then(function(response) { 
	    	console.log(response);
	    	$scope.masterData = response.data;
	    },function(error){
	    	console.log(error);
	    });
	    
	    $http({
	        url: 'get-new-users',
	        method: "GET"
	     })
	    .then(function(response) {
			console.log(response);
			$scope.newUserList = response.data;
			setTimeout(() => {
				$('#toVerifyUser').DataTable( {
			    	"bPaginate": true,
			        "bLengthChange": true,
			        "bInfo": true,
			        "bFilter": true,
			        buttons: [
			            'copy', 'csv', 'excel', 'pdf', 'print'
			        ]
			    } );
			}, 100);
		 }, function(error){
			console.log(error);
		 });
	    
	    $scope.viewUser = function(employee){
	    	  $scope.modalUser = employee
	    	  $('#userModal').modal('show'); 
	    }
	    $scope.editUser = function(user){
	    	  $scope.editModalUser = user;
	    	  $('#editUserModal').modal('show'); 
	    }
	    $scope.verifyUser = function(status,employeeCode){
	    	var message = (status)?"Are you sure to APPROVE the user details?":"Are you sure to REJECT the user details?";
	    	var msg = (status)?"APPROVED":"REJECTED";
	    	
	    	if( confirm(message) ){
	    		 var remarks = '';
	    		 if(!status){
	    			 remarks = prompt("Please enter value:");	 
	    		 }
	    		 $http({
	    	        url: 'verify-user-details',
	    	        method: "POST",
	    	        data:{"employeeCode":employeeCode,"status":status,"remarks":remarks}
	    	     })
	    	    .then(function(response) {
	    			console.log(response);
	    			alert("User details "+msg+" successfully!");
	    			window.location.reload();
	    		 }, function(error){
	    			console.log(error);
	    			alert("User details "+msg+" successfully!");
	    			window.location.reload();
	    		 });
	    	}
	    }
	    $scope.updateUserDetails = function(){
	    	if( confirm("Are you sure you wish to update the profile") ==true ){ 
	    		$("#err").html("");
	    		$(".required").each(function(index){
	    			event.preventDefault();
	    			if($(this).val()==""){
	    			   $(this).focus();
	    			   $("#err").html("Please fill all required fields!");
	    			   return false;
	    			}
	    			else if(index == $(".required").length-1){ 
	    				var editProfileFile = document.getElementById("editProfile").files[0];
					    
					    if(editProfileFile != "" && editProfileFile != undefined && (editProfileFile.size > 2324530 || editProfileFile.type.substring(0,5)!='image')){
					       $("#err").html("Must be an image upto 2MB only"); 
					       $("#editProfileFile").focus();
					       return false;
					    }
					    else{
					    	if(editProfileFile){
						    	    var reader = new FileReader();
								    reader.onloadend = function() {
								    $scope.editModalUser.profile = reader.result;
									$scope.$apply();
								  }
								  reader.readAsDataURL(editProfileFile);
						    }
					    	setTimeout(() => {
					    		$http({
			    	    	        url: 'update-user-details/'+$scope.editModalUser.employeeCode,
			    	    	        method: "POST",
			    	    	        data:$scope.editModalUser
			    	    	     })
			    	    	    .then(function(response) {
			    	    			console.log(response);
			    	    			alert("User details updated successfully!");
			    	    			window.location.reload();
			    	    		 }, function(error){
			    	    			console.log(error);
			    	    			alert("Error: "+error.data.message);
			    	    			window.location.reload();
			    	    		 });
							}, 500);
		    			}
	    			}
	    		});
	    	}
	    }
});

app.controller('userListCtrl', function($scope,$http,$compile) {	
	$http({
        url: 'get-all-users',
        method: "GET"
     })
    .then(function(response) {
		console.log(response);
		$scope.usersList = response.data;
		setTimeout(() => {
			$('#userListTable').DataTable( {
		    	"bPaginate": true,
		        "bLengthChange": true,
		        "bInfo": true,
		        "bFilter": true,
		        buttons: [
		            'copy', 'csv', 'excel', 'pdf', 'print'
		        ]
		    } );
		}, 100);
	 }, function(error){
		console.log(error);
	 });
	 $scope.toggleStatus = function(employeeCode,index){ 
		    $http({
		        url: 'update-user-status/'+employeeCode+'/'+$("#status_"+index).is(":checked"),
		        method: "GET"
		     })
		    .then(function(response) { 
		    	alert("User status updated successfully!");
		    },function(error){
		    	alert(error.data.message);
		    });
	 }
	 $scope.viewUser = function(employee){
   	  $scope.modalUser = employee
   	  $('#userModal').modal('show'); 
   }
   $scope.updateRole = function(employeeCode){
	   
       if($("#role").val() == "" || $("#role").val() == undefined){
    	  alert("Please select role properly! ");
    	  $("#role").focus();
    	  return false;
       }
	   else if(confirm("Are you sure you wish to update role of employee code: "+employeeCode+" to "+$("#role").val())==true){
		     $http({
		        url: 'update-role/'+employeeCode+'/'+$("#role").val(),
		        method: "POST"
		     })
		    .then(function(response) { 
		    	console.log(response);
		    	alert("Role updated successfully");
                window.location.reload();
		    },function(error){
		    	console.log(error);
		    	alert("Error: "+error.data.message);
		    });
	   }
   }
});

app.controller('homeCtrl', function($scope,$http,$compile) {
	
     $http({
        url: 'get-master-data',
        method: "GET"
     })
    .then(function(response) { 
    	console.log(response);
    	$scope.existingDepartments = response.data.departments;
    	$scope.existingDesignations = response.data.designations;
    	$scope.existingAreaOfInterest = response.data.researchInterests;
    },function(error){
    	console.log(error);
    });
     
     $http({
         url: 'save-profiles',
         method: "GET"
      })
     .then(function(response) { 
     	console.log(response);
     },function(error){
     	console.log(error);
     });
	 
    $scope.addNewDesignation = function(){
    	if($scope.newDesignation == undefined || $scope.newDesignation.name=="" ){
    	   alert("Please enter valid designation name!");
    	   $("#newDesignation").focus();
    	   return false;
    	}else{
    		$http({
		        url: 'add-update-designation',
		        method: "POST",
		        data: $scope.newDesignation
		     })
		    .then(function(response) { 
		    	alert("Designation added successfully!");
		    	window.location.reload();
		    },function(error){
		    	alert(error.data.message);
		    });
    	}
    }
    $scope.addNewDepartment = function(){
    	if($scope.newDepartment == undefined || $scope.newDepartment.name=="" ){
    	   alert("Please enter valid department name!");
    	   $("#newDepartment").focus();
    	   return false;
    	}else{
    		$http({
		        url: 'add-update-department',
		        method: "POST",
		        data: $scope.newDepartment
		     })
		    .then(function(response) { 
		    	alert("Department added successfully!");
		    	window.location.reload();
		    },function(error){
		    	alert(error.data.message);
		    });
    	}
    }
    $scope.addNewResearchInterest = function(){
		if($("#newInterest").val() == "" || $("#newInterest").val() == undefined){
			alert("Please enter valid area of research interest!");
			$("#newInterest").focus();
		}else{
			$http({
		        url: 'add-new-research-interest',
		        method: "POST",
		        data: $scope.newInterest
		     })
		    .then(function(response) { 
		    	alert("Researh interest added successfully!");
		    	window.location.reload();
		    },function(error){
		    	alert(error.data.message);
		    });
		}
	}
    $scope.editDesignation = function(designation){
   	 $scope.newDesignation = designation;
   	 $("#newDesignation").focus();
   }
   $scope.editDepartment = function(department){
  	  $scope.newDepartment = department;
  	  $("#newDepartment").focus();
   }
   $scope.editAreaOfInterest = function(interest){
	  	  $scope.newInterest = interest;
	  	  $("#newInterest").focus();
   }
   $scope.removeDesignation = function(designation){
	   if(confirm("are you sure to remove designation: "+designation.name)==true){
		   $http({
		        url: 'remove-designation/'+designation.id,
		        method: "POST"
		     })
		    .then(function(response) { 
		    	alert("Designation  removed successfully!");
		    	window.location.reload();
		    },function(error){
		    	alert('Error: '+error.data.message);
		    });
	   }
   }
   $scope.removeDepartment = function(department){
	   if(confirm("are you sure to remove department: "+department.name)==true){
		   $http({
		        url: 'remove-department/'+department.id,
		        method: "POST"
		     })
		    .then(function(response) { 
		    	alert("Department  removed successfully!");
		    	window.location.reload();
		    },function(error){
		    	alert('Error: '+error.data.message);
		    });
	   }
   }
	$scope.removeAreaOfInterest = function(interest){
		if(confirm("Are you sure to remove research interest: "+interest.title)){
			$http({
		        url: 'remove-research-interest/'+interest.id,
		        method: "POST"
		     })
		    .then(function(response) { 
		    	alert("Researh interest removed successfully!");
		    	window.location.reload();
		    },function(error){
		    	alert(error.data.message);
		    });
		}
	}
   
});

app.controller('contentVerficationCtrl', function($scope,$http,$compile) { 
	$http({
        url: 'get-submitted-files',
        method: "GET"
     })
    .then(function(response) { 
    	console.log(response);
    	$scope.profileList = response.data;
		setTimeout(() => {
			$('#toVerifyContent').DataTable( {
		    	"bPaginate": true,
		        "bLengthChange": true,
		        "bInfo": true,
		        "bFilter": true,
		        buttons: [
		            'copy', 'csv', 'excel', 'pdf', 'print'
		        ]
		    } );
		}, 100);
    },function(error){
    	alert(error.data.message);
    });
	
	 $scope.viewProfile = function(profile){
	   	  $scope.modalProfile = profile;
	   	  $('#profileModal').modal('show'); 
	 }
	 $scope.verifyProfile = function(status,profile){
		 var status = (status)?'APPROVE':'REJECT';
		 if(confirm("Are you sure to "+status+" the profile of "+profile.user.fullname+', '+profile.user.designation) == true){
			     var newStatus = (status)?"APPROVED":"REJECTED";
			     $http({
			        url: 'profile-verification/'+profile.id+'/'+newStatus,
			        method: "POST"
			     })
			    .then(function(response) { 
			    	alert("profile "+newStatus+" successfully!");
			    	window.location.reload();
			    },function(error){
			    	alert(error.data.message);
			    	window.location.reload();
			    });
		 }
	 }
});
