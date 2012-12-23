<div class="messageWrapper messageTo">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<div class="messageStatus">
		<%= request.getParameter("messageStatus") %>
	</div>
	<br/>
	<div class="messageDateTime"><%= request.getParameter("date") %></div>
</div>
