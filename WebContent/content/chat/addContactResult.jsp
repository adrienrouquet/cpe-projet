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

<%
	ArrayList<User> users = null;
		
	//On remplit le champ "login" avec la "searchString" pour effectuer une recherche
	users = UserManager.findContacts(searchUserBean.getLogin());
	
	if (users != null)
	{
		if(users.size() > 0)
		{
			for(User user : users)
			{
				int unreadMessageCount = user.getMsgManager().getNonDeliveredMessageCount(user.getId());
	
	
%>

<div id="contactWrapper<%= user.getId() %>" class="contactWrapperNoHover" >
	<div class="contactName">
		<%= user.getFirstName() %> <%= user.getLastName() %>
		<div class="add">
			<form method="post" id="addContactForm" name="addContactForm" action="ChatServlet">
				<input type="hidden" name="action" value="addContact" />
				<input type="hidden" name="contactId" value="0"/>
		
				<input type="button" value="Add Contact" onclick="setValue('addContactForm','action','addContact');setValue('addContactForm','contactId','<%= user.getId() %>');submitForm('addContactForm');"/>	
			</form>
		</div>	
	</div>
</div>

<%-- onclick="setValue('mainForm','action','submitAddContact');setValue('mainForm','contactId','<%= user.getId() %>');submitForm('mainForm');" --%>

<%
			}
		}
		
		else{
%>
		<h1>TESTEMPTY</h1>
<%		
		}
	}
%>
