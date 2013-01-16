
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;" />
		<title>MyAM</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<link rel="stylesheet" type="text/css" href="style/contact.css" />
		<link rel="stylesheet" type="text/css" href="style/chat.css" />
		<script type="text/javascript" src="script/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="script/jquery.validate.min.js"></script>
		<script type="text/javascript" src="script/jquery.defaultvalue.js"></script>
		<script type="text/javascript" src="script/script.js"></script>
		<script type="text/javascript" src="script/websocket.js"></script>
	</head>
	<body>	
	<div id="logoBGContainer"></div>	
	<% 
		if( chatRouterBean.getUrl() != "")  
		{
	%>  
		<jsp:include page="<%= chatRouterBean.getUrl() %>" />
	<% 
		}
	%>
	</body>
</html>
