<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<title>Super Messenger</title>
		<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />
	</head>
	<body class="login">
		<div class="content">
			<h1>SuperMessenger</h1>
			<div class="login">
				<% 
				if( chatRouterBean.getUrl() != "")  
				{
				%>  
					<jsp:include page="<%= chatRouterBean.getUrl() %>" />
				<% 
					}
				%>

<!-- 	 			<form method="post" action="LoginServlet"> -->
<!-- 					<input type="text" name="login" value="Login"/> -->
<!-- 					<br /> -->
<!-- 					<input type="password" name="password" value="Password"/> -->
<!-- 					<br /> -->
<!-- 					<button type="button" onclick="this.form.submit();"> Login </button> -->
<!-- 				</form> -->
			</div>
		</div>
		
	</body>
</html>
