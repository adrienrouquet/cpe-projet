
<!DOCTYPE html>
<jsp:useBean id="accountRouterBean" class="Bean.AccountRouter" scope="session" />  
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width" />
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<title>Super Messenger</title>
		<script type="text/javascript" src="script/jquery-1.8.3.js" ></script>
		<script type="text/javascript" src="script/jquery.validate.min.js"></script>
		<script type="text/javascript" src="script/jquery.defaultvalue.js"></script>
		
		
</head>
	<body>
		<div class="section" style="text-align: center;">
			<% 
				if( accountRouterBean.getUrl() != "")  
				{
			%>  
				<jsp:include page="<%= accountRouterBean.getUrl() %>" />
			<% 
				}
			%>
		</div>
		
	</body>
</html>
