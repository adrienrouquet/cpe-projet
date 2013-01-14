<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="AppCore.User"%>
<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="searchUserBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />

<script type="text/javascript" src="script/websocketContact.js"></script>
<div id="container">
	<header class="black">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
		<input type="hidden" name="action" value="<%=chatRouterBean.getAction()%>" />
		<input type="button" class="imageButton back h50 w50 floatLeft" value="" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>	
		Contact requests
	</header>

	<section id="contactRequests" class="addContactSection">
		<h1>Contact requests</h1>
		
		<form method="post" id="contactRequestsForm" name="contactRequestsForm" action="ChatServlet">
			<input type="hidden" name="action" value="submitContactRequests" />
			<input type="hidden" name="contactId" value="0"/>
			<input type="hidden" name="acceptRequest" value="false"/>
			
			<%
				ArrayList<User> users = null;
				
				users = userBean.getContactRequests();
				
				
					
					if (users != null)
					{
						if(users.size() > 0)
						{
							for(User user : users)
							{
			%>
		
							<div class="contactWrapperNoHover" >
								<div class="addContactName">
									<%= user.getName() %>
								</div>
								<input type="button" class="imageButton ignore floatRight w30 h30" value="" onclick="setValue('contactRequestsForm','contactId','<%= user.getId() %>');setValue('contactRequestsForm','acceptRequest','false');submitForm('contactRequestsForm');"/>
								<input type="button" class="imageButton add floatRight w30 h30" value="" onclick="setValue('contactRequestsForm','contactId','<%= user.getId() %>');setValue('contactRequestsForm','acceptRequest','true');submitForm('contactRequestsForm');"/>	
							</div>
		
			<%
							}
						}else{
													
			%>
							<h1 >No requests to display...</h1>
			<%	
						}
					}
			%>
		</form>
	</section>
</div>