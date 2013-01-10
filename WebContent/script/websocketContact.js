$(document).ready(function() {
	var _websocket = window._websocket;
	var JSONMessage = window._JSONMessage;
	
	function listeningEvents() {
		_websocket.on("messageNotification", function(data) {
			 json = new JSONMessage();
			json.parse(data);
			
			$("#"+json.srcLogin).addClass('contactHasUnreadMessages');
			var unreadMessageWrapper = $("#contactUnreadMessageWrapper"+json.srcLogin);
			if(unreadMessageWrapper.html() != undefined)
				unreadMessageWrapper.css("display", "inline");
			
			var unreadMessageCountSpan = $("#contactUnreadMessageCount"+json.srcLogin);
			if(unreadMessageCountSpan.html() != undefined)
				unreadMessageCountSpan.html(parseInt(unreadMessageCountSpan.html())+1);
		});
		
		_websocket.on("updateContactStatus", function(login, message) {
			$("#"+login).children(".contactStatus").html(message);
		});
	}
	
	(function() {
		listeningEvents();
	})();
});
