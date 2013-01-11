<form method="post" id="addContactForm" name="addContactForm" action="ChatServlet">
	<input type="hidden" name="action" value="openAddContactWindow" />
	<input type="submit" class="imageButton add floatLeft w60 h60 " value="" onclick="submitForm('addContactForm');"/>	
</form>
<form method="post" id="contactRequestsForm" name="contactRequestsForm" action="ChatServlet">
	<input type="hidden" name="action" value="openContactRequestsWindow" />
	<input type="submit" id="contactRequestSubmit" class="imageButton contactRequests floatLeft w60 h60 " value="<%= request.getParameter("contactRequestsCount") %>" onclick="submitForm('contactRequestsForm');"/>	
</form>
<form method="post" id="logoutForm" name="logoutForm" action="AccountServlet">
	<input type="hidden" name="action" value="logout" />
	<input type="button" class="imageButton logout floatRight w60 h60 " value="" onclick="submitForm('logoutForm');"/>	
</form>
<form method="post" id="settingsForm" name="settingsForm" action="ChatServlet">
	<input type="hidden" name="action" value="openSettingsWindow" />
	<input type="button" class="imageButton settings floatRight w60 h60 " value="" onclick="submitForm('settingsForm');"/>	
</form>