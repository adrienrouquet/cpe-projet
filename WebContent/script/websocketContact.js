$(document).ready(function() {
	var _websocket = window._websocket;
	var JSONMessage = window._JSONMessage;
	
	function listeningEvents() {
		_websocket.on("messageNotification", function(data) {
			 json = new JSONMessage();
			json.parse(data);
			alert(json.sender + " vous a envoyé un message !");
		});
	}
	
	(function() {
		listeningEvents();
	})();
});
