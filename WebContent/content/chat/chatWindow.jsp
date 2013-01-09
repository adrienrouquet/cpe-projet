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
	<header class="black">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
		<input type="hidden" name="action" value="<%=chatRouterBean.getAction()%>" />
		<input type="button" class="back" value="Back" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>	
		<div class="contactName">
			<%=User.getName(userBean.getMsgManager().getDstUserId())%>
		</div>
		<div class="contactStatus">
			<%= User.getLastLoginDateFormated(userBean.getMsgManager().getDstUserId()) %>
		</div>
	</header>
	<section class="messageSection scroll" id="messageForm">
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
								user.getWebsocket().emit("updateMessageStatus" ,msg.getJsonStringifyMsg("id", "status"));
							}
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
	</section>
	<footer class="newMessageWrapper" id="newMessageWrapper">
			<div class="newMessageContent">
				<textarea placeholder="Enter Message..." id="content" name="content" rows="2" wrap="soft" class="messageContent"></textarea>			
			</div>
			<div class="newMessageSend">
				<button class="button" onclick='doSend()'>Send</button>
			</div>
	</footer>
</div>
