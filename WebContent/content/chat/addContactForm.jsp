<script>
	
	$.validator.setDefaults({
		success: "valid"
	});
	
	$(document).ready(function() {
		$("#mainForm").validate({
			rules: {
				searchString: {
					minlength: 3
				}				
			}
		});
	});		
</script>

<form name="mainForm" id="mainForm" method="post" action="ChatServlet">
	<div class="contactName">
		<input type="hidden" name="action" value="view"/>
		
		<br /><input placeholder="Search a contact..." type="text" id="searchString" name="searchString" />
		<br /><input type="button" value="Find Contact" onclick="setValue('mainForm','action','findContact');submitForm('mainForm');"/>
	</div>
</form>