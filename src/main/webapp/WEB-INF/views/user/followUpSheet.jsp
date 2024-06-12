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

					<h5>Follow up sheet</h5>
					<div class="row justify-content-center">

						<div class="col-lg-3">

							<label for="fullname" class="form-label require">Followup
								Date:</label> <input type="date" class="required form-control" />
						</div>

						<div class="col-lg-3">
							<label for="fullname" class="form-label require">Height(cm):</label>
							<input type="number" class="required form-control"
								placeholder="Height" />
						</div>

						<div class="col-lg-3">
							<label for="fullname" class="form-label require">Weight(kg):</label>
							<input type="number" name="age" id="age"
								class="required form-control" placeholder="Weight" />
						</div>

						<div class="col-lg-3">
							<label for="fullname" class="form-label require">BP(mmHg):</label>
							<input type="number" name="age" id="age"
								class="required form-control" placeholder="Weight" />
						</div>


					</div>
					<br />
					<div class="row justify-content-center">
						<div class="col-lg-3">
							<label for="employee_code" class="form-label require">Prepubertal/Breast
								stage:</label> <input type="number" name="uhid" id="uhid"
								class="required form-control" maxlength="2" />
						</div>

						<div class="col-lg-2">
							<label for="employee_code" class="form-label require">Pubic
								hair stage:</label> <input type="number" name="uhid" id="uhid"
								class="required form-control" maxlength="2" />
						</div>

						<div class="col-lg-3">
							<label for="employee_code" class="form-label require">Lipodystrophy.</label>
							<input type="text" name="dob" id="dob"
								class="required form-control" placeholder="Absent/Present" />
						</div>

						<div class="col-lg-2">
							<label for="contactNo" class="form-label require">HbAIC
								date.</label> <input type="date" class="required form-control" />
						</div>
						<div class="col-lg-2">
							<label for="emailId" class="form-label require">Value(%).</label>
							<input type="number" class="required form-control" />
						</div>
					</div>
					<br />


					<div class="row justify-content-center">
						<div class="col-lg-3">
							<label for="contactNo" class="form-label require">Thyroid
								profile date.</label> <input type="date" class="required form-control" />
						</div>
						<div class="col-lg-2">
							<label for="emailId" class="form-label require">T3(ng/dl).</label>
							<input type="number" class="required form-control" />
						</div>

						<div class="col-lg-2">
							<label for="emailId" class="form-label require">T4(<span>&#181;</span>g/dl).
							</label> <input type="number" class="required form-control" />
						</div>
						<div class="col-lg-2">
							<label for="emailId" class="form-label require">TSH(mlU/L)</label>
							<input type="number" class="required form-control" />
						</div>
						<div class="col-lg-2">
							<label for="emailId" class="form-label require">FT4(ng/dl)</label>
							<input type="number" class="required form-control" />
						</div>

					</div>
					<br />


					<div class="row justify-content-center">
						<div class="col-lg-3">
							<label for="contactNo" class="form-label require">Celiac
								serology date.</label> <input type="date" class="required form-control" />
						</div>
						<div class="col-lg-2">
							<label for="emailId" class="form-label require">Value(lU/ml)</label>
							<input type="number" class="required form-control" />
						</div>
						<div class="col-lg-2">
							<label for="remarks" class="form-label require">Remarks</label> <input
								type="number" class="required form-control" />
						</div>

					</div>
					<br />

					<div class="row justify-content-center">
						<div class="col-lg-8">
							<input ng-model="showInput" class="form-check-input required"
								type="checkbox" /> <label for="diagnosis1" class="require">Date
								of last visit in the multidisciplinary clinic(Monday OPD cards):</label>
						</div>
						<div ng-if="showInput" class="col-lg-3">
							<input class="required form-control" type="date"
								placeholder="Enter Duration...">
						</div>
					</div>
					<br />
					<div class="row justify-content-center">
						<div class="col-lg-6">
							<label for="fullname" class="form-label require">Insulin
								dose written: </label>
						</div>
						<div class="col-lg-3">
							<input type="radio" id="" name="diabetes"> <label
								class="">Yes</label> <input type="radio" id="" name="diabetes">
							<label class="">No</label>
						</div>
					</div>
					<br />

					<div class="row justify-content-center">
						<div class="col-lg-4">
							<label for="fullname" class="form-label require">Total
								daily dose of insulin(Units/day):</label> <input type="number"
								class="required form-control">
						</div>

						<div class="col-lg-4">
							<label for="fullname" class="form-label require">Basal
								insulin dose(units/day):</label> <input type="number"
								class="required form-control">
						</div>

						<div class="col-lg-3">
							<label for="fullname" class="form-label require">Bolus
								insulin(units/day):</label> <input type="number"
								class="required form-control">
						</div>
					</div>
					<br />

					<div class="row justify-content-center">
						<div class="col-lg-6">
							<label for="fullname" class="form-label require">
								Investigations to be done in next OPD written or not</label>
						</div>
						<div class="col-lg-3">
							<input type="radio" id="" name="diabetes"> <label
								class="">Yes</label> <input type="radio" id="" name="diabetes">
							<label class="">No</label>
						</div>
					</div>
					<br />

					<div class="row justify-content-center">
						<div class="col-lg-8">
							<input ng-model="showInput1" class="form-check-input required"
								type="checkbox" /> <label for="diagnosis1" class="require">Next
								appointment given or not(yes/no)& date of next appointment</label>
						</div>
						<div ng-if="showInput1" class="col-lg-3">
							<input class="required form-control" type="date">
						</div>
					</div>
					<br />

					<div class="row justify-content-center">
						<div class="col-lg-4">
							<label for="employee_code" class="form-label require">Frequencey
								of monitoring of BG/day.</label> <input type="text" name="dob" id="dob"
								class="required form-control" />
						</div>

						<div class="col-lg-5">
							<label for="employee_code" class="form-label require">Frequencey
								of monitoring of BG/month.</label> <input type="text" name="dob"
								id="dob" class="required form-control" />
						</div>


						<div class="col-lg-3">
							<label for="fullname" class="form-label require">
								Physical activity</label> <input type="radio" id="" name="diabetes">
							<label class="">Yes</label> <input type="radio" id=""
								name="diabetes"> <label class="">No</label>
						</div>
					</div>
					<br />
					<div class="row justify-content-center">
						<div class="col-lg-3">
							<label for="employee_code" class="form-label require">Insulin
								storage.</label> <input type="text" name="dob" id="dob"
								class="required form-control" />
						</div>
						
						<div class="col-lg-3">
							<label for="fullname" class="form-label require">
								Insulin rotation</label> <input type="radio" id="" name="diabetes">
							<label class="">Yes</label> <input type="radio" id=""
								name="diabetes"> <label class="">No</label>
						</div>
						<div class="col-lg-5">
							<label for="fullname" class="form-label require">
								Correction being given correctly.</label> <input type="radio" id="" name="diabetes">
							<label class="">Yes</label> <input type="radio" id=""
								name="diabetes"> <label class="">No</label>
						</div>
					</div>
					<br />
					
					<div class="row justify-content-center">
						<div class="col-lg-8">
							<input ng-model="showInput1" class="form-check-input required"
								type="checkbox" /> <label for="diagnosis1" class="require">If duration of
								diabetes is more than 5 years and age of child is > 10 Years</label>
						</div>
						<div ng-if="showInput1" class="col-lg-3">
							<input class="required form-control" type="date">
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
	<script type="text/javascript" src="assets/js/followup.js"></script>
</body>
</html>