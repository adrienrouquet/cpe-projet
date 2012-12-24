$(document).ready(function() {

	//Cette fonction instancie l'ecoute et les events a ecouter
	function listenSSE()
	{
		console.log("Init SSE");
		if (window.EventSource) 
		{
			var eventSource = new EventSource("http://" + window.location.host + "/cpe-projet/SSEServlet");
			
			//Event messageDelivered
			eventSource.addEventListener('messageDelivered', function (e) {
				var json = new JSONMessageDelivered();
				json.parse(e.data);
				for (var id in json.msgId)
				{
					//Update du statut > le message a ete recu
					writeStatus(json.msgId[id]);
				}
			});
			
			//Event newMessage
			eventSource.addEventListener('newMessageReceived', function (e) {
				var json = new JSONNewMessageReceived();
				json.parse(e.data);
				for (var id in json.srcUserId)
				{
					//Update du nombre de messages non lus du contact
					writeUpdateCount(json.srcUserId[id]);
				}
			});
			
			
		}
		else 
		{
		}

	}
	
	
	function writeStatus(deliveredMsgId) {
		console.log("Writestatus msg"+deliveredMsgId);
		var msgStatus = document.getElementById("msgStatus"+deliveredMsgId);
		if(msgStatus != null)
			msgStatus.innerHTML = 'V';
	}
	
	function writeUpdateCount(srcUserId) {
		console.log("WriteUpdateCount user"+srcUserId);
		var contactDiv = document.getElementById("contactWrapper"+srcUserId);
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
	}
	
	
	function JSONMessageDelivered() {
		this.msgId = "";
		
		this.getJSON = function () {
			return {
				"msgId": this.msgId				
			};
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			//console.log(jsonString);
			
			var obj = JSON.parse(jsonString);
			
			this.msgId = (obj["msgId"]!=undefined)?obj["msgId"]:"";
		};
	}
	
	function JSONNewMessageReceived() {
		this.srcUserId = "";
		
		this.getJSON = function () {
			return {
				"srcUserId": this.srcUserId				
			};
		};
		
		this.stringify = function() {
			return JSON.stringify(this.getJSON());
		};
		
		this.parse = function(jsonString) {
			//console.log(jsonString);
			
			var obj = JSON.parse(jsonString);
			
			this.srcUserId = (obj["srcUserId"]!=undefined)?obj["srcUserId"]:"";
		};
	}
	
	

(function () {
	listenSSE();
})();

});