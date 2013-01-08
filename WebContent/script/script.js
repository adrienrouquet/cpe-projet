$(document).ready(function() {


	function scrollDown() {
		$(".scroll").animate({
			scrollTop: $(".scroll").prop("scrollHeight")
			},
			0);
	}
	
	function init() {
		scrollDown();
		$("#messageForm").change(scrollDown);
	}
	
	function initGlobal() {
	}
	
	(function() {
		init();
		initGlobal();
	})();

});