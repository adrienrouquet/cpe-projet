<script>
	
	$.validator.setDefaults({
		success: "valid"
	});
	
	$(document).ready(function() {
		$("#mainForm").validate({
			rules: {
				login: {
					minlength: 4
				},
				name: {
					minlength: 4
				}
				
			}
		});
	});		
</script>

<form name="mainForm" id="mainForm" method="post" action="ChatServlet">
	<div class="contactName">
		<input type="hidden" name="action" value="view"/>
		<input type="hidden" name="contactId" value="0"/>
		
		<br /><input placeholder="Search by name..." type="text" id="name" name="name" />
		<br /><input placeholder="Search by login..." type="text" id="login" name="login" />
		<br /><input placeholder="Search by email..." type="text" name="email" />
		<br /><input placeholder="Search by phone..." type="text" name="phone" />
		<br /><input type="button" value="Find Contact" onclick="setValue('mainForm','action','findContact');submitForm('mainForm');"/>
	</div>
</form>