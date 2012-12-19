<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<jsp:useBean id="userBean" class="Beans.User" scope="session" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<title>Super Messenger</title>
	<script language="javascript">
	function submitForm()
	{
		document.forms['mainForm'].submit();
	}
	function setValue(pElement, pValue)
	{
		document.forms['mainForm'].elements[pElement].value = pValue;		
	}
	</script>
	</head>
	<body>
		<div class="content">			
			<div class="header">
				<h1>Super Messenger</h1>
			</div>
			<div id="contactForm">
				<form name="mainForm" method="post" action="LoginServlet">		
					<input type="hidden" name="action" value="view"/>
					<input type="hidden" name="contactId" value="0"/>
					
					<%
						ResultSet rs = userBean.getUsers();
						if(rs != null)
						{
							rs.first();
							do
							{
					%>
					<div class="contactWrapper" onclick="setValue('action','openChat');setValue('contactId','<%= rs.getString("id") %>');submitForm();">
						<div class="contactName">
							<%= rs.getString("firstName") %> <%= rs.getString("lastName") %>
						</div>
						<div class="contactStatus">
							Last login: <%= ((Timestamp) rs.getObject("lastLoginDate")).toString() %>
						</div>
					</div>
					<%
							}while(rs.next());
						}
					%>			
				</form>
			</div>
		</div>	
	</body>
</html>