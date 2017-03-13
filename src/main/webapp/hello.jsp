<!doctype html>
<html>
<head>
	<title>Caching Server</title>
</head>
<body>
<h2>Hello World!</h2>
UI State is: 
<h3>Intro</h3>

<button id="clickable">Send POST</button>


<script type="text/javascript">
	var $btn = document.querySelector('#clickable');
	var $header3 = document.querySelector('h3');
	var updateUI = function(state) {
		$header3.textContent = JSON.stringify(state);
	}
	var updateUIState = function(state, url) {
		updateUI(state);
		// up-to-date browser state
  		history.replaceState(state, null, url);
	}
	var getRandomAlphabeticStringOfSize = function(n) {
		var str = '';
		for (var i = 0; i < n; i++) {
			str += (String.fromCharCode(Math.round(Math.random() * (25) + 	97)))
		}
		return str;
	}
	
	$btn.addEventListener('click', function(e) {
		var str = getRandomAlphabeticStringOfSize(6)
		var xhr = new XMLHttpRequest();
		
		// create new payload and URL for each button click
		var json =  {
			hello: str
		};
		var url = '/hello/'+str;
		
		e.preventDefault();
		
		// push current state to DOM
		history.pushState(json, null, location.href);
		
		xhr.open('POST', url, true)
		xhr.onreadystatechange = function() {
			if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) updateUIState(xhr.response, url)
		}
		xhr.setRequestHeader("Content-Type", "application/json")
		xhr.send(JSON.stringify(json))
	})

	window.addEventListener('popstate', function(e) {
		updateUI(state);
	})
</script>
<script type="text/javascript">
	updateUIState(<%= request.getAttribute("jsonData") %>);
</script>
</body>
</html>
