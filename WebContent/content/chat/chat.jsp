<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width" />
		<title>Chat - Main Window</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
	<script>
		function submitForm()
		{
			document.forms['mainForm'].submit();
		}
		function setValue(pElement, pValue)
		{
			document.forms['mainForm'].elements[pElement].value = pValue;		
		}
	</script>
	<script type="text/javascript" src="script/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="script/websocket.js"></script>
	</head>
	<body style="background: rgb(230,230,230); text-align: center;">		
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