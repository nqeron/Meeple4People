/**
 * 
 */

function validateForm(){
	var firstname = document.forms["shippingForm"]["firstname"].value;
	
	if(firstname == ""){
		alert("You must fill out the firstname!");
		return false;
	}
	
	var lastname = document.forms["shippingForm"]["lastname"].value;
	if(lastname == ""){
		alert("You must fill out the lastname!");
		return false;
	}
	
	var address1 =  document.forms["shippingForm"]["address1"].value;
	if(address1 == ""){
		alert("You must fill out the address line 1!");
		return false;
	}
	
	var city = document.forms["shippingForm"]["city"].value;
	var state = document.forms["shippingForm"]["state"].value;
	var country = document.forms["shippingForm"]["country"].value;
	
	if(city == "" || state == "" || country == ""){
		alert("Please set the zipcode to a value");
		return false;
	}
	
	return true;
	
}