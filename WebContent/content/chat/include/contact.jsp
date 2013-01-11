<div id="<%= request.getParameter("contactLogin") %>" class="<%= request.getParameter("contactWrapperClass") %>" onclick="<%= request.getParameter("onClickContent") %>">
	<div class="contactImg"></div>
	<div class="contactName">
		<%= request.getParameter("contactName") %>
		<span id="contactUnreadMessageWrapper<%= request.getParameter("contactLogin") %>" style="<%= request.getParameter("unreadMessageStyle") %>">
			(<span id="contactUnreadMessageCount<%= request.getParameter("login") %>"><%= request.getParameter("unreadMessageCount") %></span>)
		</span>		
	</div>
	<div class="contactStatus">
		<%= request.getParameter("contactStatus") %>
	</div>
</div>