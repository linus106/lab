/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 * synchronized:jdk自带的同步关键字，通过Monitor对象和MonitorEnter、MonitorExit字节码指令来完成。

 * 1、Monitor对象-C语言实现的数据结构-ObjectMonitor.hpp
 * {
 *     _count=0;        //持有monitor对象的次数(可重入)
 *     _EntryList=NULL; //等待锁的线程:synchronized(object) {}
 *     _WaitSet=NULL;   //等待被唤醒的线程:object.wait();object.notify();
 *     ...省略
 * }
 *
 * 2、synchronized(object) {//do sth}等价于，逻辑跟AQS类似，不赘述
 * MonitorEnter
 * //do sth
 * MonitorExit
 *
 * 3、ReentrantLock VS synchronized-逻辑类似
 * lock.lock()          ==      MonitorEnter
 * lock.unlock()        ==      MonitorExit
 * condition.wait()     ==      object.wait()
 * condition.signal()   ==      object.signal()
 *
 * 4、synchronized的锁升级机制借助对象头的完成，对象结构如下：
 * {
 *     "对象头" : {
 *          "markword" : {                      //4字节 or 8字节，取决虚拟机的位数,轻量级锁、重量级锁、无锁、偏向锁每位的含义均不太一样
 *              01 00 00 00 00 00 00 00
 *              "锁标志位" : 01 00 10 11,        //2位
 *              "是否偏向锁" : 0 1,              //1位
 *              "对象分带年龄": 0 - 15,          //4位
 *              "对象hashcode":,                 //25位
 *          },
 *          "类元数据指针" : 6d 01 00 f8,       //指向元空间对应的类元信息
 *          "数组长度": 0a 00 00 00             //4字节
 *     },
 *     "实例数据" : {//属性值，包括父类属性
 *          "a" : 1
 *     },
 *     "对齐填充";{}//为了保证对象大小是8字节的倍数，起到压缩指针，提升寻址效率的作用。
 * }
 *
 * 5、锁升级过程，不同的对象头见SyncHeader.java
 *
 *                  ->无锁     -> 尝试cas把线程id写到对象头里
 *                /
 * Monitor enter  --->偏向锁   -> 暂停A线程,修改markword和A线程锁记录
 *                \
 *                  ->轻量级锁 -> 尝试cas markword变成自己的锁记录  --超过尝试次数--> 修改markword重量级锁,把自己加入block队列,阻塞自己
 *                 \
 *                  ->重量级锁 -> 把自己加入block队列,阻塞自己
 *
 *
 *                  ->偏向锁     -> 什么都不做
 *                /
 * Monitor exit   --->轻量级锁   -> cas修改markword为无锁 --发现已经变成重量级锁--> 唤醒阻塞的队列
 *                \
 *                  ->重量级锁   -> 什么不做，等待一段时间如果没竞争，markword无锁 (据实验和猜测，3s以上会变成无锁，100ms还是重锁，应该是有个线程专门干这事，把长时间没用的重锁给改成无锁)
 */
package com.linus.lab.juc.sync;