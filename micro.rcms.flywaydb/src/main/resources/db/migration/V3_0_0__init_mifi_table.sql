-- 卡商表 tpl_mifi_merchant_t
CREATE TABLE IF NOT EXISTS `tpl_mifi_merchant_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '企业（商户）ID',
  `merchant_code` VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `merchant_name` VARCHAR(100) NOT NULL COMMENT '卡商名称',
  `remark` VARCHAR(100) COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`enterprise_id`, `merchant_code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


-- 卡商-运营商 tpl_mifi_merchant_carrier_t
CREATE TABLE IF NOT EXISTS `tpl_mifi_merchant_carrier_t` (
  `merchant_code` VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `carrier_code` VARCHAR(100) NOT NULL COMMENT '运营商编码',
  `app_key` VARCHAR(100) NOT NULL COMMENT '运营商appKey',
  `app_secret` VARCHAR(100) NOT NULL COMMENT '运营商appSecret',
  `disable_area` VARCHAR(100) COMMENT '禁用区域',
  `remark` VARCHAR(100) COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY ( `carrier_code`, `merchant_code`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- SIM 卡
CREATE TABLE IF NOT EXISTS `tpl_mifi_sim_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `iccid` VARCHAR(100) NOT NULL COMMENT 'ICCID', -- 20位
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `merchant_code` VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `device_sn` VARCHAR(100) COMMENT '设备SN',
  `carrier_code` VARCHAR(100) NOT NULL COMMENT '运营商',
  `net_type` VARCHAR(100) NOT NULL COMMENT '网络类型', -- 4G，5G，6G
  `name_status` VARCHAR(100) COMMENT '实名状态', -- 1:已实名 2:未实名 3:待实名 4:实名失败
  `online_status` VARCHAR(100)  COMMENT '是否在线', -- 1:在线 2:离线
  `flow_status` VARCHAR(100)  COMMENT '卡流量状态', -- 1:正常 2:欠费 3:停机 4:销号 5:待激活 6:未知
  `sim_type` VARCHAR(100) COMMENT '卡类型', -- 1:本地卡 2:云卡
  `imei` VARCHAR(100)  COMMENT 'imei',
  `flow_used` VARCHAR(100)  COMMENT '已使用流量', -- 单位：MB
  `flow_remain` VARCHAR(100)  COMMENT '剩余流量', -- 单位：MB
  `flow_used_day` VARCHAR(100) COMMENT '今日已使用流量', -- 单位：MB
  `remark` VARCHAR(100) COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`iccid`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 设备类型(租户级)
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_type_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `type_id` VARCHAR(100) NOT NULL COMMENT '设备类型ID',
  `type_name` VARCHAR(100) NOT NULL COMMENT '设备名称',
  `type_code` VARCHAR(100) NOT NULL COMMENT '设备编码',
  `sell_name` VARCHAR(100) NOT NULL COMMENT '销售名称',
  `device_no` VARCHAR(10) NOT NULL COMMENT '设备型号',
  `card_mode` VARCHAR(4) NOT NULL COMMENT '卡模式',
  `main_pic` VARCHAR(100) COMMENT '设备主图',
  `devi_pic` VARCHAR(100) COMMENT '设备图',
  `status` VARCHAR(2) NOT NULL COMMENT '状态',
  `remark` VARCHAR(100) COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`type_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 设备分组（商户级）
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_group_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `group_id` VARCHAR(100) NOT NULL COMMENT '分组ID',
  `group_name` VARCHAR(100) NOT NULL COMMENT '分组名称',
  `remark` VARCHAR(100) COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`group_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;
