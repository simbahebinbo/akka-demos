package com.github.lansheng228.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 消息模式
@Getter
@AllArgsConstructor
public enum MessageContentEnum {
    INVALID(0, "invalid"),
    MESSAGE_CONTENT_DATE(1, "message_content_date"),
    MESSAGE_CONTENT_PING(2, "message_content_ping"),
    MESSAGE_CONTENT_PONG(3, "message_content_pong"),

    ;

    private final Integer code;
    private final String value;
}
