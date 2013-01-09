<% 
String unreadMessageStyle = "display: none;";
String classes = "contactWrapper";
String onclick = "";
if( Integer.parseInt(request.getParameter("unreadMessageCount")) > 0 ) 
{
	unreadMessageStyle = "display: inline;";
	classes += " contactHasUnreadMessages";
}
if (request.getParameter("id") != "null") {
	onclick = "setValue('mainForm','action','openChat');setValue('mainForm','contactId','" + request.getParameter("id") + "');submitForm('mainForm');";
}
%>

<div id="<%= request.getParameter("login") %>" class="<%= classes %>" onclick=<%= onclick %>>
	<div class="contactName">
		<%= request.getParameter("name") %>
		<span id="contactUnreadMessageWrapper<%= request.getParameter("login") %>" style="<%= unreadMessageStyle %>">
			(<span id="contactUnreadMessageCount<%= request.getParameter("login") %>"><%= request.getParameter("unreadMessageCount") %></span>)
		</span>
		
	</div>
	<div class="contactStatus">
		<%= request.getParameter("status") %>
	</div>
</div>