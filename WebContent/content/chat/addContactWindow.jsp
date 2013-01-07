<script>
	
	$.validator.setDefaults({
		success: "valid"
	});
	
	$(document).ready(function() {
		$("#mainForm").validate({
			rules: {
				login: {
					minlength: 4
				},
				name: {
					minlength: 4
				}
			}
		})
	});	

	
</script>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Bean.UserBean"%>
<%@page import="Class.User"%>

<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="searchUserBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />
<script type="text/javascript" src="script/websocketContact.js"></script>
<div class="content">			
	<div class="header black">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
		<input type="hidden" name="action" value="<%=chatRouterBean.getAction()%>" />
		<input type="button" class="back" value="Back" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>	
		TEST
	</div>
	<div class="section">
		<div id="contactForm">
			<form name="mainForm" id="mainForm" method="post" action="ChatServlet">
				<input type="hidden" name="action" value="view"/>
				<input type="hidden" name="contactId" value="0"/>
				<%
					ArrayList<User> users = null;
						
							users = UserManager.findContacts(searchUserBean.getFirstName(),searchUserBean.getLogin(),searchUserBean.getEmail(),searchUserBean.getPhone());
							
							if(users.size() > 0)
							{
								for(User user : users)
								{
									int unreadMessageCount = user.getMsgManager().getNonDeliveredMessageCount(user.getId());
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
				%>
				<input type="button" value="Clear" onclick="this.form.reset();setValue('mainForm','action','clearAddContactWindow');submitForm('mainForm');"/>
				<%
					}
					else
					{
				%>
				<div class="contactName">
					<br /><input placeholder="Search by name..." type="text" id="name" name="name" />
					<br /><input placeholder="Search by login..." type="text" id="login" name="login" />
					<br /><input placeholder="Search by email..." type="text" name="email" />
					<br /><input placeholder="Search by phone..." type="text" name="phone" />
					<br /><input type="button" value="Find Contact" onclick="setValue('mainForm','action','findContact');submitForm('mainForm');"/>
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
