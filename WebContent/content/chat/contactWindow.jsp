<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Manager.UserManager"%>

<jsp:useBean id="userBean" class="Bean.User" scope="session" />

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
					ResultSet rs = UserManager.getUsers();
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