
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.User"%>

<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />
<script type="text/javascript" src="script/websocketContact.js"></script>
<div id="container">
	<header class="black h50">
		<h2><%= userBean.getFirstName() + " " + userBean.getLastName() %></h2>
	</header>
	<section id="contactForm" class="contactSection scroll">
		<form name="mainForm" method="post" action="ChatServlet">
			<input type="hidden" name="action" value="view"/>
			<input type="hidden" name="contactId" value="0"/>
			<%
				ArrayList<User> users = null;
						
				users = userBean.getContacts();
							
				if(users.size() > 0)
				{
					for(User user : users)
					{
						int unreadMessageCount = userBean.getMsgManager().getNonDeliveredMessageCount(user.getId());
			%>
			<div id="<%= user.getLogin() %>" class="contactWrapper<% if( unreadMessageCount > 0 ) { out.print(" contactHasUnreadMessages"); } %>" onclick="setValue('mainForm','action','openChat');setValue('mainForm','contactId','<%= user.getId() %>');submitForm('mainForm');">
				<div class="contactImg<% if( unreadMessageCount > 0 ) { out.print(" contactHasUnreadMessagesImg"); } %>"></div>
				<div class="contactName">
					<%= user.getName() %>
					<% 
						String unreadMessageStyle = "display: none;";
						if( unreadMessageCount > 0 ) 
						{
							unreadMessageStyle = "display: inline;";
						}
					%>
					<span id="contactUnreadMessageWrapper<%= user.getLogin() %>" style="<%= unreadMessageStyle %>">
						(<span id="contactUnreadMessageCount<%= user.getLogin() %>"><%= unreadMessageCount %></span>)
					</span>
					
				</div>
				<div class="contactStatus">
					<%= user.getLastLoginDateFormated() %>
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
	</section>
	<footer class="black">
		<form method="post" id="addContactForm" name="addContactForm" action="ChatServlet">
		<input type="hidden" name="action" value="openAddContactWindow" />
		<input type="submit" class="imageButton add floatLeft w80 h80 " value="" onclick="submitForm('addContactForm');"/>	
		</form>
		<form method="post" id="contactRequestsForm" name="contactRequestsForm" action="ChatServlet">
		<input type="hidden" name="action" value="openContactRequestsWindow" />
		<input type="submit" class="imageButton contactRequests floatLeft w80 h80 " value="1" onclick="submitForm('contactRequestsForm');"/>	
		</form>
		<form method="post" id="logoutForm" name="logoutForm" action="AccountServlet">
		<input type="hidden" name="action" value="logout" />
		<input type="button" class="imageButton logout floatRight w80 h80 " value="" onclick="submitForm('logoutForm');"/>	
		</form>
		<form method="post" id="settingsForm" name="settingsForm" action="ChatServlet">
		<input type="hidden" name="action" value="openSettingsWindow" />
		<input type="button" class="imageButton settings floatRight w80 h80 " value="" onclick="submitForm('settingsForm');"/>	
		</form>
	</footer>
</div>