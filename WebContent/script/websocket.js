$(document).ready(function() {
	
	var _wsUri = "ws://" + window.location.host + "/cpe-projet/WebsocketServlet";
	var _websocket = null;
	var _incomingMessage = null;
	var _outgoingMessage = null;
	
	function JSONMessage() {
		this.date = "";
		this.content = "";
		this.newMsgId = "null";
		this.sentMsgId = "null";
		this.deliveredMsgId = "null";
		
		this.getJSON = function () {
			return {
				"date": this.date,
				"content": this.content,
				"newMsgId": this.newMsgId,
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
			this.newMsgId = (obj["newMsgId"]!=undefined)?obj["newMsgId"]:"";
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
		
		//Si on recoit un nouveau message
		if(json.newMsgId != "null")
		{
			console.log("Has newMsgId: " + json.newMsgId);
			var incomingMsg = _incomingMessage.clone();		
			//Le destinataire ecrit le message dans sa page
			writeNewMessage(incomingMsg, json);
			//Il renvoit un accuse de reception
			doSendDelivered(json.newMsgId);
		}
		//Si on recoit un id seul du message envoye
		if(json.sentMsgId != "null")
		{
			console.log("Has sentMsgId: " + json.sentMsgId);
			//L'emetteur du message complete les infos donnees par le serveur (msgId)
			writeUpdateMessageId(json.sentMsgId);
		}
		//Si on recoit un accuse de reception
		if(json.deliveredMsgId != "null")
		{
			console.log("Has deliveredMsgId: " + json.deliveredMsgId);
			//L'emetteur du message change le statut de son message comme delivre
			writeUpdateStatus(json.deliveredMsgId);
		}
		//
		
	}
	
	//Renvoi d'un accuse de reception
	function doSendDelivered(msgId)
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
		writeNewMessage(outgoingMsg, json);
		
		var message = json.stringify();
		_websocket.send(message);
		
	}

	
	//Nouvelle version de la fonction write d'Adrien
	function writeNewMessage(element, json) {
		console.log("WriteNewMessage ");
		if(document.getElementById("messageForm") != null)
		{
			element.find(".messageContent").html(json.content);
			element.find(".messageDateTime").html(json.date);
			$('.messagesWrapper').append(element);
			document.getElementById("msgnull").id = "msg"+json.sentMsgId;
			document.getElementById("msgContentnull").id = "msgContent"+json.sentMsgId;
			document.getElementById("msgDateTimenull").id = "msgDateTime"+json.sentMsgId;
		}
	}
	
	function writeUpdateMessageId(msgId) {
		if(document.getElementById("messageForm") != null)
		{
			console.log("WriteUpdateMessageId msg"+msgId);
			document.getElementById("msgnull").id = "msg"+msgId;
			document.getElementById("msgContentnull").id = "msgContent"+msgId;
			document.getElementById("msgDateTimenull").id = "msgDateTime"+msgId;
			document.getElementById("msgStatusnull").id = "msgStatus"+msgId;
		}
	}
	
	function writeUpdateStatus(msgId) {
		if(document.getElementById("messageForm") != null)
		{
			console.log("WriteUpdateStatus msg"+msgId);
			document.getElementById("msgStatus"+msgId).innerHTML = 'V';
		}
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