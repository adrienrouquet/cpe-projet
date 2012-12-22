cpe-projet TODO
===============


===============
FINISHED
Par Adrien:
Dans chatWindow.jsp, enlever le code de formation des messages, les deplacer dans des jsp déparés
(incomingMessage.jsp et outcomingMessage.jsp) et utiliser <jsp:include...
--Fait par Loic

Par Loic pour Loic:
Try catch Finally: fermer la connexion a chaque fois!
--Fait par Loic

Par Loic pour Loic:
Scinder les fichiers DB.sql selon le contenu (procedure, tables, entrees)
--Fait par Loic

Par Adrien:
Ne pas rester sur la LoginServlet quand on est dans l'application :
  Utiliser un res.sendRedirect("url") a la place d'un rd.forward...
  Si vous voulez un point d'entrée unique à l'app, créer une nouvelle servlet.
--Fait par Loic: il n'existe pas de sendRedirect, j'ai donc cree la nouvelle servlet CoreServlet.
--Finalement c'est plus interessant car on fait passer l'utilisateur par des controles sur la servlet Login,
--puis un bon dispatch selon ce sur quoi il etait.

Par Adrien:
Créer un MessageBean quand on entre dans chatWindow.jsp et initialiser les IDs sender et receiver.
--Fait par Loic: C'est un msgManagerBean qui set tout ca maintenant.

Par Loic:
On a redefini le sens des Beans, il faut donc bouger des methodes/supprimer des attributs:
-supprimer attribut contactId de ChatRouter, car on va definir un MsgManager
-changer Msg: c'est juste une classe, et non plus un bean, par contre il doit etre gere entierement par
msgManager
--Fait par Loic: Ca fonctionne et c'est beaucoup mieux comme ca

Par Loic pour Henri:
Preparer un meilleur squelette HTML/CSS
Changer les headers pour HTML5, et faire des meilleurs include pour limiter les changements de code.
(nom de page principale = nom du dossier)
--Fait par Henri et Loic: Voila! HTML 5 et bons includes

===============
TODO

Par Loic pour tout le monde:
COMMENTER LE CODE en FRANCAIS (Et pas: ceci est une boucle)
Supprimer les anciens bouts de code inutiles


Par Adrien, Pour Adrien:
Plug websocket sur chatWindows.jsp
Afficher message dans la page (append after last div?), ou preparer un div en avance...
Envoyer la websocket et ensuite le MsgManager enverra le message.

Par Loic:
Plug des fonctions de send message et de la liaison bd dans la websocket
Voir avec Adrien comment on lie tout ca.

Par Loic pour tout le monde:
-changer User: mettre getContacts (aujourdhui on a un getUsers pour le moment)
-Regarder si ca a un sens d'avoir un userManager
@LOIC: IM ON IT

