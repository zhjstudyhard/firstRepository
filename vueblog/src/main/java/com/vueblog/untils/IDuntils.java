package com.vueblog.untils;

import java.util.UUID;

/**
 * @Author: haojie
 * @qq :1422471205
 * @CreateTime: 2021-03-19-21-45
 */
public class IDuntils {
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }
}
