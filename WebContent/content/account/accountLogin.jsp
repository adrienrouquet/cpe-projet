<script>
	
	$.validator.setDefaults({
		success: "valid"
	});
	
	$(document).ready(function() {
		$("#accountLoginForm").validate({
			rules: {
				login: {
					required: true,
					minlength: 4
				},
				password: {
					required: true,
					minlength: 4
				}
			}
		})
	});	

	
</script>
<div class="login">
		<form method="post" id="accountLoginForm" action="AccountServlet">
		<input type="hidden" name="action" value="login"/>
		<input placeholder="Enter Login..." type="text" id="login" name="login"/>
		<br />
		<input placeholder="Enter Password..." type="password" id="password" name="password"/>
		<br />
		<input type="submit" class="button" value="Login" />
	</form>
</div>
		