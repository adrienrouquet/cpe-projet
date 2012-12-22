<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.Msg"%>

<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />
<jsp:useBean id="msgManagerBean" class="Bean.MsgManager" scope="session" />
<jsp:useBean id="userBean" class="Bean.User" scope="session" />


<div class="content">
	<div class="header">
		<div class="contactHeader">
			<div class="contactName">
				<%= UserManager.getName(msgManagerBean.getDstUserId()) %>
			</div>
			<div class="contactStatus">
				Last login: <%= new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm").format(UserManager.getLastLogin(msgManagerBean.getDstUserId())) %>
			</div>
		</div>
	</div>
	<div class="section">
		<div id="messageForm">
			<form method="post" id="chatWindowForm" action="CoreServlet">
				<%
					ArrayList<Msg> messages = msgManagerBean.getMessages(msgManagerBean.getDstUserId());
					for( Msg msg : messages)
					{
						if(msg.getSrcUserId() != userBean.getId())
						{
				%>
				<jsp:include page="incomingMessage.jsp">
					<jsp:param value='<%= msg.getContent() %>' name='content'/>
					<jsp:param value='<%= new SimpleDateFormat("MM/dd/yyyy \'at\' HH:mm").format(msg.getSentDate()) %>' name="date"/>
				</jsp:include>
				<%
						}
						else
						{
				%>
				<jsp:include page="outgoingMessage.jsp">
					<jsp:param value='<%= msg.getContent() %>' name='content'/>
					<jsp:param value='<%= msg.isDelivered().toString() %>' name='messageStatus'/>
					<jsp:param value='<%= new SimpleDateFormat("MM/dd/yyyy \'at\' HH:mm").format(msg.getSentDate()) %>' name="date"/>
				</jsp:include>			
				<%
						}
					}
				%>		
				<div class="newMessageWrapper">
					<div class="newMessageContent">
						<textarea placeholder="Enter Message..." id="content" name="content" rows="4" wrap="soft" class="messageContent"></textarea>			
					</div>
					<div class="newMessageSend" >
						<input type="submit" class="button" value="Send"/>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
