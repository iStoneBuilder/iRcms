-- 初始化平台企业数据
INSERT INTO `tpl_fram_enterprise_t` (`parent_id`,`id`, `name`, `tenant_id`, `sort`,`level`, `type`,`status`, `principal`, `phone`, `email`, `remark`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`) VALUES (0,100000000000, 'RCMS平台','100000000000', 1,1,'platform', 'Y','RCMS', '13265044565', '', '', NOW(), 'system',NOW(),'system');
-- 初始化平台账户数据
INSERT INTO `tpl_fram_account_t` (`tenant_id`,`enterprise_id`,`account_code`, `account_name`, `account_type`, `password`, `account_roles`, `status`,`CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`) VALUES ('100000000000','100000000000', 'superAdmin', '超级管理员', 'account', 'e10adc3949', 'platformAdmin', 'Y',NOW(), 'system',NOW(),'system');
-- 初始化平台角色数据
INSERT INTO `tpl_fram_role_t` (`tenant_id`,`enterprise_id`,`parent_id`, `id`, `code`, `name`, `description`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY`) VALUES (100000000000,100000000000, 0, 1, 'platformAdmin', '平台管理员', '平台管理员角色', NOW(), 'system',NOW(),'system');

-- 基础数据 (栏目配置)

-- 初始化平台管理员栏目权限
INSERT INTO tpl_fram_role_menu_t ( SELECT 'platformAdmin',mt.id,now(),'SYSTEM',now(),'SYSTEM' FROM tpl_fram_menu_t mt
WHERE mt.id NOT in(SELECT menu_id FROM tpl_fram_role_menu_t WHERE role_code = 'platformAdmin'));

-- 基础数据 (字典配置)
INSERT INTO tpl_fram_classify_t ( `CLASSIFY_CODE`, `CLASSIFY_NAME`, `DESCRIPTION`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY` )VALUES ( 'RCMS_SYS_LANGUAGE', '语言', '', '2024-11-22 21:49:00', 'superAdmin', '2024-11-22 21:49:00', 'superAdmin' );
INSERT INTO tpl_fram_item_t ( `CLASSIFY_CODE`, `ITEM_ID`, `ITEM_CODE`, `ITEM_NAME`, `LANGUAGE`, `ITEM_INDEX`, `IS_ENABLED`, `DESCRIPTION`, `ITEM_ATTR1`, `ITEM_ATTR2`, `ITEM_ATTR3`, `ITEM_ATTR4`, `ITEM_ATTR5`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY` ) VALUES ( 'RCMS_SYS_LANGUAGE', '7e5de89d-79a6-41c6-afd3-08402d7cbf0f', 'zh-CN', '中文', 'zh-CN', '1', 'Y', '', NULL, NULL, NULL, NULL, NULL, '2024-11-22 21:49:45', 'superAdmin', '2024-11-22 21:49:45', 'superAdmin' );
INSERT INTO tpl_fram_item_t ( `CLASSIFY_CODE`, `ITEM_ID`, `ITEM_CODE`, `ITEM_NAME`, `LANGUAGE`, `ITEM_INDEX`, `IS_ENABLED`, `DESCRIPTION`, `ITEM_ATTR1`, `ITEM_ATTR2`, `ITEM_ATTR3`, `ITEM_ATTR4`, `ITEM_ATTR5`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY` ) VALUES ( 'RCMS_SYS_LANGUAGE', '88d8a83f-6ee6-4346-a1c5-5497bb13cbb0', 'zh-CN', 'Chinese', 'en-US', '1', 'Y', '', NULL, NULL, NULL, NULL, NULL, '2024-11-22 21:51:01', 'superAdmin', '2024-11-22 21:51:01', 'superAdmin' );
INSERT INTO tpl_fram_item_t ( `CLASSIFY_CODE`, `ITEM_ID`, `ITEM_CODE`, `ITEM_NAME`, `LANGUAGE`, `ITEM_INDEX`, `IS_ENABLED`, `DESCRIPTION`, `ITEM_ATTR1`, `ITEM_ATTR2`, `ITEM_ATTR3`, `ITEM_ATTR4`, `ITEM_ATTR5`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY` ) VALUES ( 'RCMS_SYS_LANGUAGE', '9660a32b-7c4d-4a13-8f42-22fef623f571', 'en-US', 'English', 'en-US', '2', 'Y', '', NULL, NULL, NULL, NULL, NULL, '2024-11-22 21:51:33', 'superAdmin', '2024-11-22 21:51:33', 'superAdmin' );
INSERT INTO tpl_fram_item_t ( `CLASSIFY_CODE`, `ITEM_ID`, `ITEM_CODE`, `ITEM_NAME`, `LANGUAGE`, `ITEM_INDEX`, `IS_ENABLED`, `DESCRIPTION`, `ITEM_ATTR1`, `ITEM_ATTR2`, `ITEM_ATTR3`, `ITEM_ATTR4`, `ITEM_ATTR5`, `CREATED_TIME`, `CREATED_BY`, `UPDATED_TIME`, `UPDATED_BY` ) VALUES ( 'RCMS_SYS_LANGUAGE', 'f2d5065e-1dbb-45d6-b810-fb53bf5176ac', 'en-US', '英文', 'zh-CN', '2', 'Y', '', NULL, NULL, NULL, NULL, NULL, '2024-11-22 21:50:17', 'superAdmin', '2024-11-22 21:50:17', 'superAdmin' );

