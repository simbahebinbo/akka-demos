# play2-sample
the project is a test sample. 

环境：
java 1.8
sbt 1.3.8

编译打包部署

```
$ sbt clean dist
$ unzip ./target/universal/play2-sample-1.0.0.zip -d ./target/universal/ 
$ ./target/universal/play2-sample-1.0.0/bin/play2-sample
```

调试运行
```
$ sbt run
```

测试
```
$ sbt test
```

浏览器打开链接： http://localhost:9000


