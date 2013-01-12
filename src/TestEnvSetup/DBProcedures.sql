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

   SELECT LAST_INSERT_ID() AS id;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS userExists;
DELIMITER //
CREATE PROCEDURE userExists
(
	IN pEmail VARCHAR(255),
	IN pPhone VARCHAR(255),
	IN pLogin VARCHAR(255)
)
BEGIN
	
   SELECT SUM(CASE WHEN pLogin = login THEN 1 ELSE 0 END) as loginExists, SUM(CASE WHEN pPhone = phone THEN 1 ELSE 0 END) as phoneExists, SUM(CASE WHEN pEmail = email THEN 1 ELSE 0 END) as emailExists
   FROM users 
   WHERE pLogin = login OR pEmail = email OR pPhone = phone
   ;
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
DROP PROCEDURE IF EXISTS findContacts;
DELIMITER //
CREATE PROCEDURE findContacts
(
	IN pUserId INT,	
	IN pSearchString VARCHAR(255)
)
BEGIN
	DECLARE searchString VARCHAR(255);
	SET searchString = CONCAT('%', LOWER(pSearchString), '%');
	SELECT * FROM users
	WHERE 
		(
			CONCAT(LOWER(firstName), ' ', LOWER(lastName)) LIKE searchString
			OR
				login LIKE searchString
			OR
				email LIKE searchString
			OR
				phone LIKE searchString
		)
		AND NOT pSearchString = ""
		AND id NOT IN (SELECT dstUserId FROM contacts WHERE srcUserId = pUserId AND approvalStatus = 1)
		AND NOT id = pUserId
	;
   
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

	SELECT users.*, contacts.approvalStatus AS approvalStatus FROM contacts
	INNER JOIN users ON users.id = contacts.srcUserId
	WHERE 
		contacts.dstUserId = pUserId
	AND
		contacts.srcUserId IN (SELECT dstUserId FROM contacts WHERE srcUserId = pUserId AND approvalStatus = 1)
	ORDER BY users.firstName ASC
	;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS hasApprovedContact;
DELIMITER //
CREATE PROCEDURE hasApprovedContact
(
	IN pSrcUserId INT,
	IN pDstUserId INT
)
BEGIN

	SELECT CASE WHEN COUNT(*) > 0 THEN 1 ELSE 0 END AS hasApprovedContact FROM contacts
	WHERE 
		srcUserId = pSrcUserId
	AND
		dstUserId = pDstUserId
	AND
		approvalStatus = 1
	;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getContactRequests;
DELIMITER //
CREATE PROCEDURE getContactRequests
(
	IN pUserId INT
)
BEGIN

	SELECT * FROM users
	WHERE id IN 
	(
   		SELECT dstUserId FROM contacts
   		WHERE srcUserId = pUserId AND approvalStatus = 0
   	);
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getContactRequestsCount;
DELIMITER //
CREATE PROCEDURE getContactRequestsCount
(
	IN pUserId INT
)
BEGIN

	SELECT COUNT(*) as contactRequestsCount FROM contacts
   	WHERE srcUserId = pUserId AND approvalStatus = 0
   	;
   
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
DROP PROCEDURE IF EXISTS getLogin;
DELIMITER //
CREATE PROCEDURE getLogin
(
	IN pUserId INT
)
BEGIN

   	SELECT login FROM users 
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
DROP PROCEDURE IF EXISTS updateUserLastLogin;
DELIMITER //
CREATE PROCEDURE updateUserLastLogin 
(
   IN pUserId INT
)
BEGIN

   UPDATE users SET lastLoginDate = NOW()
   WHERE id = pUserId
   ;
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

	IF pSrcUserId NOT IN (SELECT srcUserId FROM contacts WHERE dstUserId = pDstUserId AND srcUserId = pSrcUserId)
	THEN
		INSERT INTO contacts
	   (
	      srcUserId,
	      dstUserId,
	      approvalStatus
	   ) 
	   VALUES 
	   (
	      pSrcUserId,
	      pDstUserId,
	      1
	   );	
   ELSE
   		UPDATE contacts SET approvalStatus = 1 WHERE srcUserId = pSrcUserId AND dstUserId = pDstUserId;
   END IF;
   IF pSrcUserId NOT IN (SELECT dstUserId FROM contacts WHERE srcUserId = pDstUserId AND dstUserId = pSrcUserId)
   THEN
      INSERT INTO contacts
	   (
	      srcUserId,
	      dstUserId,	      
	      approvalStatus
	   ) 
	   VALUES 
	   (
	      pDstUserId,
	      pSrcUserId,
	      0
	   );
	END IF;
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS deleteContact;
DELIMITER //
CREATE PROCEDURE deleteContact 
(
   IN pSrcUserId INT,
   IN pDstUserId INT
)
BEGIN
	
	UPDATE contacts
	SET
		approvalStatus = 2
	WHERE
		srcUserId = pSrcUserId AND dstUserId = pDstUserId
	;
		
	IF pSrcUserId IN (SELECT dstUserId FROM contacts WHERE srcUserId = pDstUserId AND dstUserId = pSrcUserId AND approvalStatus = 2)
	THEN
		DELETE FROM contacts WHERE srcUserId = pSrcUserId AND dstUserId = pDstUserId;
		DELETE FROM contacts WHERE srcUserId = pDstUserId AND dstUserId = pSrcUserId;
		DELETE FROM messages WHERE srcUserId = pSrcUserId AND dstUserId = pDstUserId;
		DELETE FROM messages WHERE srcUserId = pDstUserId AND dstUserId = pSrcUserId;
	END IF;

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

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS setMessageDelivered;
DELIMITER //
CREATE PROCEDURE setMessageDelivered
(
	IN pMsgId INT
)
BEGIN

   	UPDATE messages 
   	SET
   		isDelivered = true
   	WHERE
   		id = pMsgId
   	;
   
END //
DELIMITER ;

/*---------------------------------------------------*/
DROP PROCEDURE IF EXISTS getNonDeliveredMessageCount;
DELIMITER //
CREATE PROCEDURE getNonDeliveredMessageCount 
(
   IN pSrcUserId INT,
   IN pDstUserId INT
)
BEGIN

   	SELECT COUNT(*) AS count FROM messages
   	WHERE
   	srcUserId = pDstUserId AND dstUserId = pSrcUserId AND isDelivered = false
    ;
   
END //
DELIMITER ;
