CREATE TABLE `t_event`(
	`id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `event_id` varchar(64) NOT NULL COMMENT '事件id',
    `entity_id` varchar(64) NOT NULL COMMENT '实体id',
    `event_data` varchar(4096) NOT NULL COMMENT '事件消息序列化后JSON串',
    `event_time` datetime NOT NULL COMMENT '事件发生时间',
    `event_type` varchar (32) NOT NULL COMMENT '事件类型',
    `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常；1-已删除]',
	`created_by` VARCHAR(100) COMMENT '创建人',
	`created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`modified_by` VARCHAR(100) COMMENT '更新人',
	`modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`version` bigint DEFAULT 1 COMMENT '乐观锁',
    PRIMARY KEY(`id`),
    UNIQUE INDEX unique_event_id (event_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE utf8mb4_bin COMMENT '领域事件表';

CREATE TABLE `t_entity`(
	`id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `entity_id` varchar(64) NOT NULL COMMENT '实体id',
    `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常；1-已删除]',
	`created_by` VARCHAR(100) COMMENT '创建人',
	`created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`modified_by` VARCHAR(100) COMMENT '更新人',
	`modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`version` bigint DEFAULT 1 COMMENT '乐观锁',
    PRIMARY KEY(`id`),
    UNIQUE INDEX unique_entity_id (entity_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE utf8mb4_bin COMMENT '实体表';

CREATE TABLE `t_snapshot`(
	`id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `entity_id` varchar(64) NOT NULL COMMENT '事件id',
    `event_id` varchar(64) NOT NULL COMMENT '事件id',
    `event_time` datetime NOT NULL COMMENT '事件发生时间',
    `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除标记[0-正常；1-已删除]',
	`created_by` VARCHAR(100) COMMENT '创建人',
	`created_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`modified_by` VARCHAR(100) COMMENT '更新人',
	`modified_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`version` bigint DEFAULT 1 COMMENT '乐观锁',
    PRIMARY KEY(`id`),
    UNIQUE INDEX unique_event_id (entity_id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE utf8mb4_bin COMMENT '快照表';



