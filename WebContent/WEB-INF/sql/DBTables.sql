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
  PRIMARY KEY (`id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`srcUserId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`dstUserId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE IF NOT EXISTS `contacts` (
  `srcUserId` int(11) NOT NULL,
  `dstUserId` int(11) NOT NULL,
  `approvalStatus` int(2) NOT NULL DEFAULT '0',
  KEY `contacts_ibfk_1` (`srcUserId`),
  KEY `contacts_ibfk_2` (`dstUserId`),
  CONSTRAINT `contacts_ibfk_2` FOREIGN KEY (`dstUserId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `contacts_ibfk_1` FOREIGN KEY (`srcUserId`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
