$(document).ready(function()
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 





$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateCartForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
 
 
 
 
// If valid------------------------
var type = ($("#hidcartIdSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "CartAPI", 
 type : type, 
 data : $("#formCart").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCartSaveComplete(response.responseText, status); 
 } 
 }); 
});







function onCartSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCartGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
 $("#hidcartIdSave").val(""); 
 $("#formCart")[0].reset(); 
}
 
 
 
 //delete
 
 function onCartDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divCartGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}
 
 
 
 
 $(document).on("click", ".btnRemove", function(event)
{ 
 $.ajax( 
 { 
 url : "CartAPI", 
 type : "DELETE", 
 data : "cartId=" + $(this).data("cartid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCartDeleteComplete(response.responseText, status); 
 } 
 }); 
});
 
 
 
 


 

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidcartIdSave").val($(this).closest("tr").find('#hidcartIdUpdate').val()); 
 $("#itemId").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#itemName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#itemPrice").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#quantity").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#description").val($(this).closest("tr").find('td:eq(4)').text()); 
});





// CLIENT-MODEL================================================================
function validateCartForm() 
{ 
// ITEMID
if ($("#itemId").val().trim() == "") 
 { 
 return "Insert Item Id."; 
 } 
 // is numerical value
var tmpItem = $("#itemId").val().trim(); 
if (!$.isNumeric(tmpItem)) 
 { 
 return "Insert a numerical value for Item Id."; 
 } 
// convert to Integer quantity
 $("#itemId").val(parseInt(tmpItem)); 
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


// QUANTITY-------------------------------
if ($("#quantity").val().trim() == "") 
 { 
 return "Insert Item Quantity."; 
 } 
// is numerical value
var tmpQuantity = $("#quantity").val().trim(); 
if (!$.isNumeric(tmpQuantity)) 
 { 
 return "Insert a numerical value for Item Quantity."; 
 } 
// convert to Integer quantity
 $("#quantity").val(parseInt(tmpQuantity)); 

// DESCRIPTION------------------------
if ($("#description").val().trim() == "") 
 { 
 return "Insert Item Description."; 
 } 
return true; 
}