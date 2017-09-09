# Kafka启动配置

## kafka目录结构
```
kafka
    +-bin
        +-windows
    +-config
        +-zookeeper.properties
        +-server.properties
    +-libs
    +-logs
    +-site-docs
```


- 启动zookeeper
    - `zookeeper-server-start config/zookeeper.properties`
    - `clientPort`配置绑定端口，默认为2181
- 启动kafka
    - `kafka-server-start config/server.properties`
    - `zookeeper.connect`设置ZooKeeper地址和端口，默认为`localhost:2181`,多个Zookeeper节点可以用逗号分隔
    
- 查看Topic列表
    - `kafka-topics --list --zookeeper localhost:2181`