<h2>Create an account</h2>
<form method="post" action="LoginServlet">
	<input type="text" name="firstname" value="Enter first name"/>
	<br />
	<input type="text" name="lastname" value="Enter last name"/>
	<br />
	<input type="email" name="email" value="Enter email address"/>
	<br />
	<input type="text" name="login" value="Enter login"/>
	<br />
	<input type="password" name="password" value="Enter password"/>
	<br />
	<input type="password" name="confirmpassword" value="Confirm password"/>
	<br />
	<button type="button" onclick="this.form.submit();"> Create account </button>
</form>