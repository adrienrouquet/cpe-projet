cpe-projet TODO
===============



JOYEUX NOEL
TROUDUCS!



===============
FINISHED

Par Adrien:
Ne pas rester sur la LoginServlet quand on est dans l'application :
  Utiliser un res.sendRedirect("url") a la place d'un rd.forward...
  Si vous voulez un point d'entrée unique à l'app, créer une nouvelle servlet.
--Fait par Loic: il n'existe pas de sendRedirect, j'ai donc cree la nouvelle servlet CoreServlet.
--Finalement c'est plus interessant car on fait passer l'utilisateur par des controles sur la servlet Login,
--puis un bon dispatch selon ce sur quoi il etait.
----Edit par Adrien: Le probleme n'est pas résolu : on arrive dans CoreServlet, on forward vers
----AccountServlet, puis AccountServlet forward vers ChatServlet...
------Fait par Adrien: CoreServlet ("/!") redirige vers AccountServlet. Apres le login, on est 
------redirigé vers ChatServlet...
------ATTENTION: Dans les formulaires, les actions correspondent à la servlet et plus à Core

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

21/12/2012 - Loic pour tout le monde:
-changer User: mettre getContacts (aujourdhui on a un getUsers pour le moment)
-Regarder si ca a un sens d'avoir un userManager
--Fait par Loic: j'ai laisse l'ancienne stored procedure de getUsers dans le code au cas ou

21/12/2012 - Loic:
Plug des fonctions de send message et de la liaison bd dans la websocket
Voir avec Adrien comment on lie tout ca.
--Fait par Loic: On a une version qui fonctionne au 24/12

===============
TODO


21/12/2012 - Loic pour tout le monde:
COMMENTER LE CODE en FRANCAIS (Et pas: ceci est une boucle)
Supprimer les anciens bouts de code inutiles




Par Adrien, Pour Adrien:
Plug websocket sur chatWindows.jsp
Afficher message dans la page (append after last div?), ou preparer un div en avance...
Envoyer la websocket et ensuite le MsgManager enverra le message.
--23/12/2012@0203 - Adrien
--Il manque le send du msgManager: pour l'instant le sender = receiver
----24/12/2012@0132 - Adrien
----Le sender n'est plus le receiver: je peux etablir une communication entre 2 contacts.
----PROBLEME: On recoit les msg quelque soit leur sender si on est sur une page chatWindow (meme si
----la chatWindow ouverte n'est pas celle de l'emeteur...) => Prevoir systeme de notification
----("Vous avez un message de ...") sauf si la chatWindow ouverte est celle de l'emeteur de msg.
----PROBLEME: Plantage lorsqu'on utilise MsgManager.sendMessage()...



24/12/2012 - Adrien pour tout le monde:
Est-il utile de garder 2 routers (ChatRouter et AccountRouter) qui ont la même fonction ?
--Loic: Je pense que oui, ca nous permet d'avoir un controleur par servlet, et d'eviter d'avoir des switch 
enormes dans le futur.


24/12/2012@0201 - Adrien
Problemes avec les websockets: pas possible de garder la connexion lors d'un changement de page
(par exemple entre contactWindow et chatWindow). La solution sera de refondre l'interface pour
eviter les changement de page en faisant par exemple des requete JS (cf websocket.js)
--Loic: On a resolu le pb, ca marche niquel avec un systeme hybride webSocket / SSE (notifications)

24/12/2012@0201 - Adrien
Probleme d'interface: je suis en chat avec Loic et je recois un message d'Henry: comment faire la
notification ? Quelles infos doivent etre ajouté au message ? Intéret d'une URL qui renvoie vers la
fenetre de chat associer au contact qui m'envoie le nouveau message ?
--Loic: Alors pour l'instant on gere seulement les notifications de type "contact a recu ton message" 
et "contact t'a envoye un novueau message" mais sur la page contactWindow.

24/12/2012@0144 - Adrien
pas de liste des utilisateurs connectés ?? utilisation de Usermanager pour ca ?
--Loic: Oui j'y pensais, on va en avoir besoin pour le status. Je sais meme pas si on en a besoin, 
je pense qu'on devrait gerer ca avec une webSocket. D'ailleurs je voudrais t'en parler au tel ou sur google, 
savoir comment tu vois les choses.
Soit on decide de faire davantage de controles dans le code, soit on decide de s'affranchir des managers pour se concentrer
sur les websocket. Tu fais souvent un parcours des websocket pour savoir si tu as une websocket du user en question, (
cf webSocketServlet getsocket), ca peut etre notre test non? Si on a une socket avec srcUserId = celuiquoncherche alors il est connecte.


