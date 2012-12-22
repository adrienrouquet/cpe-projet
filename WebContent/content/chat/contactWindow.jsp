<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Bean.User"%>

<jsp:useBean id="userBean" class="Bean.User" scope="session" />


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
	
	<div class="content">			
		<div class="header">
			<h1>Super Messenger</h1>
		</div>
		<div id="contactForm">
			<form name="mainForm" method="post" action="ChatServlet">		
				<input type="hidden" name="action" value="view"/>
				<input type="hidden" name="contactId" value="0"/>
				<%
					ArrayList<User> users = null;
				
					users = UserManager.getContacts(userBean.getId());
					
					if(users.size() > 0)
					{
						for(User user : users)
						{
				%>
				<div class="contactWrapper" onclick="setValue('action','openChat');setValue('contactId','<%= user.getId() %>');submitForm();">
					<div class="contactName">
						<%= user.getFirstName() %> <%= user.getLastName() %>
					</div>
					<div class="contactStatus">
						Last login: <%= user.getLastLoginDate().toString() %>
					</div>
				</div>
				<%
						}
					}
					else
					{
				%>
				<div class="contactWrapper">
					<div class="contactName">
						You have no contact to display yet
					</div>
				</div>
				<%
					}
				%>			
			</form>
		</div>
	</div>	