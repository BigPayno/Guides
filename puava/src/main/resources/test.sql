CREATE TABLE `t_referral_activity_relation` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'pk',
	`parent_activity_id` BIGINT(20) NOT NULL COMMENT 't_referral_activity pk',
	`child_activity_id` BIGINT(20) NOT NULL COMMENT 't_referral_activity pk',
	`create_user_id` BIGINT(20) NOT NULL COMMENT '创建人ID',
	`create_user_type` TINYINT(4) NOT NULL COMMENT '创建人类型',
	`create_time` DATETIME NOT NULL COMMENT '创建时间',
	`last_update_user_id` BIGINT(20) NOT NULL COMMENT '最后修改人ID',
	`last_update_user_type` TINYINT(4) NOT NULL COMMENT '最后修改人类型',
	`last_update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
	`is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
	PRIMARY KEY (`id`)
)
COMMENT='转介绍活动关系表'
COLLATE='utf8mb4_unicode_ci'
ENGINE=InnoDB
;
