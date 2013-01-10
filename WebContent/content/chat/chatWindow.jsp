<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.Msg"%>
<%@page import="Class.User"%>

<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />
<script type="text/javascript" src="script/websocketChat.js"></script>
<script type="text/javascript" src="script/script.js"></script>

<div id="container">
	<header class="black h50">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
			<input type="hidden" name="action" value="<%=chatRouterBean.getAction()%>" />
			<input type="button" class="imageButton back w50 h50 floatLeft" value="" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>
		<%
		int dstId = userBean.getMsgManager().getDstUserId();
		%>
		<div id=<%= User.getLogin(dstId) %>>
			<div class="contactName">
				<%= User.getName(dstId) %>
			</div>
			<div class="contactStatus">
				<%= User.getLastLoginDateFormated(dstId) %>
			</div>
		</div>
	</header>
	<section class="messageSection scroll" id="messageForm">
		<br />
		<%
			ArrayList<Msg> messages = userBean.getMsgManager().getMessages(userBean.getId(),userBean.getMsgManager().getDstUserId());
				for( Msg msg : messages)
				{
					if(msg.getSrcUserId() != userBean.getId())
					{
						//Si le message n'avait pas ete delivre, on le marque comme delivre maintenant
						if (!msg.isDelivered())
						{
							userBean.getMsgManager().setMessageDelivered(msg.getId());
							msg.setIsDelivered(true);
							for (User user : UserManager.getUsersConnected(userBean.getMsgManager().getDstUserId())) {
								if (user.getMsgManager().getDstUserId() == userBean.getId())
									user.getWebsocket().emit("updateMessageStatus" ,msg.getJsonStringifyMsg("id", "status"));
							}
						}
		%>
		<jsp:include page="incomingMessage.jsp">
			<jsp:param value='<%= msg.getId() %>' name='id'/>
			<jsp:param value='<%= msg.getContent() %>' name='content'/>
			<jsp:param value='<%= msg.getDateFormated() %>' name="sentDate"/>
		</jsp:include>
		<%
				}
				else
				{
		%>
		<jsp:include page="outgoingMessage.jsp">
			<jsp:param value='<%= msg.getId() %>' name='id'/>
			<jsp:param value='<%= msg.getContent() %>' name='content'/>
			<jsp:param value='<%= msg.isDeliveredFormated() %>' name='status'/>
			<jsp:param value='<%= msg.getDateFormated() %>' name="sentDate"/>
		</jsp:include>
		<%
				}
			}
		%>
		<br />
	</section>
	<footer class="newMessageWrapper" id="newMessageWrapper">
			<div class="newMessageContent">
				<textarea placeholder="Enter Message..." id="content" name="content" rows="2" wrap="soft" class="messageContent"></textarea>			
			</div>
			<div class="newMessageSend">
				<input type="button" class="button" onclick='doSend()' value="Send" />
			</div>
	</footer>
</div>
