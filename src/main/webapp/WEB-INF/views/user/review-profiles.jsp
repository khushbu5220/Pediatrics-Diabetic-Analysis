<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AIIMS Research Directory | Review Profiles</title>
<link href="../assets/css/bootstrap.min.css" rel="stylesheet">
<link href="../assets/css/jquery.dataTables.min.css" rel="stylesheet">
<link href="../assets/font-awesome/css/all.min.css" rel="stylesheet">
<link href="../assets/font-awesome/css/fontawesome.css" rel="stylesheet">
<link href="../assets/css/datatables.min.css" rel="stylesheet" />
<script src="../ckeditor5/build/ckeditor.js"></script>
<style type="text/css">
/* Add your CSS styles here */
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

.active-li span, .active-li small {
	color: white !important;
}

.tiles-heading {
	text-align: center;
}

.tiles-heading span {
	background-color: #cee8ff;
	padding: 2px 50px;
}

.content {
	padding: 5px;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body class="d-flex flex-column h-100 bg-secondary">
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<!-- Begin page content -->
	<main class="flex-shrink-0" ng-controller="reviewProfilesCtrl"
		id="homeCtrl">
		<div loading></div>
		<div class="container card mt-5 p-5">
			<div class="row">
				<div class="col-lg-3">
					<h4>Submitted Profiles: {{submittedProfiles.length}}</h4>
					<br />
					<div class="row">
						<div class="col-11">
							<label>Sort By</label> <select class="form-control"
								ng-model="sortBy" ng-init="sortBy='employee_code'"
								ng-change="sorting()">
								<option value="employee_code">Employee Code</option>
								<option value="employee_name">Name</option>
								<option value="department">Department</option>
								<option value="designation">Designation</option>
							</select>
						</div>
					</div>
					<br />
					<ul class="list-group"
						style="max-height: 500px; min-height: 500px; overflow-y: scroll">
						<li class="list-group-item"
							ng-repeat="submittedProfile in submittedProfiles" class="btn"
							ng-click="selectProfile(submittedProfile)"><a
							href="javascript:void(0)" style="text-decoration: none"
							ng-bind="submittedProfile.user.employeeCode+': '+submittedProfile.user.fullname"></a><br />
							<small class="text-muted" style="font-size: 11px;"
							ng-bind="submittedProfile.user.designationName"></small><br /> <small
							class="text-muted" style="font-size: 11px;"
							ng-bind="submittedProfile.user.departmentName"></small></li>
					</ul>
				</div>
				<div class="col-lg-9"
					ng-if="selectedProfile != null && selectedProfile != undefined">
					<h4
						ng-bind="selectedProfile.user.employeeCode+': '+selectedProfile.user.fullname"></h4>
					<br />
					<nav>
						<div class="nav nav-tabs" id="nav-tab" role="tablist">
							<button class="nav-link active" id="submitted-version-tab"
								data-bs-toggle="tab" data-bs-target="#submitted-version"
								type="button" role="tab" aria-controls="submitted-version"
								aria-selected="true">Submitted Version</button>
							<button class="nav-link" id="editorial-version-tab"
								data-bs-toggle="tab" data-bs-target="#editorial-version"
								type="button" role="tab" aria-controls="editorial-version"
								aria-selected="false">Editorial Version</button>
							<button class="nav-link" id="preview-tab" data-bs-toggle="tab"
								data-bs-target="#preview" type="button" role="tab"
								aria-controls="preview" aria-selected="false">Preview</button>
						</div>
					</nav>
					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active p-5" id="submitted-version"
							role="tabpanel" aria-labelledby="submitted-version-tab">
							<div class="row justify-content-center">
								<div class="row justify-content-center">
									<div class="col-lg-2">
										<img
											src="{{'../assets/profiles/'+selectedProfile.user.employeeCode+'.png'}}"
											class="w-100"
											style="border: 1px solid gray; border-radius: 50%;" />
									</div>
									<div class="col-lg-6">
										<h6 ng-bind="selectedProfile.user.fullname"></h6>
										<span ng-bind="selectedProfile.user.designationName"></span><span
											ng-bind="selectedProfile.user.institutePost"></span><br /> <span
											ng-bind="selectedProfile.user.departmentName"></span><br />
										<span ng-bind="'Email: '+selectedProfile.user.email"></span><br />
									</div>

								</div>
								<div class="row content mt-3"
									ng-if="selectedProfile.edition.userVersion.researchInterest.length>0 || (selectedProfile.edition.userVersion.additionalResearchInterest != '' && selectedProfile.edition.userVersion.additionalResearchInterest !=null) ">
									<div class="col text-center">
										<h6 class='tiles-heading'>
											<span>AREA OF RESEARCH INTEREST</span>
										</h6>
										&nbsp;<b
											ng-repeat="interest in selectedProfile.edition.userVersion.researchInterest">
											<span ng-bind="interest"></span><span
											ng-if="$index<selectedProfile.edition.userVersion.researchInterest.length-1">
											</span>
										</b> <b
											ng-if="selectedProfile.edition.userVersion.researchInterest.length > 0 && selectedProfile.edition.userVersion.additionalResearchInterest != '' && selectedProfile.edition.userVersion.additionalResearchInterest != null ">
											| </b> <b
											ng-bind="selectedProfile.edition.userVersion.additionalResearchInterest"></b>
									</div>
								</div>
								<br />
								<div class="row content mt-3"
									ng-if="selectedProfile.edition.userVersion.keyContribution!=''&&selectedProfile.edition.userVersion.keyContribution!=null">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>KEY RESEARCH CONTRIBUTIONS</span>
										</h6>
										<p style="text-align: justify" class="w-100"
											ng-bind-html="selectedProfile.edition.userVersion.keyContribution"></p>
									</div>
								</div>
								<br />
								<div class="row content"
									ng-if="selectedProfile.edition.userVersion.researchProjects.length>0">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>MAJOR RESEARCH PROJECTS</span>
										</h6>
										<ol type="1">
											<li ng-repeat="project in selectedProfile.edition.userVersion.researchProjects"><span
												ng-bind="project"></span></li>
										</ol>
									</div>
								</div>
								<br />
								<div class="row content"
									ng-if="selectedProfile.edition.userVersion.publications.length>0">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>MAJOR PUBLICATIONS</span>
										</h6>
										<ol type="1">
											<li
												ng-repeat="publication in selectedProfile.edition.userVersion.publications"
												style="text-align: justify"><span ng-bind="publication"></span></li>
										</ol>
									</div>
								</div>
								<br />
								<div class="row content"
									ng-if="selectedProfile.edition.userVersion.patents.length>0">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>PATENTS</span>
										</h6>
										<ol type="1">
											<li style="text-align: justify"
												ng-repeat="patent in selectedProfile.edition.userVersion.patents"><span
												ng-bind="patent"></span></li>
										</ol>
									</div>
								</div>
								<br />
							</div>
						</div>

						<div class="tab-pane fade p-5" id="editorial-version"
							role="tabpanel" aria-labelledby="editorial-version-tab">
							<!-- <div class="row">
								<div class="col-md-5 col-lg-5"> -->
							<div class="row">
								<div class="col">
									<h6>
										<span>AREA OF RESEARCH INTEREST (upto 4)</span>
									</h6>
									<table class="table w-100">
										<tr>
											<th><select ng-model="test" id="input-datalist"
												class="form-control"
												ng-options="interest as interest for interest in researchIterestList track by interest">
													<option value="">--select interest--</option>
											</select></th>
											<th style="width: 20%;"><button
													class="btn btn-outline-secondary" type="button"
													ng-click="addResearchInterest()">ADD</button></th>
										</tr>
									</table>
									{{selectedProfile.edition.editorialVersion.researchInterest.length}}
									<span
										ng-repeat="interest in selectedProfile.edition.editorialVersion.researchInterest">
										<span ng-bind="interest"></span> <a href="javascript:;"
										ng-click="removeInterest(interest)" class="text-danger">x</a>
										<span
										ng-if="$index<selectedProfile.edition.editorialVersion.researchInterest.length-1">
											| </span>
									</span>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col">
									<h6>
										<span>Additional Research Interest (upto 5 words)</span>
									</h6>
									<table class="table w-100">
										<tr>
											<th><input type="text"
												onkeypress="return (event.key != ',' && event.key != '|')"
												class="form-control"
												placeholder="ADDITIONAL RESEARCH INTEREST"
												ng-model="selectedProfile.edition.editorialVersion.additionalResearchInterest"
												id="additionalResearchInterest" /></th>
										</tr>
									</table>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col">
									<h6>
										<span>KEY RESEARCH CONTRIBUTIONS (max 500 words)</span>
									</h6>
									<div class="form-group">
										<textarea rows="5" id="keyContibution"
											ng-model="selectedProfile.edition.editorialVersion.keyContribution"
											ckeditor></textarea>

									</div>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col">
									<h6>
										<span>MAJOR RESEARCH PROJECTS (2 allowed)</span>
									</h6>
									<table class="table w-100">
										<tr>
											<th><input  type="text"
												class="form-control" placeholder="RESEARCH PROJECT"
												id="research_project" /></th>
											<th style="width: 20%;"><button
													class="btn btn-outline-secondary" ng-click="addProject()"
													>ADD</button>
												</th>
										</tr>
										<tr
											ng-repeat="project in selectedProfile.edition.editorialVersion.researchProjects track by $index">
											<td><input type="text" class="form-control" ng-model="selectedProfile.edition.editorialVersion.researchProjects[$index]" id="{{'researchProjects_'+$index}}"/></td>
											<td><a
												class="btn btn-sm btn-outline-danger"
												ng-click="removeItem('research_project',$index)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</table>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col">
									<h6>
										<span data-bs-toggle="tooltip" data-bs-title="Default tooltip">MAJOR
											PUBLICATIONS (in Vancouver format only)</span>
										<svg width="20" height="20"
											class="w-6 h-6 text-gray-800 dark:text-white"
											aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
											fill="currentColor" viewBox="0 0 20 20">
                                  <path
												d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z" />
                                </svg>
									</h6>
									<table class="table w-100">
										<tr>
											<th><input type="text"
												class="form-control" placeholder="MAJOR PUBLICATIONS"
												id="publication" /></th>
											<th style="width: 20%;"><button
													class="btn btn-outline-secondary"
													ng-click="addPublication()">ADD</button>
												</th>
										</tr>
										<tr
											ng-repeat="publication in selectedProfile.edition.editorialVersion.publications track by $index">
											<td><input type="text" class="form-control" ng-model="selectedProfile.edition.editorialVersion.publications[$index]" id="{{'publications_'+$index}}"/></td>
											<td> <a
												class="btn btn-sm btn-outline-danger"
												ng-click="removeItem('publications',$index)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
									</table>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col">
									<h6>
										<span>PATENTS</span>
									</h6>
									<table class="table w-100">
										<tr>
											<th><input  type="text"
												class="form-control" placeholder="PATENT" id="patent" /></th>
											<th style="width: 20%;"><button
													class="btn btn-outline-secondary" ng-click="addPatent()"
													id="addButton" >Add</button>
												
										</tr>
										<tr ng-repeat="patent in selectedProfile.edition.editorialVersion.patents track by $index">
										  <td><input type="text" class="form-control" ng-model="selectedProfile.edition.editorialVersion.patents[$index]" id="{{'patents_'+$index}}" />
										     </td>
										  <td><a href="javascript:;" class="btn btn-sm btn-outline-danger"
												ng-click="removeItem('patents',$index)"><i
													class="fa fa-trash"></i></a></td>
										</tr>
										

									</table>
								</div>
							</div>
							<br />
							<div class="row mt-3 justify-content-center"
								ng-if="selectedProfile.edition.editorialVersion.status == 'DRAFT'">
								<div class="col-6">
									<button class="btn w-100 btn-outline-secondary"
										ng-click="draftSave()">Save As Draft</button>
								</div>
								<div class="col-6">
									<button class="btn w-100 btn-outline-primary"
										ng-click="finalSave()">Submit</button>
								</div>
								<!-- 	</div>
								</div> -->

							</div>


						</div>

						<div class="tab-pane fade p-5" id="preview" role="tabpanel"
							aria-labelledby="preview-tab">
							<!-- <div class="row justify-content-center">
								<div class="col-md-7 col-lg-7">-->
							<div class="row justify-content-center">
								<div class="row justify-content-center">
									<div class="col-lg-2">
										<img
											src="{{'../assets/profiles/'+selectedProfile.user.employeeCode+'.png'}}"
											class="w-100"
											style="border: 1px solid gray; border-radius: 50%;" />
									</div>
									<div class="col-lg-6">
										<h6 ng-bind="selectedProfile.user.fullname"></h6>
										<span ng-bind="selectedProfile.user.designationName"></span><span
											ng-bind="selectedProfile.user.institutePost"></span><br /> <span
											ng-bind="selectedProfile.user.departmentName"></span><br />
										<span ng-bind="'Email: '+selectedProfile.user.email"></span><br />
									</div>

								</div>
								<div class="row content mt-3"
									ng-if="selectedProfile.edition.editorialVersion.researchInterest.length>0 || (selectedProfile.edition.editorialVersion.additionalResearchInterest != '' && selectedProfile.edition.editorialVersion.additionalResearchInterest !=null) ">
									<div class="col text-center">
										<h6 class='tiles-heading'>
											<span>AREA OF RESEARCH INTEREST</span>
										</h6>
										&nbsp;<b
											ng-repeat="interest in selectedProfile.edition.editorialVersion.researchInterest">
											<span ng-bind="interest"></span><span
											ng-if="$index<selectedProfile.edition.editorialVersion.researchInterest.length-1">
												| </span>
										</b> <b
											ng-if="selectedProfile.edition.editorialVersion.researchInterest.length > 0 && selectedProfile.edition.editorialVersion.additionalResearchInterest != '' && selectedProfile.edition.editorialVersion.additionalResearchInterest != null ">
											| </b> <b
											ng-bind="selectedProfile.edition.editorialVersion.additionalResearchInterest"></b>
									</div>
								</div>
								<br />
								<div class="row content mt-3"
									ng-if="selectedProfile.edition.editorialVersion.keyContribution!=''&&selectedProfile.edition.editorialVersion.keyContribution!=null">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>KEY RESEARCH CONTRIBUTIONS</span>
										</h6>
										<p style="text-align: justify" class="w-100"
											ng-bind-html="selectedProfile.edition.editorialVersion.keyContribution"></p>
									</div>
								</div>
								<br />
								<div class="row content"
									ng-if="selectedProfile.edition.editorialVersion.researchProjects.length>0">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>MAJOR RESEARCH PROJECTS</span>
										</h6>
										<ol type="1">
											<li
												ng-repeat="project in selectedProfile.edition.editorialVersion.researchProjects"><span
												ng-bind="project"></span></li>
										</ol>
									</div>
								</div>
								<br />
								<div class="row content"
									ng-if="selectedProfile.edition.editorialVersion.publications.length>0">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>MAJOR PUBLICATIONS</span>
										</h6>
										<ol type="1">
											<li
												ng-repeat="publication in selectedProfile.edition.editorialVersion.publications"
												style="text-align: justify"><span ng-bind="publication"></span></li>
										</ol>
									</div>
								</div>
								<br />
								<div class="row content"
									ng-if="selectedProfile.edition.editorialVersion.patents.length>0">
									<div class="col">
										<h6 class='tiles-heading'>
											<span>PATENTS</span>
										</h6>
										<ol type="1">
											<li style="text-align: justify"
												ng-repeat="patent in selectedProfile.edition.editorialVersion.patents"><span
												ng-bind="patent"></span></li>
										</ol>
										<!-- 	</div>
										</div> -->
										<br />
									</div>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
		</div>
	</main>
	<br />
	<jsp:include page="fragments/footer.jsp"></jsp:include>


	<script src="../assets/js/bootstrap.bundle.min.js"></script>
	<script src="../assets/js/angular.min.js"></script>
	<script src="../assets/js/angular-sanitize.js"></script>
	<script src="../assets/js/jquery.min.js"></script>
	<script src="../assets/js/jquery.dataTables.min.js"></script>
	<script src="../assets/js/sweetalert2.js?v=1.2"></script>
	<script src="../assets/js/userApp.js?v=1.4"></script>

</body>
</html>
