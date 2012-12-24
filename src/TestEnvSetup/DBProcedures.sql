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
DROP PROCEDURE IF EXISTS getContacts;
DELIMITER //
CREATE PROCEDURE getContacts
(
	IN pUserId INT
)
BEGIN

	SELECT * FROM users
	WHERE id IN 
	(
   		SELECT dstUserId FROM contacts
   		WHERE srcUserId = pUserId
   	);
   
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
DROP PROCEDURE IF EXISTS getUser;
DELIMITER //
CREATE PROCEDURE getUser
(
	IN pUserId INT
)
BEGIN

   SELECT * FROM users
   WHERE id = pUserId
   ;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getMessages;
DELIMITER //
CREATE PROCEDURE getMessages
(
	IN pSrcUserId INT,
	IN pDstUserId INT
)
BEGIN

   	SELECT * FROM messages 
   	WHERE 
   		(srcUserId = pSrcUserId AND dstUserId = pDstUserId)
   	OR
   		(dstUserId = pSrcUserId AND srcUserId = pDstUserId)
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
DROP PROCEDURE IF EXISTS addContact;
DELIMITER //
CREATE PROCEDURE addContact 
(
   IN pSrcUserId INT,
   IN pDstUserId INT
)
BEGIN

   DECLARE tempIdentity INT;
   INSERT INTO contacts
   (
      srcUserId,
      dstUserId
   ) 
   VALUES 
   (
      pSrcUserId,
      pDstUserId
   );

   SET tempIdentity = LAST_INSERT_ID();
   
   SELECT tempIdentity;
   
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS checkCredentials;
DELIMITER //
CREATE PROCEDURE checkCredentials 
(
   IN pLogin VARCHAR(255),
   IN pPassword VARCHAR(255)
)
BEGIN

   SELECT 
      CASE WHEN password = pPassword THEN TRUE ELSE FALSE END AS 'valid'
   FROM users 
   	WHERE 
   		login = pLogin
   	;
   
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getIdFromLogin;
DELIMITER //
CREATE PROCEDURE getIdFromLogin 
(
    IN pLogin VARCHAR(255)
)
BEGIN

    SELECT 
    	id
   	FROM users 
   	WHERE 
   		login = pLogin
   	;   
END //
DELIMITER ;