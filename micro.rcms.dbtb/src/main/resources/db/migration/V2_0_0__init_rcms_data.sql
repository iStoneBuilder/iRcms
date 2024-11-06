-- 初始化平台企业数据
INSERT INTO `tpl_enterprise_t` (`parent_id`,id`, `name`, `code`,sort, `type`,`status`, `principal`, `phone`, `mail`, `remark`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`)
VALUES (0,1000001, 'RCMS平台','10000000000000', 1,'platform', 1,'RCMS', '', '', '', NOW(), 'system',NOW(),'system');
-- 初始化平台账户数据
INSERT INTO `tpl_account_t` (enterprise_id`,`account_code`, `account_name`, `account_type`, `password`, `account_roles`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`)
VALUES (1000001, 'platformAdmin', '平台管理员', 'user', 'e10adc3949ba59abbe56e057f20f883e', 'platformAdmin', NOW(), 'system',NOW(),'system');
-- 初始化平台角色数据
INSERT INTO `tpl_role_t` (`enterprise_id`,`parent_id`, `id`, `code`, `name`, `description`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`)
VALUES (1000001, 0, 100000, 'platformAdmin', '平台管理员', '平台管理员角色', NOW(), 'system',NOW(),'system');