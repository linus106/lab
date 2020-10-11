/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/12
 *
 * AbstractQueuedSynchronizer-抽象队列同步器
 * aqs:jdk以juc包的形式提供给开发者，由doug lea通过java实现
 *
 * 1、从lock认识AQS
 * 要实现锁，主要要实现两个功能：lock和unlock。除此之外lock代码块内支持条件等待和唤醒，类似synchronized中的wait和notify
 * lock: 判断锁状态 --无锁-->获取锁(修改锁状态)
 *                  \
 *                   -有锁-->等待:自旋(继续使用CPU)或者阻塞(不继续使用CPU)
 *
 * unlock: 修改锁状态--唤醒阻塞线程
 * lock.condition.await:释放当前锁,进入等待状态,等待某个开发者定义的条件满足;
 *                  |
 *               signal:唤醒其他await状态的线程
 *
 * 2、同步的其他场景
 * 除了lock，juc提供了很多其他的同步工具：
 * (1)Semaphore-基于AQS                   信号量:同时允许N个线程执行同步代码
 * (2)ReentrantReadWriteLock-基于AQS      读写锁:写时不能读写;读时不能写
 * (3)CountDownLatch-基于AQS              等待其他N个任务执行完
 * (4)CyclicBarrier-基于Lock              等待其他N-1个任务到达等待点，再同时执行后边代码
 * (5)BlockingQueue-基于Lock-condition    队列+线程安全+条件等待
 * (6)ThreadPoolExecutor-基于BQ和AQS      线程池:任务通过阻塞度列管理,Work的打断通过AQS实现
 * (7)ConcurrentHashMap-基于Lock          ConcurrentXXX:基本的数据结构的线程安全实现
 * (8)CopyOnWriteArrayList-基于CAS        CopyOnWriteXXX:复制-修改-cas写回,没有同步代码，也就不需要锁，用空间换时间
 * (9)Exchanger-基于CAS和ThreadLocal      线程对间交换数据
 * (10)ForkJoinPool-基于BQ和AQS           CPU密集型任务，递归切分子任务，用线程池执行，工作窃取算法保障工作量均衡。
 * (11)DelayQueue-基于PriorityQueue和BQ   PriorityQueue是一种有序队列,保证时间先的在队头;同时基于BQ,来实现延迟性
 * (12)ScheduledThreadPoolExecutor基于DQ  在DQ的基础上，执行完任务,在队列中加入新的任务。
 * (13)AtomicInteger基于CAS               逻辑简单,通过CAS不需要同步代码可以保证线程安全。
 *
 * 3、AQS的基本功能
 * AQS是一种对同步操作的抽象，包括几个部分:
 * (0)CAS
 * AQS能实现线程间同步的一个前提是存在一种(读-判断-写)的轻量级基本原子操作,否则为了保证多线程下的线程安全，要么同步粒
 * 度会非常大，根本没法并行。要么到处都是调用操作系统底层的互斥量代码，程序性能很差。CAS就是这样的一种轻量级原子操作，通
 * 过循环的包装成为了一种自旋锁。CAS最大的问题是它的逻辑不支持开发人员扩展，针对复杂的场景就需要有进一步的抽象。
 *
 * (1)状态-@see LockStructure.java
 * 表明当前同步状态是否有其他线程正在执行，有几个线程正在执行(Semaphore)。状态是决定线程应该等待还是执行同步代码
 * 的依据。当决定能进入同步状态的时候，先用CAS修改状态，修改成功则可以执行同步代码。
 *
 * (2)同步队列
 * 没获取到锁的线程不能执行同步代码块，也不能放弃锁的竞争，因此需要有一种等待的机制。AQS和sync的机制类似，都是先自旋等待
 * 再阻塞等待。针对阻塞等待的线程，由正在执行同步代码的线程负责唤醒。因此采用了CLH双端队列的方式把等待的锁的线程记录下来
 * ，由将要阻塞自己的线程负责记录。阻塞是通过UNSAFE类中的park和unpark来实现。
 *
 * (3)条件队列
 * 除了同步阻塞，还存在条件阻塞的情况。例如阻塞队列的put()|take()在无竞争且队列满|空的情况下为了避免CPU空转，同样需要以
 * 阻塞的方式来实现等待。不同于同步队列，条件队列可能存在多个。比如：A线程尝试从阻塞队列获取数据，发现条件不满足，则先把
 * 自己加入到条件队列中，再阻塞自己。B线程在put方法执行成功之后，有责任唤醒阻塞在"队列不空"这个条件上的线程。
 *
 * 4、AQS的扩展
 * AQS的基本结构满足了其同步的核心功能，通过继承aqs进一步扩展。
 * (1)可重入锁
 * 记录thread,当获取不到锁的时候，判断是否为同一个thread，如果是则把state + 1，然后继续执行同步代码。退出的时候state-1
 * (2)公平锁 | 非公平锁
 * 新来的线程能不能和队列中的线程竞争锁，如果可以就是非公平锁，如果不可以就是公平锁。进入到队列，顺序就固定了下来。
 * (3)中断
 * lockInterruptibly() 等待获取锁的过程中，可以通过thread.interrupt()来中断。
 *
 */
package com.linus.lab.juc.aqs;