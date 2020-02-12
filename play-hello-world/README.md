# Play Hello World Web Tutorial for Java

环境：
java 1.8
sbt 1.3.8

编译打包部署

```
$ sbt clean dist
$ unzip ./target/universal/play-hello-world-1.0.0.zip -d ./target/universal/ 
$ ./target/universal/play-hello-world-1.0.0/bin/play-hello-world
```

调试运行
```
$ sbt run
```

链接： http://localhost:9000

```
$ curl -X GET "http://localhost:9000"
Hello World!
```

