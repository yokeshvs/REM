<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Patient Ledger</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/ledger-util.js"></script>
<link rel="stylesheet" type="text/css" href="css/rem-util.css" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Patient Ledger</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link"
					href="equipmentDetails.jsp">Equipment Tracker</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Patient
						Ledger</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row mt-5 justify-content-center border">

			<div class="col-8">
				<div class="row text-center">
					<div class="col-12">
						<label class="font-weight-bold h3 mt-1">Enter Ulcer
							Details
							</h3>
					</div>
				</div>
				<div class="form-group mb-3 mt-5">
					<label for="name" class="font-weight-bold mb-1">Patient
						Name</label> <input type="text" class="form-control" id="name"
						aria-describedby="emailHelp" placeholder="Enter your Name">
				</div>
				<div class="form-group mb-3">
					<label for="PatientId" class="font-weight-bold mb-1">Patient
						Id</label> <input type="text" class="form-control" id="patientId"
						placeholder="10010">
				</div>
				<div class="form-group mb-3">
					<label for="initialStage" class="font-weight-bold mb-1">Intial
						Ulcer Stage</label> <select id="initialStage" class="form-control">
						<option selected>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select>
				</div>
				<div class="form-group mb-3">
					<label for="finalstage" class="font-weight-bold mb-1">Final
						Ulcer stage</label> <select id="finalStage" class="form-control">
						<option selected>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select>
				</div>
				<div class="form-group mb-3">
					<label for="mrnNo" class="font-weight-bold mb-1">MRN No</label> <input
						type="number" class="form-control" id="mrnNo"
						placeholder="102212010">
				</div>
				<div class="form-group mb-3">
					<label for="admitDate" class="font-weight-bold mb-1">Admit
						Date</label> <input type="text" class="form-control" id="admitDate"
						placeholder="102212010">
				</div>
				<div class="form-group mb-3">
					<label for="dischargeDate" class="font-weight-bold mb-1">Discharge
						Date</label> <input type="number" class="form-control" id="dischargeDate"
						placeholder="102212010">
				</div>
				<fieldset class="form-group">
					<legend class="h6 font-weight-bold">Device used</legend>
					<div class="form-check">
						<label class="form-check-label"> <input type="radio"
							class="form-check-input" name="deviceUsed" id="optionsRadios1"
							value="true" checked> yes
						</label>
					</div>
					<div class="form-check">
						<label class="form-check-label"> <input type="radio"
							class="form-check-input" name="deviceUsed" id="optionsRadios2"
							value="false"> No
						</label>
					</div>


				</fieldset>
				<div class="row justify-content-center mb-5">
					<div class="col-sm-12 col-md-6 col-lg-3">
						<button type="button" class="btn btn-primary btn-block"
							id="ledgerSubmitBtn">Submit</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/rem-util.js"></script>
	<link rel="stylesheet" type="text/css" href="css/rem-util.css" />
</body>
</html>
