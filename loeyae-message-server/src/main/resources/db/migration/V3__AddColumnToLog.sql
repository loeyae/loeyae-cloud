ALTER TABLE `consumer_logger` ADD `retries` TINYINT NOT NULL DEFAULT '0' COMMENT '重试次数' AFTER `status`;
ALTER TABLE `consumer_logger` ADD `original` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '源LOG ID' AFTER `retries`;
COMMIT;