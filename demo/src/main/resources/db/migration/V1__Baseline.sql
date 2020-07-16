-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： 127.0.0.1:3306
-- 生成日期： 2020-07-16 11:48:54
-- 服务器版本： 5.7.26
-- PHP 版本： 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- 数据库： `springboot_demo`
--

-- --------------------------------------------------------

--
-- 表的结构 `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `wallet` decimal(10,2) UNSIGNED NOT NULL DEFAULT '0.00' COMMENT '钱包',
  `earning` double(10,2) NOT NULL DEFAULT '0.00' COMMENT '收入',
  `box` float(5,2) NOT NULL DEFAULT '0.00' COMMENT 'box',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态',
  `source` text NOT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
