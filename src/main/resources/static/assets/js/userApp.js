var app = angular.module('myApp', ['ngSanitize']);
app.directive('loading',   ['$http' ,function ($http)    
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
	 $scope.url = window.location.href.split('/user/')[1];	
 });
 app.controller('homeCtrl', function($scope,$http) { 
	 
	 $scope.triggerFileInput = function() {
                document.getElementById('fileInput').click();
            };
         
	 
	 $scope.newDraftVersion = undefined;
	 $scope.selectedProfile = undefined;
	 
	 $http({
        url: 'get-my-details',
        method: "GET"
     })
    .then(function(response) {
    	console.log(response.data);
    	$scope.myDetails = response.data;
     },function(error){
    	console.log(error);
     });
	 
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
        url: 'get-current-edition/2023',
        method: "GET"
     })
    .then(function(response) {
    	console.log(response.data);
    	$scope.selectedProfile = response.data.userVersion;
    },function(error){
    	console.log(error);
    });
	     
	 $scope.addNewDraftVersion = function(){
		 var version = '';
		 var maxVersion = 0;
		 for(var i=0; i<$scope.profiles.length; i++){
			 if( parseInt($scope.profiles[i].version.split('.')[0]) > maxVersion ){
				 maxVersion = parseInt($scope.profiles[i].version.split('.')[0]);
			 }
		 }
		 if( i == $scope.profiles.length){
			 $scope.newDraftVersion = {"version":(maxVersion+1)+".0", "status":"NEW", "id":0, "researchInterest":[],"researchProjects":[],"publications":[],"patents":[]};
			 $scope.profiles.push($scope.newDraftVersion);
			 $scope.selectedProfile = $scope.newDraftVersion;
		}
	 }
	 $scope.dropNewProfile = function(){
		 $scope.newDraftVersion = undefined;
		 $scope.selectedProfile = undefined;
		 for(var i=0; i<$scope.profiles.length; i++){ 
			 if($scope.profiles[i].id == 0){
				$scope.profiles.splice(i,1);
			 }
		 }
	 }
	 $scope.addResearchInterest = function(){
		 if($("#input-datalist").val()!=""){
			var researchInterest = $("#input-datalist").val();
			if(!$scope.selectedProfile.researchInterest.includes(researchInterest)){
			    if($scope.selectedProfile.researchInterest.length < 4){
			       $scope.selectedProfile.researchInterest.push(researchInterest);
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
		 for(var i=0; i<$scope.selectedProfile.researchInterest.length; i++){
			 if($scope.selectedProfile.researchInterest[i] == interest){
				 $scope.selectedProfile.researchInterest.splice(i,1);
			 }
		 }
	 }
	 $scope.addProject = function(){
		 if($("#research_project").val()!="" && ($("#research_project").val().split(' ')).length <= 50 ){
			var research_project = $("#research_project").val();
			if(!$scope.selectedProfile.researchProjects.includes(research_project)){
			    if($scope.selectedProfile.researchProjects.length < 2){
			    	$scope.selectedProfile.researchProjects.push(research_project);
                                $("#research_project").val("");
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
			if(!$scope.selectedProfile.publications.includes(publication)){
			    if($scope.selectedProfile.publications.length < 5){
			       $scope.selectedProfile.publications.push(publication);
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
				if(!$scope.selectedProfile.patents.includes(patent)){
					if($scope.selectedProfile.patents.length < 5){
						$scope.selectedProfile.patents.push(patent);
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
			$scope.selectedProfile.researchProjects.splice(index,1); 
		 }
		 else if(removeFrom === 'publications'){
			$scope.selectedProfile.publications.splice(index,1); 
		 }
		 else if(removeFrom === 'patents'){
			$scope.selectedProfile.patents.splice(index,1); 
		 }
	 }
	 $scope.draftSave = function(){
		   if($scope.selectedProfile.researchInterest.length == 0){
			  alert("Please enter at least one research interest!");
			  $("#input-datalist").focus();
			  return false;
		   }
		   else if($scope.selectedProfile.keyContribution == null || $scope.selectedProfile.keyContribution == '' || $scope.selectedProfile.keyContribution == undefined){
			  alert("Please write something in key contributions!");
			  $("#keyContibution").focus();
			  return false;
		   }
		   else if($scope.selectedProfile.keyContribution != '' && ($scope.selectedProfile.keyContribution.split(' ')).length > 500 ){
			  alert("Key contributions content length can't be exceed 500 words!");
			  $("#keyContibution").focus();
			  return false; 
		   }
		   else if($scope.selectedProfile.additionalResearchInterest != null && $scope.selectedProfile.additionalResearchInterest != undefined && ($scope.selectedProfile.additionalResearchInterest.split(' ')).length > 4){
			         alert("Maximum 5 words are allowed for additional research interest");
			         $("#additionalResearchInterest").focus();
			  return false;  
		   }
		   else{
			     $http({
			        url: 'save-as-draft',
			        method: "POST",
			        data:  $scope.selectedProfile
			     })
			    .then(function(response) {
			    	console.log(response.data);
			    	alert("saved as draft successfully!");
			    	window.location.reload();
			    },function(error){
			    	console.log(error);
			    	alert("something going wrong!");
			    }); 
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
				  if($scope.selectedProfile.researchInterest.length == 0){
					   alert("Please enter at least one research interest!");
					   $("#input-datalist").focus();
					   return false;
					}
					else if($scope.selectedProfile.keyContribution == null || $scope.selectedProfile.keyContribution == '' || $scope.selectedProfile.keyContribution == undefined){
					   alert("Please write something in key contributions!");
					   $("#keyContibution").focus();
					   return false;
					}
					else if(($scope.selectedProfile.keyContribution.split(' ')).length > 500 ){
					   alert("Key contributions content length can't be exceed 500 words!");
					   $("#keyContibution").focus();
					   return false; 
					}
					else if( ($scope.selectedProfile.additionalResearchInterest.split(' ')).length > 4){
							  alert("Maximum 5 words are allowed for additional research interest");
							  $("#additionalResearchInterest").focus();
					   return false;  
					}
					else{
						  $http({
							 url: 'save-profile',
							 method: "POST",
							 data:  $scope.selectedProfile
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
	 
 });
 
 app.controller('reviewProfilesCtrl', function($scope,$http) {
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
	        url: '../reviewer/get-submitted-profiles',
	        method: "GET"
	     })
	    .then(function(response) {
	    	console.log(response.data);
	    	$scope.submittedProfiles = response.data;
                $scope.sorting();
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
			$scope.draftSave = function(){
				 
				if($scope.selectedProfile.edition.editorialVersion.researchInterest.length == 0){
				   alert("Please enter at least one research interest!");
				   $("#input-datalist").focus();
				   return false;
				}
				else if($scope.selectedProfile.edition.editorialVersion.keyContribution == null || $scope.selectedProfile.edition.editorialVersion.keyContribution == '' || $scope.selectedProfile.edition.editorialVersion.keyContribution == undefined){
				   alert("Please write something in key contributions!");
				   $("#keyContibution").focus();
				   return false;
				}
				else if(($scope.selectedProfile.edition.editorialVersion.keyContribution.split(' ')).length > 500 ){
				   alert("Key contributions content length can't be exceed 500 words!");
				   $("#keyContibution").focus();
				   return false; 
				}
				else if( ($scope.selectedProfile.edition.editorialVersion.additionalResearchInterest.split(' ')).length > 4){
						  alert("Maximum 5 words are allowed for additional research interest");
						  $("#additionalResearchInterest").focus();
				   return false;  
				}
				else{
					  $http({
						 url: '../reviewer/save-as-draft',
						 method: "POST",
						 data:  $scope.selectedProfile.edition.editorialVersion
					  })
					 .then(function(response) {
						 console.log(response.data);
						 alert("saved as draft successfully!");
						 window.location.reload();
					 },function(error){
						 console.log(error);
						 alert("something going wrong!");
					 }); 
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
					  if($scope.selectedProfile.edition.editorialVersion.researchInterest.length == 0){
						   alert("Please enter at least one research interest!");
						   $("#input-datalist").focus();
						   return false;
						}
						else if($scope.selectedProfile.edition.editorialVersion.keyContribution == null || $scope.selectedProfile.edition.editorialVersion.keyContribution == '' || $scope.selectedProfile.edition.editorialVersion.keyContribution == undefined){
						   alert("Please write something in key contributions!");
						   $("#keyContibution").focus();
						   return false;
						}
						else if(($scope.selectedProfile.edition.editorialVersion.keyContribution.split(' ')).length > 500 ){
						   alert("Key contributions content length can't be exceed 500 words!");
						   $("#keyContibution").focus();
						   return false; 
						}
						else if( ($scope.selectedProfile.edition.editorialVersion.additionalResearchInterest.split(' ')).length > 4){
								  alert("Maximum 5 words are allowed for additional research interest");
								  $("#additionalResearchInterest").focus();
						   return false;  
						}
						else{
							  $http({
								 url: '../reviewer/submit-profile/'+$scope.selectedProfile.user.employeeCode,
								 method: "POST",
								 data:  $scope.selectedProfile.edition.editorialVersion
							  })
							 .then(function(response) {
								 console.log(response.data);
								 alert("profile submitted for verification!");
								 window.location.reload();
							 },function(error){
								 console.log(error);
								 alert("something going wrong!");
							 }); 
						}  
				      
				  }
				});   
		 }
 });