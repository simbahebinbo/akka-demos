package com.lab.util.java8;

import com.lab.util.PrintUtil;
import com.lab.util.java8.functions.IDog;
import com.lab.util.java8.functions.IPerson;
import com.lab.util.optional.ICar;
import com.lab.util.optional.WeiLaiCar;
import play.libs.F;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口测试
 */
public class FunctionTest {

  public static void customeFunctionTest() {
    IPerson.run("beijing");

    IPerson person = a -> "the person said : " + a;
    PrintUtil.printTest(person.say("hello world"));

    IDog dog = a -> "dog name : " + a;
    PrintUtil.printTest(dog.name("Tim"));
  }

  public static void ConsumerTest() {
    Consumer<Integer> consumer = a -> PrintUtil.printTest("Value is " + a);
    consumer.accept(101);
    //接收一个参数，没有任何返回
    consumer.andThen(consumer).accept(102);

    Consumer<Integer> consumer2 = a -> PrintUtil.printTest("Value2 is " + a);
    consumer.andThen(consumer2).andThen(consumer).accept(103);
    //andThan是将传入的consumer在当前consumer的accept执行后执行它的accept
  }

  public static void functionTest() {
    Function<Integer, String> function = a -> "== " + a;
    PrintUtil.printTest(function.apply(101));
    Function<String, Boolean> function1 = c -> c.length() > 2;
    PrintUtil.printTest(function1.apply("1a"));

    PrintUtil.printTest(function.andThen(function1).apply(111));
    //andThen类似consumer，是前一个function执行后结果作为参数传新生成的function执行，结构：true

    Function<Integer, Integer> function2 = c -> c * c;
    //compose和andThen正好逻辑相反，传入的参数function先执行后范围结果作为参数传给新生成的function执行
    PrintUtil.printTest(function.compose(function2).apply(2));
    //先执行function2，返回结果作为参数再执行function，结果：== 4
    PrintUtil.printTest(function.compose(function2).andThen(function1).apply(2));
    //先执行function2，其次执行funciton，最后执行function1，结果：true
    PrintUtil.printTest(function2.compose(function2).apply(2));
    //先执行第二个function2，返回结果作为参数再执行第一个function2，结果：16

    Function<String, String> function3 = Function.identity();//static方法
    PrintUtil.printTest(function3.apply("hello"));
    //identity定义了一个只返回输入参数的function,结果：hello
  }

  public static void predicateTest() {
    Predicate<ICar> carHas4Wheel = a -> a.getWheelCount() > 3;
    ICar weilai = new WeiLaiCar(3);

    PrintUtil.printTest(carHas4Wheel.test(weilai));
    //false
    PrintUtil.printTest(carHas4Wheel.negate().test(weilai));
    //true
    PrintUtil.printTest(carHas4Wheel.and(b -> b.getWheelCount() > 2).test(weilai));
    //false
    PrintUtil.printTest(carHas4Wheel.and(b -> b.getWheelCount() > 2).test(new WeiLaiCar(4)));
    //true
    PrintUtil.printTest(carHas4Wheel.or(b -> b.getWheelCount() > 2).test(new WeiLaiCar(3)));
    //true

    PrintUtil.printTest(Predicate.isEqual(weilai).test(weilai));
    //true
    PrintUtil.printTest(Predicate.isEqual(weilai).test(new WeiLaiCar(3)));
    //false
  }

  public static void supplierTest() {
    Supplier<Integer> supplier = () -> 1;
    PrintUtil.printTest(supplier.get());
    //1
    Supplier<ICar> carSupplier = () -> new WeiLaiCar(5);
    PrintUtil.printTest(carSupplier.get() + " : " + carSupplier.get().getWheelCount());
    //com.lab.util.optional.WeiLaiCar@32a1bec0 : 5
  }

  public static void lambdaTest() {
    //当你这种写法是编译器会提示你用lambda
    IPerson person = new IPerson() {
      @Override
      public String say(String input) {
        return "My said is " + input;
      }
    };
    PrintUtil.printTest(person.say("i love china."));

    //lambda写法
    IPerson person2 = a -> "My said is " + a;
    PrintUtil.printTest(person2.say("i love china."));
    //结果是一样的，My said is i love china.
  }

  public static void main(String[] args) {
    FunctionTest.lambdaTest();
  }
}
