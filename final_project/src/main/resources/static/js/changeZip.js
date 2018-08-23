function changeZip(){
	var city = document.getElementById("city");
	var state = document.getElementById("state");
	var zipcodeElem = document.getElementById("zipcode");
	var country = document.getElementById("country");
	
	var zipData = {
			zipcode : $("#zipcode").val(), 
			city : null,
			state : null,
			country : null
	}
	
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/getZipcode",
		data : JSON.stringify(zipData),
		dataType : 'json',
		success : function(result){
			if(result.status == "Done"){
				$("#city").val(result.data.city);
				$("#state").val(result.data.state);
				$("#country").val(result.data.country);
			}	
		},
		error : function(e){
			alert("Error!")
			console.log("ERROR: ", e)
		}
	});
}