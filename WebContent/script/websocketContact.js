$(document).ready(function() {
	var _websocket = window._websocket;
	var JSONMessage = window._JSONMessage;
	
	function listeningEvents() {
		_websocket.on("messageNotification", function(data) {
			 json = new JSONMessage();
			json.parse(data);
			var contactWrapper = $("#"+json.srcLogin);
			
			contactWrapper.addClass('contactHasUnreadMessages');
			var unreadMessageWrapper = contactWrapper.find("#contactUnreadMessageWrapper");
			if(unreadMessageWrapper.html() != undefined)
				unreadMessageWrapper.css("display", "inline");
			
			var unreadMessageCountSpan = contactWrapper.find("#contactUnreadMessageCount");
			if(unreadMessageCountSpan.html() != undefined)
				unreadMessageCountSpan.html(parseInt(unreadMessageCountSpan.html())+1);
		});
		
		_websocket.on("contactApprovedNotification", function(login) {
			 
			
			$("#"+login).removeClass();
			$("#"+login).addClass('contactWrapper');
			
		});
		
		_websocket.on("updateContactStatus", function(login, message) {
			$("#"+login).children(".contactStatus").html(message);
		});
	}
	
	(function() {
		listeningEvents();
	})();
});
