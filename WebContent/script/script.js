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
	
	function submitForm(pForm)
	{
		document.forms[pForm].submit();
	}
	function setValue(pForm, pElement, pValue)
	{
		document.forms[pForm].elements[pElement].value = pValue;		
	}
	
	function initGlobal() {
		window.setValue = setValue;
		window.submitForm = submitForm;
	}
	
	(function() {
		init();
		initGlobal();
	})();

});