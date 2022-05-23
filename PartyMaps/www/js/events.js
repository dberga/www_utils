
var eventnum = 8;

var eventnames = new Array();
var eventtimes= new Array();

var eventlatitude= new Array();
var eventlonguitude= new Array();

var eventphotos= new Array();


function carregarevents(){
	for(var i=0; i<eventnum; i++){
		eventnames[i] = "Event"+i.toString();
		eventtimes[i] = "22:00 to 02:00";
		eventlatitude[i] = Math.floor(Math.random() * (i*70 - 0 + 1)).toString();
		eventlonguitude[i] = Math.floor(Math.random() * (i*50 - 0 + 1)).toString();
		if(i%2 == 0) eventphotos[i] = "img/event1.jpg"; else eventphotos[i] = "img/event2.jpeg";
		
	}
}

var htmlevents = new Array();


function carregarhtmlevents(){

	for(var i=0; i<eventnum; i++)
	{
		htmlevents[i] = "<li> <div style='float:left;' ><img src='"+eventphotos[i]+"' width='120%'  /></div> <div style='float:left; margin-left:10%;'><h3>"+eventnames[i]+"</h3></div> <div style='float:left; margin-left:25%; font-style:italic;'><p>"+eventtimes[i]+"</p></div> <div style='float:right; '><button  data-role='button' data-icon='user' data-iconpos='notext'></button></div> <div style='float:right; '><button  data-role='button' data-icon='navigation' data-iconpos='notext'></button></div> </li>";
		
	
	}
}

							
						

function printarllistaevents(){
	$("#cos").html("");
	
	
	$("#cos").append("<ul data-role='listview' data-inset='true' data-theme='b'>");
	
	for(var i=0; i<eventnum; i++)
	{
		$("#cos").append(htmlevents[i]);
	}
	$("#cos").append("</ul>");
	


}

function eventList(){
	carregarevents();
	carregarhtmlevents();
	printarllistaevents();
}

function dispEvents(){
	$("#cos1").hide();
	$("#cos2").show();
	$("#cos3").hide();
}




