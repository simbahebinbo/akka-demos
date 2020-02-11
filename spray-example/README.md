spray-example
============

The simple example of Spray based on Akka

1.准备环境

```
$ brew install scala
$ brew install sbt
```

2.编译并打包:

```
$ sbt clean assembly
```

3.启动:

```
$ sbt run
或者
$ java -jar target/scala-2.11/spray-example-assembly-1.0.0.jar
``` 

4. 路径 http://localhost:8080

```
$ curl -X GET  "http://localhost:8080/customers"
$ curl -X GET  "http://localhost:8080/customers/1"
$ curl -X POST -H "Content-Type:application/json" --data '{"id": 1111111, "birthDate":"1985-04-13T00:00:00Z","name":"zhangsan"}' "http://localhost:8080/"
```


