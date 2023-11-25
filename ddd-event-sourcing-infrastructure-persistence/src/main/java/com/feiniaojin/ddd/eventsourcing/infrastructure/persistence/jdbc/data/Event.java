package com.feiniaojin.ddd.eventsourcing.infrastructure.persistence.jdbc.data;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;
import java.io.Serializable;
import java.util.Date;

/**
 * 表名称：t_event
 * 表注释：领域事件表
 * NOTICE:本文件由代码生成器naaf-generator生成，不要在本文件手工追加任何内容，因为随时可能重新生成替换
 * github：https://github.com/feiniaojin/code-generator
 */
@Data
@Table("t_event")
@Generated("generator")
public class Event implements Serializable {
    /**
     * 自增主键
     */
    @Id
    private Long id;
    /**
     * 事件id
     */
    private String eventId;
    /**
     * 实体id
     */
    private String entityId;
    /**
     * 事件消息序列化后JSON串
     */
    private String eventData;
    /**
     * 事件发生时间
     */
    private Date eventTime;
    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 逻辑删除标记[0-正常；1-已删除]
     */
    private Integer deleted;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
     @CreatedDate
    private Date createdTime;
    /**
     * 更新人
     */
    private String modifiedBy;
    /**
     * 更新时间
     */
    @LastModifiedDate
    private Date modifiedTime;
    /**
     * 乐观锁
     */
    @Version
    private Long version;
}
