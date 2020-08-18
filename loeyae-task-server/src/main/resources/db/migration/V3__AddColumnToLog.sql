ALTER TABLE `job_execute_log` ADD `result` TEXT NOT NULL COMMENT '执行结果' AFTER `original`;
COMMIT;