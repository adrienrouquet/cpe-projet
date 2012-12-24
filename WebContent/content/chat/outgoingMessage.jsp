<div class="messageWrapper messageTo" id="msg<%= request.getParameter("id") %>">
	<div class="messageContent" id="msgContent<%= request.getParameter("id") %>">
		<%= request.getParameter("content") %>
	</div>
	<div class="messageStatus" id="msgStatus<%= request.getParameter("id") %>">
		<%= request.getParameter("messageStatus") %>
	</div>
	<br/>
	<div class="messageDateTime" id="msgDateTime<%= request.getParameter("id") %>"><%= request.getParameter("date") %></div>
</div>
