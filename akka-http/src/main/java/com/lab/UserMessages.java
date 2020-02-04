package com.lab;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
