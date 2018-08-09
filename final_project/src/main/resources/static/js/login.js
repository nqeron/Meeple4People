/**
 * 
 */

function validateForm(){
	var username = document.forms["loginForm"]["username"].value;
	if(username == ""){
		alert("You must fill out the username!");
		return false;
	}
	return true;
}