package com.github.lansheng228.common.toolkit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.Date;


/**
 * 日期Util类
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTool {
    private static final Clock clock = Clock.systemDefaultZone();

    /**
     * 获取现在时间
     * 返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getNow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static long currentTimeMillis() {

        return clock.millis();
    }
}

