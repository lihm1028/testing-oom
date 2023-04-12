# testing-oom


# GC
gc() 方法用于调用垃圾收集器以执行清理操作。
gc() 定义在System和Runtime类中。
finalize()方法 每次在垃圾回收对象之前会调用finalize()方法。
finalize()方法可用于执行清理操作。
finalize方法定义在Object类中。


# 参考
http://t.zoukankan.com/wchaos-p-14868351.html


# 查看使用的GC
```
java -XX:+PrintCommandLineFlags -version

-XX:InitialHeapSize=789801856 -XX:MaxHeapSize=12636829696 -XX:+PrintCommandLineFlags -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseParallelGC 

-XX:G1ConcRefinementThreads=4 -XX:GCDrainStackTargetSize=64 -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=2147483648 -XX:+PrintCommandLineFlags -XX:ReservedCodeCacheSize=251658240 -XX:+SegmentedCodeCache -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseG1GC 

-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintReferenceGC -verbose:gc  -XX:+HeapDumpOnOutOfMemoryError
```


#  回收器GC如何选择    指定 UseParallelGC
https://blog.csdn.net/glf2002lucky/article/details/127143060
```
-XX:+UseParallelGC 
-XX:ParallelGCThreads=12   # 配置并行收集器的线程数，即：同时多少个线程一起进行垃圾回收。此值最好配置与处理器数目相等,默认是3/4.
```


# GC算法
标记-清除
标记-整理算法（压缩算法）
标记-复制算法
分代收集算法（思想）


# GC种类 （串行收集器、并行收集器、并发收集器）

Serial 串行收集器：古老、新生代、单线程、STW、
ParNew 收集器：新生代、多线程、-XX:+UseParNewGC选项来开启
Parallel Scavenge 并行清除收集器：新生代、多线程、用户可控制吞吐量、标记-复制算法 应用程序运行时间 / (应用程序运行时间 + GC时间)
Serial Old：
Parallel Old: 老年代、多线程、标记-整理（压缩）算法。

CMS 收集器：老年代、低停顿、多线程、标记-清除算法。分为4步：1-初始标记 2-并发标记 3-重新标记 4-并发清除，有碎片，最主要目标是获取最短垃圾回收停顿时间

G1收集器：目前是最新的，精准控制停顿时间，在不牺牲吞吐量情况下实现低停顿回收，不产生内存碎片,适用新生代和老年代、标记-整理压缩算法、 -XX:+UseG1GC #开启

ZGC: zgc是一款可拓展的低时延，为实现以下几个目标而诞生的垃圾回收器：停顿时间不超过10ms/停顿时间不会导致堆大小增长 /堆大小范围可支持几G到几T

https://www.lmlphp.com/user/16529/article/item/535205/


# Java最前沿技术——ZGC ZGC介绍
原创 Oracle 作者：椰子奶咖 时间：2021-01-26 20:10:32  1705  0
ZGC（The Z Garbage Collector）是JDK 11中推出的一款追求极致低延迟的实验性质的垃圾收集器，它曾经设计目标包括：
停顿时间不超过10ms；
停顿时间不会随着堆的大小，或者活跃对象的大小而增加；
支持8MB~4TB级别的堆（未来支持16TB）。

当初，提出这个目标的时候，有很多人都觉得设计者在吹牛逼。
但今天看来，这些“吹下的牛逼”都在一个个被实现。
基于最新的JDK15来看，“停顿时间不超过10ms”和“支持16TB的堆”这两个目标已经实现，并且官方明确指出JDK15中的ZGC不再 是实验性质的垃圾收集器，且建议投入生产了。
本文会从ZGC的设计思路出发，讲清楚为何ZGC能在低延时场景中的应用中有着如此卓越的表现。

# 组合使用
-XX:+UseSerialGC        新生代：Serial (DefNew)    老年代 Serial Old(PSOldGen)
-XX:+UseParallelGC      新生代：Parallel Scavenge  老年代 Serial Old(PSOldGen)
-XX:+UseParallelOldGC   新生代：Parallel Scavenge  老年代 Parallel Old (ParOldGen)
-XX:+UseParNewGC        新生代：ParNew             老年代 Serial Old(PSOldGen)
-XX:+UseConcMarkSweepG  新生代：ParNew            老年代 CMS+ Serial Old
-XX:+UseG1GC            新生代：G1                 老年代 G1



# 启动jar包时添加参数改变GC回收器类型:
如设置成G1GC（demo.log为GC日志，demo.jar是你要运行的jar包）
java -jar -Xms256m -Xmx512m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -Xloggc:C:\demo.log -XX:+PrintGCDetails -XX:+PrintGCDateStamps demo.jar &
GC回收器类型：
使用SerialGC添加参数: -XX:+UseSerialGC
使用ParallelGC添加参数: -XX:+UseParallelGC
使用CMSGC添加参数: -XX:+UseConcMarkSweepGC
使用G1GC添加参数: -XX:+UseG1GC



# GC 算法与种类
对于垃圾收集（GC）, 我们需要考虑三件事情：哪些内存需要回收？如何判断是垃圾对象？垃圾回收算法有哪些？
https://www.cnblogs.com/qdhxhz/p/9211095.html
https://zhuanlan.zhihu.com/p/297965515

# 频繁FGC解决方案
https://www.cnblogs.com/qmillet/p/12970595.html


# 教你如何通过分析GC日志来进行JVM调优
https://blog.51cto.com/u_14637492/2898787

# 怎么拿到GC日志
-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:D:\\gc.log

[Eden: 180.0M(180.0M)->0.0B(178.0M) Survivors: 26.0M->16.0M Heap: 432.5M(512.0M)->262.5M(512.0M)]
这显示了Eden占用了180M，在收集前它的容量也是180M。收集之后，它的容量降到了0，自所有对象从Eden区疏散/晋升后。它的目标大小增长到了178M,
Survivors收集之后Survivor从26M变到16M，
Heap堆空间总占有量和总容量分别是432M和512M回收之前，回收之后分别变为262和512M。


# GC安全点(Safepoint)

    程序执行时并非在所有地方都能停顿下来开始GC，只有在特定的位置才能停顿下来开始GC，这些位置称为“安全点（Safepoint） ”
    Safe Point的选择很重要，如果太少可能导致GC等待的时间太长，如果太频繁可能导致运行时的性能问题。大部分指令的执行时间都非常短暂，通常会根据“是否具有让程序长时间执行的特征”为标准。
    比如：选择些执行时间较长的指令作为Safe Point， 如方法调用、循环跳转和异常跳转等。

# FGC ---堆空间80%触发FGC
即对整个堆对垃圾回收,堆空间80%触发FGC
包括young gen、old gen、perm ge等内存区域

# 1.YGC和FGC是什么
YGC ：对新生代堆进行gc。频率比较高，因为大部分对象的存活寿命较短，在新生代里被回收。性能耗费较小。
FGC ：全堆范围的gc。默认堆空间使用到达80%(可调整)的时候会触发fgc。以我们生产环境为例，一般比较少会触发fgc，有时10天或一周左右会有一次。

## 2.什么时候执行YGC和FGC
a.Eden空间不足,执行 young gc
b.old空间不足，perm空间不足，调用方法System.gc() ，ygc时的悲观策略, dump live的内存信息时(jmap –dump:live)，都会执行full gc


## 找出导致频繁Full GC的原因
jmap -histo:live 9301
jstat -gcutil 9301 1000

参数说明如下：
S0: 新生代中Survivor space 0区已使用空间的百分比
S1: 新生代中Survivor space 1区已使用空间的百分比
E: 新生代已使用空间的百分比
O: 老年代已使用空间的百分比
P: 永久带已使用空间的百分比
YGC: 从应用程序启动到当前，发生Yang GC 的次数
YGCT: 从应用程序启动到当前，Yang GC所用的时间【单位秒】
FGC: 从应用程序启动到当前，发生Full GC的次数
FGCT: 从应用程序启动到当前，Full GC所用的时间
GCT: 从应用程序启动到当前，用于垃圾回收的总时间【单位秒】


# GC 参数介绍
https://www.cnblogs.com/superlsj/p/11671675.html



# GC 常见信息 打印GC信息
-XX:+PrintGC
-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintReferenceGC -verbose:gc  -XX:+HeapDumpOnOutOfMemoryError
-XX:+PrintReferenceGC  打印GC引用信息


## GC (Allocation Failure) 表明本次引起GC的原因是因为在年轻代中没有足够的空间能够存储新的数据了。
FinalReference 强应用 （GC不会回收它，需要手动释放）
SoftReference 软引用 （内存不足GC才会收）
WeakReference 弱引用 （拥有更短暂的生命周期---GC扫描到回收）
PhantomReference 虚引用 （并不会决定对象的生命周期--在任何时候都可能被垃圾回收器回收，回收，虚引用必须和引用队列绑定）
强引用  >  软引用  >  弱引用  >  虚引用


```
 [GC (Allocation Failure) [SoftReference, 0 refs, 0.0000309 secs][WeakReference, 14 refs, 0.0000153 secs][FinalReference, 105 refs, 0.0000230 secs][PhantomReference, 3 refs, 51 refs, 0.0000266 secs][JNI Weak Reference, 0.0000241 secs]
 [PSYoungGen: 2639854K->4622K(2714112K)] 2965995K->330811K(8306688K), 0.0099945 secs] [Times: user=0.11 sys=0.00, real=0.01 secs] 

PSYoungGen: 2639854K->4622K(2714112K)] 2965995K->330811K(8306688K), 0.0099945 secs


[GC (Allocation Failure) 2023-04-10T21:04:04.514+0800: 562.422: [SoftReference, 0 refs, 0.0000879 secs]
2023-04-10T21:04:04.514+0800: 562.422: [WeakReference, 2 refs, 0.0000500 secs]
2023-04-10T21:04:04.514+0800: 562.422: [FinalReference, 34 refs, 0.0003024 secs]
2023-04-10T21:04:04.514+0800: 562.422: [PhantomReference, 0 refs, 1 refs, 0.0000743 secs]
2023-04-10T21:04:04.514+0800: 562.422: [JNI Weak Reference, 0.0000581 secs]
[PSYoungGen: 2496576K->1024K(2662400K)] 2778176K->282632K(8254976K), 0.0106466 secs] [Times: user=0.08 sys=0.01, real=0.01 secs] 


通过PSYoungGen可以看出，本次进行的是新生代GC

GC (Allocation Failure) [PSYoungGen: 524800K①->12561K②(611840K③)] 524800K④->12649K⑤(2010112K⑥), 0.0345720 secs⑦] [Times: user=0.05⑧ sys=0.01⑨, real=0.03 secs⑩

①：YoungGC前新生代的内存占用量；
②：YoungGC后新生代的内存占用量；
③：新生代总内存大小；
④：YoungGC前JVM堆内存占用量；
⑤：YoungGC后JVM堆内存使用量；
⑥：JVM堆内存总大小；
⑦：YoungGC耗时
⑧：YoungGC用户耗时；
⑨：YoungGC系统耗时；
⑩：YoungGC实际耗时；


```