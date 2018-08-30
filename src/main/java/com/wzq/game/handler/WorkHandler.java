package com.wzq.game.handler;

import com.alibaba.fastjson.JSONObject;
import com.wzq.game.common.Constant;
import com.wzq.game.server.HandlerManager;
import com.wzq.game.util.Log;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

// 具体业务处理
public class WorkHandler extends SimpleChannelInboundHandler<String> {
    private final String TAG = "WorkHandler";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(msg);
        int id = jsonObject.getIntValue(Constant.KEY_ID);
        String message = jsonObject.getString(Constant.KEY_MESSAGE);

        Log.i(TAG, id);
        Log.i(TAG, message);

        writeAfterWhile(ctx.channel().id().asLongText());
    }

    private void writeAfterWhile(String id) throws Exception {
        Thread.sleep(5000);
        HandlerManager.get(id).writeAndFlush("after some");
    }

}
