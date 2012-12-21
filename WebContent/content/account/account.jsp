<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<title>Super Messenger</title>
	</head>
	<body class="login">
		<div class="content">
			<h1>SuperMessenger</h1>
			<div class="login">
	 			<form method="post" action="CoreServlet">
					<input type="hidden" name="action" value="login"/>
					<input type="text" name="login" value="Login"/>
					<br />
					<input type="password" name="password" value="Password"/>
					<br />
					<button type="button" onclick="this.form.submit();"> Login </button>
				</form>
			</div>
		</div>
		
	</body>
</html>
