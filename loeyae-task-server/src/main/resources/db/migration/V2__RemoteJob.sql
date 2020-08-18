
-- --------------------------------------------------------

--
-- 表的结构 `actuality_job`
--
CREATE TABLE `actuality_job` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
    `job_name` CHAR(36) NOT NULL COMMENT '任务名称' ,
    `status` TINYINT NOT NULL DEFAULT '0' COMMENT '状态，0：初始化，1：激活，2：暂停，3：取消' ,
    `service` VARCHAR(64) NOT NULL COMMENT '服务名称' ,
    `url` VARCHAR(255) NOT NULL COMMENT '任务URL' ,
    `method` VARCHAR(10) NOT NULL COMMENT '请求模式' ,
    `parameter` JSON NOT NULL COMMENT '请求参数' ,
    `cron` VARCHAR(64) NOT NULL COMMENT '计划' ,
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_time` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
     PRIMARY KEY (`id`),
     UNIQUE `uk_name` (`job_name`)
 ) ENGINE = InnoDB CHARSET=utf8mb4 COLLATE utf8mb4_general_ci COMMENT = '真实任务';


-- --------------------------------------------------------

--
-- 表的结构 `job_execute_log`
--
CREATE TABLE IF NOT EXISTS `job_execute_log` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `job_id` int(11) NOT NULL COMMENT '任务ID',
  `success` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否执行成功',
  `retries` tinyint(4) NOT NULL DEFAULT '0' COMMENT '重试次数',
  `original` bigint(20) NOT NULL DEFAULT '0' COMMENT '源log id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_jod_id` (`job_id`),
  KEY `idx_original` (`original`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='任务执行log';

COMMIT;