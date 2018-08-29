/**
 * 
 */

function validateForm(){
	var email = document.forms["profileForm"]["email"].value;
	if(email == ""){
		alert("You must fill out the email!");
		return false;
	}
	
	var emailValid = validateEmail(email);
	if(!emailValid){
		alert("That's not a valid email address");
		return false
	}
	
	var phone = document.forms["profileForm"]["phone"].value;
	if(phone != ""){
		var phoneValid = validatePhoneNum(phone);
		if(!phoneValid){
			alert("That's not a valid phone number!");
			return false;
		}
	}
	
	return true;
	
}

//borrowed from stack overflow answer
function validateEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function validatePhoneNum(phone){
	return phone.match(/\d/g).length===10;
}