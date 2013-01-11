<div id="<%= request.getParameter("contactLogin") %>" class="<%= request.getParameter("contactWrapperClass") %>" onclick="<%= request.getParameter("onClickContent") %>">
		<div class="contactImg"></div>
		<div class="contactName">
			<%= request.getParameter("contactName") %>
			<span id="contactUnreadMessageWrapper" style="<%= request.getParameter("unreadMessageStyle") %>">
			(<span id="contactUnreadMessageCount"><%= request.getParameter("unreadMessageCount") %></span>)
			</span>		
		</div>
		<div class="<%= request.getParameter("contactStatusClass") %>">
			<%= request.getParameter("contactStatus") %>
		</div>
</div>
