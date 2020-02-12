package com.lab.util.java8.functions;

import com.lab.util.PrintUtil;

/**
 * 自定义函数式接口
 */
@FunctionalInterface
public interface IPerson {

  //只能有一个抽象方法，不然编译无法默认识别调用
  String say(String input);

  static void run(String xx) {
    PrintUtil.printTest("IPerson run : " + xx);
  }

  static void walk() {
    PrintUtil.printTest("IPerson walk");
  }

  default void eat(int a, int b) {
    PrintUtil.printTest("IPerson eat : " + a + " - " + b);
  }
}
