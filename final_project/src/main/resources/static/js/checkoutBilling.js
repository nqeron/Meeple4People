/**
 * 
 */

function validateForm(){
	var fullname = document.forms["billingForm"]["fullname"].value;
	
	if(fullname == ""){
		alert("You must fill out the name!");
		return false;
	}
	
	var address1 =  document.forms["billingForm"]["address1"].value;
	if(address1 == ""){
		alert("You must fill out the address line 1!");
		return false;
	}
	
	var city = document.forms["billingForm"]["city"].value;
	var state = document.forms["billingForm"]["state"].value;
	var country = document.forms["billingForm"]["country"].value;
	
	if(city == "" || state == "" || country == ""){
		alert("Please set the zipcode to a value");
		return false;
	}
	
	var creditCardNum = document.forms["billingForm"]["creditCardNum"].value;
	if(creditCardNum == ""){
		alert("Please enter a credit card number");
		return false;
	}
	
	creditCardValid = valid_credit_card(creditCardNum);
	if(!creditCardValid){
		alert("The credit card number is invalid!");
		return false;
	}
	
	var cvv = document.forms["billingForm"]["cvv"].value;
	if(cvv == ""){
		alert("Please fill in the CVV");
		return false;
	}
	
	var expireMM = document.forms["billingForm"]["expireMM"].value;
	var expireYYYY = document.forms["billingForm"]["expireYYYY"].value;
	
	if(expireMM == "" || expireYYYY == ""){
		alert("Please fill in the expiration date");
		return false;
	}
	
	var expCheck = checkExiprationDate(expireYYYY,expireMM);
	if(!expCheck){
		alert("Please enter a current expiration date");
		return false;
	}
	
	
	return true;
	
}

function checkExiprationDate(expireYYYY, expireMM){
	testDay = new Date(expireYYYY, expireMM - 1);
	today = new Date();
	return testDay >= today;
}

var luhnChk = (function (arr) {
    return function (ccNum) {
        var 
            len = ccNum.length,
            bit = 1,
            sum = 0,
            val;

        while (len) {
            val = parseInt(ccNum.charAt(--len), 10);
            sum += (bit ^= 1) ? arr[val] : val;
        }

        return sum && sum % 10 === 0;
    };
}([0, 2, 4, 6, 8, 1, 3, 5, 7, 9]));

function valid_credit_card(value) {
	  //Borrowed from https://gist.github.com/DiegoSalazar/4075533
		if (/[^0-9-\s]+/.test(value)) return false;

		
		var nCheck = 0, nDigit = 0, bEven = false;
		value = value.replace(/\D/g, "");

		for (var n = value.length - 1; n >= 0; n--) {
			var cDigit = value.charAt(n),
				  nDigit = parseInt(cDigit, 10);

			if (bEven) {
				if ((nDigit *= 2) > 9) nDigit -= 9;
			}

			nCheck += nDigit;
			bEven = !bEven;
		}

		return (nCheck % 10) == 0;
}