package com.feiniaojin.ddd.eventsourcing.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProcessorRegister {

    private static final Map<Class<?>, CommandProcessor> map = new ConcurrentHashMap<>();


    static {
        //注册默认的
    }

    /**
     * 找到匹配的命令处理器
     *
     * @param command
     * @return
     */
    public static CommandProcessor mappingCommandProcessor(Command command) {
        return map.get(command.getClass());
    }

    public static void registerCommandProcessor(Class<? extends Command> clazz, CommandProcessor commandProcessor) {
        map.put(clazz, commandProcessor);
    }
}
