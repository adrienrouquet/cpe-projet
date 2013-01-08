<script>
	
	$.validator.setDefaults({
		success: "valid"
	});
	
	$(document).ready(function() {
		$("#accountSubscribeForm").validate({
			rules: {
				firstName: {
					required: true,
					minlength: 4
				},
				lastName: {
					required: true,
					minlength: 4
				},
				email: {
					required: true,
					email: true
				},
				phone: {
					required: true
				},
				login: {
					required: true,
					minlength: 4
				},
				firstName: {
					required: true,
					minlength: 4
				},
				password: {
					required: true,
					minlength: 4,
				},
				confirmPassword: {
					required: true,
					equalTo: "#password"
				}
			}
		})
	});	
</script>
<div id="container">
	<header>
		<h2>Create an account</h2>
	</header>
	<section>
		<form method="post" action="AccountServlet" id="accountSubscribeForm">
			<input type="hidden" name="action" value="submitSubscribe" />
			<input type="text" name="firstName" placeholder="Enter first name"/>
			<br />
			<input type="text" name="lastName" placeholder="Enter last name"/>
			<br />
			<input type="email" name="email" placeholder="Enter email address"/>
			<br />
			<input type="phone" name="phone" placeholder="Enter phone number"/>
			<br />
			<input type="text" name="login" placeholder="Enter login"/>
			<% 
			if(request.getParameter("error") != null && request.getParameter("error").equals("userExists"))
			{
			%>
			<label for="login" generated="true" class="error" style="display: inline-block;">Login already exists, please choose a different one</label>		
			<%
			}
			%>
			<br />
			<input type="password" id="password" name="password" placeholder="Enter password"/>
			<br />
			<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password"/>
			<br />
			<input type="submit" value="Create account" />

		</form>
	</section>
</div>