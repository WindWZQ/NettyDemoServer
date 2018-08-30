package com.wzq.game.handler;

import com.alibaba.fastjson.JSONObject;
import com.wzq.game.common.Constant;
import com.wzq.game.server.HandlerManager;
import com.wzq.game.util.Log;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

// 第一处理
public class GateHandler extends SimpleChannelInboundHandler<String> {
    private final String TAG = "GateHandler";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 判断msg是否为json格式
        try {
            JSONObject jsonObject = JSONObject.parseObject(msg);
            int code = jsonObject.getIntValue(Constant.KEY_ID);
            switch (code) {
                case Constant.ID_NULL:
                    // id错误，不处理
                    break;
                case Constant.ID_HEART:
                    // 心跳，不处理
                    break;
                default:
                    // 其他业务，往下传递
                    ctx.fireChannelRead(msg);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        Log.i(TAG, "userEventTriggered");
        if (evt instanceof IdleStateEvent) {
            ctx.close();
            HandlerManager.remove(ctx.channel().id().asLongText());
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Log.i(TAG, "channelActive");
        HandlerManager.add(ctx.channel().id().asLongText(), ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Log.i(TAG, "exceptionCaught");
        ctx.close();
        HandlerManager.remove(ctx.channel().id().asLongText());
    }

}
