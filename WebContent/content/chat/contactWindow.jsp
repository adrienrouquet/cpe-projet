<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="style/style.css" />
		<title>Super Messenger</title>
	<script language="javascript">
	function submitForm()
	{
		document.forms['mainForm'].submit();
	}
	function setValue(pElement, pValue)
	{
		document.forms['mainForm'].elements[pElement].value = pValue;		
	}
	</script>
	</head>
	<body>
		<div class="content">			
			<div class="header">
				<h1>Super Messenger</h1>
			</div>
			<div id="contactForm">
				<form name="mainForm" method="post" action="LoginServlet">		
				<input type="hidden" name="action" value="view"/>
				<input type="hidden" name="contactId" value="0"/>		
					<div class="contactWrapper" onclick="setValue('action','openChat');setValue('contactId','2');submitForm();">
						<div class="contactName">
							Loic
						</div>
						<div class="contactStatus">Last login: 01/01/01 at 00:00</div>
					</div>
					<div class="contactWrapper" onClick="setValue('action','openChat');setValue('contactId','3');submitForm();">
						<div class="contactName">
							Adrien
						</div>
						<div class="contactStatus">Last login: 01/01/01 at 00:00</div>
					</div>
					<div class="contactWrapper" onclick="setValue('action','openChat');setValue('contactId','4');submitForm();">
						<div class="contactName">
							Henri
						</div>
						<div class="contactStatus">Last login: 01/01/01 at 00:00</div>
					</div>
				</form>
			</div>
		</div>	
	</body>
</html>