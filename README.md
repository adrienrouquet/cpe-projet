cpe-projet
==========

Ne pas rester sur la LoginServlet quand on est l'application :
  Utiliser un res.sendRedirect("url") a la place d'un rd.forward...
  Si vous voulez un point d'entrée unique à l'app, créer une nouvelle servlet.
  
Dans chatWindow.jsp, enlever le code de formation des messages, les deplacer dans des jsp déparés
(incomingMessage.jsp et outcomingMessage.jsp) et utiliser <jsp:include... 