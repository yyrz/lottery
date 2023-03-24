CREATE TABLE `kuaileba` (
                            `id` bigint(20) NOT NULL COMMENT 'id',
                            `code` varchar(255) DEFAULT NULL,
                            `date` datetime DEFAULT NULL,
                            `week` varchar(255) DEFAULT NULL,
                            `red` varchar(1000) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;