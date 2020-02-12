package com.lab.util.optional;


public interface ICar {

  /**
   * 轮子数量
   */
  int getWheelCount();

  void setCarWheel(int count);

  default void print() {
    System.out.println(this.getClass().getName());
  }
}
