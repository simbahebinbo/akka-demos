package com.github.lansheng228.common.toolkit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeTool {

    // 睡眠 millis 毫秒
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {
        }
    }

    public static long epochSeconds() {
        return System.currentTimeMillis() / 1_000L;
    }

    public static Long setSeconds(Long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).getEpochSecond();
    }
}
