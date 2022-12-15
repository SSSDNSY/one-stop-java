# one-stop-java

<p align="center">
  <a href="https://travis-ci.com/xkcoding/spring-boot-demo"><img alt="Travis-CI" src="https://travis-ci.com/xkcoding/spring-boot-demo.svg?branch=master"/></a>
  <a href="https://www.oracle.com/technetwork/java/javase/downloads/index.html"><img alt="JDK" src="https://img.shields.io/badge/JDK-1.8.0_162-orange.svg"/></a>
  <a href="https://docs.spring.io/spring-boot/docs/2.1.0.RELEASE/reference/html/"><img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-2.1.0.RELEASE-brione-stop-javatgreen.svg"/></a>
</p>

## java 知识体系个人学习和复习 

## 模块结构 

```text

├───src                          
│   ├───main                     
│   │   ├───java                 
│   │   │   ├───algorithm        
│   │   │   │   ├───array1string 
│   │   │   │   ├───hash
│   │   │   │   ├───LinkedList
│   │   │   │   ├───ods
│   │   │   │   ├───other
│   │   │   │   │   └───famous
│   │   │   │   ├───printer
│   │   │   │   │   └───tree
│   │   │   │   ├───queue
│   │   │   │   ├───sort
│   │   │   │   ├───stack
│   │   │   │   ├───think
│   │   │   │   └───tree
│   │   │   │       ├───base
│   │   │   │       └───benchmark
│   │   │   ├───designpattern
│   │   │   │   ├───command
│   │   │   │   ├───observer
│   │   │   │   └───singleton 
│   │   │   ├───gui
│   │   │   │   ├───javafx
│   │   │   │   └───yfiles
│   │   │   ├───lang
│   │   │   │   ├───abasic
│   │   │   │   ├───athread
│   │   │   │   ├───bcollections
│   │   │   │   ├───cjuc
│   │   │   │   │   ├───concurrent
│   │   │   │   │   │   ├───collection
│   │   │   │   │   │   ├───concurrentUtils
│   │   │   │   │   │   ├───disrupter
│   │   │   │   │   │   │   └───base
│   │   │   │   │   │   ├───lock
│   │   │   │   │   │   ├───masterworker
│   │   │   │   │   │   └───threadpools
│   │   │   │   │   └───concurrent2
│   │   │   │   │       ├───img
│   │   │   │   │       └───killdemo
│   │   │   │   ├───dio
│   │   │   │   │   ├───aio
│   │   │   │   │   ├───bio
│   │   │   │   │   └───nio
│   │   │   │   ├───features
│   │   │   │   │   ├───annotation
│   │   │   │   │   ├───jdk8
│   │   │   │   │   ├───jdk9
│   │   │   │   │   ├───jmx
│   │   │   │   │   ├───reflection
│   │   │   │   │   ├───spi
│   │   │   │   │   │   └───DictionaryServiceDemo
│   │   │   │   │   │       ├───DictionaryDemo
│   │   │   │   │   │       │   ├───build 
│   │   │   │   │   │       │   │   └───dictionary
│   │   │   │   │   │       │   ├───dist
│   │   │   │   │   │       │   └───src
│   │   │   │   │   │       │       └───dictionary
│   │   │   │   │   │       ├───DictionaryServiceProvider
│   │   │   │   │   │       │   ├───build
│   │   │   │   │   │       │   │   └───dictionary
│   │   │   │   │   │       │   │       └───spi
│   │   │   │   │   │       │   ├───dist
│   │   │   │   │   │       │   └───src
│   │   │   │   │   │       │       └───dictionary
│   │   │   │   │   │       │           └───spi
│   │   │   │   │   │       ├───ExtendedDictionary
│   │   │   │   │   │       │   ├───build
│   │   │   │   │   │       │   │   ├───dictionary
│   │   │   │   │   │       │   │   └───META-INF
│   │   │   │   │   │       │   │       └───services
│   │   │   │   │   │       │   ├───dist
│   │   │   │   │   │       │   └───src
│   │   │   │   │   │       │       ├───dictionary
│   │   │   │   │   │       │       └───META-INF
│   │   │   │   │   │       │           └───services
│   │   │   │   │   │       └───GeneralDictionary
│   │   │   │   │   │           ├───build
│   │   │   │   │   │           │   ├───dictionary 
│   │   │   │   │   │           │   └───META-INF
│   │   │   │   │   │           │       └───services
│   │   │   │   │   │           ├───dist
│   │   │   │   │   │           └───src
│   │   │   │   │   │               ├───dictionary
│   │   │   │   │   │               └───META-INF
│   │   │   │   │   │                   └───services
│   │   │   │   │   └───unsafe
│   │   │   │   └───jvm
│   │   │   │       ├───gc
│   │   │   │       ├───linux
│   │   │   │       └───vmargs
│   │   │   ├───middleware
│   │   │   │   ├───activiemq
│   │   │   │   ├───arthas
│   │   │   │   ├───cache
│   │   │   │   │   ├───local
│   │   │   │   │   │   └───interfaces
│   │   │   │   │   ├───memcache
│   │   │   │   │   │   ├───client
│   │   │   │   │   │   ├───driver
│   │   │   │   │   │   │   ├───io
│   │   │   │   │   │   │   └───util
│   │   │   │   │   │   ├───interfaces 
│   │   │   │   │   │   ├───performance
│   │   │   │   │   │   │   └───impl
│   │   │   │   │   │   └───util
│   │   │   │   │   ├───redis
│   │   │   │   │   └───util
│   │   │   │   ├───es
│   │   │   │   │   └───doc
│   │   │   │   ├───jackson
│   │   │   │   │   ├───deserializer
│   │   │   │   │   ├───pojo
│   │   │   │   │   └───serializable
│   │   │   │   ├───lucenes
│   │   │   │   │   └───web
│   │   │   │   ├───protoc
│   │   │   │   │   ├───protoc-3.20.1-win32
│   │   │   │   │   │   ├───bin
│   │   │   │   │   │   └───include
│   │   │   │   │   │       └───google
│   │   │   │   │   │           └───protobuf
│   │   │   │   │   │               └───compiler
│   │   │   │   │   └───protoc-3.20.1-win64
│   │   │   │   │       ├───bin 
│   │   │   │   │       └───include
│   │   │   │   │           └───google
│   │   │   │   │               └───protobuf
│   │   │   │   │                   └───compiler
│   │   │   │   ├───rabbitmq
│   │   │   │   ├───solr
│   │   │   │   │   └───entity
│   │   │   │   └───zookeeper
│   │   │   ├───net
│   │   │   │   ├───heartbeat
│   │   │   │   ├───netty
│   │   │   │   │   ├───demo0
│   │   │   │   │   ├───demo1
│   │   │   │   │   │   ├───aio
│   │   │   │   │   │   ├───bio
│   │   │   │   │   │   └───nio
│   │   │   │   │   ├───demo10
│   │   │   │   │   ├───demo11
│   │   │   │   │   ├───demo12
│   │   │   │   │   │   ├───client
│   │   │   │   │   │   ├───domain
│   │   │   │   │   │   ├───proto
│   │   │   │   │   │   ├───server
│   │   │   │   │   │   └───util
│   │   │   │   │   ├───demo13 
│   │   │   │   │   │   ├───client
│   │   │   │   │   │   ├───codec
│   │   │   │   │   │   ├───domain
│   │   │   │   │   │   ├───server
│   │   │   │   │   │   └───util
│   │   │   │   │   ├───demo14
│   │   │   │   │   │   ├───client
│   │   │   │   │   │   ├───codec
│   │   │   │   │   │   ├───domain
│   │   │   │   │   │   ├───server
│   │   │   │   │   │   └───util
│   │   │   │   │   ├───demo15
│   │   │   │   │   │   ├───client
│   │   │   │   │   │   ├───domain
│   │   │   │   │   │   ├───server
│   │   │   │   │   │   ├───util
│   │   │   │   │   │   └───web
│   │   │   │   │   ├───demo2
│   │   │   │   │   ├───demo3
│   │   │   │   │   ├───demo4
│   │   │   │   │   ├───demo5
│   │   │   │   │   ├───demo6
│   │   │   │   │   ├───demo7
│   │   │   │   │   ├───demo8
│   │   │   │   │   └───demo9
│   │   │   │   ├───serial
│   │   │   │   ├───soap
│   │   │   │   └───updownload
│   │   │   │       ├───download
│   │   │   │       └───upload
│   │   │   └───utils
│   │   │       ├───doc
│   │   │       ├───mvn
│   │   │       ├───qr
│   │   │       ├───security
│   │   │       ├───sigar
│   │   │       └───snow
│   │   └───resources
│   │       ├───config
│   │       ├───jar
│   │       ├───templates 
│   │       │   └───word
│   │       ├───webapp
│   │       │   ├───res
│   │       │   │   ├───img
│   │       │   │   └───js
│   │       │   └───WEB-INF
│   │       └───面试题
│   └───test
│       └───java
│           └───algorithm
│               └───tree
└───target
    ├───classes
    │   ├───algorithm
    │   │   ├───array1string 
    │   │   ├───hash
    │   │   ├───LinkedList
    │   │   ├───ods 
    │   │   ├───other 
    │   │   │   └───famous
    │   │   ├───printer
    │   │   │   └───tree 
    │   │   ├───queue 
    │   │   ├───sort
    │   │   ├───stack 
    │   │   ├───think 
    │   │   └───tree
    │   │       ├───base 
    │   │       └───benchmark
    │   │           └───jmh_generated
    │   ├───config 
    │   ├───designpattern 
    │   │   ├───command 
    │   │   ├───observer 
    │   │   └───singleton
    │   ├───dictionary
    │   │   └───spi
    │   ├───gui
    │   │   └───javafx 
    │   ├───jar
    │   ├───lang
    │   │   ├───abasic
    │   │   ├───bcollections 
    │   │   ├───cjuc
    │   │   │   ├───concurrent
    │   │   │   │   ├───collection 
    │   │   │   │   ├───concurrentUtils
    │   │   │   │   ├───disrupter 
    │   │   │   │   │   └───base 
    │   │   │   │   ├───lock
    │   │   │   │   ├───masterworker
    │   │   │   │   └───threadpools
    │   │   │   └───concurrent2
    │   │   │       └───killdemo 
    │   │   ├───dio
    │   │   │   ├───aio
    │   │   │   ├───bio
    │   │   │   └───nio 
    │   │   ├───features
    │   │   │   ├───annotation 
    │   │   │   ├───jdk8 
    │   │   │   ├───jdk9 
    │   │   │   ├───jmx
    │   │   │   ├───reflection
    │   │   │   └───unsafe
    │   │   └───jvm
    │   │       ├───gc
    │   │       └───vmargs
    │   ├───META-INF 
    │   ├───middleware
    │   │   ├───activiemq 
    │   │   ├───arthas
    │   │   ├───cache
    │   │   │   ├───local
    │   │   │   │   └───interfaces 
    │   │   │   ├───memcache
    │   │   │   │   ├───client 
    │   │   │   │   ├───driver 
    │   │   │   │   │   ├───io
    │   │   │   │   │   └───util
    │   │   │   │   ├───interfaces
    │   │   │   │   ├───performance
    │   │   │   │   │   └───impl
    │   │   │   │   └───util
    │   │   │   ├───redis
    │   │   │   └───util
    │   │   ├───jackson
    │   │   │   ├───deserializer 
    │   │   │   ├───pojo
    │   │   │   └───serializable
    │   │   ├───lucenes
    │   │   │   └───web
    │   │   ├───rabbitmq
    │   │   ├───solr 
    │   │   │   └───entity
    │   │   └───zookeeper
    │   ├───net
    │   │   ├───heartbeat
    │   │   ├───netty 
    │   │   │   ├───demo0 
    │   │   │   ├───demo1
    │   │   │   │   ├───aio
    │   │   │   │   ├───bio
    │   │   │   │   └───nio
    │   │   │   ├───demo10 
    │   │   │   ├───demo12
    │   │   │   │   ├───client
    │   │   │   │   ├───domain
    │   │   │   │   ├───server
    │   │   │   │   └───util
    │   │   │   ├───demo13
    │   │   │   │   ├───client
    │   │   │   │   ├───codec
    │   │   │   │   ├───domain
    │   │   │   │   ├───server
    │   │   │   │   └───util
    │   │   │   ├───demo14
    │   │   │   │   ├───client
    │   │   │   │   ├───codec
    │   │   │   │   ├───domain
    │   │   │   │   ├───server 
    │   │   │   │   └───util
    │   │   │   ├───demo15
    │   │   │   │   ├───client
    │   │   │   │   ├───domain
    │   │   │   │   ├───server
    │   │   │   │   ├───util
    │   │   │   │   └───web
    │   │   │   ├───demo2
    │   │   │   ├───demo3
    │   │   │   ├───demo4
    │   │   │   ├───demo5
    │   │   │   ├───demo6
    │   │   │   ├───demo7
    │   │   │   ├───demo8
    │   │   │   └───demo9 
    │   │   ├───serial 
    │   │   └───soap
    │   ├───templates
    │   │   └───word 
    │   ├───utils
    │   │   ├───doc
    │   │   ├───qr 
PS E:\source\one-stop-java\src> tree
卷 work 的文件夹 PATH 列表   
卷序列号为 4804-6FD7         
E:.                          
├───main                     
│   ├───java                 
│   │   ├───algorithm        
│   │   │   ├───array1string 
│   │   │   ├───hash         
│   │   │   ├───LinkedList   
│   │   │   ├───ods          
│   │   │   ├───other        
│   │   │   │   └───famous   
│   │   │   ├───printer      
│   │   │   │   └───tree     
│   │   │   ├───queue        
│   │   │   ├───sort         
│   │   │   ├───stack
│   │   │   ├───think
│   │   │   └───tree
│   │   │       ├───base
│   │   │       └───benchmark
│   │   ├───designpattern
│   │   │   ├───command
│   │   │   ├───observer
│   │   │   └───singleton
│   │   ├───gui
│   │   │   ├───javafx
│   │   │   └───yfiles
│   │   ├───lang
│   │   │   ├───abasic
│   │   │   ├───athread
│   │   │   ├───bcollections
│   │   │   ├───cjuc
│   │   │   │   ├───concurrent
│   │   │   │   │   ├───collection
│   │   │   │   │   ├───concurrentUtils
│   │   │   │   │   ├───disrupter
│   │   │   │   │   │   └───base
│   │   │   │   │   ├───lock
│   │   │   │   │   ├───masterworker
│   │   │   │   │   └───threadpools 
│   │   │   │   └───concurrent2
│   │   │   │       ├───img
│   │   │   │       └───killdemo
│   │   │   ├───dio
│   │   │   │   ├───aio
│   │   │   │   ├───bio
│   │   │   │   └───nio
│   │   │   ├───features
│   │   │   │   ├───annotation
│   │   │   │   ├───jdk8
│   │   │   │   ├───jdk9
│   │   │   │   ├───jmx
│   │   │   │   ├───reflection
│   │   │   │   ├───spi
│   │   │   │   │   └───DictionaryServiceDemo
│   │   │   │   │       ├───DictionaryDemo
│   │   │   │   │       │   ├───build
│   │   │   │   │       │   │   └───dictionary
│   │   │   │   │       │   ├───dist
│   │   │   │   │       │   └───src
│   │   │   │   │       │       └───dictionary
│   │   │   │   │       ├───DictionaryServiceProvider
│   │   │   │   │       │   ├───build
│   │   │   │   │       │   │   └───dictionary
│   │   │   │   │       │   │       └───spi
│   │   │   │   │       │   ├───dist
│   │   │   │   │       │   └───src
│   │   │   │   │       │       └───dictionary
│   │   │   │   │       │           └───spi
│   │   │   │   │       ├───ExtendedDictionary
│   │   │   │   │       │   ├───build
│   │   │   │   │       │   │   ├───dictionary
│   │   │   │   │       │   │   └───META-INF
│   │   │   │   │       │   │       └───services
│   │   │   │   │       │   ├───dist
│   │   │   │   │       │   └───src
│   │   │   │   │       │       ├───dictionary
│   │   │   │   │       │       └───META-INF
│   │   │   │   │       │           └───services
│   │   │   │   │       └───GeneralDictionary
│   │   │   │   │           ├───build
│   │   │   │   │           │   ├───dictionary 
│   │   │   │   │           │   └───META-INF
│   │   │   │   │           │       └───services
│   │   │   │   │           ├───dist
│   │   │   │   │           └───src
│   │   │   │   │               ├───dictionary
│   │   │   │   │               └───META-INF
│   │   │   │   │                   └───services
│   │   │   │   └───unsafe
│   │   │   └───jvm
│   │   │       ├───gc
│   │   │       ├───linux
│   │   │       └───vmargs
│   │   ├───middleware
│   │   │   ├───activiemq
│   │   │   ├───arthas
│   │   │   ├───cache
│   │   │   │   ├───local
│   │   │   │   │   └───interfaces
│   │   │   │   ├───memcache
│   │   │   │   │   ├───client
│   │   │   │   │   ├───driver
│   │   │   │   │   │   ├───io
│   │   │   │   │   │   └───util
│   │   │   │   │   ├───interfaces
│   │   │   │   │   ├───performance
│   │   │   │   │   │   └───impl
│   │   │   │   │   └───util
│   │   │   │   ├───redis
│   │   │   │   └───util
│   │   │   ├───es
│   │   │   │   └───doc
│   │   │   ├───jackson
│   │   │   │   ├───deserializer
│   │   │   │   ├───pojo
│   │   │   │   └───serializable
│   │   │   ├───lucenes
│   │   │   │   └───web 
│   │   │   ├───protoc
│   │   │   │   ├───protoc-3.20.1-win32
│   │   │   │   │   ├───bin
│   │   │   │   │   └───include
│   │   │   │   │       └───google
│   │   │   │   │           └───protobuf
│   │   │   │   │               └───compiler
│   │   │   │   └───protoc-3.20.1-win64
│   │   │   │       ├───bin
│   │   │   │       └───include
│   │   │   │           └───google
│   │   │   │               └───protobuf
│   │   │   │                   └───compiler
│   │   │   ├───rabbitmq
│   │   │   ├───solr
│   │   │   │   └───entity
│   │   │   └───zookeeper
│   │   ├───net
│   │   │   ├───heartbeat
│   │   │   ├───netty
│   │   │   │   ├───demo0
│   │   │   │   ├───demo1
│   │   │   │   │   ├───aio
│   │   │   │   │   ├───bio
│   │   │   │   │   └───nio
│   │   │   │   ├───demo10
│   │   │   │   ├───demo11
│   │   │   │   ├───demo12
│   │   │   │   │   ├───client
│   │   │   │   │   ├───domain
│   │   │   │   │   ├───proto
│   │   │   │   │   ├───server
│   │   │   │   │   └───util
│   │   │   │   ├───demo13
│   │   │   │   │   ├───client
│   │   │   │   │   ├───codec
│   │   │   │   │   ├───domain
│   │   │   │   │   ├───server 
│   │   │   │   │   └───util
│   │   │   │   ├───demo14
│   │   │   │   │   ├───client
│   │   │   │   │   ├───codec
│   │   │   │   │   ├───domain
│   │   │   │   │   ├───server
│   │   │   │   │   └───util
│   │   │   │   ├───demo15
│   │   │   │   │   ├───client
│   │   │   │   │   ├───domain
│   │   │   │   │   ├───server
│   │   │   │   │   ├───util
│   │   │   │   │   └───web
│   │   │   │   ├───demo2
│   │   │   │   ├───demo3
│   │   │   │   ├───demo4
│   │   │   │   ├───demo5
│   │   │   │   ├───demo6
│   │   │   │   ├───demo7
│   │   │   │   ├───demo8
│   │   │   │   └───demo9
│   │   │   ├───serial
│   │   │   ├───soap
│   │   │   └───updownload
│   │   │       ├───download
│   │   │       └───upload
│   │   └───utils
│   │       ├───doc
│   │       ├───mvn
│   │       ├───qr
│   │       ├───security
│   │       ├───sigar
│   │       └───snow
│   └───resources
│       ├───config
│       ├───jar
│       ├───templates
│       │   └───word
│       ├───webapp
│       │   ├───res
│       │   │   ├───img
│       │   │   └───js
│       │   └───WEB-INF
│       └───面试题
└───test
    └───java
        └───algorithm
            └───tree

```
