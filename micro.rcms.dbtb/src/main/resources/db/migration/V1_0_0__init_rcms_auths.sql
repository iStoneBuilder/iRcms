-- 企业表 done
CREATE TABLE IF NOT EXISTS `tpl_enterprise_t`(
    `parent_id`      VARCHAR(100) NOT NULL  COMMENT '父节点ID，为空表示根节点',
    `id`      VARCHAR(100) NOT NULL  COMMENT '企业ID',
    `code`    VARCHAR(100) NOT NULL COMMENT '企业编码',
    `name`    VARCHAR(100) NOT NULL COMMENT '企业名称',
    `type`   VARCHAR(30) NOT NULL COMMENT '企业类型:platform/enterprise/merchant',
    `principal`    VARCHAR(100)  COMMENT '企业负责人',
    `email`    VARCHAR(100)  COMMENT '邮箱',
    `phone`    VARCHAR(30)  COMMENT '电话',
    `remark`    VARCHAR(100) COMMENT '备注',
    `sort`        INT(11) NOT NULL COMMENT '排序',
    `level`        INT(11) NOT NULL COMMENT '层级',
    `status`    varchar(2) NOT NULL COMMENT '状态Y/N' default 'Y',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- 角色表 done
CREATE TABLE IF NOT EXISTS `tpl_role_t`(
    `enterprise_id`      bigint NOT NULL COMMENT '企业ID',
    `parent_id`    bigint NOT NULL COMMENT '父节点ID，为空表示根节点',
    `id`    int NOT NULL COMMENT '角色ID',
    `code`    VARCHAR(100) NOT NULL COMMENT '角色CODE',
    `name`    VARCHAR(100) NOT NULL COMMENT '角色名',
    `DESCRIPTION`    VARCHAR(100) NOT NULL COMMENT '描述',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`code`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- 权限表 done
CREATE TABLE IF NOT EXISTS `tpl_permission_t`(
    `api_code`    VARCHAR(100) NOT NULL  COMMENT '接口编码',
    `auth_code`    VARCHAR(100)  COMMENT '权限编码',
    `api_path`    VARCHAR(100) NOT NULL COMMENT '接口地址',
    `api_name`    VARCHAR(100) NOT NULL  COMMENT '接口名称',
    `api_type`    VARCHAR(100) NOT NULL COMMENT '接口类型：system/custom',
    `api_method`    VARCHAR(100) NOT NULL COMMENT '请求方式',
    `open_api`    VARCHAR(100)  COMMENT '是否为openApi，程序账户授权时可选',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`api_code`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- 角色权限关系表 done
CREATE TABLE IF NOT EXISTS `tpl_role_permission_t`(
    `role_code`    VARCHAR(100) NOT NULL COMMENT '角色编码',
    `auth_code`    VARCHAR(100) NOT NULL COMMENT '权限编码',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`role_code`,`auth_code`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- 账户表 done
CREATE TABLE IF NOT EXISTS `tpl_account_t`(
   `enterprise_id`      bigint NOT NULL COMMENT '企业ID',
    `account_code`    VARCHAR(100) NOT NULL COMMENT '账户登录',
    `account_name`    VARCHAR(100) NOT NULL COMMENT '账户名称',
    `account_type`    VARCHAR(100) NOT NULL COMMENT '账户类型：user/app',
    `password`    VARCHAR(100) NOT NULL COMMENT '账户密码',
    `status`    VARCHAR(10) NOT NULL COMMENT '账户状态：Y/N',
    `account_roles`    VARCHAR(100) NOT NULL COMMENT '账户角色',
    `description`    VARCHAR(100)  COMMENT '备注',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`account_code`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;


