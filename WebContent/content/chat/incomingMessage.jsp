<div class="messageWrapper messageFrom" id="msg<%= request.getParameter("id") %>">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<br/>
	<div class="messageDateTime"><%= request.getParameter("date") %></div>
</div>