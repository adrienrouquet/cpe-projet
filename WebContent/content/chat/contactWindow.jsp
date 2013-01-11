
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
						
						String onClickContent		= "";
						String contactWrapperClass 	= "";
						int unreadMessageCount 		= 0;
						String unreadMessageStyle 	= "display: none;";
						
						unreadMessageCount = userBean.getMsgManager().getNonDeliveredMessageCount(user.getId());
						
						if(user.getApprovalStatus())
						{
							contactWrapperClass = "contactWrapper";
							onClickContent		= "setValue('mainForm','action','openChat');setValue('mainForm','contactId','" + user.getId() + "');submitForm('mainForm');";
							if( unreadMessageCount > 0 )
							{
								contactWrapperClass += " contactHasUnreadMessages";
								unreadMessageStyle = "display: inline;";
							}
						}
						else
						{
							contactWrapperClass = "contactWrapperNoHover greyed";
						}							
			%>
			
			<jsp:include page="include/contact.jsp">
			<jsp:param value='<%= contactWrapperClass %>' name='contactWrapperClass'/>
			<jsp:param value='<%= user.getLogin() %>' name="contactLogin"/>
			<jsp:param value='<%= user.getName() %>' name="contactName"/>
			<jsp:param value='<%= unreadMessageCount %>' name='unreadMessageCount'/>
			<jsp:param value='<%= unreadMessageStyle %>' name='unreadMessageStyle'/>
			<jsp:param value='<%= onClickContent %>' name='onClickContent'/>
			<jsp:param value='<%= user.getLastLoginDateFormated() %>' name='contactStatus'/>
			</jsp:include>
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
		<input type="submit" class="imageButton contactRequests floatLeft w80 h80 " value="<%= userBean.getContactRequestsCount() %>" onclick="submitForm('contactRequestsForm');"/>	
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