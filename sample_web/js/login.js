

function login(){
	
	var success;
	
	//POST
	
	success = true;
	
	if(success == true){
		alert("Login Successful!  "+$('#loginusername').val());
		$.mobile.changePage("#list");
	}else{
		alert("Wrong Credentials!  "+$('#loginusername').val());
	}
	
	
	
}