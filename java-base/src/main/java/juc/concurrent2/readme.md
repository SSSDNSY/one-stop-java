## 多线程

众所周知，CPU、内存、I/O 设备的速度是有极大差异的，为了合理利用 CPU
的高性能，平衡这三者的速度差异，计算机体系结构、操作系统、编译程序都做出了贡献，主要体现为:

- CPU 增加了缓存，以均衡与内存的速度差异；// 导致 可见性问题
- 操作系统增加了进程、线程，以分时复用 CPU，进而均衡 CPU 与 I/O 设备的速度差异；// 导致 原子性问题
- 编译程序优化指令执行次序，使得缓存能够得到更加合理地利用。// 导致 有序性问题

### 线程安全的方法

- 互斥同步 关键字: Synchronized详解 JUC锁: ReentrantLock详解
- 非阻塞同步 atomicInteger 原子类（CAS）
- 无同步方案 线程私有变量 threadLocal 可重入代码(Reentrant Code\Pure code)

### 基础线程机制

- ¶Executor   
  CachedThreadPool: 一个任务创建一个线程；
  FixedThreadPool: 所有任务只能使用固定大小的线程；
  SingleThreadExecutor: 相当于大小为 1 的 FixedThreadPool
- ¶Daemon
  守护线程是程序运行时在后台提供服务的线程，不属于程序中不可或缺的部分。
  当所有非守护线程结束时，程序也就终止，同时会杀死所有守护线程。

### 锁的类型

    Synchronied同步锁，一共有四种状态：无锁、偏向锁、轻量级所、重量级锁
     