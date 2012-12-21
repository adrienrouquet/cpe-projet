<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Manager.UserManager"%>

<jsp:useBean id="msgBean" class="Manager.MsgManager" scope="session" />
<jsp:useBean id="userBean" class="Bean.User" scope="session" />

<jsp:useBean id="chatRouterBean" class="Bean.ChatRouter" scope="session" />

<div class="content">
	<div class="header">
		<div class="contactHeader">
			<div class="contactName">
				<%= UserManager.getName(chatRouterBean.getContactId()) %>
			</div>
			<div class="contactStatus">
				Last login: <%= new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm").format((Timestamp) UserManager.getLastLogin(chatRouterBean.getContactId())) %>
			</div>
		</div>
	</div>
	<div id="messageForm">
		<%
			ResultSet rs = msgBean.getMessages(chatRouterBean.getContactId());
			if(rs != null)
			{
				rs.first();
				do
				{
					if(Integer.parseInt(rs.getString("srcUserId")) != userBean.getId())
					{
		%>
		<jsp:include page="incomingMessage.jsp">
			<jsp:param value='<%= rs.getString("content") %>' name='content'/>
			<jsp:param value='<%= new SimpleDateFormat("MM/dd/yyyy \'at\' HH:mm").format((Timestamp) rs.getObject("sentDate")) %>' name="date"/>
		</jsp:include>
		<%
					}
					else
					{
		%>
		<jsp:include page="outgoingMessage.jsp">
			<jsp:param value='<%= rs.getString("content") %>' name='content'/>
			<jsp:param value='<%= rs.getString("isDelivered") %>' name='messageStatus'/>
			<jsp:param value='<%= new SimpleDateFormat("MM/dd/yyyy \'at\' HH:mm").format((Timestamp) rs.getObject("sentDate")) %>' name="date"/>
		</jsp:include>			
		<%
					}
					
				}while(rs.next());
			}
		%>		
		<div class="newMessageWrapper">
			<div class="messageContent">
				<textarea name="message" rows="4" wrap="hard" class="messageContent">
				<!-- FAUT TROUVER UN MOYEN DE RECUPERER CE TEXTE -->
				Enter your message here...
				</textarea>			
			</div>
<%-- 				<div class="messageSend" 	onclick="setValue('action','sendMsg'); --%>
<%-- 											setValue('srcUserId','<%= userBean.getId() %>'); --%>
<%-- 											setValue('dstUserId','<%= chatRouterBean.getContactId() %>'); --%>
<%-- 											setValue('content','<%=  %>'); --%>
<%-- 											submitForm();"> --%>
			<div class="messageSend" >
				<button class="button"> Send </button>
			</div>
		</div>
	</div>
</div>
