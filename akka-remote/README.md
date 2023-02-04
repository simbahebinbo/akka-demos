# akka远程实例

## 基本命令

### 构建

构建可部署包

```
$ mvn clean package -DskipTests
```

分别启动程序

```
先启动远程节点
$ java -jar remote-node/target/remote-node-1.0.0.jar
再启动本地节点
$ java -jar local-node/target/local-node-1.0.0.jar
```
