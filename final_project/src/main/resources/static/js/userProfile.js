/**
 * 
 */

function validateForm(){
	var email = document.forms["profileForm"]["email"].value;
	if(email == ""){
		alert("You must fill out the email!");
		return false;
	}
	
	return true;
	
}