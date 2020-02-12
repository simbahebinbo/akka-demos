package com.lab.util;

import com.lab.util.optional.ICar;
import com.lab.util.optional.WeiLaiCar;

import java.util.List;
import java.util.Optional;

/**
 * Optional测试用例
 */
public class Java8OptionalTest {

  static Integer sum = new Integer(1);

  List<String> stringList = null;

  ICar car = new WeiLaiCar();

  public static void equilsTest() {
    Java8OptionalTest test2 = new Java8OptionalTest();
    Java8OptionalTest test1 = test2;
    Java8OptionalTest test3 = new Java8OptionalTest();
    pringTest(Optional.of(test1).equals(test1));
    pringTest(Optional.of(test1).equals(test2));
    pringTest(Optional.of(test1).equals(test3));
  }

  public static void filterTest() {
    Java8OptionalTest test = new Java8OptionalTest();
    Optional<Java8OptionalTest> optional = Optional.of(test);

    Optional result = optional.filter(a -> a.getCar() != null).filter(b -> b.getClass().getName() != null);
    pringTest(result.isPresent() ? result.get().getClass().getName() : result.isPresent());
    //com.lab.util.Java8OptionalTest
    Optional result1 = optional.filter(a -> a.getStringList() != null);
    pringTest(result1.get());
    //java.util.NoSuchElementException: No value present
  }

  public static void presentTest() {
    Java8OptionalTest test = new Java8OptionalTest();
    Optional<Java8OptionalTest> optional = Optional.of(test);

    pringTest(optional.isPresent());
    //true
    optional.ifPresent(a -> pringTest(a.getCar().getClass().getName()));
    //com.lab.util.optional.WeiLaiCar
    optional.ifPresent(a -> Optional.ofNullable(a.getStringList()).ifPresent(b -> pringTest("StringList:" + (b == null))));
    //第一级的ifPresent是存在test对象，所以执行了lambda表达式，而第二级的ifPresent的stringList是null，所以没有执行表达式
    optional.ifPresent(a -> Optional.ofNullable(a.getCar()).ifPresent(b -> pringTest("car:" + (b == null))));
    //car:false
    //第二级ifPresent的car对象是存在的，所以第二级的表达式执行了
  }

  public static void mapTest() {
    Java8OptionalTest test = new Java8OptionalTest();
    Optional<Java8OptionalTest> optional = Optional.of(test);

    Optional opt1 = optional.map(a -> a.getCar());
    pringTest(opt1.get());
    //com.lab.util.optional.WeiLaiCar@5d6f64b1
    int wheel = 0;//传统null判断写法
    if (test != null) {
      if (test.getCar() != null) {//实际业务里面层级也许会超过3层
        wheel = test.getCar().getWheelCount();
      }
    }
    pringTest("传统:" + wheel);
    //传统:4
    Optional opt2 = optional.map(a -> a.getCar()).map(b -> b.getWheelCount());//Optional支持下的写法
    pringTest("optinal:" + opt2.get());
    //optinal:4
    Optional opt3 = optional.map(a -> a.getStringList()).map(b -> b.size());
    pringTest(opt3);
    //Optional.empty
    //flatMap使用演示
    Optional opt4 = optional.flatMap(a -> Optional.of(a.getCar()));//主动包裹Optional对象
    pringTest(opt4);
    //Optional[com.lab.util.optional.WeiLaiCar@5d6f64b1]
    Optional opt5 = optional.flatMap(a -> Optional.of(a.getCar())).flatMap(b -> Optional.ofNullable(b.getWheelCount()));
    pringTest(opt5);
    //Optional[4]
  }

  public static void orElseTest() {
    Java8OptionalTest one = null;
    Java8OptionalTest test = new Java8OptionalTest();
    Optional<Java8OptionalTest> optional = Optional.ofNullable(one);
    pringTest(optional);
    //Optional.empty
    pringTest(optional.orElse(test));
    //com.lab.util.Java8OptionalTest@5197848c
    pringTest(optional.orElseGet(() -> new Java8OptionalTest()));
    //com.lab.util.Java8OptionalTest@5d6f64b1
    pringTest(optional.orElseThrow(() -> new RuntimeException("orElseThrow")));
    //java.lang.RuntimeException: orElseThrow
  }

  public static void main(String[] args) {
    Java8OptionalTest.orElseTest();
  }

  public static void pringTest(Object print) {
    sum = sum + 1;
    System.out.println(sum + " : " + print);
  }

  public ICar getCar() {
    return car;
  }

  public void setCar(ICar car) {
    this.car = car;
  }

  public List<String> getStringList() {
    return stringList;
  }

  public void setStringList(List<String> stringList) {
    this.stringList = stringList;
  }
}
