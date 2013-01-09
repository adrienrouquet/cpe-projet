<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Manager.UserManager"%>
<%@page import="Class.User"%>
<jsp:useBean id="userBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="searchUserBean" class="Bean.UserBean" scope="session" />
<jsp:useBean id="chatRouterBean" class="Bean.Router" scope="session" />

<script type="text/javascript" src="script/websocketContact.js"></script>
<div id="container">
	<header class="black">
		<form method="post" id="backForm" name="backForm" action="ChatServlet">
		<input type="hidden" name="action" value="<%=chatRouterBean.getAction()%>" />
		<input type="button" class="back" value="Back" onclick="setValue('backForm','action','backToContactView');submitForm('backForm');"/>	
		</form>	
		Find Contact
	</header>

	<section id="search">
		<div class="searchForm">
			<jsp:include page="addContactForm.jsp" />
		</div>
		
		<div class="searchResult">
			<jsp:include page="addContactResult.jsp" />
			
		</div>
	</section>
</div>