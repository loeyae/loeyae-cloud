-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 2020-08-04 07:38:47
-- 服务器版本： 5.7.19
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `service_message`
--

-- --------------------------------------------------------

--
-- 表的结构 `producer`
--

CREATE TABLE IF NOT EXISTS `producer` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `service` varchar(32) NOT NULL COMMENT '服务名称',
  `action` varchar(32) NOT NULL COMMENT '事件名称',
  `definition` json NOT NULL COMMENT '消息体结构',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE `uk_service_action` ( `service`, `action`),
  INDEX `idx_status` ( `status`),
  INDEX `idx_delete` ( `deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生产者';

-- --------------------------------------------------------

--
-- 表的结构 `consumer`
--

CREATE TABLE IF NOT EXISTS `consumer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
  `service` VARCHAR(32) NOT NULL COMMENT '消费者服务名称' ,
  `target` INT(32) NOT NULL COMMENT '消费的生产者id' ,
  `status` TINYINT NOT NULL DEFAULT '1' COMMENT '状态' ,
  `deleted` INT NOT NULL DEFAULT '0' COMMENT '是否删除' ,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间' ,
  PRIMARY KEY (`id`), INDEX `idx_status` (`status`), INDEX `idx_deleted` (`deleted`),
  UNIQUE `uk_service_target` (`service`, `target`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '消费者';
COMMIT;
