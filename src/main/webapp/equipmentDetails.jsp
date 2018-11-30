<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">

<title>Equipment Details</title>
</head>

<body>
	<!-- <h1>Hello, world!</h1> -->

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Remote Equipment Monitor</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="index.jsp">Home</a></li>
			</ul>
		</div>
	</nav>

	<div class="container">
		<!-- Content here -->
		<div class="device-selector-container">
			<div class="device-selector-element menu-label">Select an
				equipment:</div>
			<div class="device-selector-element">
				<select id="deviceSelector" class="form-control">
					<option value="0">Please select a device</option>
				</select>
			</div>
		</div>
		<div id="device-details-container" class="hide">
			<div class="device-container-title container-title">
				Equipment Status and History <i class="fas fa-sync-alt fa-1x"
					id="sync-eq-details"></i>
			</div>
			<hr />
			<div class="eq-detail-container row" id="eq-detail-container">
				<div class="col">
					<div class="eq-detail-label">Equipment ID</div>
					<hr width="50%">
				</div>
				<div class="col">
					<div class="eq-detail-label">Zone</div>
					<hr width="50%">
				</div>
				<div class="col">
					<div class="eq-detail-label">Status</div>
					<hr width="50%">
				</div>
				<div class="col">
					<div class="eq-detail-label">Last Seen</div>
					<hr width="50%">
				</div>
			</div>

			<div class="eq-detail-container row" id="eq-detail-dyn-container">
				<div class="col">
					<div class="eq-detail-content" id="eq-detail-deviceID"></div>
				</div>
				<div class="col">
					<div class="eq-detail-content" id="eq-detail-edgeID"></div>
				</div>
				<div class="col">
					<div class="eq-detail-content" id="eq-detail-status"></div>
				</div>
				<div class="col">
					<div class="eq-detail-content" id="eq-detail-timeStamp"></div>
				</div>
			</div>

			<table class="table eq-details-table" id="eq-details-table">
				<thead class="thead-light">
					<tr>
						<th scope="col">Zone</th>
						<th scope="col">Status</th>
						<th scope="col">Time</th>
					</tr>
				</thead>
				<tbody id="eq-details-table-data">
				</tbody>
			</table>
		</div>
	</div>
	<!-- 	<div id="chartContainer" style="height: 370px; width: 100%;"></div> -->
	<!-- 	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script> -->

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/rem-util.js"></script>
	<link rel="stylesheet" type="text/css" href="css/rem-util.css" />
	<link rel="stylesheet"
		href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
		integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
		crossorigin="anonymous">
</body>

</html>