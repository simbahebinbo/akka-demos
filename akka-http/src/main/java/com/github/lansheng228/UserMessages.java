package com.github.lansheng228;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

public interface UserMessages {

    @AllArgsConstructor
    class ActionPerformed implements Serializable {

        private static final long serialVersionUID = 1L;

        @Getter
        private final String description;
    }

    @AllArgsConstructor
    class CreateUserMessage implements Serializable {

        private static final long serialVersionUID = 1L;

        @Getter
        private final User user;
    }


    @AllArgsConstructor
    class GetUserMessage implements Serializable {

        private static final long serialVersionUID = 1L;

        @Getter
        private final Long userId;
    }

}
