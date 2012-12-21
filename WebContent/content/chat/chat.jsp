<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chat - Main Window</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
	</head>
	<body style="background: rgb(230,230,230); text-align: center;">		
		<div class="section" style="text-align: center;">
			<% 
				if( chatRouterBean.getUrl() != "")  
				{
			%>  
				<jsp:include page="<%= chatRouterBean.getUrl() %>" />
			<% 
				}
			%>
		</div>
	</body>
</html>