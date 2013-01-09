<div class="messageWrapper messageTo" id="<%= request.getParameter("id") %>">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<div class="messageStatus<% 
		if(request.getParameter("messageStatus").equals("/"))
		{
			out.print(" messageStatusSent");
		}
		else if(request.getParameter("messageStatus").equals("//"))
		{
			out.print(" messageStatusReceived");
		}
	%>">
	
	</div>
	<br/>
	<div class="messageDateTime"><%= request.getParameter("date") %></div>
</div>
