/*---------------------------------------------------*/
CALL addUser("loic","loic","ortola.loic@gmail.com","0658008166","Loïc","Ortola");
CALL addUser("adrien","adrien","adrien.rouquet@gmail.com","0606060606","Adrien","Rouquet");
CALL addUser("henri","henri","lahoud.henri@gmail.com","0606060606","Henri","Lahoud");
CALL addUser("john","john","john.doe@gmail.com","0606060606","John","Doe");
CALL addUser("jane","jane","jane.doe@gmail.com","0606060607","Jane","Doe");
CALL addUser("jude","jude","jude.doe@gmail.com","0606060608","Jude","Doe");
CALL addUser("jacquy","azerty","ja@gmail.com","0606060609","Jacques","Appar");
CALL addUser("jac32","azerty","jsaray@gmail.com","0606060609","Jacques","Sellaire");
CALL addUser("jacoeil","azerty","jcoeil@gmail.com","0696000609","Jacques","Oeil");
CALL addUser("jachoste","azerty","jchoste@gmail.com","0606890609","Jacques","Hoste");
CALL addUser("jachyrra","azerty","jkirra@gmail.com","0606890119","Jacques","Hyrra");


/*---------------------------------------------------*/
//CALL addContact(2,2);
CALL addContact(2,3);
CALL addContact(2,5);
CALL addContact(2,6);
CALL addContact(2,7);
CALL addContact(2,8);
CALL addContact(2,9);
CALL addContact(2,10);
CALL addContact(2,11);
CALL addContact(2,12);
CALL addContact(7,2);
CALL addContact(8,2);
CALL addContact(9,2);
CALL addContact(10,2);
CALL addContact(11,2);
CALL addContact(12,2);
CALL addContact(3,2);
CALL addContact(3,4);
CALL addContact(3,5);
CALL addContact(4,2);
CALL addContact(4,3);
CALL addContact(5,3);
CALL addContact(6,2);
CALL addContact(5,4);

/*---------------------------------------------------*/
CALL sendMessage(2,3,1,"Salut Adrien, dis moi tu te rappelles je crois qu on a un projet de fin dannee a faire");
CALL sendMessage(2,3,1,"Je sais plus trop pour quand c est mais ce serait cool qu on regarde... On est le 15 janvier quand meme");
CALL sendMessage(3,2,1,"T es con, c est demain la presentation. On est dans la merde je crois");
CALL sendMessage(2,3,1,"Faudrait peut etre faire quelques tests histoire de garder la tete haute tu vois...");
CALL sendMessage(3,2,1,"Je suis assez d'accord");
CALL sendMessage(4,2,1,"Ehh Lolo, je crois que j ai encore fait le con. Mon ordi marche plus.");
CALL sendMessage(2,4,1,"Et que veux tu que je fasse?");
CALL sendMessage(4,2,1,"Bonne question.");

