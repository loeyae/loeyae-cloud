
-- --------------------------------------------------------

--
-- 表的结构 `producer_logger`
--

CREATE TABLE IF NOT EXISTS `producer_logger` (
 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
 `producer` INT NOT NULL DEFAULT '0' COMMENT 'producer ID' ,
 `service` VARCHAR(32) NOT NULL COMMENT 'service name' ,
 `action` VARCHAR(32) NOT NULL COMMENT 'action name' ,
 `message` JSON NOT NULL COMMENT 'message' ,
 `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
 `modify_time` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
 PRIMARY KEY (`id`),
 INDEX `idx_producer` (`producer`),
 INDEX `idex_service_action` (`service`, `action`),
 INDEX `idx_created` (`create_time`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '生产者日志';

-- --------------------------------------------------------

--
-- 表的结构 `consumer_logger`
--

CREATE TABLE IF NOT EXISTS  `consumer_logger` (
 `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
 `consumer` INT NOT NULL COMMENT '消费者ID' ,
 `service` VARCHAR(32) NOT NULL COMMENT '消费者服务名称' ,
 `producer` VARCHAR(32) NOT NULL COMMENT '生产者服务名称' ,
 `action` VARCHAR(32) NOT NULL COMMENT '生产者事件ID' ,
 `message` JSON NOT NULL COMMENT '消息' ,
 `status` TINYINT NOT NULL default '0' COMMENT '状态' ,
 `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
 `modify_time` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
 PRIMARY KEY (`id`),
 INDEX `idx_consumer` (`consumer`),
 INDEX `idx_producer_action` (`producer`, `action`)
) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_general_ci COMMENT = '消费者日志';