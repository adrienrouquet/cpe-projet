<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="Manager.UserManager"%>

<jsp:useBean id="msgBean" class="Beans.Msg" scope="session" />
<jsp:useBean id="userBean" class="Beans.User" scope="session" />

<jsp:useBean id="chatRouterBean" class="Beans.ChatRouter" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<title>Insert title here</title>
</head>
<body>
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
		<form>
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
			<div class="messageWrapper messageFrom">
				<div class="messageContent">
					<%= rs.getString("content") %>
				</div>
				<div class="messageDateTime"><%= new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm").format((Timestamp) rs.getObject("sentDate")) %></div>
			</div>
			<%
						}
						else
						{
			%>
			<div class="messageWrapper messageTo">
				<div class="messageContent">
					<%= rs.getString("content") %>
				</div>
				<div class="messageStatus">V</div>
				<div class="messageDateTime"><%= new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm").format((Timestamp) rs.getObject("sentDate")) %></div>
			</div>
			<%
						}
						
					}while(rs.next());
				}
			%>		
			<div class="newMessageWrapper">
				<div class="messageContent">
					<textarea name="message" rows="4" class="messageContent">
					Enter your message here...
					</textarea>			
				</div>
				<div class="messageSend">
					<button class="button"> Send </button>
				</div>
			</div>		
		</form>
	</div>
</div>
</body>
</html>
