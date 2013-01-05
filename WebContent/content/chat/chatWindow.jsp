<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.Msg"%>
<%@page import="Class.Websocket"%>

<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />
<jsp:useBean id="msgManagerBean" class="Bean.MsgManager" scope="session" />
<jsp:useBean id="userBean" class="Bean.User" scope="session" />

<script type="text/javascript" src="script/websocketChat.js"></script>
<div class="content">
	<div class="header">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
		<input type="hidden" name="action" value="<%= chatRouterBean.getAction() %>" />
		<input type="button" class="back" value="Back" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>	
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
				<div class="messagesWrapper">
					<%
						ArrayList<Msg> messages = msgManagerBean.getMessages(msgManagerBean.getSrcUserId(),msgManagerBean.getDstUserId());
						for( Msg msg : messages)
						{
							if(msg.getSrcUserId() != userBean.getId())
							{
								//Si le message n'avait pas ete delivre, on le marque comme delivre maintenant
								if (!msg.isDelivered())
								{
									msgManagerBean.setMessageDelivered(msg.getId());
									msg.setIsDelivered(true);
									Websocket WS = Manager.WebsocketManager.getWebsocket(msgManagerBean.getDstUserId());
									if (WS != null)
										WS.emit("updateMessageStatus" ,msg.getJsonStringifyMsg());
								}
					%>
					<jsp:include page="incomingMessage.jsp">
						<jsp:param value='<%= msg.getId() %>' name='id'/>
						<jsp:param value='<%= msg.getContent() %>' name='content'/>
						<jsp:param value='<%= msg.getDateFormated() %>' name="date"/>
					</jsp:include>
					<%
							}
							else
							{
					%>
					<jsp:include page="outgoingMessage.jsp">
						<jsp:param value='<%= msg.getId() %>' name='id'/>
						<jsp:param value='<%= msg.getContent() %>' name='content'/>
						<jsp:param value='<%= msg.isDeliveredFormated() %>' name='messageStatus'/>
						<jsp:param value='<%= msg.getDateFormated() %>' name="date"/>
					</jsp:include>
					<%
							}
						}
					%>
					<br />	
				</div>
		</div>
	</div>
	<div class="newMessageWrapper" id="newMessageWrapper">
			<div class="newMessageContent">
				<textarea placeholder="Enter Message..." id="content" name="content" rows="2" wrap="soft" class="messageContent"></textarea>			
			</div>
			<div class="newMessageSend">
				<button class="button" onclick='doSend()'>Send</button>
			</div>
	</div>
</div>
