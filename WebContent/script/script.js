$(document).ready(function() {


	function scrollDown() {
		$(".scroll").animate({
			scrollTop: $(".scroll").prop("scrollHeight")
			}, 0);
	}
	
	function init() {
		scrollDown();
		$("#messageForm").bind("DOMNodeInserted", function() {
			scrollDown();
		});
	}
	
	function initGlobal() {
	}
	
	(function() {
		init();
		initGlobal();
	})();

});