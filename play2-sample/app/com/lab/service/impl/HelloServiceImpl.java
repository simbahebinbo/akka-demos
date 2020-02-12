package com.lab.service.impl;

import com.lab.service.IHelloService;

public class HelloServiceImpl implements IHelloService {

  @Override
  public String showSelf() {
    return "i am " + this.getClass().getName();
  }
}
