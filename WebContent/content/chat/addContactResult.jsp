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
		
	users = UserManager.findContacts(searchUserBean.getFirstName(),searchUserBean.getLogin(),searchUserBean.getEmail(),searchUserBean.getPhone());
	
	if (users != null)
	{
		if(users.size() > 0)
		{
			for(User user : users)
			{
				int unreadMessageCount = user.getMsgManager().getNonDeliveredMessageCount(user.getId());
	
	
%>

<div id="contactWrapper<%= user.getId() %>" class="contactWrapper<% if( unreadMessageCount > 0 ) { out.print(" contactHasUnreadMessages"); } %>" onclick="setValue('mainForm','action','submitAddContact');setValue('mainForm','contactId','<%= user.getId() %>');submitForm('mainForm');">
	<div class="contactName">
		<%= user.getFirstName() %> <%= user.getLastName() %>
		<% 
				String unreadMessageStyle = "display: none;";
				if( unreadMessageCount > 0 ) 
				{
					unreadMessageStyle = "display: inline;";
				}
		%>
		<span id="contactUnreadMessageWrapper<%= user.getId() %>" style="<%= unreadMessageStyle %>">
			(<span id="contactUnreadMessageCount<%= user.getId() %>"><%= unreadMessageCount %></span>)
		</span>
		
	</div>
	<div class="contactStatus">
		Last login: <%= user.getLastLoginDate().toString() %>
	</div>
	<form method="post" id="addContactForm" name="addContactForm" action="ChatServlet">
			<input type="hidden" name="action" value="addContact" />
			<input type="button" class="add" value="Add Contact" onclick="submitForm('addContactForm');"/>	
	</form>
</div>
<%
			}
		}
		
		else{
%>
		
<%		
		}
	}else
	{
%>
		<h1>TESTEMPTY</h1>
<%
	}
%>