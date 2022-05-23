

function register(){
	
	var success;
	
	//POST
	
	success = false;
	
	if(success == true){
		alert("Duplicated Username!  "+$('#registerusername').val());
		
	}else{
		alert("Register Successful!  "+$('#registerusername').val());
		$.mobile.changePage("#home");
	}
	
	
	
}