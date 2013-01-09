<%@page import="java.util.Vector"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.User"%>
<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="searchUserBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />

<script type="text/javascript" src="script/websocketContact.js"></script>

<h1>Search results</h1>
<form method="post" id="addContactForm" name="addContactForm" action="ChatServlet">
	<input type="hidden" name="action" value="addContact" />
	<input type="hidden" name="contactId" value="0"/>

	<%
		ArrayList<User> users = null;
				
		//On remplit le champ "login" avec la "searchString" pour effectuer une recherche
		users = UserManager.findContacts(userBean.getId(),searchUserBean.getLogin());
		
		if (users != null)
		{
			if(users.size() > 0)
			{
				for(User user : users)
				{
	%>

	<div id="contactWrapper<%= user.getId() %>" class="contactWrapperNoHover" >
		<div class="addContactName">
			<%= user.getFirstName() %> <%= user.getLastName() %>
		</div>
		<input type="button" class="imageButton add floatRight w30 h30" value="" onclick="setValue('addContactForm','contactId','<%= user.getId() %>');submitForm('addContactForm');"/>	
	</div>
	
<%-- onclick="setValue('mainForm','action','submitAddContact');setValue('mainForm','contactId','<%= user.getId() %>');submitForm('mainForm');" --%>

	<%
				}
			}
		
			else{
	%>
	<h1>No result</h1>
	<%		
			}
		}
	%>
	</form>