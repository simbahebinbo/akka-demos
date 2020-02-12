package com.lab.util.optional;


public class WeiLaiCar implements ICar {

  public WeiLaiCar() {
  }

  public WeiLaiCar(Integer wheels) {
    this.wheels = wheels;
  }

  Integer wheels = new Integer(4);

  @Override
  public int getWheelCount() {
    return this.wheels;
  }

  @Override
  public void setCarWheel(int count) {
    this.wheels = count;
  }
}
