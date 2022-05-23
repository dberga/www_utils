
				
			/*
	{ //input = show_id
		status:
		{
			succes: true,
			data{
				[
					{	
						id:1
						name: de la season
						short_name
						nb_chapters:
						emitted:
					},
					{
						id:1
						name: de la season
						short_name
						nb_chapters:
						emitted:
					},
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
			function clicksea(identif){
				//alert("mirant detalls");
				
				
				$(document).ready(function(){
				
				$.mobile.changePage($("#pageseasons"));
				
				if(localStorage.getItem("sea_"+identif) === null){
					var markers = {	
									"show_id": identif
								  };
					
					$.ajax({		
						type:	"GET",		
						url:	"http://upf.angeldiaz.es/aism2013_14/ws/consulta/0/season_list",		
						data: 	markers,		
						contentType:	"application/x-www-form-urlencoded; charset=UTF-8",		
						dataType:	"json",		
						success:	function(data){		 //connected properly to the wbs	
							//alert("success"); //
							//alert(JSON.stringify(data, null, 4)); //
							if(data.status.success){ //wbs response ok
								alert("Season consultat ");	
								
								

								var conti ="";
								
								for(var i=0;i < data.status.data.length; i++){
									conti += "<div>";
									conti += "<p> Name:"+data.status.data[i].name+"</p>";
									conti += "<p> Short Name: "+data.status.data[i].short_name+"</p>";
									conti += "<p> Chapters: "+ data.status.data[i].nb_chapters+"</p>";
									conti += "<p> Emitted: "+ data.status.data[i].emitted+"</p>";
									
									
								}
								$('#divseasons').html(conti);
								localStorage.setItem("sea_"+identif,conti);
								
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
					$('#divdetails').html(localStorage.getItem("sea_"+identif));
				}
				
				
				});

			};
