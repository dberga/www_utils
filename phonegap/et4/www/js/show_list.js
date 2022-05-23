
	/*
	{
		status:
		{
			succes: true,
			data{
				[
					{	
						id:1
						name: Game of thrones
						nb_seasons:3
						image_url: url
					},
					{
						id:1
						name: Game of thrones
						nb_seasons:3
						image_url: url
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
				
			function refrescarllista(){
				localStorage.clear();
				$.mobile.changePage($("#pageone"));
			};
			
			$(document).ready(function(){
			
			
			
			
			
			$("#bpageshowlist").click(function(){
			
				if(localStorage.getItem("list") === null){
					consultarllista();
				}else{
					$('#divlist').html(localStorage.getItem("list"));
				
				}
		    });
			
			
			
			
			
			
			
			function consultarllista(){
				var markers;

					$.ajax({		
						type:	"GET",		
						url:	"http://upf.angeldiaz.es/aism2013_14/ws/consulta/0/show_list",		
						data:	markers,		
						contentType:	"application/x-www-form-urlencoded; charset=UTF-8",		
						dataType:	"json",							
						success:	function(data){	//connected properly to the wbs
							//alert("success"); //
							//alert(JSON.stringify(data, null, 4)); //
							if(data.status.success){ //wbs response ok	
								alert("Llista consultada");
								
								var contingut ="";
								for(var i=0;i < data.status.data.length; i++){
									
									contingut += "<div >";
									contingut += "<p>"+data.status.data[i].name+"</p>";
									contingut += "<p> Seasons: "+data.status.data[i].nb_seasons+"</p>";
									contingut += "<img src='"+data.status.data[i].image_url+"' "+"alt='some_text' id='"+data.status.data[i].id+"' onclick=clickdet(this.id); ></img>";
									contingut += "</div>";
									
									
								}
								$('#divlist').html(contingut);
								localStorage.setItem("list",contingut);
							}else{ //wbs response not ok
								alert("Consulta dolenta, revisa el servidor");
								//alert("WBS Error="+data.status.error.toString());
							}
							
						},		

						failure:	function(errMsg)	{	 //could not connect to the wbs
							//alert("failure"); //
							alert("WBS Failure="+errMsg);		
						}					
					});
			
			}
			
			
			
			
			    
			});
