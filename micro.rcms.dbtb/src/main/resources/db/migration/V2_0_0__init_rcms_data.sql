-- 初始化平台企业数据
INSERT INTO `tpl_enterprise_t` (`parent_id`,id`, `name`, `code`,sort, `type`, `principal`, `phone`, `mail`, `remark`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`)
VALUES (100000,1000001, 'RCMS平台','10000000000000', 1,'platform', 'RCMS', '', '', '', NOW(), 'system',NOW(),'system');