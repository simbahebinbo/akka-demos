package com.github.lansheng228.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 消息模式
@Getter
@AllArgsConstructor
public enum MessageModeEnum {
    INVALID(0, "invalid"),
    MESSAGE_MODE_TO_LOCAL(1, "message_mode_to_local"),
    MESSAGE_MODE_TO_REMOTE(2, "message_mode_to_remote"),
    ;

    private final Integer code;
    private final String value;
}
