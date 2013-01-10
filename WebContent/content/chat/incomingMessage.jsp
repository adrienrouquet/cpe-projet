<div class="messageWrapper messageFrom" id="<%= request.getParameter("id") %>">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<br/>
	<div class="messageDateTime"><%= request.getParameter("sentDate") %></div>
</div>