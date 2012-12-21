<div class="messageWrapper messageTo">
	<div class="messageContent">
		<%= request.getParameter("content") %>
	</div>
	<div class="messageStatus">
	<% 
		if(Integer.parseInt(request.getParameter("messageStatus")) == 1)
			out.print("V");
		else
			out.print("X");	
	%>
	</div>
	<div class="messageDateTime"><%= request.getParameter("date") %></div>
</div>