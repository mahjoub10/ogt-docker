
-- create table use --

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activated` bit(1) NOT NULL,
  `authority` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `first_name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `lang_key` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `login` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY  (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;