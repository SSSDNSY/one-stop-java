#Netty学习包简介

- demo1: IO初体验 BIO NIO AIO 
- demo2: 接收消息
- demo3: 字符串解码器
- demo4: 收发消息
- demo5: 字符串编码器
- demo6: 群发消息
- demo7: 粘包、半包处理
- demo8: ChannelOut/In boundHandlerAdapter 使用
- demo9: UDP的netty实例
- demo10: 基于Netty提供的HTTP服务
- demo10: 基于Netty提供的HTTP服务
- demo11: 集成SpringBoot
- demo15: 心跳服务与断线重连


[netty教程链接](https://bugstack.cn/md/netty)



```markdown
1、ChannelOption.SO_BACKLOG ChannelOption.SO_BACKLOG对应的是tcp/ip协议listen函数中的backlog参数，函数listen(int socketfd,int backlog)用来初始化服务端可连接队列，服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
2、ChannelOption.SO_REUSEADDR ChanneOption.SO_REUSEADDR对应于套接字选项中的SO_REUSEADDR，这个参数表示允许重复使用本地地址和端口， 比如，某个服务器进程占用了TCP的80端口进行监听，此时再次监听该端口就会返回错误，使用该参数就可以解决问题，该参数允许共用该端口，这个在服务器程序中比较常使用， 比如某个进程非正常退出，该程序占用的端口可能要被占用一段时间才能允许其他进程使用，而且程序死掉以后，内核一需要一定的时间才能够释放此端口，不设置SO_REUSEADDR就无法正常使用该端口。
3、ChannelOption.SO_KEEPALIVE Channeloption.SO_KEEPALIVE参数对应于套接字选项中的SO_KEEPALIVE，该参数用于设置TCP连接，当设置该选项以后，连接会测试链接的状态，这个选项用于可能长时间没有数据交流的连接。当设置该选项以后，如果在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文。
4、ChannelOption.SO_SNDBUF和ChannelOption.SO_RCVBUF ChannelOption.SO_SNDBUF参数对应于套接字选项中的SO_SNDBUF，ChannelOption.SO_RCVBUF参数对应于套接字选项中的SO_RCVBUF这两个参数用于操作接收缓冲区和发送缓冲区的大小，接收缓冲区用于保存网络协议站内收到的数据，直到应用程序读取成功，发送缓冲区用于保存发送数据，直到发送成功。
5、ChannelOption.SO_LINGER ChannelOption.SO_LINGER参数对应于套接字选项中的SO_LINGER,Linux内核默认的处理方式是当用户调用close（）方法的时候，函数返回，在可能的情况下，尽量发送数据，不一定保证会发生剩余的数据，造成了数据的不确定性，使用SO_LINGER可以阻塞close()的调用时间，直到数据完全发送
6、ChannelOption.TCP_NODELAY ChannelOption.TCP_NODELAY参数对应于套接字选项中的TCP_NODELAY,该参数的使用与Nagle算法有关,Nagle算法是将小的数据包组装为更大的帧然后进行发送，而不是输入一次发送一次,因此在数据包不足的时候会等待其他数据的到了，组装成大的数据包进行发送，虽然该方式有效提高网络的有效负载，但是却造成了延时，而该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输，于TCP_NODELAY相对应的是TCP_CORK，该选项是需要等到发送的数据量最大的时候，一次性发送数据，适用于文件传输。
7、IP_TOS IP参数，设置IP头部的Type-of-Service字段，用于描述IP包的优先级和QoS选项。
8、ALLOW_HALF_CLOSURE Netty参数，一个连接的远端关闭时本地端是否关闭，默认值为False。值为False时，连接自动关闭；为True时，触发ChannelInboundHandler的userEventTriggered()方法，事件为ChannelInputShutdownEvent。
```


#ByteBuf的数据结构在使用方式中的剖析

## 数据结构
```yaml
     +-------------------+------------------+------------------+
     | discardable bytes |  readable bytes  |  writable bytes  |
     |                   |     (CONTENT)    |                  |
     +-------------------+------------------+------------------+
     |                   |                  |                  |
     0      <=      readerIndex   <=   writerIndex    <=    capacity
     
```

##内存模型
    **1、堆内内存（JVM堆空间内** 最常用的ByteBuf模式是将数据存储在JVM的堆空间中。它能在没有使用池化的情况下提供快速的分配和释放。
    **2、堆外内存（本机直接内存** JDK允许JVM实现通过本地调用来分配内存。主要是为了避免每次调用本地I/O操作之前（或者之后）将缓冲区的内容复制到一个中间缓冲区（或者从中间缓冲区把内容复制到缓冲区）。
    **3、复合缓冲区（以上2种缓冲区多个混合** 常用类：CompositeByteBuf，它为多个ByteBuf提供一个聚合视图，将多个缓冲区表示为单个合并缓冲区的虚拟表示。 比如：HTTP协议：头部和主体这两部分由应用程序的不同模块产生。这个时候把这两部分合并的话，选择CompositeByteBuf是比较好的。

 ##功能案例
```java
    ByteBufTest.java
```
    
 
 ##内容总结
     ByteBuf提供了两个指针；读、写，分别用来标记“可读”、“可写”、“可丢弃”的字节
     通过调用write*方法写入数据后，写指针将会向后移动
     通过调用read*方法读取数据后，读指针将会向后移动
     写入数据或读取数据时会检查是否有足够多的空间可以写入和是否有数据可以读取
     写入数据之前会进行容量检查，当剩余可写的容量小于需要写入的容量时，需要执行扩容操作
     clear等修改读写指针的方法，只会更改读写指针的值，并不会影响ByteBuf中已有的内容
     
# ctx.writeAndFlush()


```xml
   I/O Request
                                           via {@link Channel} or
                                       {@link ChannelHandlerContext}
                                                     |
 +---------------------------------------------------+---------------+
 |                           ChannelPipeline         |               |
 |                                                  \|/              |
 |    +---------------------+            +-----------+----------+    |
 |    | Inbound Handler  N  |            | Outbound Handler  1  |    |
 |    +----------+----------+            +-----------+----------+    |
 |              /|\                                  |               |
 |               |                                  \|/              |
 |    +----------+----------+            +-----------+----------+    |
 |    | Inbound Handler N-1 |            | Outbound Handler  2  |    |
 |    +----------+----------+            +-----------+----------+    |
 |              /|\                                  .               |
 |               .                                   .               |
 | ChannelHandlerContext.fireIN_EVT() ChannelHandlerContext.OUT_EVT()|
 |        [ method call]                       [method call]         |
 |               .                                   .               |
 |               .                                  \|/              |
 |    +----------+----------+            +-----------+----------+    |
 |    | Inbound Handler  2  |            | Outbound Handler M-1 |    |
 |    +----------+----------+            +-----------+----------+    |
 |              /|\                                  |               |
 |               |                                  \|/              |
 |    +----------+----------+            +-----------+----------+    |
 |    | Inbound Handler  1  |            | Outbound Handler  M  |    |
 |    +----------+----------+            +-----------+----------+    |
 |              /|\                                  |               |
 +---------------+-----------------------------------+---------------+
                 |                                  \|/
 +---------------+-----------------------------------+---------------+
 |               |                                   |               |
 |       [ Socket.read() ]                    [ Socket.write() ]     |
 |                                                                   |
 |  Netty Internal I/O Threads (Transport Implementation)            |
 +-------------------------------------------------------------------+
```
 
 
 
 
 