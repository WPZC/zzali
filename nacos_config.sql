/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : nacos_config

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 24/11/2020 17:25:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (1, 'zz-gateway-router.yaml', 'DEFAULT_GROUP', 'spring:\r\n    cloud:\r\n        gateway:\r\n            routes:\r\n            - id: zz-jwt\r\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzjwt/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-test\r\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzztest/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-app\r\n              uri: lb://zz-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzapp/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider', '08235ef94a01721be4f22a757b453d36', '2020-09-09 05:52:47', '2020-09-09 05:52:47', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (2, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '[\r\n    {\r\n        \"resource\": \"/vzzjwt/token\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 1,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    },\r\n    {\r\n        \"resource\": \"/vzzapp/**\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 20,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    }\r\n]', '6dfbb100cc0878712453452af93c3566', '2020-09-09 05:53:07', '2020-09-09 05:53:07', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'json', NULL);
INSERT INTO `config_info` VALUES (4, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '[\r\n    {\r\n        \"resource\": \"/vzzjwt/token\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 1,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    },\r\n    {\r\n        \"resource\": \"/vzzapp/**\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 20,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    }\r\n]', '6dfbb100cc0878712453452af93c3566', '2020-09-09 06:23:13', '2020-09-09 06:23:13', NULL, '0:0:0:0:0:0:0:1', '', '18c8053c-9797-4f43-9d5f-06c77ea10222', NULL, NULL, NULL, 'json', NULL);
INSERT INTO `config_info` VALUES (5, 'zz-app-dev.yaml', 'DEFAULT_GROUP', 'spring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 200MB\r\n      max-request-size: 200MB\r\nlogging:\r\n  file:\r\n    path: /usr/local/project/swr/log/${spring.application.name}\r\n  config: classpath:logback-spring.xml\r\n\r\nsystem:\r\n  #fileUrl: /usr/local/nginx/html/swr/file/\r\n  fileUrl: D:\\work\\beiye\\wr\\wrfile\\swrfile\\\r\n  #accessUrl: http://139.159.184.129:9090/swrfile/\r\n  accessUrl: http://192.168.0.120:6570/swrfile/\r\n', '9312a8a468aaf7aef336e140df2df2e9', '2020-09-11 05:46:49', '2020-09-11 05:46:49', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (11, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nvzztest: /${api.version}/apiswr/vzztest/**\nvzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-test\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zztest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider', 'e68755e0f19f59c0551073b6f29c65b4', '2020-09-27 09:48:33', '2020-09-28 09:43:14', NULL, '0:0:0:0:0:0:0:1', '', '18c8053c-9797-4f43-9d5f-06c77ea10222', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (12, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', 'spring:\n  datasource:\n    name: db-base\n    url: jdbc:mysql://localhost:3306/cloudall?serverTimezone=GMT%2B8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver\n  jpa:\n    properties:\n      hibernate:\n        format_sql: true\n        show_sql: true\n    hibernate:\n      ddl-auto: update\n  main:\n    allow-bean-definition-overriding: true\ncustomer:\n  get: false\n  routes:\n    - /ac/**\n', 'c7a92fcb6a4ab872e9b3fcfd617b569b', '2020-09-27 09:48:33', '2020-09-27 09:48:33', NULL, '0:0:0:0:0:0:0:1', '', '18c8053c-9797-4f43-9d5f-06c77ea10222', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (13, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '[\r\n    {\r\n        \"resource\": \"/vzzjwt/token\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 1,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    },\r\n    {\r\n        \"resource\": \"/vzzapp/**\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 20,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    }\r\n]', '6dfbb100cc0878712453452af93c3566', '2020-10-12 02:06:54', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', '', '7551d42a-a041-4d51-8307-fa9d1c11f0b9', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (14, 'zz-app-server.yaml', 'DEFAULT_GROUP', 'spring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 200MB\r\n      max-request-size: 200MB\r\nlogging:\r\n  file:\r\n    path: D:\\work\\beiye\\swr\\log\\${spring.application.name}\r\n  config: classpath:logback-spring.xml\r\nribbon:\r\n  ReadTimeout: 210000\r\n  ConnectTimeout: 210000\r\nsystem:\r\n  #fileUrl: /usr/local/nginx/html/swr/file/\r\n  fileUrl: D:\\work\\beiye\\wr\\wrfile\\swrfile\\\r\n  #accessUrl: http://139.159.184.129:9090/swrfile/\r\n  accessUrl: http://192.168.0.120:6570/swrfile/\r\n', '19ba234c9dcc008bc4c459d488c7c456', '2020-10-12 02:06:54', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', '', '7551d42a-a041-4d51-8307-fa9d1c11f0b9', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (15, 'zz-jwt-server.yaml', 'DEFAULT_GROUP', 'logging:\r\n  file:\r\n    path: D:\\work\\beiye\\swr\\log\\${spring.application.name}\r\n  config: classpath:logback-spring.xml', '310480536269cdba09821d732faa0547', '2020-10-12 02:06:54', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', '', '7551d42a-a041-4d51-8307-fa9d1c11f0b9', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (16, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', 'spring:\r\n    cloud:\r\n        gateway:\r\n            routes:\r\n            - id: zz-jwt\r\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzjwt/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-test\r\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzztest/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-app\r\n              uri: lb://zz-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzapp/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-base-api\r\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzbaseapi/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\nlogging:\r\n  file:\r\n    path: D:\\work\\beiye\\swr\\log\\${spring.application.name}\r\n  config: classpath:logback-spring.xml', 'b271ac4f50fd6628d82496ff50957395', '2020-10-12 02:06:54', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', '', '7551d42a-a041-4d51-8307-fa9d1c11f0b9', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (17, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', 'customer:\r\n  get: false\r\n  routes:\r\n    - \'/ac/**\'', 'ba4a51b10ed818bc3b82f0486ac81765', '2020-10-12 02:06:54', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', '', '7551d42a-a041-4d51-8307-fa9d1c11f0b9', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (18, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '[\n    {\n        \"resource\": \"/vzzjwt/token\",\n        \"limitApp\": \"default\",\n        \"grade\": 1,\n        \"count\": 1,\n        \"strategy\": 0,\n        \"controlBehavior\": 0,\n        \"clusterMode\": false\n    },\n]', '9e23a40d27250d617402b23fe1e326ad', '2020-10-13 06:50:19', '2020-10-13 06:51:56', NULL, '0:0:0:0:0:0:0:1', '', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288', '', '', '', 'json', '');
INSERT INTO `config_info` VALUES (19, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nvzztest: /${api.version}/apiswr/vzztest/**\nvzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nzzswrapp: /${api.version}/apiswr/zzswrapp/**\nzzsuyuan: /${api.version}/apiswr/zzsuyuan/**\nzzpwhs: /${api.version}/apiswr/zzpwhs/**\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-test\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zztest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-swr-app\n              uri: lb://zz-swr-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzswrapp}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-suyuan\n              uri: lb://zz-suyuan #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzsuyuan}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-pwhs\n              uri: lb://zz-pwhs #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzpwhs}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider', '4c5c9ed3d5cba840c371a1a3347e4d83', '2020-10-13 06:50:19', '2020-10-14 01:24:28', NULL, '0:0:0:0:0:0:0:1', '', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (20, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', 'customer:\n  get: false\n  routes:\n    - /ac/**\n', '51f481d0d3a857bd5e6209918b7c6bf9', '2020-10-13 06:50:19', '2020-10-13 06:54:15', NULL, '0:0:0:0:0:0:0:1', '', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (23, 'zz-swr-app-server.yaml', 'DEFAULT_GROUP', 'customer:\r\n    routes:\r\n        - /**', '7d06978e0ba11802a212c1c3bb938474', '2020-10-13 08:00:30', '2020-10-13 08:00:30', NULL, '0:0:0:0:0:0:0:1', '', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (24, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '[\n    {\n        \"resource\": \"/vzzjwt/token\",\n        \"limitApp\": \"default\",\n        \"grade\": 1,\n        \"count\": 1,\n        \"strategy\": 0,\n        \"controlBehavior\": 0,\n        \"clusterMode\": false\n    },\n    {\n        \"resource\": \"/vzzbaseapi/**\",\n        \"limitApp\": \"default\",\n        \"grade\": 1,\n        \"count\": 5,\n        \"strategy\": 0,\n        \"controlBehavior\": 0,\n        \"clusterMode\": false\n    }\n]', '9fa7e6cfc5278c28157f6ecc0a5ae5e6', '2020-11-24 07:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `config_info` VALUES (25, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nzzswrapp: /${api.version}/apiswr/vzzswrapp/**\nzzlogserver: /${api.version}/apiswr/vzzlogserver/**\n#zzrhpkserver: /${api.version}/apiswr/vzzrhpkserver/**\n\n#测试区\nzzfiletest: /${api.version}/apiswr/zzfiletest/**\n\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-swr-app\n              uri: lb://zz-swr-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzswrapp}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-logserver\n              uri: lb://zz-logserver #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzlogserver}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            #- id: zz-rhpk-server\n            #  uri: lb://zz-rhpk-server #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n            #  predicates:             #断言\n            #  - Path=${zzrhpkserver}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n            #  filters:\n            #  - StripPrefix=3 #1为去掉path的/provider\n              \n\n            #测试区\n            - id: zz-file-test\n              uri: lb://zz-file-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzfiletest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            ', '3a298ebd990b292e6ee23eeec63b39e8', '2020-11-24 07:57:44', '2020-11-24 08:00:30', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', '', '', '', 'yaml', '');
INSERT INTO `config_info` VALUES (26, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', 'customer:\n  get: false\n  routes:\n    - /ac/**\n', '51f481d0d3a857bd5e6209918b7c6bf9', '2020-11-24 07:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (27, 'zz-swr-app-server.yaml', 'DEFAULT_GROUP', 'customer:\r\n    routes:\r\n        - /**', '7d06978e0ba11802a212c1c3bb938474', '2020-11-24 07:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (28, 'shared.yaml', 'dev', '#tomcat配置\nserver:\n  tomcat:\n    uri-encoding: UTF-8\nspring:\n  #开启aop\n  aop:\n    auto: true\n  #bean允许覆盖\n  main:\n    allow-bean-definition-overriding: true\n  #上传文件大小\n  servlet:\n    multipart:\n      max-file-size: 200MB\n      max-request-size: 200MB\n  #JPA配置打印sql\n  jpa:\n    properties:\n      hibernate:\n        format_sql: true\n        show_sql: true\n    hibernate:\n      ddl-auto: none\n#连接超时时间\nribbon:\n  ReadTimeout: 210000\n  ConnectTimeout: 210000\n#日志\nlogging:\n  file:\n    path: F:\\myself\\project\\alibabaclouddev\\log\\${spring.application.name}\n  config: classpath:logback-spring.xml', '46e152f92a41aaf99c89cccbf3c000c9', '2020-11-24 07:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (29, 'kafka-producer.yaml', 'dev', 'spring:\n  kafka:\n    bootstrap-servers: \n      - 192.168.0.10:9092\n      - 192.168.0.10:9093\n      - 192.168.0.10:9094\n    producer:\n      # 发生错误后，消息重发的次数。\n      retries: 0\n      #当有多个消息需要被发送到同一个分区时，生产者会把它们放在同一个批次里。该参数指定了一个批次可以使用的内存大小，按照字节数计算。\n      batch-size: 16384\n      # 设置生产者内存缓冲区的大小。\n      buffer-memory: 33554432\n      # 键的序列化方式\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      # 值的序列化方式\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n      # acks=0 ： 生产者在成功写入消息之前不会等待任何来自服务器的响应。\n      # acks=1 ： 只要集群的首领节点收到消息，生产者就会收到一个来自服务器成功响应。\n      # acks=all ：只有当所有参与复制的节点全部收到消息时，生产者才会收到一个来自服务器的成功响应。\n      acks: 0', 'f3eeb86e6cac46666f46f8a7f2d335dd', '2020-11-24 07:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', NULL, NULL, NULL, 'yaml', NULL);
INSERT INTO `config_info` VALUES (30, 'kafka-customer.yaml', 'dev', 'spring:\n  kafka:\n    bootstrap-servers: \n      - 192.168.0.10:9092\n      - 192.168.0.10:9093\n      - 192.168.0.10:9094\n    consumer:\n      # 自动提交的时间间隔 在spring boot 2.X 版本中这里采用的是值的类型为Duration 需要符合特定的格式，如1S,1M,2H,5D\n      auto-commit-interval: 1S\n      # 该属性指定了消费者在读取一个没有偏移量的分区或者偏移量无效的情况下该作何处理：\n      # latest（默认值）在偏移量无效的情况下，消费者将从最新的记录开始读取数据（在消费者启动之后生成的记录）\n      # earliest ：在偏移量无效的情况下，消费者将从起始位置读取分区的记录\n      auto-offset-reset: earliest\n      # 是否自动提交偏移量，默认值是true,为了避免出现重复数据和数据丢失，可以把它设置为false,然后手动提交偏移量\n      enable-auto-commit: false\n      # 键的反序列化方式\n      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer\n      # 值的反序列化方式\n      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer\n    listener:\n      # 在侦听器容器中运行的线程数。\n      concurrency: 5\n      #listner负责ack，每调用一次，就立即commit\n      ack-mode: manual_immediate\n      missing-topics-fatal: false\n', '192185f2d769818382c646cded64e126', '2020-11-24 07:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', '', '37b78898-9f3b-465c-ba15-57e9fadddb71', NULL, NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_aggr
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_beta
-- ----------------------------

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info_tag
-- ----------------------------

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_tags_relation
-- ----------------------------

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (0, 42, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"resource\": \"/vzzjwt/token\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 1,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    },\r\n    {\r\n        \"resource\": \"/vzzapp/**\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 20,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    }\r\n]', '6dfbb100cc0878712453452af93c3566', '2020-10-12 10:06:53', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', 'I', '7551d42a-a041-4d51-8307-fa9d1c11f0b9');
INSERT INTO `his_config_info` VALUES (0, 43, 'zz-app-server.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  servlet:\r\n    multipart:\r\n      max-file-size: 200MB\r\n      max-request-size: 200MB\r\nlogging:\r\n  file:\r\n    path: D:\\work\\beiye\\swr\\log\\${spring.application.name}\r\n  config: classpath:logback-spring.xml\r\nribbon:\r\n  ReadTimeout: 210000\r\n  ConnectTimeout: 210000\r\nsystem:\r\n  #fileUrl: /usr/local/nginx/html/swr/file/\r\n  fileUrl: D:\\work\\beiye\\wr\\wrfile\\swrfile\\\r\n  #accessUrl: http://139.159.184.129:9090/swrfile/\r\n  accessUrl: http://192.168.0.120:6570/swrfile/\r\n', '19ba234c9dcc008bc4c459d488c7c456', '2020-10-12 10:06:53', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', 'I', '7551d42a-a041-4d51-8307-fa9d1c11f0b9');
INSERT INTO `his_config_info` VALUES (0, 44, 'zz-jwt-server.yaml', 'DEFAULT_GROUP', '', 'logging:\r\n  file:\r\n    path: D:\\work\\beiye\\swr\\log\\${spring.application.name}\r\n  config: classpath:logback-spring.xml', '310480536269cdba09821d732faa0547', '2020-10-12 10:06:53', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', 'I', '7551d42a-a041-4d51-8307-fa9d1c11f0b9');
INSERT INTO `his_config_info` VALUES (0, 45, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'spring:\r\n    cloud:\r\n        gateway:\r\n            routes:\r\n            - id: zz-jwt\r\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzjwt/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-test\r\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzztest/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-app\r\n              uri: lb://zz-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzapp/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\n            - id: zz-base-api\r\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\r\n              predicates:             #断言\r\n              - Path=/apiswr/vzzbaseapi/**     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\r\n              filters:\r\n              - StripPrefix=2 #1为去掉path的/provider\r\nlogging:\r\n  file:\r\n    path: D:\\work\\beiye\\swr\\log\\${spring.application.name}\r\n  config: classpath:logback-spring.xml', 'b271ac4f50fd6628d82496ff50957395', '2020-10-12 10:06:53', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', 'I', '7551d42a-a041-4d51-8307-fa9d1c11f0b9');
INSERT INTO `his_config_info` VALUES (0, 46, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', '', 'customer:\r\n  get: false\r\n  routes:\r\n    - \'/ac/**\'', 'ba4a51b10ed818bc3b82f0486ac81765', '2020-10-12 10:06:53', '2020-10-12 02:06:54', NULL, '0:0:0:0:0:0:0:1', 'I', '7551d42a-a041-4d51-8307-fa9d1c11f0b9');
INSERT INTO `his_config_info` VALUES (0, 47, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"resource\": \"/vzzjwt/token\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 1,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    },\r\n    {\r\n        \"resource\": \"/vzzapp/**\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 20,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    }\r\n]', '6dfbb100cc0878712453452af93c3566', '2020-10-13 14:50:19', '2020-10-13 06:50:19', NULL, '0:0:0:0:0:0:0:1', 'I', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (0, 48, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nvzztest: /${api.version}/apiswr/vzztest/**\nvzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-test\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zztest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider', 'e68755e0f19f59c0551073b6f29c65b4', '2020-10-13 14:50:19', '2020-10-13 06:50:19', NULL, '0:0:0:0:0:0:0:1', 'I', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (0, 49, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    name: db-base\n    url: jdbc:mysql://localhost:3306/cloudall?serverTimezone=GMT%2B8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver\n  jpa:\n    properties:\n      hibernate:\n        format_sql: true\n        show_sql: true\n    hibernate:\n      ddl-auto: update\n  main:\n    allow-bean-definition-overriding: true\ncustomer:\n  get: false\n  routes:\n    - /ac/**\n', 'c7a92fcb6a4ab872e9b3fcfd617b569b', '2020-10-13 14:50:19', '2020-10-13 06:50:19', NULL, '0:0:0:0:0:0:0:1', 'I', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (18, 50, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"resource\": \"/vzzjwt/token\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 1,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    },\r\n    {\r\n        \"resource\": \"/vzzapp/**\",\r\n        \"limitApp\": \"default\",\r\n        \"grade\": 1,\r\n        \"count\": 20,\r\n        \"strategy\": 0,\r\n        \"controlBehavior\": 0,\r\n        \"clusterMode\": false\r\n    }\r\n]', '6dfbb100cc0878712453452af93c3566', '2020-10-13 14:51:56', '2020-10-13 06:51:56', NULL, '0:0:0:0:0:0:0:1', 'U', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (20, 51, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', '', 'spring:\n  datasource:\n    name: db-base\n    url: jdbc:mysql://localhost:3306/cloudall?serverTimezone=GMT%2B8\n    username: root\n    password: root\n    driver-class-name: com.mysql.cj.jdbc.Driver\n  jpa:\n    properties:\n      hibernate:\n        format_sql: true\n        show_sql: true\n    hibernate:\n      ddl-auto: update\n  main:\n    allow-bean-definition-overriding: true\ncustomer:\n  get: false\n  routes:\n    - /ac/**\n', 'c7a92fcb6a4ab872e9b3fcfd617b569b', '2020-10-13 14:54:14', '2020-10-13 06:54:15', NULL, '0:0:0:0:0:0:0:1', 'U', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (0, 52, 'zz-swr-app-server.yaml', 'DEFAULT_GROUP', '', 'customer:\r\n    routes:\r\n        - /**', '7d06978e0ba11802a212c1c3bb938474', '2020-10-13 16:00:29', '2020-10-13 08:00:30', NULL, '0:0:0:0:0:0:0:1', 'I', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (19, 53, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nvzztest: /${api.version}/apiswr/vzztest/**\nvzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-test\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zztest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider', 'e68755e0f19f59c0551073b6f29c65b4', '2020-10-13 16:06:17', '2020-10-13 08:06:17', NULL, '0:0:0:0:0:0:0:1', 'U', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (19, 54, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nvzztest: /${api.version}/apiswr/vzztest/**\nvzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nzzswrapp: /${api.version}/apiswr/zzswrapp/**\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-test\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zztest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-swr-app\n              uri: lb://zz-swr-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzswrapp}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider', '38fd0342b38f6e913a8c209d25e3e6fe', '2020-10-13 18:40:00', '2020-10-13 10:39:57', NULL, '0:0:0:0:0:0:0:1', 'U', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (19, 55, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nvzztest: /${api.version}/apiswr/vzztest/**\nvzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nzzswrapp: /${api.version}/apiswr/zzswrapp/**\nzzsuyuan: /${api.version}/apiswr/zzsuyuan/**\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-test\n              uri: lb://zz-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zztest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-swr-app\n              uri: lb://zz-swr-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzswrapp}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-suyuan\n              uri: lb://zz-suyuan #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzsuyuan}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider', '3ac2a59893b677f1622902f7195e1d04', '2020-10-14 09:24:35', '2020-10-14 01:24:28', NULL, '0:0:0:0:0:0:0:1', 'U', '09740bb3-4ee5-45f8-a5f2-f24d7cd96288');
INSERT INTO `his_config_info` VALUES (0, 56, 'zz-gateway-sentinel', 'DEFAULT_GROUP', '', '[\n    {\n        \"resource\": \"/vzzjwt/token\",\n        \"limitApp\": \"default\",\n        \"grade\": 1,\n        \"count\": 1,\n        \"strategy\": 0,\n        \"controlBehavior\": 0,\n        \"clusterMode\": false\n    },\n    {\n        \"resource\": \"/vzzbaseapi/**\",\n        \"limitApp\": \"default\",\n        \"grade\": 1,\n        \"count\": 5,\n        \"strategy\": 0,\n        \"controlBehavior\": 0,\n        \"clusterMode\": false\n    }\n]', '9fa7e6cfc5278c28157f6ecc0a5ae5e6', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (0, 57, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nzzswrapp: /${api.version}/apiswr/vzzswrapp/**\nzzrhoutlet: /${api.version}/apiswr/vzzrhoutlet/**\nzzsbmanage: /${api.version}/apiswr/vzzsbmanage/**\nzzlogserver: /${api.version}/apiswr/vzzlogserver/**\n#zzrhpkserver: /${api.version}/apiswr/vzzrhpkserver/**\n\n#测试区\nzzfiletest: /${api.version}/apiswr/zzfiletest/**\n\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-swr-app\n              uri: lb://zz-swr-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzswrapp}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-rhoutlet\n              uri: lb://zz-rhoutlet #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzrhoutlet}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-sbmanage\n              uri: lb://zz-sbmanage #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzsbmanage}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-logserver\n              uri: lb://zz-logserver #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzlogserver}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            #- id: zz-rhpk-server\n            #  uri: lb://zz-rhpk-server #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n            #  predicates:             #断言\n            #  - Path=${zzrhpkserver}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n            #  filters:\n            #  - StripPrefix=3 #1为去掉path的/provider\n              \n\n            #测试区\n            - id: zz-file-test\n              uri: lb://zz-file-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzfiletest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            ', '201d1de3daa356b366705e6085ec1ba4', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (0, 58, 'zz-base-api-server.yaml', 'DEFAULT_GROUP', '', 'customer:\n  get: false\n  routes:\n    - /ac/**\n', '51f481d0d3a857bd5e6209918b7c6bf9', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (0, 59, 'zz-swr-app-server.yaml', 'DEFAULT_GROUP', '', 'customer:\r\n    routes:\r\n        - /**', '7d06978e0ba11802a212c1c3bb938474', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (0, 60, 'shared.yaml', 'dev', '', '#tomcat配置\nserver:\n  tomcat:\n    uri-encoding: UTF-8\nspring:\n  #开启aop\n  aop:\n    auto: true\n  #bean允许覆盖\n  main:\n    allow-bean-definition-overriding: true\n  #上传文件大小\n  servlet:\n    multipart:\n      max-file-size: 200MB\n      max-request-size: 200MB\n  #JPA配置打印sql\n  jpa:\n    properties:\n      hibernate:\n        format_sql: true\n        show_sql: true\n    hibernate:\n      ddl-auto: none\n#连接超时时间\nribbon:\n  ReadTimeout: 210000\n  ConnectTimeout: 210000\n#日志\nlogging:\n  file:\n    path: F:\\myself\\project\\alibabaclouddev\\log\\${spring.application.name}\n  config: classpath:logback-spring.xml', '46e152f92a41aaf99c89cccbf3c000c9', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (0, 61, 'kafka-producer.yaml', 'dev', '', 'spring:\n  kafka:\n    bootstrap-servers: \n      - 192.168.0.10:9092\n      - 192.168.0.10:9093\n      - 192.168.0.10:9094\n    producer:\n      # 发生错误后，消息重发的次数。\n      retries: 0\n      #当有多个消息需要被发送到同一个分区时，生产者会把它们放在同一个批次里。该参数指定了一个批次可以使用的内存大小，按照字节数计算。\n      batch-size: 16384\n      # 设置生产者内存缓冲区的大小。\n      buffer-memory: 33554432\n      # 键的序列化方式\n      key-serializer: org.apache.kafka.common.serialization.StringSerializer\n      # 值的序列化方式\n      value-serializer: org.apache.kafka.common.serialization.StringSerializer\n      # acks=0 ： 生产者在成功写入消息之前不会等待任何来自服务器的响应。\n      # acks=1 ： 只要集群的首领节点收到消息，生产者就会收到一个来自服务器成功响应。\n      # acks=all ：只有当所有参与复制的节点全部收到消息时，生产者才会收到一个来自服务器的成功响应。\n      acks: 0', 'f3eeb86e6cac46666f46f8a7f2d335dd', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (0, 62, 'kafka-customer.yaml', 'dev', '', 'spring:\n  kafka:\n    bootstrap-servers: \n      - 192.168.0.10:9092\n      - 192.168.0.10:9093\n      - 192.168.0.10:9094\n    consumer:\n      # 自动提交的时间间隔 在spring boot 2.X 版本中这里采用的是值的类型为Duration 需要符合特定的格式，如1S,1M,2H,5D\n      auto-commit-interval: 1S\n      # 该属性指定了消费者在读取一个没有偏移量的分区或者偏移量无效的情况下该作何处理：\n      # latest（默认值）在偏移量无效的情况下，消费者将从最新的记录开始读取数据（在消费者启动之后生成的记录）\n      # earliest ：在偏移量无效的情况下，消费者将从起始位置读取分区的记录\n      auto-offset-reset: earliest\n      # 是否自动提交偏移量，默认值是true,为了避免出现重复数据和数据丢失，可以把它设置为false,然后手动提交偏移量\n      enable-auto-commit: false\n      # 键的反序列化方式\n      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer\n      # 值的反序列化方式\n      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer\n    listener:\n      # 在侦听器容器中运行的线程数。\n      concurrency: 5\n      #listner负责ack，每调用一次，就立即commit\n      ack-mode: manual_immediate\n      missing-topics-fatal: false\n', '192185f2d769818382c646cded64e126', '2020-11-24 15:57:44', '2020-11-24 07:57:44', NULL, '0:0:0:0:0:0:0:1', 'I', '37b78898-9f3b-465c-ba15-57e9fadddb71');
INSERT INTO `his_config_info` VALUES (25, 63, 'zz-gateway-server.yaml', 'DEFAULT_GROUP', '', 'api:\n  version: v1.0.0\n#貌似数组里不能带有.的东西，所以就写在这了\nzzjwt: /${api.version}/apiswr/vzzjwt/**\nzzbaseapi: /${api.version}/apiswr/vzzbaseapi/**\nzzswrapp: /${api.version}/apiswr/vzzswrapp/**\nzzrhoutlet: /${api.version}/apiswr/vzzrhoutlet/**\nzzsbmanage: /${api.version}/apiswr/vzzsbmanage/**\nzzlogserver: /${api.version}/apiswr/vzzlogserver/**\n#zzrhpkserver: /${api.version}/apiswr/vzzrhpkserver/**\n\n#测试区\nzzfiletest: /${api.version}/apiswr/zzfiletest/**\n\nspring:\n    cloud:\n        gateway:\n            routes:\n            - id: zz-jwt\n              uri: lb://zz-jwt #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzjwt}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-base-api\n              uri: lb://zz-base-api #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzbaseapi}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-swr-app\n              uri: lb://zz-swr-app #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzswrapp}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-rhoutlet\n              uri: lb://zz-rhoutlet #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzrhoutlet}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-sbmanage\n              uri: lb://zz-sbmanage #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzsbmanage}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            - id: zz-logserver\n              uri: lb://zz-logserver #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzlogserver}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            #- id: zz-rhpk-server\n            #  uri: lb://zz-rhpk-server #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n            #  predicates:             #断言\n            #  - Path=${zzrhpkserver}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n            #  filters:\n            #  - StripPrefix=3 #1为去掉path的/provider\n              \n\n            #测试区\n            - id: zz-file-test\n              uri: lb://zz-file-test #代表从注册中心获取服务，且以lb（负载均衡）的方式转发\n              predicates:             #断言\n              - Path=${zzfiletest}     #表示将以/vzz1/**开头的请求转发到uri为lb://zz-jwt的地址上\n              filters:\n              - StripPrefix=3 #1为去掉path的/provider\n            ', '201d1de3daa356b366705e6085ec1ba4', '2020-11-24 16:00:30', '2020-11-24 08:00:30', NULL, '0:0:0:0:0:0:0:1', 'U', '37b78898-9f3b-465c-ba15-57e9fadddb71');

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions`  (
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `action` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `uk_role_permission`(`role`, `resource`, `action`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permissions
-- ----------------------------

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  UNIQUE INDEX `idx_user_role`(`username`, `role`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_capacity
-- ----------------------------

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tenant_info
-- ----------------------------
INSERT INTO `tenant_info` VALUES (1, '1', '18c8053c-9797-4f43-9d5f-06c77ea10222', 'zzali', 'zzali自建项目，编写cloud alibaba微服务工具', 'nacos', 1599632216410, 1599632216410);
INSERT INTO `tenant_info` VALUES (4, '1', '37b78898-9f3b-465c-ba15-57e9fadddb71', 'zzali-2', '本地', 'nacos', 1603088074693, 1606204651401);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
