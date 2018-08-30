package com.wzq.game.server;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class HandlerManager {

    private static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<>();

    public static void add(String id, Channel channel) {
        channelMap.put(id, channel);
    }

    public static void remove(String id) {
        channelMap.remove(id);
    }

    public static Channel get(String id) {
        return channelMap.get(id);
    }

}
