
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Bean.User"%>

<jsp:useBean id="userBean" class="Bean.User" scope="session" />
<jsp:useBean id="searchUserBean" class="Bean.User" scope="session" />
<jsp:useBean id="msgManagerBean" class="Bean.MsgManager" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />
<script type="text/javascript" src="script/websocketContact.js"></script>
<div class="content">			
	<div class="header black">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
		<input type="hidden" name="action" value="<%= chatRouterBean.getAction() %>" />
		<input type="button" class="back" value="Back" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>	
		TEST
	</div>
	<div class="section">
		<div id="contactForm">
			<form name="mainForm" method="post" action="ChatServlet">
				<input type="hidden" name="action" value="submitAddContact"/>
				<input type="hidden" name="contactId" value="0"/>
				<%
					ArrayList<User> users = null;
				
					users = UserManager.findContacts(searchUserBean.getFirstName(),searchUserBean.getLogin(),searchUserBean.getEmail(),searchUserBean.getPhone());
					
					if(users.size() > 0)
					{
						for(User user : users)
						{
							int unreadMessageCount = msgManagerBean.getNonDeliveredMessageCount(user.getId()); 
				%>
				<div id="contactWrapper<%= user.getId() %>" class="contactWrapper<% if( unreadMessageCount > 0 ) { out.print(" contactHasUnreadMessages"); } %>" onclick="setValue('mainForm','action','openChat');setValue('mainForm','contactId','<%= user.getId() %>');submitForm('mainForm');">
				
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
	<div class="footer">
		<form method="post" id="addContactForm" name="addContactForm" action="ChatServlet">
		<input type="hidden" name="action" value="addContact" />
		<input type="button" class="logout" value="Add Contact" onclick="submitForm('addContactForm');"/>	
		</form>
	</div>
</div>	
