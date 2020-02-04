package com.lab;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Getter
  private Long id;

  @Getter
  private String name;
}
