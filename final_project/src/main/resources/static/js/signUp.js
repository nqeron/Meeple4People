/**
 * 
 */
function validateForm(){
	var username = document.forms["registerForm"]["username"].value;
	if(username == ""){
		alert("You must fill out the username!");
		return false;
	}
	
	var email = document.forms["registerForm"]["email"].value;
	if(email == ""){
		alert("You must fill out the email!");
		return false;
	}
	
	var pass = document.forms["registerForm"]["inputPassword"].value;
	if(pass == ""){
		alert("You must fill out the Password!");
		return false;
	}
	
	var confirmPass = document.forms["registerForm"]["confirmPass"].value;
	if(confirmPass !== pass){
		alert("Your passwords must match!");
		return false;
	}
	
	return true;
	
}