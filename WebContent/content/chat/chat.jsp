
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width" />
		<title>Chat - Main Window</title>
		<link rel="stylesheet" type="text/css" href="style/style.css" />
	<script>
		function submitForm(pForm)
		{
			document.forms[pForm].submit();
		}
		function setValue(pForm, pElement, pValue)
		{
			document.forms[pForm].elements[pElement].value = pValue;		
		}
	</script>
	<script type="text/javascript" src="script/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="script/websocket.js"></script>
<!-- 	<script type="text/javascript" src="script/sse.js"></script> -->
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
