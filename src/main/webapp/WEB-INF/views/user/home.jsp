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
	      <!-- <div class="col-lg-2 mt-5">
	        <h5><i class="fa fa-history"></i> Version History</h5><hr/>
	        <ul class="list-group mt-3">
			  <li class="list-group-item btn btn-link" ng-class="(selectedProfile.id==profile.id)?'active active-li':''" ng-repeat="profile in profiles">
			      <small ng-bind="profile.status+': '" ></small>
				  <span ng-bind="profile.version"></span>
			  </li>
			</ul>
	      </div> -->
	      <h4 class="text-center"><u>Research profile submission for 2024 edition</u></h4>
	      <div class="col-lg-4 mt-5" ng-if="selectedProfile!=undefined && selectedProfile.status == 'DRAFT'">
	        <h5><i class="fa fa-edit"></i> Edit  </h5><hr/><br/>
	        <div class="row">
	          <div class="col">
	            <h6><span>AREA OF RESEARCH INTEREST (upto 4)</span></h6>
	            <table class="table w-100">
	              <tr>
	                <th>
	                    <select ng-model="test" id="input-datalist" class="form-control" ng-options="interest as interest for interest in researchIterestList track by interest">
	                      <option value="">--select interest--</option>
	                    </select>
					</th>
	                <th style="width: 20%;"><button class="btn btn-outline-secondary" type="button" ng-click="addResearchInterest()"> ADD </button></th>
	              </tr>
	            </table>
				<span ng-repeat="interest in selectedProfile.researchInterest">
	                <span ng-bind="interest"></span>
	                <a href="javascript:;" ng-click="removeInterest(interest)" class="text-danger">x</a>
	                <span ng-if="$index<selectedProfile.researchInterest.length-1"> | </span>
	             </span>
              </div>
	        </div><br/>
	        <div class="row">
	          <div class="col">
	            <h6><span>Additional Research Interest (upto 5 words)</span></h6>
	            <table class="table w-100">
	              <tr>
	                <th><input type="text" onkeypress="return (event.key != ',' && event.key != '|')" class="form-control" placeholder="ADDITIONAL RESEARCH INTEREST" ng-model="selectedProfile.additionalResearchInterest" id="additionalResearchInterest"/></th>
	              </tr>
	             </table>
              </div>
	        </div><br/>
	        <div class="row">
	          <div class="col">
	            <h6><span>KEY RESEARCH CONTRIBUTIONS (max 500 words)</span></h6>
	            <div class="form-group">
	              <textarea rows="5" id="keyContibution" placeholder="KEY RESEARCH CONTRIBUTIONS" ng-model="selectedProfile.keyContribution" class="form-control"></textarea>
	            </div>
              </div>
	        </div><br/>
	        <div class="row">
	          <div class="col">
	            <h6><span>MAJOR RESEARCH PROJECTS (2 allowed)</span></h6>
	            <table class="table w-100">
	              <tr>
	                <th><input type="text" class="form-control" placeholder="RESEARCH PROJECT" id="research_project"/></th>
	                <th style="width: 20%;"><button class="btn btn-outline-secondary" ng-click="addProject()"> ADD </button></th>
	              </tr>
	              <tr ng-repeat="project in selectedProfile.researchProjects">
	                <td><span ng-bind="($index+1)+'. '+project"></span></td>
	                <td><button class="btn btn-sm btn-outline-danger" ng-click="removeItem('research_project',$index)">remove</button></td>
	              </tr>
	            </table>
              </div>
	        </div><br/>
	        <div class="row">
	          <div class="col">
	            <h6><span  data-bs-toggle="tooltip" data-bs-title="Default tooltip">MAJOR PUBLICATIONS (in Vancouver format only)</span>
	               <svg  width="20" height="20" class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
    					<path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
  					</svg>
                </h6>
	            <table class="table w-100">
	              <tr>
	                <th><input type="text" class="form-control" placeholder="MAJOR PUBLICATIONS" id="publication"/></th>
	                <th style="width: 20%;"><button class="btn btn-outline-secondary" ng-click="addPublication()"> ADD </button></th>
	              </tr>
	              <tr ng-repeat="publication in selectedProfile.publications">
	                <td><span ng-bind="($index+1)+'. '+publication"></span></td>
	                <td><button class="btn btn-sm btn-outline-danger" ng-click="removeItem('publications',$index)">remove</button></td>
	              </tr>
	            </table>
              </div>
	        </div><br/>
	        <div class="row">
	          <div class="col">
	            <h6><span>PATENTS</span></h6>
	            <table class="table w-100">
	              <tr>
	                <th><input type="text" class="form-control" placeholder="PATENT" id="patent"/></th>
	                <th style="width: 20%;"><button class="btn btn-outline-secondary" ng-click="addPatent()"> ADD </button></th>
	              </tr>
	              <tr ng-repeat="patent in selectedProfile.patents">
	                <td><span ng-bind="($index+1)+'. '+patent"></span></td>
	                <td><button class="btn btn-sm btn-outline-danger" ng-click="removeItem('patents',$index)">remove</button></td>
	              </tr>
	            </table>
              </div>
	        </div><br/>
	      </div>
	      <div class="col-lg-7 mt-5" ng-if="selectedProfile!=undefined">
	        <h5 ng-if="selectedProfile.status == 'DRAFT'"><i class="fa fa-eye"></i> Preview</h5>
	        <h5 ng-if="selectedProfile.status != 'DRAFT'"><i class="fa fa-eye"></i> <span ng-bind="selectedProfile.status+' File'"></span></h5><hr/>
	        <div class="row justify-content-center">
	          <div class="row justify-content-center">
                    <div class="col-lg-2">
	              <img src="{{'../assets/profiles/'+myDetails.employeeCode+'.png'}}" class="w-100" style="border:1px solid gray; border-radius: 50%;"/>
	            </div>
	            <div class="col-lg-6">
	              <h6 ng-bind="myDetails.fullname"></h6>
                      <span ng-bind="myDetails.designationName"></span><span ng-bind="myDetails.institutePost"></span><br/>
                      <span ng-bind="myDetails.departmentName"></span><br/>
	              <span ng-bind="'Email: '+myDetails.email"></span><br/>
	            </div>
	            
	          </div>	          
                  <div class="row content mt-3" ng-if="selectedProfile.researchInterest.length>0 || (selectedProfile.additionalResearchInterest != '' && selectedProfile.additionalResearchInterest !=null) ">
	           <div class="col text-center">
	             <h6 class='tiles-heading'><span>AREA OF RESEARCH INTEREST</span></h6>
	             &nbsp;<b ng-repeat="interest in selectedProfile.researchInterest">
	                <span ng-bind="interest"></span><span ng-if="$index<selectedProfile.researchInterest.length-1"> | </span>
	             </b>
	             <b ng-if="selectedProfile.researchInterest.length > 0 && selectedProfile.additionalResearchInterest != '' && selectedProfile.additionalResearchInterest != null "> | </b>
	             <b ng-bind="selectedProfile.additionalResearchInterest"></b>
	           </div>
	          </div><br/>
	          <div class="row content mt-3" ng-if="selectedProfile.keyContribution!=''&&selectedProfile.keyContribution!=null">
	           <div class="col">
	             <h6 class='tiles-heading'><span>KEY RESEARCH CONTRIBUTIONS</span></h6>
	             <p style="text-align:justify" class="w-100" ng-bind-html="selectedProfile.keyContribution"></p>
	           </div>
	          </div><br/>
	          <div class="row content" ng-if="selectedProfile.researchProjects.length>0">
	           <div class="col">
	             <h6 class='tiles-heading'><span>MAJOR RESEARCH PROJECTS</span></h6>
	             <ol type="1">
	               <li ng-repeat="project in selectedProfile.researchProjects"><span ng-bind="project"></span></li>
	             </ol>
	           </div>
	          </div><br/>
	          <div class="row content" ng-if="selectedProfile.publications.length>0">
	           <div class="col">
	             <h6 class='tiles-heading'><span>MAJOR PUBLICATIONS</span></h6>
	             <ol type="1">
	               <li ng-repeat="publication in selectedProfile.publications" style="text-align:justify"><span ng-bind="publication"></span></li>
	             </ol>
	           </div>
	          </div><br/>
	          <div class="row content" ng-if="selectedProfile.patents.length>0">
	           <div class="col">
	             <h6 class='tiles-heading'><span>PATENTS</span></h6>
	             <ol type="1">
	               <li style="text-align:justify" ng-repeat="patent in selectedProfile.patents"><span ng-bind="patent"></span></li>
	             </ol>
	           </div>
	          </div><br/>
	           <div class="row mt-3 justify-content-center" ng-if="selectedProfile.status == 'DRAFT'">
		          <div class="col-3">
		            <button class="btn w-100 btn-outline-secondary" ng-click="draftSave()"> Save As Draft </button>
		          </div>
		          <div class="col-3">
		            <button class="btn w-100 btn-outline-primary" ng-click="finalSave()"> Submit </button>
		          </div>
		        </div>
		        <div class="row mt-3 justify-content-center" ng-if="selectedProfile.status == 'SUBMITTED'">
		          <div class="col-4">
		            <button class="btn w-100 btn-warning"> Under Review</button>
		          </div>
		        </div>
		        <div class="row mt-3 justify-content-center" ng-if="selectedProfile.status == 'APPROVED'">
		          <div class="col-4">
		            <button class="btn w-100 btn-outline-success"> APPROVED </button>
		          </div>
		        </div>
	        </div>
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
