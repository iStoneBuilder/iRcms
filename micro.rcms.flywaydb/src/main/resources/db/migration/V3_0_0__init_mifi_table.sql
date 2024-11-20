-- 卡商表 tpl_mifi_merchant_t
CREATE TABLE IF NOT EXISTS `tpl_mifi_merchant_t`(
  `enterprise_id`    VARCHAR(100) NOT NULL COMMENT '企业（商户）ID',
  `merchant_code`    VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `merchant_name`    VARCHAR(100) NOT NULL COMMENT '卡商名称',
  `remark`    VARCHAR(100)  COMMENT '备注',
  `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`enterprise_id`,`merchant_code`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 卡商-运营商 tpl_mifi_merchant_carrier_t
CREATE TABLE IF NOT EXISTS `tpl_mifi_merchant_carrier_t`(
  `enterprise_id`    VARCHAR(100) NOT NULL COMMENT '企业（商户）ID',
  `merchant_code`    VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `carrier_code`    VARCHAR(100) NOT NULL COMMENT '运营商编码',
  `app_key`    VARCHAR(100) NOT NULL COMMENT '运营商appKey',
  `app_secret`    VARCHAR(100) NOT NULL COMMENT '运营商appSecret',
  `disable_area`    VARCHAR(100)  COMMENT '禁用区域',
  `remark`    VARCHAR(100)  COMMENT '备注',
  `CREATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY`   varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`enterprise_id`,`carrier_code`,`merchant_code`)
  ) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
