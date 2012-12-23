<div class="messageWrapper messageTo">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<div class="messageStatus">
	<% 
		if(request.getParameter("messageStatus").equals("true"))
			out.print("V");
		else
			out.print("X");	
	%>
	</div>
	<br/>
	<div class="messageDateTime"><%= request.getParameter("date") %></div>
</div>