package com.feiniaojin.ddd.eventsourcing.domain.commands;

import com.feiniaojin.ddd.eventsourcing.domain.Command;
import lombok.Data;

@Data
public class ProductCreateCommand extends Command {

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品图片
     */
    private String picture;

    /**
     * 产品数量
     */
    private Integer count;
}
