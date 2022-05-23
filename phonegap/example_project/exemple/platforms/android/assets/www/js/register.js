
		/* si success -> data=
				{
				status:
					{
					succes:
						true,
					data:
						{
						registered: true
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

			  $("#botoregistrar").click(function(){
				
				
				var markers =  {
								"grup": $( "#rgrup" ).val(),
								"motdepas":  $( "#rmotdepas" ).val(),
								"nias": [
												$( "#rintegrants1" ).val(),
												$( "#rintegrants2" ).val()
										]
								
				
								};
								
							  
				$.ajax({		
					type:	"POST",		
					url:	"http://upf.angeldiaz.es/aism2013_14/ws/autenticacio/0/registre",		
					data:	markers,		
					contentType:	"application/x-www-form-urlencoded; charset=UTF-8",		
					dataType:	"json",							
					success:	function(data){	//connected properly to the wbs
						//alert("success"); //
						//alert(JSON.stringify(data, null, 4)); //
						//alert("Input data: \n grup="+markers.grup+"\n motdepas="+markers.motdepas+"\n nias[0]="+markers.nias[0]+"\n nias[1]="+markers.nias[1]);
						if(data.status.succes){ //wbs response ok	
							alert("Registrat correctament!");
							$('#afterregister').toggle();
							$('#afterregister').html("<p>grup="+markers.grup+"</p><p> motdepas="+markers.motdepas+"</p><p> nias[0]="+markers.nias[0]+"</p><p>nias[1]="+markers.nias[1]+"</p>");
							$('#beforeregister').hide();
							//document.getElementById(boxid).style.visibility="visible";
						}else{ //wbs response not ok
							alert("Registre malament! Revisa els camps");
							//alert("WBS Error="+data.status.error.toString());
						}
						
					},		

					failure:	function(errMsg)	{	 //could not connect to the wbs
						//alert("failure"); //
						alert("WBS Failure="+errMsg);		
					}					
				});
			  });  
			});
