
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
<div id="container">
	<header>
		<h1>Super Messenger</h1>
	</header>
	<section class="login">
		<% 
			if(request.getParameter("valid") != null && request.getParameter("valid").contains("subscribe"))
			{
		%>
		<div class="valid" style="display: inline-block;">Account created Successfully</div>		
		<%
			}
		%>
		<form method="post" id="accountLoginForm" action="AccountServlet">
			<input type="hidden" name="action" value="login"/>
			<input placeholder="Enter Login..." type="text" id="login" name="login"/>
			<br />
			<input placeholder="Enter Password..." type="password" id="password" name="password"/>
			<br />
			<input type="submit" class="button" value="Login" />
			<br /><a href="AccountServlet?action=subscribe"> Sign up </a>
		</form>
	</section>
</div>