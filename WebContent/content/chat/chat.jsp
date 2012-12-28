<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Chat - Main Window</title>
		<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />
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