$(document).ready(function() {
	var _websocket = window._websocket;
	var JSONMessage = window._JSONMessage;
	
	function listeningEvents() {
		_websocket.on("messageNotification", function(data) {
			 json = new JSONMessage();
			json.parse(data);
//			alert(json.sender + " vous a envoyé un message !");
			$("#"+json.src).addClass('contactHasUnreadMessages');
			var unreadMessageWrapper = $("#contactUnreadMessageWrapper"+json.src);
			if(unreadMessageWrapper.html() != undefined)
				unreadMessageWrapper.css('display="inline"');
			
			var unreadMessageCountSpan = $("#contactUnreadMessageCount"+json.src);
			if(unreadMessageCountSpan)
				unreadMessageCountSpan.html(parseInt(unreadMessageCountSpan.html())+1);
		});
	}
	
	(function() {
		listeningEvents();
	})();
});

/*
var contactDiv = document.getElementById(json.src);
if(contactDiv)
{	
	contactDiv.className = 'contactWrapper contactHasUnreadMessages';
}
var unreadMessageCountSpan = document.getElementById("contactUnreadMessageCount"+srcUserId);
if(unreadMessageCountSpan)
{
	unreadMessageCountSpan.innerHTML= parseInt(unreadMessageCountSpan.innerHTML) + 1;
}
var unreadMessageWrapper = document.getElementById("contactUnreadMessageWrapper"+srcUserId);
if(unreadMessageWrapper)
{
	unreadMessageWrapper.style.display = "inline";
}
//*/