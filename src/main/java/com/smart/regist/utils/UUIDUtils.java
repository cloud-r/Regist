package com.smart.regist.utils;

import java.util.UUID;

/**
 * @ClassName UUIDUtils
 * @Descriptuion TODO 生成激活码
 * @Author life
 * @Date 2019/10/25 12:26
 * @Version 1.0
 **/
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
