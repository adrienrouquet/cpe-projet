<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	 			<form method="post" action="LoginServlet">
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