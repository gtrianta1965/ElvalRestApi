<html>
<head>
  <script src="http://cdnjs.cloudflare.com/ajax/libs/json3/3.3.2/json3.min.js"></script>
</head>
<body>
<h2>Check Active Notifications</h2>
	<script>
	  function checkAlert() {
		  var nextRefresh = 60;
		  var xhr;
                  
                  if (window.XMLHttpRequest) {
                     // code for IE7+, Firefox, Chrome, Opera, Safari
                     xhr = new XMLHttpRequest();
                  }
                  else {
                       // code for IE6, IE5
                       xhr = new ActiveXObject("Microsoft.XMLHTTP");
                  }
		  xhr.open('GET', 'rest/notification/active',false);
                  xhr.onreadystatechange = function() {
                      if(xhr.readyState === 4) {
                        if (xhr.status === 200) {
                           console.log('Success');
                           var responseJSON = JSON.parse(xhr.responseText);
			   console.log(responseJSON);
                           if (responseJSON.message != null) {
			       alert(responseJSON.message + " (" + responseJSON.period + ")");
                           }
			   nextRefresh = responseJSON.period;
			   console.log("Success:set next refresh to: " + nextRefresh * 1000);
			   setTimeout(checkAlert, nextRefresh * 1000);                        
                        } else {
                           console.log('Request failed.  Returned status of ' + xhr.status);
                           console.log("Failed:set next refresh to: " + nextRefresh * 1000);
                           //alert('Request failed ' + xhr.status + " next refresh=" + nextRefresh)
                           setTimeout(checkAlert, nextRefresh * 1000);
                        }
                      } 
                  }
                  
                  //The following seems not to work with IE, while onreadystatechange works on 3
                  //major browsers (IE,Firefox and Chrome). I duplicate the functionality to 
                  //onreadystatechange
		  xhr.onload__ = function () {
			  if (xhr.status === 200) {
                                  console.log('Respone=' + xhr.responseText );
				  var responseJSON = JSON.parse(xhr.responseText);
				  console.log(responseJSON);
                                  if (responseJSON.message != null) {
				     alert(responseJSON.message + " (" + responseJSON.period + ")");
                                  }
				  nextRefresh = responseJSON.period;
				  console.log("Success:set next refresh to: " + nextRefresh * 1000);
				  setTimeout(checkAlert, nextRefresh * 1000);
			  }
			  else {
				  console.log('Request failed.  Returned status of ' + xhr.status);
				  console.log("Failed:set next refresh to: " + nextRefresh * 1000);
				  setTimeout(checkAlert, nextRefresh * 1000);
			  }
		  };
                  xhr.onerror = function() {
                     alert('Something went wrong with AJAX call.');
                  }
		  xhr.send();
		  return nextRefresh;
	  }
          //This is for IE - Doesn't have console function. 
          if (typeof console == "undefined") {
             this.console = { log: function (msg) {  } };
          }
	  checkAlert();
	</script>
</body>
</html>
