<html>
<body>
<h2>Hello World!</h2>
	<script>
	  function checkAlert() {
		  var nextRefresh = 60;
		  var xhr = new XMLHttpRequest();
		  xhr.open('GET', 'rest/alerts/active');
		  xhr.onload = function () {
			  if (xhr.status === 200) {
				  var responseJSON = JSON.parse(xhr.responseText);
				  console.log(responseJSON);
				  alert('Response= ' + responseJSON.title);
				  nextRefresh = responseJSON.period;
				  console.log("Sucess:set next refresh to: " + nextRefresh * 1000);
				  setTimeout(checkAlert, nextRefresh * 1000);
			  }
			  else {
				  console.log('Request failed.  Returned status of ' + xhr.status);
				  console.log("Failed:set next refresh to: " + nextRefresh * 1000);
				  setTimeout(checkAlert, nextRefresh * 1000);
			  }
		  };
		  xhr.send();
		  return nextRefresh;
	  }
	  checkAlert();
	</script>
</body>
</html>
