package com.feiniaojin.ddd.eventsourcing.domain.commands;

import com.feiniaojin.ddd.eventsourcing.domain.Command;
import lombok.Data;

/**
 * 减少产品数量的命令
 */
@Data
public class ProductCountReduceCommand extends Command {
    /**
     * 将要减少的数量，例如原来有10个，将要减少8个，此处的count代表8
     */
    private Integer count;
}
