<%@page import="com.Cart"%>
<%@page import="com.CartAPI"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/cart.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Cart Management</h1>
				<form id="formCart" name="formCart">

					Item Id: <input id="itemId" name="itemId" type="text"
						class="form-control form-control-sm"> <br> Item name:
					<input id="itemName" name="itemName" type="text"
						class="form-control form-control-sm"> <br> Item
					price: <input id="itemPrice" name="itemPrice" type="text"
						class="form-control form-control-sm"> <br> Item
					Quantity: <input id="quantity" name="quantity" type="text"
						class="form-control form-control-sm"> <br> Item
					description: <input id="description" name="description" type="text"
						class="form-control form-control-sm"> <br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidcartIdSave" name="hidcartIdSave" value="">


				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divCartGrid">
					<%
					Cart itemObj = new Cart();
					out.print(itemObj.readCart());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>