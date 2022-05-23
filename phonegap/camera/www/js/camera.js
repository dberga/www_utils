
var pictureSource;
var destinationType;
var taken = 0;
document.addEventListener("deviceready",onDeviceReady,false);

    function onDeviceReady() {
        pictureSource=navigator.camera.PictureSourceType;
        destinationType=navigator.camera.DestinationType;
    }

    function onPhotoDataSuccess(imageData) {

	  taken++;
	  $('#imgfetes').append("<img style='width:60px;height:60px;' hspace='1' id='"+taken+"' src='data:image/jpeg;base64," + imageData+"' />");
	  
    }

    function onPhotoURISuccess(imageURI) {

	  $('#imgcarregada').html("<img style='width:100%;height:100%;' align='middle' src='"+imageURI+"' />");
    }
	
	function onFail(message) {
      alert('Failed because: ' + message + ' please take the photo and press on the save button to let show it on the app');
    }


    function capturePhoto() {
		navigator.camera.getPicture(
			onPhotoDataSuccess, 
			onFail, 
			{ 
				quality: 50, 
				allowEdit: true,
				destinationType: destinationType.DATA_URL
			}
		);
    }



    function getPhoto(source) {
		navigator.camera.getPicture(
			onPhotoURISuccess, 
			onFail, 
			{
				quality: 50,
				destinationType: destinationType.FILE_URI,
				sourceType: source 
			}
		);
	}

    
	