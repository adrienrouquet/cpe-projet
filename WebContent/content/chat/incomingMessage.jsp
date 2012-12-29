<div class="messageWrapper messageFrom" id="msg<%= request.getParameter("id") %>">
	<div class="messageContent" id="msgContent<%= request.getParameter("id") %>">
		<%= request.getParameter("content") %>
	</div>
	<br/>
	<div class="messageDateTime" id="msgDateTime<%= request.getParameter("id") %>"><%= request.getParameter("date") %></div>
</div>