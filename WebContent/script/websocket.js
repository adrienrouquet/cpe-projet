$(document).ready(function() {
	
	var _wsUri = "ws://" + window.location.host + "/cpe-projet/WebsocketServlet";
	var _websocket = null;
	var _incomingMessage = null;
	var _outgoingMessage = null;
	
	function JSONMessage() {
		this.date = "";
		this.content = "";
		this.sentMsgId = "null";
		this.deliveredMsgId = "null";
		
		this.getJSON = function () {
			return {
				"date": this.date,
				"content": this.content,
				"sentMsgId": this.sentMsgId,
				"deliveredMsgId": this.deliveredMsgId
				
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
			this.sentMsgId = (obj["sentMsgId"]!=undefined)?obj["sentMsgId"]:"";
			this.deliveredMsgId = (obj["deliveredMsgId"]!=undefined)?obj["deliveredMsgId"]:"";
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
		
		if(json.sentMsgId != "null")
		{
			console.log("Has sentMsgId: " + json.sentMsgId);
			var incomingMsg = _incomingMessage.clone();		
			write(incomingMsg, json);
			doSendStatus(json.sentMsgId);
		}
		if(json.deliveredMsgId != "null")
		{
			console.log("Has deliveredMsgId: " + json.deliveredMsgId);
			writeStatus(json.deliveredMsgId);
		}
		
	}
	
	function doSendStatus(msgId)
	{
		var json = new JSONMessage();
		
		json.deliveredMsgId = msgId;
		
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
		
		element.find(".messageContent").html(json.content);
		element.find(".messageDateTime").html(json.date);
		$('.messagesWrapper').append(element);
		document.getElementById("msgnull").id = "msg"+json.sentMsgId;
		document.getElementById("msgContentnull").id = "msgContent"+json.sentMsgId;
		document.getElementById("msgDateTimenull").id = "msgDateTime"+json.sentMsgId;
	}
	
	function writeStatus(deliveredMsgId) {
		console.log("Writestatus msg"+deliveredMsgId);
		
		document.getElementById("msgStatusnull").innerHTML = 'V';
		document.getElementById("msgnull").id = "msg"+deliveredMsgId;
		document.getElementById("msgContentnull").id = "msgContent"+deliveredMsgId;
		document.getElementById("msgDateTimenull").id = "msgDateTime"+deliveredMsgId;
		document.getElementById("msgStatusnull").id = "msgStatus"+deliveredMsgId;
				
	}
	
	function init() {
		
		$.get('content/chat/outgoingMessage.jsp', function(data) {
			_outgoingMessage = $(data);
			_outgoingMessage.find(".messageStatus").html('');
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