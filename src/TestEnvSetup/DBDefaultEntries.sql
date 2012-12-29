/*---------------------------------------------------*/
CALL addUser("loic","loic","ortola.loic@gmail.com","0658008166","Loic","Ortola");
CALL addUser("adrien","adrien","adrien.rouquet@gmail.com","0606060606","Adrien","Rouquet");
CALL addUser("henri","henri","lahoud.henri@gmail.com","0606060606","Henri","Lahoud");
CALL addUser("john","john","john.doe@gmail.com","0606060606","John","Doe");
CALL addUser("jane","jane","jane.doe@gmail.com","0606060606","Jane","Doe");

/*---------------------------------------------------*/
CALL addContact(2,2);
CALL addContact(2,3);
CALL addContact(2,4);
CALL addContact(2,5);
CALL addContact(2,6);
CALL addContact(3,2);
CALL addContact(3,4);
CALL addContact(3,5);
CALL addContact(4,2);
CALL addContact(4,3);
CALL addContact(5,2);
CALL addContact(5,3);
CALL addContact(6,2);

/*---------------------------------------------------*/
CALL sendMessage(2,3,1,"Salut Adrien, dis moi tu te rappelles je crois qu on a un projet de fin dannee a faire");
CALL sendMessage(2,3,1,"Je sais plus trop pour quand c est mais ce serait cool qu on regarde... On est le 15 janvier quand meme");
CALL sendMessage(3,2,1,"T es con, c est demain la presentation. On est dans la merde je crois");
CALL sendMessage(2,3,1,"Faudrait peut etre faire quelques tests histoire de garder la tete haute tu vois...");
CALL sendMessage(3,2,1,"Je suis assez d'accord");
CALL sendMessage(4,2,1,"Ehh Lolo, je crois que j ai encore fait le con. Mon ordi marche plus.");
CALL sendMessage(2,4,1,"Et que veux tu que je fasse?");
CALL sendMessage(4,2,1,"Bonne question.");

