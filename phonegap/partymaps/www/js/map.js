


	var watchID = null;
	var latitut;
	var longitut;
	var trobat;
	/*
	document.addEventListener("deviceready", onDeviceReady, false);
	
    function onDeviceReady() {

        var options = { enableHighAccuracy: true };
        watchID = navigator.geolocation.watchPosition(onSuccess, onError, options);
    }
	
	
	function getpos(){
		 latitut = "0";
		 longitut = "0";
		var options = { enableHighAccuracy: true };
		watchID = navigator.geolocation.watchPosition(onSuccess, onError, options);
	}
    
	function onSuccess(position) {
		
        latitut = position.coords.latitude.toString();
		longitut = position.coords.longitude.toString();
		trobat = true;
    }

    function onError(error) {
        alert('code: '    + error.code    + '\n' +
              'message: ' + error.message + '\n');
    }
	
	function clearWatch() {
        if (watchID != null) {
            navigator.geolocation.clearWatch(watchID);
            watchID = null;
        }
    }
	
	
	
	function printarposicio(){
		
		while(trobat==false){
		
			
		}
		alert(latitut+","+longitut);
		$("#cos").html(
			
			"<p>"+latitut+","+longitut+"</p>"
		
		
		);
	}
	
	
	function carregant(){
	
		$("#cos").html(
			
			"<img src='js/jquerymobile/images/ajax-loader.gif' id='carrega'/>"
		);
	
	}
	
	function buscarME(){
		trobat = false;

		carregant();
		
		getpos();
		printarposicio();
	}
	*/
	
	document.addEventListener('deviceready', loadMap, false);
	var latit=43.069452;
	var longit=-89.411373;
		var map;
		var mapOptions;
	function loadMap() {
		 mapOptions = {
			center: new google.maps.LatLng(latit, longit),
			zoom: 16,
			mapTypeId: google.maps.MapTypeId.ROADMAP
		};
		map = new google.maps.Map(document.getElementById("cos"), mapOptions);
	}
	
	
	
	
	var marker = new google.maps.Marker({
		position: new google.maps.LatLng(latit, longit),
		map: map,
		title: "This is a marker!",
		animation: google.maps.Animation.DROP
	});
	
	
	
	function dispMap(){
	loadMap();
	$("#cos1").show();
	$("#cos2").hide();
	$("#cos3").hide();
}

function changepos(latt, longg){
	latit = latt;
	longit = longg;
	
	$.mobile.changePage("#pageone",{ transition: "slidefade", changeHash: false });
	loadMap();

}
	
	