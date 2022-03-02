# gh

## java 知识体系个人学习和复习


```text
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─algorithm  算法
│  │  │  │  ├─array1string
│  │  │  │  ├─hash
│  │  │  │  ├─LinkedList
│  │  │  │  ├─ods
│  │  │  │  ├─other
│  │  │  │  │  └─famous
│  │  │  │  ├─printer
│  │  │  │  │  └─tree
│  │  │  │  ├─queue
│  │  │  │  ├─sort
│  │  │  │  ├─stack
│  │  │  │  ├─think
│  │  │  │  └─tree
│  │  │  │      ├─base
│  │  │  │      └─benchmark
│  │  │  ├─designpattern
│  │  │  │  ├─command
│  │  │  │  └─observer
│  │  │  ├─gui
│  │  │  │  ├─javafx
│  │  │  │  └─yfiles
│  │  │  ├─lang Java特性
│  │  │  │  ├─abasic
│  │  │  │  ├─bcollections
│  │  │  │  ├─cjuc
│  │  │  │  │  ├─concurrent
│  │  │  │  │  │  ├─collection
│  │  │  │  │  │  ├─concurrentUtils
│  │  │  │  │  │  ├─disrupter
│  │  │  │  │  │  │  └─base
│  │  │  │  │  │  ├─lock
│  │  │  │  │  │  ├─masterworker
│  │  │  │  │  │  └─threadpools
│  │  │  │  │  └─concurrent2
│  │  │  │  ├─dio
│  │  │  │  │  ├─aio
│  │  │  │  │  ├─bio
│  │  │  │  │  └─nio
│  │  │  │  ├─features
│  │  │  │  │  ├─annotation
│  │  │  │  │  ├─jdk8
│  │  │  │  │  ├─jdk9
│  │  │  │  │  ├─jmx
│  │  │  │  │  ├─reflection
│  │  │  │  │  ├─spi
│  │  │  │  │  │  └─DictionaryServiceDemo
│  │  │  │  │  │      ├─DictionaryDemo
│  │  │  │  │  │      │  ├─build
│  │  │  │  │  │      │  │  └─dictionary
│  │  │  │  │  │      │  ├─dist
│  │  │  │  │  │      │  └─src
│  │  │  │  │  │      │      └─dictionary
│  │  │  │  │  │      ├─DictionaryServiceProvider
│  │  │  │  │  │      │  ├─build
│  │  │  │  │  │      │  │  └─dictionary
│  │  │  │  │  │      │  │      └─spi
│  │  │  │  │  │      │  ├─dist
│  │  │  │  │  │      │  └─src
│  │  │  │  │  │      │      └─dictionary
│  │  │  │  │  │      │          └─spi
│  │  │  │  │  │      ├─ExtendedDictionary
│  │  │  │  │  │      │  ├─build
│  │  │  │  │  │      │  │  ├─dictionary
│  │  │  │  │  │      │  │  └─META-INF
│  │  │  │  │  │      │  │      └─services
│  │  │  │  │  │      │  ├─dist
│  │  │  │  │  │      │  └─src
│  │  │  │  │  │      │      ├─dictionary
│  │  │  │  │  │      │      └─META-INF
│  │  │  │  │  │      │          └─services
│  │  │  │  │  │      └─GeneralDictionary
│  │  │  │  │  │          ├─build
│  │  │  │  │  │          │  ├─dictionary
│  │  │  │  │  │          │  └─META-INF
│  │  │  │  │  │          │      └─services
│  │  │  │  │  │          ├─dist
│  │  │  │  │  │          └─src
│  │  │  │  │  │              ├─dictionary
│  │  │  │  │  │              └─META-INF
│  │  │  │  │  │                  └─services
│  │  │  │  │  └─unsafe
│  │  │  │  └─jvm
│  │  │  │      ├─gc
│  │  │  │      ├─linux
│  │  │  │      └─vmargs
│  │  │  ├─middleware 中间件
│  │  │  │  ├─cache
│  │  │  │  │  ├─local
│  │  │  │  │  │  └─interfaces
│  │  │  │  │  ├─memcache
│  │  │  │  │  │  ├─client
│  │  │  │  │  │  ├─driver
│  │  │  │  │  │  │  ├─io
│  │  │  │  │  │  │  └─util
│  │  │  │  │  │  ├─interfaces
│  │  │  │  │  │  ├─performance
│  │  │  │  │  │  │  └─impl
│  │  │  │  │  │  └─util
│  │  │  │  │  ├─redis
│  │  │  │  │  └─util
│  │  │  │  ├─jackson
│  │  │  │  │  ├─deserializer
│  │  │  │  │  ├─pojo
│  │  │  │  │  └─serializable
│  │  │  │  ├─lucenes
│  │  │  │  │  └─web
│  │  │  │  ├─rabbitmq
│  │  │  │  ├─solr
│  │  │  │  │  └─entity
│  │  │  │  └─zookeeper
│  │  │  ├─net  网络
│  │  │  │  ├─heartbeat
│  │  │  │  ├─http
│  │  │  │  │  ├─download
│  │  │  │  │  └─upload
│  │  │  │  ├─netty
│  │  │  │  ├─serial
│  │  │  │  └─soap
│  │  │  └─utils  工具类
│  │  │      ├─doc
│  │  │      ├─mvn
│  │  │      ├─qr
│  │  │      ├─security
│  │  │      ├─sigar
│  │  │      └─snow
│  │  └─resources 配置文件
│  │      ├─config
│  │      ├─jar
│  │      ├─templates
│  │      │  └─word
│  │      └─面试题
