$(document).ready(function() {
	
	var _wsUri = "ws://localhost:8080/cpe-projet/Chat";
	var _output = document.getElementById("output");
	var _websocket = null;
	
	function onOpen(evt) {
		writeToScreen("CONNECTED");
	}
	function onClose(evt) {
		writeToScreen("DISCONNECTED");
	}
	function onMessage(evt) {
		writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
	}
	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}
	
	function doSend() {
		message = $('#msg').val();
		destination = $('#dst').val();
		$("#msg").val("");
		$("#dst").val("GUEST_");
		
		var JSONMessage = {
			"dest": destination,
			"message": message
		};
		message = JSON.stringify(JSONMessage);
		
		console.log(message);
		
		writeToScreen("SENT to "+ destination + ": " + message);
		_websocket.send(message);
	}
	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		_output.appendChild(pre);
	}

	function init() {
		var websocket = new WebSocket(_wsUri);
		websocket.onopen = function(evt) { onOpen(evt); };
		websocket.onclose = function(evt) { onClose(evt); };
		websocket.onmessage = function(evt) { onMessage(evt); };
		websocket.onerror = function(evt) { onError(evt); };
		
		_websocket = websocket;
	}
	
	function initGlobal() {
		window.doSend = doSend;
	}
	
	(function () {
		initGlobal();
		init();
	})();
	
});