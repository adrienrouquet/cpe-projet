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
			<input type="text" name="firstName" placeholder="Enter first name" value="<% if(request.getParameter("firstName") != null){ out.print(request.getParameter("firstName")); } %>"/>
			<input type="text" name="lastName" placeholder="Enter last name" value="<% if(request.getParameter("firstName") != null){ out.print(request.getParameter("lastName")); } %>"/>
			<% 
			if(request.getParameter("error") != null && request.getParameter("error").contains("emailExists"))
			{
			%>
			<input type="text" name="email" placeholder="Enter email address" class="error" value="<% if(request.getParameter("email") != null){ out.print(request.getParameter("email")); } %>"/>
			<label for="email" generated="true" class="error">Email already exists, please choose a different one</label>		
			<%
			}
			else
			{
			%>
			<input type="text" name="email" placeholder="Enter email address" value="<% if(request.getParameter("email") != null){ out.print(request.getParameter("email")); } %>"/>
			<%
			}
			%>
			
			<% 
			if(request.getParameter("error") != null && request.getParameter("error").contains("phoneExists"))
			{
			%>
			<input type="text" name="phone" placeholder="Enter phone number" class="error" value="<% if(request.getParameter("phone") != null){ out.print(request.getParameter("phone")); } %>"/>
			<label for="phone" generated="true" class="error">Phone already exists, please choose a different one</label>		
			<%
			}
			else
			{
			%>
			<input type="text" name="phone" placeholder="Enter phone number" value="<% if(request.getParameter("phone") != null){ out.print(request.getParameter("phone")); } %>"/>
			<%
			}
			%>
			<% 
			if(request.getParameter("error") != null && request.getParameter("error").contains("loginExists"))
			{
			%>
			<input type="text" name="login" placeholder="Enter login" class="error" value="<% if(request.getParameter("login") != null){ out.print(request.getParameter("login")); } %>"/>
			<label for="login" generated="true" class="error">Login already exists, please choose a different one</label>		
			<%
			}
			else
			{
			%>
			<input type="text" name="login" placeholder="Enter login" value="<% if(request.getParameter("login") != null){ out.print(request.getParameter("login")); } %>"/>
			<%
			}
			%>
			<input type="password" id="password" name="password" placeholder="Enter password"/>
			<input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm password"/>
			<br />
			<br />
			<input type="submit" class="button" value="Create account" />
			<br /><a href="AccountServlet"> Back to Login </a>

		</form>
	</section>
</div>
