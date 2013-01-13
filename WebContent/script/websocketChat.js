$(document).ready(function() {
	var _websocket = window._websocket;
	var JSONMessage = window._JSONMessage;
	
	var _incomingMessage = null;
	var _outgoingMessage = null;
	
	function getDate(timestamp) {
		var d = new Date(timestamp);
		
		var date = d.getDate();
		var month = d.getMonth()+1;
		var year = d.getFullYear();
		var hour = d.getHours();
		var min = d.getMinutes();
		
		date = (date>=10?'':'0') + date;
		month = (month>=10?'':'0') + month;
		year = '' + year;
		hour = (hour>=10?'':'0') + hour;
		min = (min>=10?'':'0') + min;
		
		return month + "/" + date + "/" + year + " at " + hour + ":" + min;
	}
	
	function doSend() {
		if (jQuery.trim($('#content').val()).length > 0) {
			var json = new JSONMessage();
			
			json.sentDate = new Date().getTime();
			json.content = $('#content').val();
			$('#content').val("");
			
			var outgoingMsg = _outgoingMessage.clone();
			writeNewMessage(outgoingMsg, json);
			
			_websocket.emit("sendMessage", json.stringify());
		}
	}
	
	function writeNewMessage(element, json) {
		if (json.content != "")
			element.find(".messageContent").html(json.content);
		
		if (json.sentDate != "")
			element.find(".messageDateTime").html(getDate(json.sentDate));
		
		if (json.id != "")
			element.attr("id", json.id);
		else {
			if (json.sentDate != 0)
				element.attr("id", json.sentDate);
		}
		
		if (json.status != "")
			element.find(".messageStatus").html(json.status);
		
		$('#messageForm').append(element);
	}
	
	function init() {
		$.get('content/chat/include/outgoingMessage.jsp', function(data) {
			_outgoingMessage = $(data);
			_outgoingMessage.find(".messageStatus").html('<div><div/>');
		});
		
		$.get('content/chat/include/incomingMessage.jsp', function(data) {
			_incomingMessage = $(data);
		});
		
		$("#content").keypress(function(e){
			if( e.keyCode == '\r'.charCodeAt() || e.keyCode == '\n'.charCodeAt()){
				e.preventDefault();
				doSend();
			}
		});
	}
	
	function listeningEvents() {
		_websocket.on('newMessage', function(data, param) {
			var json = new JSONMessage();
			json.parse(data);
			var sendUpdateStatus = true;
			
			var element = _incomingMessage;
			if (param == "outgoing") {
				element = _outgoingMessage;
				sendUpdateStatus = false;
			}
			
			writeNewMessage(element.clone(), json);
			if (sendUpdateStatus)
				_websocket.emit('updateMessageStatus', json.stringify());
		});
		
		_websocket.on('updateMessageStatus', function(data) {
			console.log("ON updateMessageStatus");
			var json = new JSONMessage();
			json.parse(data);

			if ($("#" + json.id).html() == undefined) {
				var element = $("#" + json.sentDate);
				element.attr("id", json.id);
			}
			
			$("#" + json.id).children(".messageStatus").html(json.status);
		});
		
		_websocket.on("messageNotification", function(data) {
			 json = new JSONMessage();
			json.parse(data);
			alert(json.srcName + " vous a envoyé un message !");
		});
		
		_websocket.on("updateContactStatus", function(dstLogin, message) {
			$("#"+dstLogin).children(".contactStatus").html(message);
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
