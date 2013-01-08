$(document).ready(function() {
	var _websocket = window._websocket;
	var JSONMessage = window._JSONMessage;
	
	var _incomingMessage = null;
	var _outgoingMessage = null;
	
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
		json.tmp = new Date().getTime();
		$('#content').val("");
		
		var outgoingMsg = _outgoingMessage.clone();
//		outgoingMsg.find(".messageStatus").html('X');
		writeNewMessage(outgoingMsg, json);
		
		var message = json.stringify();
		_websocket.emit("sendMessage", message);
	}
	
	function writeNewMessage(element, json) {
		if (json.content != "")
			element.find(".messageContent").html(json.content);
		
		if (json.date != "")
			element.find(".messageDateTime").html(json.date);
		
		if (json.id != "")
			element.attr("id", json.id);
		else {
			if (json.tmp != "")
				element.attr("id", json.tmp);
		}
		
		$('#messageForm').append(element);
	}
	
	function init() {
		$.get('content/chat/outgoingMessage.jsp', function(data) {
			_outgoingMessage = $(data);
			_outgoingMessage.find(".messageStatus").html('');
		});
		
		$.get('content/chat/incomingMessage.jsp', function(data) {
			_incomingMessage = $(data);
		});
	}
	
	function listeningEvents() {
		_websocket.on('newMessage', function(data) {
			var json = new JSONMessage();
			json.parse(data);
			writeNewMessage(_incomingMessage.clone(), json);
			_websocket.emit('updateMessageStatus', json.stringify());
		});
		
		_websocket.on('updateMessageStatus', function(data) {
			var json = new JSONMessage();
			json.parse(data);

			if ($("#" + json.id).html() == undefined) {
				var element = $("#" + json.tmp);
				element.attr("id", json.id);
			}
				
			$("#" + json.id).children(".messageStatus").html(json.status);
		});
		
		_websocket.on("messageNotification", function(data) {
			 json = new JSONMessage();
			json.parse(data);
			alert(json.sender + " vous a envoyé un message !");
		});
	}
	
	function initGlobal() {
		window.doSend = doSend;
	}
	
	(function() {
		init();
		listeningEvents();
		initGlobal();
	})();
})
