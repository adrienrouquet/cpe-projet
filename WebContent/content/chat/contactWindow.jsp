
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.User"%>

<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />
	<script type="text/javascript" src="script/websocketContact.js"></script>
	<div class="content">			
		<div class="header">
			<form method="post" id="logoutForm" name="logoutForm" action="AccountServlet">
			<input type="hidden" name="action" value="logout" />
			<input type="button" class="logout" value="Logout" onclick="submitForm('logoutForm');"/>	
			</form>
		</div>
		<div class="section">
			<div id="contactForm">
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
			<input type="hidden" name="action" value="openAddContactWindow" />
			<input type="button" class="logout" value="Add Contact" onclick="submitForm('addContactForm');"/>	
			</form>
		</div>
	</div>	
