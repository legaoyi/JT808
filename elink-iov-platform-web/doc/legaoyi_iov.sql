
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` varchar(32) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `sim_code` varchar(12) NOT NULL,
  `auth_code` varchar(16) DEFAULT NULL,
  `state` smallint(6) DEFAULT NULL,
  `last_online_time` bigint(20) DEFAULT NULL,
  `last_offline_time` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `device_unique_index_sim_code` (`sim_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('cc3e5099fa4547948bdb4d2b78019285', '测试00001', '013800000000', '123456', '1', '1691649813230', null, '2023-08-04 16:20:38');

-- ----------------------------
-- Table structure for `gps`
-- ----------------------------
DROP TABLE IF EXISTS `gps`;
CREATE TABLE `gps` (
  `id` varchar(32) NOT NULL,
  `sim_code` varchar(12) NOT NULL,
  `gps_time` bigint(20) NOT NULL,
  `gps_info` text NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gps
-- ----------------------------
INSERT INTO `gps` VALUES ('e4755eaa47e64266a27ee8b031bf6b3c', '013800000000', '1691649842000', '{\"alarm\":0,\"state\":2,\"lng\":121.177961,\"lat\":31.150808,\"altitude\":0,\"speed\":0.0,\"direction\":231,\"time\":1691649842000,\"mileage\":2706.1,\"oilmass\":0.0,\"dvrSpeed\":0.0}', '2023-08-10 14:44:03');
