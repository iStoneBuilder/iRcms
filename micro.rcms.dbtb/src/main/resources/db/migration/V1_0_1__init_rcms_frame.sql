--  国际化信息
CREATE TABLE IF NOT EXISTS `tpl_i18n_t`(
    `I18N_ID`      VARCHAR(100) NOT NULL COMMENT 'KEY',
    `I18N_KEY`    VARCHAR(200) NOT NULL COMMENT 'KEY',
    `I18N_NAME`   VARCHAR(1000)          NOT NULL COMMENT 'NAME',
    `LANGUAGE`   VARCHAR(1000)          NOT NULL COMMENT 'LANGUAGE',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`I18N_ID`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- LOOK UP CLASSIFY
CREATE TABLE IF NOT EXISTS `tpl_classify_t`(
    `CLASSIFY_CODE`    VARCHAR(200) NOT NULL COMMENT 'CLASSIFY_CODE',
    `CLASSIFY_NAME`   VARCHAR(200)          NOT NULL COMMENT 'CLASSIFY_NAME',
    `DESCRIPTION`   VARCHAR(1000)           COMMENT 'DESCRIPTION',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`CLASSIFY_CODE`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- LOOK UP ITEM
CREATE TABLE IF NOT EXISTS `tpl_item_t`(
 `CLASSIFY_CODE`    VARCHAR(200) NOT NULL COMMENT 'CLASSIFY_CODE',
    `ITEM_ID`    VARCHAR(100) NOT NULL COMMENT '唯一ID',
    `ITEM_CODE`    VARCHAR(200) NOT NULL COMMENT '取值编码',
    `ITEM_NAME`    VARCHAR(500) NOT NULL COMMENT '显示名称',
    `LANGUAGE`    VARCHAR(50) NOT NULL COMMENT '语言',
    `ITEM_INDEX`    VARCHAR(100) NOT NULL COMMENT '排序值',
    `IS_ENABLED`    VARCHAR(10) NOT NULL COMMENT '是否开启',
    `DESCRIPTION`    VARCHAR(1000)  COMMENT 'DESCRIPTION',

    `ITEM_ATTR1`    VARCHAR(1000)  COMMENT '备用字段1',
    `ITEM_ATTR2`    VARCHAR(1000) COMMENT '备用字段2',
    `ITEM_ATTR3`    VARCHAR(1000)  COMMENT '备用字段3',
    `ITEM_ATTR4`    VARCHAR(1000)  COMMENT '备用字段4',
    `ITEM_ATTR5`    VARCHAR(1000)  COMMENT '备用字段5',

    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`ITEM_ID`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- 用户主表
CREATE TABLE IF NOT EXISTS `tpl_user_t`(
    `USER_ID`       VARCHAR(100)     NOT NULL ,
    `USER_ACCOUNT`    VARCHAR(100) NOT NULL COMMENT '用户账号',
    `USER_NAME`    VARCHAR(100) NOT NULL COMMENT '用户名称',
    `PASSWORD`    VARCHAR(100) NOT NULL COMMENT '用户密码',
    `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
    PRIMARY KEY (`USER_ID`)
    ) ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4;

-- 栏目表
CREATE TABLE IF NOT EXISTS `tpl_menu_t`(
   `parent_id` VARCHAR(100) NOT NULL COMMENT '父栏目ID',
   `id`    VARCHAR(100) NOT NULL COMMENT '菜单ID',
   `title`    VARCHAR(100) NOT NULL COMMENT '菜单名称',
   `name`    VARCHAR(100) NOT NULL COMMENT '路由名称',
   `path`    VARCHAR(100) NOT NULL COMMENT '路由地址',
   `component`    VARCHAR(100)  COMMENT '组件路径',
   `rank_sort`    INT  COMMENT '排序',
   `redirect`    VARCHAR(100) COMMENT '重定向地址',
   `icon`    VARCHAR(100)  COMMENT '图标',
   `extra_icon`    VARCHAR(100)  COMMENT '额外图标',
   `active_path`    VARCHAR(100)  COMMENT '菜单不显示时激活路由地址',
   `roles`    VARCHAR(100)  COMMENT '菜单权限',
   `auths`    VARCHAR(100)  COMMENT '按钮权限',
   `show_link` VARCHAR(10)  COMMENT '是否在菜单中显示 （false：显示，true：不显示）',
   `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
   `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
   PRIMARY KEY (`id`)
   ) ENGINE = InnoDB
   DEFAULT CHARSET = utf8mb4;


