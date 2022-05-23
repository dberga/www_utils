
				
				/* si success -> data=
				{
				status:
					{
					succes:
						true,
					data:
						{
						darrera_autenticacio: 1391382495,
						identificador_grup: 21,
						integrants:
							[
							“uXXXXXX”,
							“uYYYYYY”,
							],
						}
					}
				}
				
				*/
				/* si failure -> data=
				{
				status:
					{
					“error”: 1,
					}
				}
				*/
			$(document).ready(function(){

			  $("#botologin").click(function(){
				//$("#formlogin").submit();
				var markers = {			
								"grup": $( "#agrup" ).val(),
								"motdepas":  $( "#amotdepas" ).val()
								
							  };
				
				$.ajax({		
					type:	"POST",		
					url:	"http://upf.angeldiaz.es/aism2013_14/ws/autenticacio/0/login",		
					data: 	markers,		
					contentType:	"application/x-www-form-urlencoded; charset=UTF-8",		
					dataType:	"json",		
					success:	function(data){		 //connected properly to the wbs	
						//alert("success"); //
						//alert(JSON.stringify(data, null, 4)); //
						//alert("Input data: \n grup="+markers.grup+"\n motdepas="+markers.motdepas);
						if(data.status.succes){ //wbs response ok
							alert("Login correcte \n ");	
						
							$('#afterlogin').toggle();
							$('#afterlogin').html("<p>darrera autenticacio="+data.status.data.darrera_autenticacio+"</p><p> identificador de grup="+data.status.data.identificador_grup+"</p><p> integrants[1]="+data.status.data.integrants[0]+"</p><p>integrants[2]="+data.status.data.integrants[1]+"</p>");
							$('#beforelogin').hide();
						}else{ //wbs response not ok
							alert("Login incorrecte");
							//alert(data.status.error.toString()); //
						}
						
					},				
					failure:	function(errMsg)	{		//could not connect to the wbs
						//alert("failure"); //		
						alert("WBS Failure="+errMsg);
					}	
				});
				
					
				
				
			  });  
			});
