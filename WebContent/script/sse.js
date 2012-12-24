$(document).ready(function() {

	function listenSSE()
	{
		console.log("LOADED ONCE");
		if (window.EventSource) 
		{
		var eventSource = new EventSource("http://" + window.location.host + "/cpe-projet/SSEServlet");
		eventSource.addEventListener('messageDelivered', function (e) {
		var json = new JSONMessage();
		json.parse(e.data);
		for (var id in json.msgId)
		{
		  writeStatus(json.msgId[id]);
		}
		
		});
		}
		else 
		{
		}

	}
	
	
	function writeStatus(deliveredMsgId) {
		console.log("Writestatus msg"+deliveredMsgId);
		document.getElementById("msgStatus"+deliveredMsgId).innerHTML = 'V';
	}
	
	
	function JSONMessage() {
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
			console.log(jsonString);
			
			var obj = JSON.parse(jsonString);
			
			this.msgId = (obj["msgId"]!=undefined)?obj["msgId"]:"";
		};
	}
	
	
	

(function () {
	listenSSE();
})();

});