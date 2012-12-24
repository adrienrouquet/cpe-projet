$(document).ready(function() {
	
	var _wsUri = "ws://" + window.location.host + "/cpe-projet/WebsocketServlet";
	var _websocket = null;
	var _incomingMessage = null;
	var _outgoingMessage = null;
	
	function JSONMessage() {
		this.date = "";
		this.content = "";
		
		this.getJSON = function () {
			return {
				"date": this.date,
				"content": this.content
			};
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			console.log(jsonString);
			
			var obj = JSON.parse(jsonString);
			
			this.date = (obj["date"]!=undefined)?obj["date"]:"";
			this.content = (obj["content"]!=undefined)?obj["content"]:"";
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
		
		if(json.hasOwnProperty(sentMsgId))
		{
			var incomingMsg = _incomingMessage.clone();		
			write(incomingMsg, json);
			doSendStatus(json.sentMsgId);			
		}
		if(json.hasOwnProperty(receivedMsgId))
		{
			var incomingMsg = _incomingMessage.clone();		
			writeStatus(incomingMsg, json);
		}
		
	}
	
	function doSendStatus(msgId)
	{
		var json = new JSONMessage();
		
		json.receivedMsgId = msgId;
		
		var message = json.stringify();
		_websocket.send(message);
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
		
		return date + "/" + month + "/" + year + " at " + hour + ":" + min;
	}
	
	function doSend() {
		
		var json = new JSONMessage();
		
		json.date = getDate();
		json.content = $('#content').val();
		$('#content').val("");
		
		var outgoingMsg = _outgoingMessage.clone();
		outgoingMsg.find(".messageStatus").html('X');
		write(outgoingMsg, json);
		
		var message = json.stringify();
		_websocket.send(message);
		
	}

	function write(element, json) {
		element.find(".messageId").html(json.sentMsgId);
		element.find(".messageContent").html(json.content);
		element.find(".messageDateTime").html(json.date);
		$('.messagesWrapper').append(element);
	}
	
	function writeStatus(element, json) {
		document.getElementById("msg"+json.receivedMsgId).appendChild(find(".messageStatus")).html("V");
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