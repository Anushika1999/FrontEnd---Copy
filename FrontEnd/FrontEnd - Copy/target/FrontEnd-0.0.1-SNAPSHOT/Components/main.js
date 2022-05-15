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
var status = validateItemForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}
// If valid-------------------------
$("#formItem").submit();
});
//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
$("#itemCode").val($(this).closest("tr").find('td:eq(0)').text());
$("#itemName").val($(this).closest("tr").find('td:eq(1)').text());
$("#itemPrice").val($(this).closest("tr").find('td:eq(2)').text());
$("#itemDesc").val($(this).closest("tr").find('td:eq(3)').text());
});
//CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#itemCode").val().trim() == "")
{
return "Insert Item Code.";
}
// NAME
if ($("#itemName").val().trim() == "")
{
return "Insert Item Name.";
}
// PRICE-------------------------------
if ($("#itemPrice").val().trim() == "")
{
return "Insert Item Price.";
}
// is numerical value
var tmpPrice = $("#itemPrice").val().trim();
if (!$.isNumeric(tmpPrice))
{
return "Insert a numerical value for Item Price.";
}
// convert to decimal price
$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2));
// DESCRIPTION------------------------
if ($("#itemDesc").val().trim() == "")
{
return "Insert Item Description.";
}
return true;
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