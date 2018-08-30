package com.wzq.game.util;

import com.wzq.game.common.Constant;

public class Log {

    public static void i(String content) {
        if (Constant.DEBUG) {
            System.out.println(content);
        }
    }

    public static void i(int content) {
        if (Constant.DEBUG) {
            System.out.println(content);
        }
    }

    public static void i(String tag, String content) {
        i(tag + ": " + content);
    }

    public static void i(String tag, int content) {
        i(tag + ": " + content);
    }

}
