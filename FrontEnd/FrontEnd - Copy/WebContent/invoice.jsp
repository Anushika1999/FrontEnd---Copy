<%@ page import="com.Invoice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
//Save---------------------------------
if (request.getParameter("invoice_id") != null)
{
Invoice invoiceObj = new Invoice();
String stsMsg = "";
//Insert--------------------------
if (request.getParameter("hidInvoiceIDSave") == "")
{
stsMsg = invoiceObj.insertInvoice(request.getParameter("invoice_id"),
request.getParameter("customer_id"),
request.getParameter("customer_name"),
request.getParameter("customer_type"),
request.getParameter("pay_units"),
request.getParameter("monthly_amount"));
}
else//Update----------------------
{
stsMsg = invoiceObj.updateInvoice(request.getParameter("hidInvoiceIDSave"),
/*request.getParameter("invoice_id"),*/
request.getParameter("customer_id"),
request.getParameter("customer_name"),
request.getParameter("customer_type"),
request.getParameter("pay_units"),
request.getParameter("monthly_amount"));
}
session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidInvoiceIDDelete") != null)
{
Invoice invoiceObj = new Invoice();
String stsMsg =
invoiceObj .deleteInvoice(request.getParameter("hidInvoiceIDDelete"));
session.setAttribute("statusMsg", stsMsg);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Invoice Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Invoice Management</h1>
				<form id="formInvoice" name="formInvoice">
					Invoice ID: 
					<input id="invoice_id" name="invoice_id" type="text" class="form-control form-control-sm"> <br>
				    Customer ID:
					<input id="customer_id" name="customer_id" type="text" class="form-control form-control-sm"> <br>
				    Customer Name: 
				    <input id="customer_name" name="customer_name" type="text" class="form-control form-control-sm"> <br>
				    Customer Type: 
				    <input id="customer_type" name="customer_type" type="text" class="form-control form-control-sm"> <br> 
				    Pay Units: 
				    <input id="pay_units" name="pay_units" type="text" class="form-control form-control-sm"> <br>
				    Monthly Amount: 
				    <input id="monthly_amount" name="monthly_amount" type="text" class="form-control form-control-sm"> <br>
				    <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> <input type="hidden" id="hidInvoiceIDSave" name="hidInvoiceIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divInvoiceGrid">
					<%
					    Invoice invoiceObj = new Invoice();
						out.print(invoiceObj.readInvoice());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>