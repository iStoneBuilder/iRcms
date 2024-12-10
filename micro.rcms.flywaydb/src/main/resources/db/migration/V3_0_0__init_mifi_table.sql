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

-- SIM 卡（企租级数据）
CREATE TABLE IF NOT EXISTS `tpl_mifi_sim_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `iccid` VARCHAR(100) NOT NULL COMMENT 'ICCID', -- 20位
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `merchant_code` VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `carrier_code` VARCHAR(100) NOT NULL COMMENT '运营商',
  `net_type` VARCHAR(100) NOT NULL COMMENT '网络类型', -- 4G，5G，6G
  `name_status` VARCHAR(100) COMMENT '实名状态', -- 1:已实名 2:未实名 3:待实名 4:实名失败
  `online_status` VARCHAR(100)  COMMENT '是否在线', -- 1:在线 2:离线
  `flow_status` VARCHAR(100)  COMMENT '卡流量状态', -- 1:正常 2:欠费 3:停机 4:销号 5:待激活 6:未知
  `sim_type` VARCHAR(100) COMMENT '卡类型', -- 1:本地卡 2:云卡
  `msisdn` VARCHAR(100)  COMMENT '物联网号',
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
  `device_no` VARCHAR(100) NOT NULL COMMENT '设备型号',
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

-- 设备信息（商户级）
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `device_sn` VARCHAR(100) NOT NULL COMMENT '设备sn',
  `imei` VARCHAR(100)  COMMENT 'imei',
  `msisdn1` VARCHAR(100)  COMMENT '物联网号1',
  `msisdn2` VARCHAR(100)  COMMENT '物联网号2',
  `net_mode` VARCHAR(100) NOT NULL COMMENT '上网模式',
  `on_line` VARCHAR(100)  COMMENT '在线？',
  `device_type` VARCHAR(100) NOT NULL COMMENT '设备类型',
  `device_group` VARCHAR(100) COMMENT '设备分组',
  `card_strategy` VARCHAR(100) NOT NULL COMMENT '选卡策略',
  `flow_mode` VARCHAR(100) NOT NULL COMMENT '流量模式',
  `batch_No` VARCHAR(100) NOT NULL COMMENT '入库批次号',
  `check_Status` VARCHAR(100) COMMENT '检测状态',
  `device_Status` VARCHAR(100) COMMENT '设备状态',
  `test_flow` VARCHAR(100)  COMMENT '测试流量',
  `remark` VARCHAR(100) COMMENT '备注',
  `active_user` VARCHAR(100)  COMMENT '激活用户',
  `active_time` VARCHAR(100)  COMMENT '激活时间',
  `device_ability` VARCHAR(100)  COMMENT '设备能力',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`device_sn`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 套餐配置 (商户级)
CREATE TABLE IF NOT EXISTS `tpl_mifi_data_plan_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `data_plan_no` VARCHAR(100) NOT NULL COMMENT '套餐编号',
  `data_plan_name` VARCHAR(100) NOT NULL COMMENT '套餐名称',
  `data_plan_pic` VARCHAR(100) COMMENT '套餐图片',
  `data_plan_type` VARCHAR(100) NOT NULL COMMENT '套餐类型(国内套餐/国际套餐)',
  `data_plan_cost` VARCHAR(100) NOT NULL COMMENT '套餐成本',
  `data_plan_price` DECIMAL(10, 2) NOT NULL COMMENT '套餐价格',
  `data_plan_flow` DECIMAL(10, 2) NOT NULL COMMENT '套餐流量',
  `data_plan_void_flow` DECIMAL(10, 2) NOT NULL COMMENT '套餐虚量',
  `charge_type` VARCHAR(100) NOT NULL COMMENT '计费类型',
  `valid_duration` VARCHAR(100) NOT NULL COMMENT '有效时长',
  `limit_speed` VARCHAR(100) NOT NULL COMMENT '是否限速',
  `gift_duration` VARCHAR(100) NOT NULL COMMENT '赠送月份',
  `is_sale` VARCHAR(100) NOT NULL COMMENT '是否上架',
  `limit_no` VARCHAR(100) NOT NULL COMMENT '限制购买次数',
  `is_gift` VARCHAR(100) NOT NULL COMMENT '是否赠送',
  `is_recommend` VARCHAR(100) NOT NULL COMMENT '是否推荐',
  `data_plan_group` VARCHAR(100) COMMENT '套餐组',
  `data_plan_rules` VARCHAR(2000) NOT NULL COMMENT '套餐规则',
  `sort` VARCHAR(100) COMMENT '排序',
  `sale_device_type` VARCHAR(1000) COMMENT '售卖设备类型',
  `sale_device_group` VARCHAR(1000) COMMENT '售卖设备组',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`data_plan_no`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 设备套餐配置
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_dp_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `device_dp_id` VARCHAR(100) NOT NULL COMMENT '设备套餐ID',
  `device_sn` VARCHAR(100) NOT NULL COMMENT '设备sn',
  `order_no` VARCHAR(100) NOT NULL COMMENT '订单号',
  `data_plan_no` VARCHAR(100) NOT NULL COMMENT '套餐编号',
  `effective_time` datetime NOT NULL COMMENT '生效时间',
  `expire_time` datetime NOT NULL COMMENT '失效时间',
  `total_flow` VARCHAR(100) NOT NULL COMMENT '套餐流量',
  `remain_flow` VARCHAR(100) NOT NULL COMMENT '套餐剩余量',
  `used_flow` VARCHAR(100) NOT NULL COMMENT '当前已用量',
  `total_used_flow` VARCHAR(100) NOT NULL COMMENT '当前总共已用量',
  `is_limit_speed` VARCHAR(10) NOT NULL COMMENT '是否限速',
  `limit_speed` VARCHAR(30)  COMMENT '限速值',
  `is_available` VARCHAR(10) NOT NULL COMMENT '是否可用',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`device_dp_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;





