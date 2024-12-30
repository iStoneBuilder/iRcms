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
  `address` VARCHAR(500) NOT NULL COMMENT '接口地址',
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
  `batch_No` VARCHAR(100) NOT NULL COMMENT '入库批次号',
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `iccid` VARCHAR(100) NOT NULL COMMENT 'ICCID', -- 20位
  `merchant_code` VARCHAR(100) NOT NULL COMMENT '卡商编码',
  `carrier_code` VARCHAR(100) NOT NULL COMMENT '运营商',
  `net_type` VARCHAR(100) NOT NULL COMMENT '网络类型', -- 4G，5G，6G
  `name_status` VARCHAR(100) COMMENT '实名状态', -- 1:已实名 2:未实名 3:待实名 4:实名失败
  `online_status` VARCHAR(100)  COMMENT '是否在线', -- 1:在线 2:离线
  `flow_status` VARCHAR(100)  COMMENT '卡流量状态', -- 0=测试，1=待激活(沉默期)，2=正常使用，3=停机，6=注销，11=空卡
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

-- SIM卡状态变更
CREATE TABLE IF NOT EXISTS `tpl_mifi_sim_status_t` (
  `request_id` VARCHAR(100) NOT NULL COMMENT '停复机请求号',
  `iccid` VARCHAR(100) NOT NULL COMMENT 'ICCID',
  `org_status` VARCHAR(100) NOT NULL  COMMENT '卡旧状态', -- 0=测试，1=待激活(沉默期)，2=正常使用，3=停机，6=注销，11=空卡
  `new_status` VARCHAR(100)  COMMENT '卡新状态', -- 0=测试，1=待激活(沉默期)，2=正常使用，3=停机，6=注销，11=空卡
  `operate_type` VARCHAR(100) NOT NULL COMMENT '操作类型',
  `remark` VARCHAR(100) COMMENT '备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`request_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 设备SIM实名记录
CREATE TABLE IF NOT EXISTS `tpl_mifi_sim_real_name_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `device_sn` VARCHAR(100) NOT NULL COMMENT '设备SN',
  `id` VARCHAR(100) NOT NULL COMMENT 'UUID',
  `iccid` VARCHAR(100) NOT NULL COMMENT 'ICCID',
  `real_name` VARCHAR(100) NOT NULL COMMENT '实名用户',
  `auth_way` VARCHAR(100) NOT NULL COMMENT '认证方式',
  `auth_status` VARCHAR(100) COMMENT '认证状态',
  `auth_apply_time` TIMESTAMP COMMENT '认证申请时间',
  `auth_pass_time` TIMESTAMP COMMENT '认证通过时间',
  `clean_apply_time` TIMESTAMP  COMMENT '清除认证申请时间',
  `clean_pass_time` TIMESTAMP COMMENT '清除认证通过时间',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`id`)
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
  `heart_Beat_Time` VARCHAR(50) COMMENT '心跳时长 单位s' DEFAULT '60',
  `flow_Upload_Time` VARCHAR(50) COMMENT '流量上报间隔时长 单位s' DEFAULT '180',
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
  `is_current` VARCHAR(10) NOT NULL COMMENT '是否当前设备:1是0否',
  `imei` VARCHAR(100)  COMMENT '移动设备识别码',
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
  `active_time` datetime  COMMENT '激活时间',
  `device_ability` VARCHAR(100)  COMMENT '设备能力',
  `signal` VARCHAR(100)  COMMENT '信号强度',
  `electric` VARCHAR(100)  COMMENT 'electric',
  `connect_num` VARCHAR(100)  COMMENT '设备连接数',
  `report_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `wifi_name` VARCHAR(100)  COMMENT 'wifi名称',
  `wifi_pwd` VARCHAR(100)  COMMENT 'wifi密码',
  `wifi_name_5G` VARCHAR(100)  COMMENT 'wifi名称5G',
  `wifi_pwd_5G` VARCHAR(100)  COMMENT 'wifi密码5G',
  `hard_ver` VARCHAR(100)  COMMENT '软件版本',
  `is_Active` VARCHAR(100)  COMMENT '是否激活 0 未激活 1 激活' DEFAULT '1',
  `open_Wifi` VARCHAR(100)  COMMENT '设备是否开启 WiFi 0 关闭 1 开启' DEFAULT '1',
  `hide_Wifi` VARCHAR(100)  COMMENT '是否隐藏wifi 0 隐藏 1 不隐藏' DEFAULT '1',
  `open_Quick_Net` VARCHAR(10)  COMMENT '设备是否开启极速上网 0 关闭 1 开启' DEFAULT '1',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`device_sn`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 设备控制
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_control_t` (
  `control_id` VARCHAR(100) NOT NULL COMMENT '控制记录ID',
  `device_sn` VARCHAR(100) NOT NULL COMMENT '设备sn',
  `cmd` INT(11) NOT NULL COMMENT '控制命令',
  `param` VARCHAR(128)  COMMENT '控制参数',
  `source` VARCHAR(32) NOT NULL COMMENT '来源app/ops',
  `remark` VARCHAR(100) NOT NULL COMMENT '控制名称',
  `is_handle` VARCHAR(100)  COMMENT '是否下发',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`control_id`)
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

-- 设备分发
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_divide_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `divide_id` VARCHAR(100) NOT NULL COMMENT '分发ID',
  `device_sn` VARCHAR(8000) NOT NULL COMMENT '分发的设备sn',
  `divide_num` VARCHAR(100) NOT NULL COMMENT '分发数量',
  `org_mch` VARCHAR(100) NOT NULL COMMENT '原始商户',
  `target_mch` VARCHAR(100) NOT NULL COMMENT '目标商户',
  `divide_status` VARCHAR(100) NOT NULL COMMENT '分发状态',
  `remark` VARCHAR(100) COMMENT '分发备注',
  `CREATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  `UPDATED_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `UPDATED_BY` varchar(100) NOT NULL DEFAULT 'UNKNOWN',
  PRIMARY KEY (`divide_id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 原始数据 iccid（设备）
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_iccid_flow_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `device_sn` VARCHAR(100) NOT NULL COMMENT '设备sn',
  `using_iccid` VARCHAR(100) NOT NULL COMMENT '当前使用的卡 ICCID',
  `seed_iccid` VARCHAR(100) NOT NULL COMMENT '种子卡ICCID',
  `isp` VARCHAR(10) NOT NULL COMMENT '运营商',
  `device_con_num` VARCHAR(100) NOT NULL COMMENT '种子卡ICCID',
  `electric` VARCHAR(100) NOT NULL COMMENT '电量',
  `signal` VARCHAR(100) NOT NULL COMMENT '信号强度',
  `seed_Total_Flow` VARCHAR(100) NOT NULL COMMENT '当前使用卡总流量 （一个流量上报周期内）',
  `device_Total_Flow` VARCHAR(100) NOT NULL COMMENT '设备使用总流量 （一个流量上报周期内）',
  `report_str` VARCHAR(5000) NOT NULL COMMENT '上报原始数据',
  `report_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

-- 上报设备数据
CREATE TABLE IF NOT EXISTS `tpl_mifi_device_report_t` (
  `tenant_id` VARCHAR(100) NOT NULL COMMENT '租户ID',
  `enterprise_id` VARCHAR(100) NOT NULL COMMENT '商户ID',
  `report_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `device_sn` VARCHAR(100) NOT NULL COMMENT '设备sn',
  `report_str` VARCHAR(5000) NOT NULL COMMENT '上报原始数据'
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

