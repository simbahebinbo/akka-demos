package com.lab.util.java8;

import com.lab.util.PrintUtil;
import com.lab.util.optional.ICar;
import com.lab.util.optional.WeiLaiCar;

import java.util.*;
import java.util.stream.*;

/**
 * 测试用例java.util.stream
 */
public class Java8StreamTest {

  static List<ICar> cars = new ArrayList<>();

  static Map<Integer, String> kv = new HashMap<>();

  public static void init() {
    for (int i = 1; i <= 10; i++) {
      WeiLaiCar car = new WeiLaiCar();
      car.setCarWheel(i);
      cars.add(car);

      kv.put(i, i + "-value");
    }
  }


  public static void test() {
    Random random = new Random();
    IntStream intStream = random.ints(10);
    intStream.limit(6).forEach(PrintUtil::printTest);
    random.ints(15).toArray();

    kv.values().stream().forEach(PrintUtil::printTest);

    cars.parallelStream().forEach(PrintUtil::printTest);

    List<ICar> tmp = cars.parallelStream().collect(Collectors.toList());
    tmp.forEach(PrintUtil::printTest);
    cars.parallelStream().collect(Collectors.toSet()).forEach(PrintUtil::printTest);
//        cars.parallelStream().collect(Collectors.toCollection(null)).forEach(PrintUtil::printTest);
  }

  public static void listAndStreamTest() {
    //list -> stream
    Stream<ICar> carStream = cars.parallelStream();
    carStream.filter(a -> a.getWheelCount() > 5).map(a -> a.hashCode() + "|" + a.getWheelCount()).forEach(PrintUtil::printTest);
    //list转换为并发stream并打印符合条件的对象的hashcode和wheel数量：730140433|8

    //stream -> list
    Stream<ICar> tmp = cars.stream();
    List<ICar> carList = tmp.collect(Collectors.toList());
    carList.forEach(PrintUtil::printTest);
    //通过collect方法把stream转换为list并打印对象全称：com.ts.util.optional.WeiLaiCar@4e515669

    //array -> stream
    ICar[] carss = {new WeiLaiCar(1), new WeiLaiCar(2)};
    Arrays.stream(carss).filter(a -> a.getWheelCount() > 2).forEach(PrintUtil::printTest);
    //最终输出8个wheel大于2个的对象，并打印对象全称：com.ts.util.optional.WeiLaiCar@4dcbadb4

    //stream -> array
    Object[] tmps = cars.stream().filter(a -> a.getWheelCount() > 7).toArray();
    PrintUtil.printTest(tmps.length);
    //从cars中转换stream选出wheel大于7个的，转换为数值最终输出数量为：3
  }

  public static void sortedStream() {
    //默认sorted()要求是值natural order，即是自然顺序的
    cars.stream().map(a -> a.getWheelCount()).sorted().forEach(PrintUtil::printTest);
    //先将对象的int类型的wheel聚合成集合，再使用默认排序（顺序）进行打印：1 2 3 4 5 6 ...10

    //自定义comparator，也可以在对象之间实现Comparator接口
    cars.stream().sorted(Comparator.comparingInt(ICar::getWheelCount)).map(a -> a.getWheelCount()).forEach(PrintUtil::printTest);
    //自定义使用ICar的int类型wheel字段顺序比较，之后再将对象的wheel字段聚合进行打印：1 2 3 4 5 6 ...10
    cars.stream().sorted(Comparator.comparingInt(ICar::getWheelCount).reversed()).map(a -> a.getWheelCount()).forEach(PrintUtil::printTest);
    //自定义使用ICar的int类型wheel字段顺序比较后再逆转，之后再将对象的wheel字段聚合进行打印
    //与上面的顺序正好相反：10 9 8 7 ... 1
  }

  public static void newStream() {
    Stream<String> stringStream = Stream.of("1", "a", "c");
    stringStream.forEach(PrintUtil::printTest);
    // 1 a c

    Stream<Integer> integerStream = Stream.empty();
    PrintUtil.printTest("count is " + integerStream.count());
    //count is 0

    double[] tm = {22D, 1.11D, 33D, 20.12D, 11.02D, 9.34D};
    DoubleStream doubleStream = StreamSupport.doubleStream(Spliterators.spliterator(tm, 0, 5, 1), true);
    doubleStream.filter(a -> a < 20).forEach(PrintUtil::printTest);
    //1.11 11.02
  }

  public static void main(String[] args) {
    Java8StreamTest.init();

    Java8StreamTest.newStream();
  }
}
