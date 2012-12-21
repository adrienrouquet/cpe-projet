
/*---------------------------------------------------*/
/*---------------------------------------------------*/
/*---------------------------------------------------*/

/*---------------------------------------------------*/
DROP TABLE IF EXISTS `messages`;
CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `srcUserId` int(11) NOT NULL,
  `dstUserId` int(11) NOT NULL,
  `contentTypeId` int(11) NOT NULL,
  `content` text NOT NULL,
  `sentDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isDelivered` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

/*---------------------------------------------------*/
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `createdDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastLoginDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

/*---------------------------------------------------*/
/*---------------------------------------------------*/
/*---------------------------------------------------*/

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS sendMessage;
DELIMITER //
CREATE PROCEDURE sendMessage 
(
   IN pSrcUserId INT(11),
   IN pDstUserId INT(11),
   IN pContentTypeId INT(11),
   IN pContent TEXT
)
BEGIN

   INSERT INTO messages
   (
      srcUserId, 
      dstUserId, 
      contentTypeId, 
      content
   ) 
   VALUES 
   (
      pSrcUserId,
      pDstUserId,
      pContentTypeId,
      pContent
   );

   SELECT LAST_INSERT_ID() AS 'msgId';
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getUser;
DELIMITER //
CREATE PROCEDURE getUser 
(
	IN pUserId INT
)
BEGIN
	
   SELECT 
   *
   FROM users 
   WHERE id = pUserId;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getUsers;
DELIMITER //
CREATE PROCEDURE getUsers
(
)
BEGIN

   SELECT * FROM users;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getMessages;
DELIMITER //
CREATE PROCEDURE getMessages
(
	IN pUserId INT
)
BEGIN

   	SELECT * FROM messages 
   	WHERE 
   		srcUserId = pUserId 
   	OR
   		dstUserId = pUserId
   	ORDER BY sentDate
   	;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getName;
DELIMITER //
CREATE PROCEDURE getName
(
	IN pUserId INT
)
BEGIN

   	SELECT CONCAT(firstName,' ',lastName) AS name FROM users 
   	WHERE 
   		id = pUserId 
   	;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getLastLogin;
DELIMITER //
CREATE PROCEDURE getLastLogin
(
	IN pUserId INT
)
BEGIN

   	SELECT lastLoginDate FROM users 
   	WHERE 
   		id = pUserId 
   	;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS addUser;
DELIMITER //
CREATE PROCEDURE addUser 
(
   IN pLogin VARCHAR(255),
   IN pPassword VARCHAR(255),
   IN pEmail VARCHAR(255),
   IN pPhone VARCHAR(255),
   IN pFirstName VARCHAR(255),
   IN pLastName VARCHAR(255)
)
BEGIN

   DECLARE tempIdentity INT;
   INSERT INTO users
   (
      login,
      password,
      email,
      phone,
      firstName,
      lastName
   ) 
   VALUES 
   (
      pLogin,
      pPassword,
      pEmail,
      pPhone,
      pFirstName,
      pLastName
   );

   SET tempIdentity = LAST_INSERT_ID();
   
   UPDATE users SET lastLoginDate = NOW();
   
   SELECT tempIdentity;
   
   
END //
DELIMITER ;

/*---------------------------------------------------*/
/*---------------------------------------------------*/
/*---------------------------------------------------*/

/*---------------------------------------------------*/
CALL addUser("loic","loic","ortola.loic@gmail.com","0658008166","Loic","Ortola");
CALL addUser("adrien","adrien","adrien.rouquet@gmail.com","0606060606","Adrien","Rouquet");
CALL addUser("henri","henri","lahoud.henri@gmail.com","0606060606","Henri","Lahoud");
CALL addUser("john","john","john.doe@gmail.com","0606060606","John","Doe");
CALL addUser("jane","jane","jane.doe@gmail.com","0606060606","Jane","Doe");

/*---------------------------------------------------*/
CALL sendMessage(2,3,1,"Salut Adrien, dis moi tu te rappelles je crois qu on a un projet de fin dannee a faire");
CALL sendMessage(2,3,1,"Je sais plus trop pour quand c est mais ce serait cool qu on regarde... On est le 15 janvier quand meme");
CALL sendMessage(3,2,1,"T es con, c est demain la presentation. On est dans la merde je crois");
CALL sendMessage(2,3,1,"Faudrait peut etre faire quelques tests histoire de garder la tete haute tu vois...");
CALL sendMessage(3,2,1,"Je suis assez d'accord");
CALL sendMessage(4,2,1,"Ehh Lolo, je crois que j ai encore fait le con. Mon ordi marche plus.");
CALL sendMessage(2,4,1,"Et que veux tu que je fasse?");
CALL sendMessage(4,2,1,"Bonne question.");

