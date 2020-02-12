# Play2 Tutorial for Java

环境：
java 1.8
gradle 6.0.1

编译打包部署

```
$ ./gradlew clean dist
$ unzip ./build/distributions/playBinary.zip -d ./build/distributions/
$ ./build/distributions/playBinary/bin/playBinary

或者

$ ./gradlew clean stage
$ ./build/stage/playBinary/bin/playBinary
```


调试运行
```
$ ./gradlew run
```

测试
```
$ ./gradlew test
```

链接： http://localhost:9000

```
$ curl -X GET "http://localhost:9000/message"
Hi!
$ curl -X GET "http://localhost:9000/count"
0
$ curl -X GET "http://localhost:9000"
Your new application is ready.
```

