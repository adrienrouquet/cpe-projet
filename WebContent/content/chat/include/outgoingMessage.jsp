<div class="messageWrapper messageTo" id="<%= request.getParameter("id") %>">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<div class="messageStatus">
		<%= request.getParameter("status") %>
	</div>
	<br/>
	<div class="messageDateTime"><%= request.getParameter("sentDate") %></div>
</div>
