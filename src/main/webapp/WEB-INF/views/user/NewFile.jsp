<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<title>Research Directory | New Registration</title>
<style>
.card {
	box-shadow: 10px 10px 15px #444A4F;
}

.card:hover {
	box-shadow: 25px 25px 30px #444A4F;
}

.border {
	border: 1px solid red;
}

.require::after {
	content: " *";
	color: red;
}

canvas {
	/*prevent interaction with the canvas*/
	pointer-events: none;
}
</style>
</head>
<body class="bg-secondary" ng-controller="registrationCtrl">
	<div class="container">
		<div class="row mt-5 justify-content-center">
			<div class="col-lg-10 text-center py-3 card">
				<h3>
					<img src="assets/images/logo.png" style="max-width: 120px;">
					<br />
				</h3>
				<h6 class="text-muted">All India Institute of Medical Sciences,
					Ansari Nagar New Delhi 29</h6>

				<form action="loginProcess" method="post">
					<div ng-show="baseLine" id="baseLine">
						<h5>Baseline form</h5>
						<div class="row justify-content-center">

							<div class="col-lg-3">

								<label for="fullname" class="form-label require">Full
									Name</label> <input type="text" ng-model="user.fullname"
									name="fullname" id="fullname"
									onkeypress='return (event.charCode >= 65 && event.charCode <= 90)||(event.charCode >= 97 && event.charCode <= 122)||(event.charCode == 32)'
									ng-blur="user.fullname=user.fullname.toUpperCase()"
									class="required form-control" placeholder="Full Name" />
							</div>

							<div class="col-lg-3">
								<label for="fullname" class="form-label require">Diagnosis</label>
								<input type="text" ng-model="user.fullnames" name="fullname"
									id="fullname"
									onkeypress='return (event.charCode >= 65 && event.charCode <= 90)||(event.charCode >= 97 && event.charCode <= 122)||(event.charCode == 32)'
									ng-blur="user.fullname=user.fullname.toUpperCase()"
									class="required form-control" placeholder="Diagnosis" />
							</div>

							<div class="col-lg-3">
								<label for="fullname" class="form-label require">Age</label> <input
									type="number" ng-model="user.age" name="age" id="age"
									class="required form-control" placeholder="Age" />
							</div>

							<div class="col-lg-3">
								<label for="sex" class="form-label require">Sex</label> <select
									id="sex" class="form-control required" ng-model="user.sex">
									<option value="">--Sex--</option>
									<option value="1">Male</option>
									<option value="2">Female</option>

								</select>
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="employee_code" class="form-label require">UHID.</label>
								<input type="number" ng-model="user.uhid" name="uhid" id="uhid"
									class="required form-control" maxlength="8"
									placeholder="00000000 (length 8)" />
							</div>

							<div class="col-lg-3">
								<label for="employee_code" class="form-label require">DOB.</label>
								<input type="date" ng-model="user.dob" name="dob" id="dob"
									class="required form-control" placeholder="dd/MM/yyyy" />
							</div>

							<div class="col-lg-3">
								<label for="fatherName" class="form-label require">Father's
									name.</label> <input type="text" ng-model="user.fatherName"
									name="fatherName" id="fatherName" class="required form-control"
									placeholder="Father's name." />
							</div>

							<div class="col-lg-3">
								<label for="motherName" class="form-label require">Mother's
									name.</label> <input type="text" ng-model="user.motherName"
									name="motherName" id="motherName" class="required form-control"
									placeholder="Mother's name." />
							</div>


						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="contactNo" class="form-label require">Contact
									No.</label> <input type="text" ng-model="user.contactNo" maxlength="10"
									onkeypress='return event.charCode >= 48 && event.charCode <= 57'
									name="contact" id="contactNo" class="required form-control"
									placeholder="Contact no." />
							</div>
							<div class="col-lg-3">
								<label for="emailId" class="form-label require">Address.</label>
								<input type="text" maxlength="50" ng-model="user.address"
									name="address" id="address" class="required form-control"
									placeholder="Address" />
							</div>
							<div class="col-lg-3">
								<label for="designation" class="form-label require">Age
									at diagnosis.</label> <input type="text" ng-model="user.ageAtDiagnosis"
									name="ageAtDiagnosis" id="ageAtDiagnosis"
									class="required form-control" placeholder="Age at diagnosis">
							</div>
							<div class="col-lg-3">
								<label for="department" class="form-label require">mm/yy
									of diagnosis.</label> <input type="date" name="monthYearDiagnosis"
									id="monthYearDiagnosis" class="required form-control"
									placeholder="Month/Year(mm/yy)">
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<h5>Presenting complaint at diagnosis:</h5>
							<div class="col-lg-3">

								<input ng-model="showInput" class="form-check-input required"
									type="checkbox" id="diagnosis1" name="diagnosis1" /> <label
									for="diagnosis1" class="required">Polyuria/polydipsia.</label>
								<div ng-if="showInput">
									<input class="required form-control" type="text"
										placeholder="Enter Duration...">
								</div>
							</div>

							<div class="col-lg-3">
								<input ng-model="weightLoss" class="form-check-input required"
									type="checkbox" id="diagnosis2" name="diagnosis2" /> <label
									for="diagnosis2" class="required">Weight loss.</label>
								<div ng-if="weightLoss">
									<input class="required form-control" type="text"
										placeholder="Enter Duration...">
								</div>

							</div>

							<div class="col-lg-3">
								<input class="form-check-input required" type="checkbox"
									id="dkaDiagnosis" name="dkaDiagnosis" /> <label
									for="dkaDiagnosis" class="required">DKA at diagnosis.</label>

							</div>

						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-6">
								<label for="followUpAndage" class="form-label require">Since
									when following up in AIIMS (Month/Year) and Age. </label> <input
									type="text" id="followUpAndage" name="followUpAndage"
									class="required form-control" />
							</div>
							<div class="col-lg-6">
								<label for="followUpAndage" class="form-label require">Remarks.
								</label> <input id="" name="" class="required form-control" />
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-2">
								<button type="button" ng-click="nextStep()"
									id="registration_btn" class="w-100 btn btn-secondary">
									Next</button>
							</div>
							<p id="err" class="text-danger"></p>

						</div>
					</div>
					<div ng-show="familyHistory" id="familyHistory">
						<h5>Family history</h5>
						<div class="row justify-content-center">
							<div class="col-lg-6">
								<label for="fullname" class="form-label require"
									>History of diabetes in
									mother/father:</label>
							</div>
							<div class="col-lg-3"></div>
							<div class="col-lg-3">
								<input type="radio" id="" name="diabetes"> <label
									class="">Yes</label> <input type="radio" id="" name="diabetes">
								<label class="">No</label>
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-8">
								<label for="fullname" class="form-label require">History
									of diabetes in maternal grandmother/grandfather: </label>
							</div>
							<div class="col-lg-1"></div>
							<div class="col-lg-3">
								<input type="radio" id="" name="diabetes"> <label
									class="">Yes</label> <input type="radio" id="" name="diabetes">
								<label class="">No</label>
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-8">
								<label for="fullname" class="form-label require">History
									of diabetes in paternal grandmother/grandfather: </label>
							</div>
							<div class="col-lg-1"></div>
							<div class="col-lg-3">
								<input type="radio" id="" name="diabetes"> <label
									class="">Yes</label> <input type="radio" id="" name="diabetes">
								<label class="">No</label>
							</div>
						</div>
						<br />

						<div class="row justify-content-center">

							<div class="col-lg-7">
								<label for="fullname" class="form-label require"
									>History of diabetes in any
									other family member: </label>
							</div>

							<div class="col-lg-5">
								<input type="text" id="" name="" placeholder="Enter here...">
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-0">
								<label for="fullname" class="form-label require">Term/preterm;
									normal vaginal delivery/BW: --/immediate cry or delayed cry;
									any history of NICU stay </label>
							</div>
							<div class="col-lg-0">
								<input type="text" id="" name="" placeholder="Remarks.">
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-0">
								<label for="followUpAndage" class="form-label require">
									Development history: Appropriate for age/Delayed</label>
							</div>
							<div class="col-lg-0">
								<input type="text" id="" name="" placeholder="Remarks.">
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-0">
								<label for="followUpAndage" class="form-label require">
									Immunisation history: Complete/Incomplete</label>
							</div>
							<div class="col-lg-0">
								<input type="text" id="" name="" placeholder="Remarks.">
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-2">
								<button type="button" ng-click="nextStep1()"
									id="registration_btn" class="w-100 btn btn-secondary">
									Next</button>
							</div>
							<p id="err" class="text-danger"></p>

						</div>

					</div>


					<div ng-show="socioeconomicHistory" id="socioeconomicHistory">
						<h5>Socioeconomic history</h5>
						<div class="row justify-content-center">
							<div class="col-lg-6">
								<label for="fullname" class="form-label require">
									Occupation father:</label> <input type="text"
									class="form-control required" placeholder="Enter Occupation...">
							</div>

							<div class="col-lg-6">
								<label for="fullname" class="form-label require"> Father
									monthly income :</label> <input type="number"
									class="form-control required" placeholder="Enter income...">
							</div>

						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-6">
								<label for="fullname" class="form-label require">
									Occupation mother:</label> <input type="text"
									class="form-control required" placeholder="Enter Occupation...">
							</div>

							<div class="col-lg-6">
								<label for="fullname" class="form-label require"> Mother
									monthly income:</label> <input type="number"
									class="form-control required" placeholder="Enter income...">
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-6">
								<label for="fullname" class="form-label require">
									Grandparents (Income/pension):</label> <input type="number"
									class="form-control required" placeholder="Enter income...">
							</div>

							<div class="col-lg-6">
								<label for="fullname" class="form-label require">
									Nuclear/Joint family:</label> <input type="number"
									class="form-control required" placeholder="Enter here...">
							</div>
						</div>
						<br />


						<div class="row justify-content-center">
							<div class="col-lg-7">
								<label for="fullname" class="form-label require"> No. of
									family member dependant on head of family member: </label>
							</div>

							<div class="col-lg-5">
								<input type="number" id="" name="" placeholder="Enter here...">
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-7">
								<label for="fullname" class="form-label require">BPL
									Card holder: </label>
							</div>

							<div class="col-lg-5">
								<input type="radio" id="" name="diabetes"> <label
									class="">Yes</label> <input type="radio" id="" name="diabetes">
								<label class="">No</label>
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-7">
								<label for="fullname" class="form-label require">Beneficiary
									of Ayushman Bharat :</label>
							</div>

							<div class="col-lg-5">
								<input type="radio" id="" name="diabetes"> <label
									class="">Yes</label> <input type="radio" id="" name="diabetes">
								<label class="">No</label>
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-7">
								<label for="fullname" class="form-label require">Beneficiary
									of CGHS/any other government scheme:</label>
							</div>

							<div class="col-lg-5">
								<input type="radio" id="" name="diabetes"> <label
									class="">Yes</label> <input type="radio" id="" name="diabetes">
								<label class="">No</label>
							</div>
						</div>
						<br />


						<div class="row justify-content-center">
							<div class="col-lg-7">
								<label for="followUpAndage" class="form-label require">
									Examination</label>
							</div>
							<div class="col-lg-5">
								<input type="text" id="" name="" placeholder="Remarks.">
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-2">
								<button type="button" ng-click="nextStep2()"
									id="registration_btn" class="w-100 btn btn-secondary">
									Next</button>
							</div>
							<p id="err" class="text-danger"></p>

						</div>

					</div>

					<div ng-show="investigations" id="investigations">
						<h5>Investigations</h5>
						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="fullname" class="form-label require"> CBC
									Date:</label> <input type="date" class="form-control required">
							</div>

							<div class="col-lg-3">
								<label for="fullname" class="form-label require">
									Hb(g/dls) :</label> <input type="number" class="form-control required"
									placeholder="g/dls">
							</div>

							<div class="col-lg-3">
								<label for="fullname" class="form-label require">
									TLC(0/mm<sup>3)</sup>:
								</label> <input type="number" class="form-control required"
									placeholder="Enter income...">
							</div>

							<div class="col-lg-3">
								<label for="fullname" class="form-label require">
									Platelets(lakhs/<span>&#181;</span>L) :
								</label> <input type="number" class="form-control required"
									placeholder="Enter here...">
							</div>

						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="fullname" class="form-label require">
									LFT/RFT Date:</label> <input type="date" class="form-control required">
							</div>

							<div class="col-lg-3">
								<label for="urea" class="form-label require">
									Urea(mg/dl):</label> <input type="number" class="form-control required"
									placeholder="Enter here...">
							</div>
							<div class="col-lg-3">
								<label for="creatinine" class="form-label require">
									Creatinine(mg/dl):</label> <input type="number"
									class="form-control required" placeholder="Enter here...">
							</div>
							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Calcium(mg/dl):</label> <input type="number"
									class="form-control required" placeholder="Enter here...">
							</div>



						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="fullname" class="form-label require">
									Phosphate(mg/dl):</label> <input type="number"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="urea" class="form-label require"> ALP(lu/L):</label>
								<input type="number" class="form-control required"
									placeholder="Enter here...">
							</div>
							<div class="col-lg-3">
								<label for="creatinine" class="form-label require">
									SGOT(lu/L):</label> <input type="number" class="form-control required"
									placeholder="Enter here...">
							</div>
							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									SGPT(lu/L):</label> <input type="number" class="form-control required"
									placeholder="Enter here...">
							</div>

						</div>
						<br />


						<div class="row justify-content-center">

							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> Celiac
									seroliogy date:</label> <input type="date"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Values(lu/ml):</label> <input type="number"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Remarks:</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>

						</div>
						<br />


						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> Urine
									albumin/cr ratio date:</label> <input type="date"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Values(mg/g):</label> <input type="number"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Remarks:</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>

						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> Fundus
									examination date:</label> <input type="date"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Values(mg/g):</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Remarks:</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>

						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> Foot
									examination date:</label> <input type="date"
									class="form-control required" placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Values(mg/g):</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Remarks:</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>

						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> Lipid
									profile date:</label> <input type="date" class="form-control required"
									placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> LDL
									values(mg/dl):</label> <input type="date" class="form-control required"
									placeholder="Enter here...">
							</div>
							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> HDL
									values(mg/dl):</label> <input type="date" class="form-control required"
									placeholder="Enter here...">
							</div>
						</div>
						<br />

						<div class="row justify-content-center">
							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> TC
									values(mg/dl):</label> <input type="date" class="form-control required"
									placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require"> TG
									values(mg/dl):</label> <input type="date" class="form-control required"
									placeholder="Enter here...">
							</div>

							<div class="col-lg-3">
								<label for="calcium" class="form-label require">
									Remarks:</label> <input type="text" class="form-control required"
									placeholder="Enter here...">
							</div>
						</div>
						<br />
						<div class="row justify-content-center">
							<div class="col-lg-2">
								<button type="button" ng-click="nextStep6()"
									id="registration_btn" class="w-100 btn btn-secondary">
									Next</button>
							</div>
							<p id="err" class="text-danger"></p>

						</div>
					</div>


				</form>

				<p class="text-center text-muted">Designed and Developed by
					Computer Facility AIIMS Delhi</p>
			</div>
		</div>
	</div>
	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="assets/js/jquery.min.js"></script>
	<script type="text/javascript" src="assets/js/angular.min.js"></script>

	<script type="text/javascript" src="assets/js/sweetalert2.js"></script>
	<script type="text/javascript" src="assets/js/myApp.js"></script>
</body>
</html>