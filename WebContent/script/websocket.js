$(document).ready(function() {
	
	var _wsUri = "ws://" + window.location.host + "/cpe-projet/WebsocketServlet";
	var _websocket = null;
	
	function JSONMessage() {
		this.id = "";
		this.sentDate = 0;
		this.content = "";
		this.srcLogin = "";
		this.srcName = "";
		this.status = "";
		
		this.getJSON = function () {
			var json = {};
			
			if (this.id != "")
				json["id"] = this.id;
			if (this.sentDate != "")
				json["sentDate"] = this.sentDate;
			if (this.content != "")
				json["content"] = this.content;
			if (this.srcLogin != "")
				json["srcLogin"] = this.srcLogin;
			if (this.srcName != "")
				json["srcName"] = this.srcName;
			if (this.status != "")
				json["status"] = this.status;
			
			return json;
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			var obj = JSON.parse(jsonString);
			
			this.id = (obj["id"]!=undefined)?obj["id"]:"";
			this.sentDate = (obj["sentDate"]!=undefined)?obj["sentDate"]:"";
			this.content = (obj["content"]!=undefined)?obj["content"]:"";
			this.srcLogin = (obj["srcLogin"]!=undefined)?obj["srcLogin"]:"";
			this.srcName = (obj["srcName"]!=undefined)?obj["srcName"]:"";
			this.status = (obj["status"]!=undefined)?obj["status"]:"";
		};
	}
	
	function onOpen(evt) {
//		alert("CONNECTED");
	}
	function onClose(evt) {
//		alert("DISCONNECTED");
	}
	function onMessage(evt) {
		var message;
		// message doit etre un JSON du type {'event': ..., 'data': ...}
		try {
			message = JSON.parse(evt.data);
			console.log("EVENT FROM SERVER: " + message.event);
			var json = new JSONMessage();
			json.parse(evt.data);
			
			var params = [];
			params.push(message.event);
			params = params.concat(message.data);
			params.push("ServerEvent");

			_websocket.emit.apply(_websocket, params);
		} catch(err) {
			console.dir(err);
			console.dir(evt.data);
		}
	}
		
	function onError(evt) {
		console.log("ERROR: ");
		console.dir(evt);
	}
	
	function init() {
		// websocket.on sert à ECOUTER les evenements
		// UTILISATION: websocket.on('event', function([arg0], [arg1], ..., [argN]) {})
		WebSocket.prototype.on = function(eventName, callback) {
			return $(this).bind(eventName, function(event) {				
				callback.apply(this, Array.prototype.slice.call(arguments, 1));
			});
		};
		// websocket.emit sert à EMETTRE des events localement ET sur le server
		// UTILISATION: websocket.emit(event, [arg0], [arg1], ..., [argN])
		WebSocket.prototype.emit = function(eventName) {
			console.log("EVENT: " + eventName);
			console.dir(arguments);
			var serverEvent = false;
			if (arguments[arguments.length-1] === "ServerEvent") {
				Array.prototype.pop.call(arguments);
				serverEvent = true;
			}
			
			var jsonEvent = {
					'event': eventName,
					'data': Array.prototype.slice.call(arguments, 1)
			};
			
			if (!serverEvent) {
				console.log("SEND EVENT TO SERVER: " + eventName);
				this.send(JSON.stringify(jsonEvent));
			}
			
			return $(this).trigger(jsonEvent.event, jsonEvent.data);
		};
	}
	
	function initGlobal() {
		window._websocket = _websocket;
		window._JSONMessage = JSONMessage;
	}
	
	function initWebsocket() {
		websocket = new WebSocket(_wsUri);
		websocket.onopen = function(evt) { onOpen(evt); };
		websocket.onclose = function(evt) { onClose(evt); };
		websocket.onmessage = function(evt) { onMessage(evt); };
		websocket.onerror = function(evt) { onError(evt); };
		_websocket = websocket;
	}
	
	(function () {
		init();
		initWebsocket();
		initGlobal();
	})();
});