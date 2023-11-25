package com.feiniaojin.ddd.eventsourcing.domain.commands;

import com.feiniaojin.ddd.eventsourcing.domain.Command;
import lombok.Data;

@Data
public class ProductPictureChangeCommand extends Command {
    /**
     * 产品图片
     */
    private String picture;
}
