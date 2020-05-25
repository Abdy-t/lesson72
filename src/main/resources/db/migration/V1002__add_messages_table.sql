use `chat`;

CREATE TABLE `messages` (
                        `id` int auto_increment NOT NULL,
                        `text` varchar(140),
                        `user_id` int not null,
                        `date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
                       ) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_general_ci;
