<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" class="h-100" ng-app="myApp">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AIIMS Research Directory | USER Panel</title>
    <link href="../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../assets/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="../assets/font-awesome/css/all.min.css" rel="stylesheet">
    <link href="../assets/font-awesome/css/fontawesome.css" rel="stylesheet">
    <link href="../assets/css/datatables.min.css" rel="stylesheet" />
    <link href="../assets/css/sticky-footer-navbar.css" rel="stylesheet">
    <style>
    
    	
    
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }
      .active-li span, .active-li small{
        color:white !important;
      }
      .tiles-heading{
        text-align: center;
      }
      .tiles-heading span{
        background-color: #cee8ff;
        padding: 2px 50px;
      }
      .content{
        padding: 5px;
      }
      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>
  <body class="d-flex flex-column h-100 bg-secondary">
  <jsp:include page="fragments/header.jsp"></jsp:include>
	<!-- Begin page content -->
	<main class="flex-shrink-0" ng-controller="homeCtrl" id="homeCtrl">
	
	  <div class="container card p-5 mt-5">
	    
	          <div class="row justify-content-center">
                    <div class="col-lg-2">
	              <img src="{{'../assets/profiles/'+myDetails.employeeCode+'.png'}}" class="w-100" style="border:1px solid gray; border-radius: 50%;"/>
	             
      <div class="edit-icon" ng-click="triggerFileInput()">
                    <i class="fa fa-edit"></i>
                </div>
                <!-- Hidden File Input -->
                <input type="file" id="fileInput" style="display: none;" ng-change="uploadImage()" ng-model="myDetails.newProfileImage" accept="image/*"/>
          
	            </div>
	            <div class="col-lg-6">
	              <h6 ng-bind="myDetails.fullname"></h6>
                      <span ng-bind="myDetails.designationName"></span><span ng-bind="myDetails.institutePost"></span><br/>
                      <span ng-bind="myDetails.departmentName"></span><br/>
	              <span ng-bind="'Email: '+myDetails.email"></span><br/>
	            </div>
	            
	          </div>	          
                  
	</div>
   </main><br/>
<jsp:include page="fragments/footer.jsp"></jsp:include>
<script src="../assets/js/bootstrap.bundle.min.js"></script>
<script src="../assets/js/angular.min.js"></script>
<script src="../assets/js/angular-sanitize.js"></script>
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/jquery.dataTables.min.js"></script>
<script src="../assets/js/sweetalert2.js?v=1.2"></script>
<script src="../assets/js/userApp.js?v=1.3"></script>
<script>
    document.addEventListener('DOMContentLoaded', e => {
    	const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
    	const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
    }, false);
</script>
</body>
</html>
