$(document).ready(function() {
	
	var _wsUri = "ws://" + window.location.host + "/cpe-projet/WebsocketServlet";
	var _websocket = null;
	var _incomingMessage = null;
	var _outgoingMessage = null;
	
	function JSONMessage() {
		this.date = "";
		this.message = "";
		
		this.getJSON = function () {
			return {
				"date": this.date,
				"message": this.message
			};
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			console.log(jsonString);
			
			var obj = JSON.parse(jsonString);
			
			this.date = (obj["date"]!=undefined)?obj["date"]:"";
			this.message = (obj["message"]!=undefined)?obj["message"]:"";
		};
	}
	
	function onOpen(evt) {
//		alert("CONNECTED");
	}
	function onClose(evt) {
//		alert("DISCONNECTED");
	}
	function onMessage(evt) {
		var json = new JSONMessage();
		json.parse(evt.data);
		
//		confirm("NEW MESSAGE: " + json.message + " @ " + json.date);
		
		var incomingMsg = _incomingMessage.clone();
		write(incomingMsg, json);
	}
	function onError(evt) {
		console.log("ERROR: " + evt.data);
	}
	
	function getDate() {
		var d = new Date();
		
		var date = d.getDate();
		var month = d.getMonth()+1;
		var year = d.getFullYear();
		var hour = d.getHours();
		var min = d.getMinutes();
		
		date = (date>10?'':'0') + date;
		month = (month>10?'':'0') + month;
		year = '' + year;
		hour = (hour>10?'':'0') + hour;
		min = (min>10?'':'0') + min;
		
		return date + "/" + month + "/" + year + " @ " + hour + ":" + min;
	}
	
	function doSend() {
		
		var json = new JSONMessage();
		
		json.date = getDate();
		json.message = $('#content').val();
		$('#content').val("");
		
		var outgoingMsg = _outgoingMessage.clone();
		outgoingMsg.find(".messageStatus").html('');
		write(outgoingMsg, json);
		
		var message = json.stringify();
		_websocket.send(message);
		
	}

	function write(element, json) {
		element.find(".messageContent").html(json.message);
		element.find(".messageDateTime").html(json.date);
		$('.messagesWrapper').append(element);
	}
	
	function init() {
		
		$.get('content/chat/outgoingMessage.jsp', function(data) {
			_outgoingMessage = $(data);
			element.find(".messageStatus").html('');
		});
		
		$.get('content/chat/incomingMessage.jsp', function(data) {
			_incomingMessage = $(data);
		});
		
		var WS = WebSocket || MozWebSocket;
		websocket = new WS(_wsUri);
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