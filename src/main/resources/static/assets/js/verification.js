var app = angular.module('myApp', ['ngSanitize']);
app.directive('loading', ['$http' ,function ($http)    
 {    
     return {    
         restrict: 'A',    
         template: '<div style="width:100%;" class="loading-spiner"><center><img style="max-width:80px;" src="https://cfapplication.aiims.edu/research-directory/assets/images/spinner.gif" /></center> </div>',    
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
	 $scope.selectedProfile = null;
	  
	    $http({
		        url: 'existing-research-interest',
		        method: "GET"
	     })
	    .then(function(response) {
	    	console.log(response.data);
	    	$scope.researchIterestList = response.data;
	     },function(error){
	    	console.log(error);
	     });
	    $http({
	        url: '../admin/get-submitted-profiles',
	        method: "GET"
	     })
	    .then(function(response) {
	    	console.log(response.data);
	    	$scope.submittedProfiles = response.data;
                //$scope.sorting();
	    },function(error){
	    	console.log(error);
	    	alert("something going wrong!");
	    }); 
	    
	    $scope.selectProfile = function(profile){
               // profile.edition.editorialVersion.keyContribution = $sanitize(profile.edition.editorialVersion.keyContribution);
	   
	    	$scope.selectedProfile = profile;
	  }
	
	 $scope.sorting = function(){
	    	console.log($scope.sortBy);
	    	$scope.submittedProfiles = $scope.submittedProfiles.sort(function(a, b) { 
		    		  if($scope.sortBy == 'employee_code'){
		    			  var nameA = a.user.employeeCode.toLowerCase();
			    		  var nameB = b.user.employeeCode.toLowerCase();
			    		  
			    		  if (nameA < nameB) return -1;
			    		  if (nameA > nameB) return 1;
			    		  return 0; 
		    		  }
		    		  else if($scope.sortBy == 'employee_name'){
		    			  var nameA = a.user.fullname.toLowerCase();
			    		  var nameB = b.user.fullname.toLowerCase();
			    		  
			    		  if (nameA < nameB) return -1;
			    		  if (nameA > nameB) return 1;
			    		  return 0;
		    		  }
		    		  else if($scope.sortBy == 'designation'){
		    			  var nameA = a.user.designationName.toLowerCase();
			    		  var nameB = b.user.designationName.toLowerCase();
			    		  
			    		  if (nameA < nameB) return -1;
			    		  if (nameA > nameB) return 1;
			    		  return 0;
		    		  }
		    		  else{
		    			  var nameA = a.user.departmentName.toLowerCase();
			    		  var nameB = b.user.departmentName.toLowerCase();
			    		  
			    		  if (nameA < nameB) return -1;
			    		  if (nameA > nameB) return 1;
			    		  return 0;
		    		  }
		    		 
	    		});
	    }
	    $scope.addResearchInterest = function(){
			 if($("#input-datalist").val()!=""){
				var researchInterest = $("#input-datalist").val();
				if(!$scope.selectedProfile.edition.editorialVersion.researchInterest.includes(researchInterest)){
				    if($scope.selectedProfile.edition.editorialVersion.researchInterest.length < 4){
				       $scope.selectedProfile.edition.editorialVersion.researchInterest.push(researchInterest);
				    }else{
				       alert("Only maximum 4 research interest allowed!");
				    } 
				}
				$("#input-datalist").val("");
			 }else{
				 alert("please enter valid research interest");
				 $("#input-datalist").focus();
			 }
		 }
		 $scope.removeInterest = function(interest){
			 for(var i=0; i<$scope.selectedProfile.edition.editorialVersion.researchInterest.length; i++){
				 if($scope.selectedProfile.edition.editorialVersion.researchInterest[i] == interest){
					 $scope.selectedProfile.edition.editorialVersion.researchInterest.splice(i,1);
				 }
			 }
		 }
		 
		 $scope.addProject = function(){
				if($("#research_project").val()!="" && ($("#research_project").val().split(' ')).length <= 50 ){
				   var research_project = $("#research_project").val();
				   if(!$scope.selectedProfile.edition.editorialVersion.researchProjects.includes(research_project)){
					   if($scope.selectedProfile.edition.editorialVersion.researchProjects.length < 2){
						   $scope.selectedProfile.edition.editorialVersion.researchProjects.push(research_project);
					   }else{
						   alert("Only maximum 2 projecs are allowed!");
					   }
				   }
				}else{
					alert("please enter valid research project with in maximum length of 50 words");
					$("#research_project").focus();
				}
			}
			
			
			
			
			$scope.addPublication = function(){
				if($("#publication").val()!="" && ($("#publication").val().split(' ')).length <= 50){
				   var publication = $("#publication").val();
				   if(!$scope.selectedProfile.edition.editorialVersion.publications.includes(publication)){
					   if($scope.selectedProfile.edition.editorialVersion.publications.length < 5){
						  $scope.selectedProfile.edition.editorialVersion.publications.push(publication);
					   }else{
						   alert("Only maximum 5 publications are allowed to add!");
					   }
				   }
				   $("#publication").val("");
				}else{
					alert("please enter valid research publication with in maximum length of 50 words");
					$("#publication").focus();
				}
			}
			
			
			 
			$scope.addPatent = function(){
					if($("#patent").val()!="" && ($("#patent").val().split(' ')).length <= 50){
					   var patent = $("#patent").val();
					   if(!$scope.selectedProfile.edition.editorialVersion.patents.includes(patent)){
						   if($scope.selectedProfile.edition.editorialVersion.patents.length < 5){
							   $scope.selectedProfile.edition.editorialVersion.patents.push(patent);
						   }else{
							   alert("Only maximum 5 patents are allowed to add!");
						   }
					   }
					   $("#patent").val("");
					}else{
						alert("please enter valid research patent with in maximum length of 50 words");
						$("#patent").focus();
					}
			}
			
			
   		
			
			$scope.removeItem = function(removeFrom, index){
				if(removeFrom === 'research_project'){
				   $scope.selectedProfile.edition.editorialVersion.researchProjects.splice(index,1); 
				}
				else if(removeFrom === 'publications'){
				   $scope.selectedProfile.edition.editorialVersion.publications.splice(index,1); 
				}
				else if(removeFrom === 'patents'){
				   $scope.selectedProfile.edition.editorialVersion.patents.splice(index,1); 
				}
			}
			
			$scope.finalSave = function(){
			    Swal.fire({
				  title: "Are you sure?",
				  text: "Once submitted, you will not be able to edit your profile!",
				  icon: "warning",
				  buttons: true,
				  dangerMode: true,
				})
				.then((willDelete) => {
				  if (willDelete) {
					  if($scope.showModalUser.edition.editorialVersion.researchInterest.length == 0){
						   alert("Please enter at least one research interest!");
						   $("#input-datalist").focus();
						   return false;
						}
						else if($scope.showModalUser.edition.editorialVersion.keyContribution == null || $scope.showModalUser.edition.editorialVersion.keyContribution == '' || $scope.showModalUser.edition.editorialVersion.keyContribution == undefined){
						   alert("Please write something in key contributions!");
						   $("#keyContibution").focus();
						   return false;
						}
						else if(($scope.showModalUser.edition.editorialVersion.keyContribution.split(' ')).length > 500 ){
						   alert("Key contributions content length can't be exceed 500 words!");
						   $("#keyContibution").focus();
						   return false; 
						}
						else if( ($scope.showModalUser.edition.editorialVersion.additionalResearchInterest.split(' ')).length > 4){
								  alert("Maximum 5 words are allowed for additional research interest");
								  $("#additionalResearchInterest").focus();
						   return false;  
						}
						else{
							  $http({
								 url: '../admin/submit-profile/'+$scope.showModalUser.user.employeeCode,
								 method: "POST",
								 data:  $scope.showModalUser.edition.editorialVersion
							  })
							 .then(function(response) {
								 console.log(response.data);
								 alert("profile submitted for publish successfully!");
								 window.location.reload();
							 },function(error){
								 console.log(error);
								 alert("something going wrong!");
							 }); 
						}  
				      
				  }
				});   
				}

	
	
	
	
	
	
	
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
	    $scope.openModal = function(user){
	    	  $scope.showModalUser = user;
	    	  $('#showUserModal').modal('show'); 
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
}).directive('ckeditor', function() {
    return {
        require: '?ngModel',
        link: function(scope, element, attrs, ngModel) {
            if (!ngModel) return;
            
            ClassicEditor
                .create(element[0])
                .then(editor => {
                    // Set initial data
                    editor.setData(ngModel.$viewValue || '');

                    // Update model on change
                    editor.model.document.on('change:data', () => {
                        scope.$evalAsync(() => {
                            ngModel.$setViewValue(editor.getData());
                        });
                    });

                    // Update editor on model change
                    ngModel.$render = () => {
                        editor.setData(ngModel.$viewValue || '');
                    };
                })
                .catch(error => {
                    console.error(error);
                });
        }
    };
});