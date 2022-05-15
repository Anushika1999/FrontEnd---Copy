$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});
//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();
// Form validation-------------------
var status = validateInvoiceForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid-------------------------
$("#formInvoice").submit();
});
//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidInvoiceIDSave").val($(this).closest("tr").find('#hidInvoiceIDUpdate').val());
$("#invoice_id").val($(this).closest("tr").find('td:eq(0)').text());
$("#customer_id").val($(this).closest("tr").find('td:eq(1)').text());
$("#customer_name").val($(this).closest("tr").find('td:eq(2)').text());
$("#customer_type").val($(this).closest("tr").find('td:eq(3)').text());
$("#pay_units").val($(this).closest("tr").find('td:eq(3)').text());
$("#monthly_amount").val($(this).closest("tr").find('td:eq(3)').text());
});
//CLIENT-MODEL================================================================
function validateInvoiceForm()
{
// INVOICE ID
if ($("#invoice_id").val().trim() == "")
{
return "Insert Invoice ID.";
}

// CUSTOMER ID
if ($("#customer_id").val().trim() == "")
{
return "Insert Customer ID.";
}

// CUSTOMER NAME
if ($("#customer_name").val().trim() == "")
{
return "Insert Customer Name.";
}

// CUSTOMER TYPE
if ($("#customer_type").val().trim() == "")
{
return "Insert Customer Type.";
}

// PAY UNITS
if ($("#pay_units").val().trim() == "")
{
return "Insert Pay Units.";
}


// Monthly Amount-------------------------------
if ($("#monthly_amount").val().trim() == "")
{
return "Insert Monthly Amount.";
}
// is numerical value
var tmpPrice = $("#monthly_amount").val().trim();
if (!$.isNumeric(monthly_amount))
{
return "Insert a numerical value for Item Price.";
}
// convert to decimal price
$("#monthly_amount").val(parseFloat(tmpPrice).toFixed(2));
}



/*
// REMOVE==========================================
$(document).on("click", ".remove", function(event)
{
$(this).closest(".student").remove();
$("#alertSuccess").text("Removed successfully.");
$("#alertSuccess").show();
});
// CLIENT-MODEL=================================================================
function validateItemForm()
{
// NAME
if ($("#txtName").val().trim() == "")
{
return "Insert student name.";
}
// GENDER
if ($('input[name="rdoGender"]:checked').length === 0)
{
return "Select gender.";
}
// YEAR
if ($("#ddlYear").val() == "0")
{
return "Select year.";
}
return true;
}
function getStudentCard(name, gender, year)
{
var title = (gender == "Male") ? "Mr." : "Ms.";
var yearNumber = "";
switch (year) {
case "1":
yearNumber = "1st";
break;
case "2":
yearNumber = "2nd";
break;
case "3":
yearNumber = "3rd";
break;
case "4":
yearNumber = "4th";
break;
}
var student = "";
student += "<div class=\"student card bg-light m-2\"style=\"max-width: 10rem; float: left;\">";
student += "<div class=\"card-body\">";
student += title + " " + name + ",";
student += "<br>";
student += yearNumber + " year";
student += "</div>";
student += "<input type=\"button\" value=\"Remove\"class=\"btn btn-danger remove\">";
student += "</div>";
return student;
}*/