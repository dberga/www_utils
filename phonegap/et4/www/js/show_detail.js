
				
				/*
				{ //input= show_id
					status:
					{
						succes: true,
						data{
							
									
									id:1
									name: 
									description
									main_actors[]
									nb_seasons
									imdb_url
									image_url
									
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
			function clickdet(identif){
				//alert("mirant detalls");
				
				
				$(document).ready(function(){
				
				$.mobile.changePage($("#pagedetails"));
				
				if(localStorage.getItem("det_"+identif) === null){
					var markers = {	
									"show_id": identif
								  };
					
					$.ajax({		
						type:	"GET",		
						url:	"http://upf.angeldiaz.es/aism2013_14/ws/consulta/0/show_detail",		
						data: 	markers,		
						contentType:	"application/x-www-form-urlencoded; charset=UTF-8",		
						dataType:	"json",		
						success:	function(data){		 //connected properly to the wbs	
							//alert("success"); //
							//alert(JSON.stringify(data, null, 4)); //
							if(data.status.success){ //wbs response ok
								alert("Detail consultat ");	
								
								
								
								var cont ="";
								cont += "<div>";
								cont += "<p>"+data.status.data.name+"</p>";
								cont += "<img src='"+data.status.data.image_url+"' "+"alt='some_text' ></img>";
								cont += "<p> Description: "+data.status.data.description+"</p>";
								
								cont += "<p> Main actors: ";
								for(var a=0; a< data.status.data.main_actors.length; a++){
									cont += "<p>		"+data.status.data.main_actors[a]+"</p>";
								
								}
								
								cont += "</p>";
								cont += "<a href='"+data.status.data.imdb_url+"'>IMDB LINK</a>";
								cont += "<p></p>";
								cont += "<p></p>";
								
								cont += "<button type='button' id='"+identif+"' onclick='clicksea(this.id);' > Seasons: "+data.status.data.nb_seasons+" </button>";
								cont += "</div>";
								
								
								
								$('#divdetails').html(cont);
								localStorage.setItem("det_"+identif,cont);
								
							}else{ //wbs response not ok
								alert(" incorrecte");
								//alert(data.status.error.toString()); //
							}
							
						},				
						failure:	function(errMsg)	{		//could not connect to the wbs
							//alert("failure"); //		
							alert("WBS Failure="+errMsg);
						}	
					});
					
				}else{
					$('#divdetails').html(localStorage.getItem("det_"+identif));
				}
				
					
				
				
				});

			};
