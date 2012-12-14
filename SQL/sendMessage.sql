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