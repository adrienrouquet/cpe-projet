<%@page import="java.util.ArrayList"%>
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
						String onClickContent		= "setValue('mainForm','action','openChat');setValue('mainForm','contactId','" + user.getId() + "');";
						String onClickDeleteContent = "setValue('mainForm','action','deleteContact');setValue('mainForm','contactId','" + user.getId() + "');submitForm('mainForm');";
						String contactWrapperClass 	= "contactWrapper";
						String contactStatusClass	= "contactStatus";
						String unreadMessageStyle 	= "display: none;";
						String contactStatus		= user.getLastLoginDateFormated();
						int unreadMessageCount 		= 0;
						
						unreadMessageCount = userBean.getMsgManager().getNonDeliveredMessageCount(user.getId());
						
						if(user.getApprovalStatus())
						{
							onClickContent += "submitForm('mainForm');";
							if( unreadMessageCount > 0 )
							{
								contactWrapperClass += " contactHasUnreadMessages";
								unreadMessageStyle = "display: inline;";
							}
						}
						else
						{
							contactWrapperClass = "contactWrapperNoHover greyed";
							contactStatusClass 	= "";
							contactStatus		= "Offline";
						}							
			%>
			
			<jsp:include page="include/contact.jsp">
			<jsp:param value='<%= contactWrapperClass %>' name='contactWrapperClass'/>
			<jsp:param value='<%= user.getLogin() %>' name="contactLogin"/>
			<jsp:param value='<%= user.getName() %>' name="contactName"/>
			<jsp:param value='<%= unreadMessageCount %>' name='unreadMessageCount'/>
			<jsp:param value='<%= unreadMessageStyle %>' name='unreadMessageStyle'/>
			<jsp:param value='<%= onClickContent %>' name='onClickContent'/>
			<jsp:param value='<%= onClickDeleteContent %>' name='onClickDeleteContent'/>
			<jsp:param value='<%= contactStatusClass %>' name='contactStatusClass'/>
			<jsp:param value='<%= contactStatus %>' name='contactStatus'/>
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
		<jsp:include page="include/contactFooter.jsp">
			<jsp:param value='<%= userBean.getContactRequestsCount() %>' name='contactRequestsCount'/>
		</jsp:include>
	</footer>
</div>