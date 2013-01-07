$(document).ready(function() {
	
	var _wsUri = "ws://" + window.location.host + "/cpe-projet/WebsocketServlet";
	var _websocket = null;
	
	function JSONMessage() {
		this.id = "";
		this.tmp = "";
		this.date = "";
		this.content = "";
		this.sender = "";
		this.status = "";
		
		this.getJSON = function () {
			return {
				"id": this.id,
				"tmp": this.tmp,
				"date": this.date,
				"content": this.content,
				"sender": this.sender,
				"status": this.status,
			};
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			var obj = JSON.parse(jsonString);
			
			this.id = (obj["id"]!=undefined)?obj["id"]:"";
			this.tmp = (obj["tmp"]!=undefined)?obj["tmp"]:"";
			this.date = (obj["date"]!=undefined)?obj["date"]:"";
			this.content = (obj["content"]!=undefined)?obj["content"]:"";
			this.sender = (obj["sender"]!=undefined)?obj["sender"]:"";
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
			
			var params =[];
			params.push(message.event);
			params = params.concat(message.data);
			params.push(false);

			_websocket.emit.apply(_websocket, params);
		} catch(err) {
			console.dir(err);
			console.dir(evt.data);
		}
	}
		
	function onError(evt) {
		console.log("ERROR: " + evt.data);
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
			
			var sendToServer = true;
			if (arguments[arguments.length-1] === false) {
				sendToServer = Array.prototype.pop.call(arguments);
			}
			
			var jsonEvent = {
					'event': eventName,
					'data': Array.prototype.slice.call(arguments, 1)
			};
			
			if (sendToServer) {
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