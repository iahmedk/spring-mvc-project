<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<head>
<style type="text/css">
body {
	font-family: Arial, Helvetica, sans-serif;
}

.navbar {
	width: 100%;
	background-color: #555;
	overflow: auto;
}

.navbar a {
	float: left;
	padding: 12px;
	color: white;
	text-decoration: none;
	font-size: 17px;
}

.navbar a:hover {
	background-color: #000;
}

.active {
	background-color: #04AA6D;
}

@media screen and (max-width: 500px) {
	.navbar a {
		float: none;
		display: block;
	}
}
</style>
</head>
<body>
	<center>
		<h1>Car Show Room</h1>
		
		<div class="navbar">
			</a> <a class="active" href="loadCar"><i class="fa fa-fw fa-plus"></i>Create</a>
			 <a href="viewcar"><i class="fa fa-fw fa-eye"></i> View</a>
			 <a href="editcar"><i class="fa fa-fw fa-plus"></i>Edit</a>
			 <a href="deletecar"><i class="fa fa-fw fa-trash"></i> Delete</a>
			 <a href="logout"><i class="fa fa-fw fa-user"></i> LogOut</a>
		</div>
	</center>
</body>
</html>
