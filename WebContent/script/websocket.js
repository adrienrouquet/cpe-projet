$(document).ready(function() {
	
	console.log(window.location.host);
	var _wsUri = "ws://"+window.location.host+"/cpe-projet/WebsocketServlet";
	var _output = $("#output");
	var _websocket = null;
	
	function JSONMessage() {
		this.sender = "";
		this.receiver = "";
		this.message = "";
		
		this.getJSON = function () {
			return {
				"sender": this.sender,
				"receiver": this.receiver,
				"message": this.message
			};
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			console.log(jsonString);
			
			var obj = JSON.parse(jsonString);
			
			this.sender = (obj["sender"]!=undefined)?obj["sender"]:"";
			this.receiver = (obj["receiver"]!=undefined)?obj["receiver"]:"";
			this.message = (obj["message"]!=undefined)?obj["message"]:"";
		};
	}
	
	function onOpen(evt) {
		writeToScreen("CONNECTED");
	}
	function onClose(evt) {
		writeToScreen("DISCONNECTED");
	}
	function onMessage(evt) {
		var json = new JSONMessage();
		json.parse(evt.data);
		
		writeToScreen('<span style="color: blue;">' + json.sender + ": " + json.message +'</span>');
	}
	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}
	
	function doSend() {
		
		var json = new JSONMessage();
		json.receiver = $('#rcv').val();
		json.message = $('#msg').val();
		
		$("#msg").val("");
		$("#rcv").val("GUEST_");

		var message = json.stringify();
		
		writeToScreen("SENT to "+ json.receiver + ": " + json.message);
		_websocket.send(message);
	}
	function writeToScreen(message) {
		_output.append("<p>" + message + "</p>");
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