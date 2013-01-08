$(document).ready(function() {


	function onChange() {
		$("#messageForm").change( function() {
			$(".scroll").animate({
				scrollTop: $(".scroll").prop("scrollHeight")
				},
				0);
			});
	}
	
	function init() {
		onChange();
	}
	
	function initGlobal() {
	}
	
	(function() {
		init();
		initGlobal();
	})();

});