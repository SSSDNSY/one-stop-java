Protocol Buffers - Google's data interchange format
Copyrione-stop-javat 2008 Google Inc.
https://developers.google.com/protocol-buffers/

This package contains a precompiled binary version of the protocol buffer
compiler (protoc). This binary is intended for users who want to use Protocol
Buffers in languages other than C++ but do not want to compile protoc
themselves. To install, simply place this binary somewhere in your PATH.

If you intend to use the included well known types then don't forget to
copy the contents of the 'include' directory somewhere as well, for example
into '/usr/local/include/'.

Please refer to our official github site for more installation instructions:
  https://github.com/protocolbuffers/protobuf



## 关于protobuf自己的理解
- 序列化时不序列号字段名，用数字编号1，2，4等代替，只系列化字段值。
- 可变长度压缩空间
- 特殊符号处理压缩空间 TLV编码，去除没有的符号，使数据更加紧凑
- 序列化的时候，没有赋值的key，不参与序列化，反序列化的时候直接使用默认值填充


## 编译；
protoc -I=源地址 --java_out=目标地址  源地址/xxx.proto

protoc.exe -I=E:\itstack\GIT\itstack.org\itstack-demo-netty\itstack-demo-netty-2-02\src\main\java\org\itstack\demo\netty\proto --java_out=E:\itstack\GIT\itstack.org\itstack-demo-netty\itstack-demo-netty-2-02\src\main
\java MsgInfo.proto
