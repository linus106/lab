package com.linus.lab.juc.jmm;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/8
 * CPU CACHE
 * 空间局部性原则：一个变量被使用，它相邻的变量也有很大的可能接下来会被使用，所以一次读64字节的缓存行
 * 时间局部性原则：一个变量被使用，接下来它可能会再次被使用，所以要缓存起来
 */
public class CPUCacheLine {

    private static final int RUNS = 10;
    private static final int DIMENSION_1 = 1024 * 1024;
    private static final int DIMENSION_2 = 8; // 8 * 8  = 64 字节 = cacheline的size

    private static long[][] longs;


    public static void main(String[] args) {
        /*
         * 初始化数组
         */
        longs = new long[DIMENSION_1][];
        for (int i = 0; i < DIMENSION_1; i++) {
            longs[i] = new long[DIMENSION_2];
            for (int j = 0; j < DIMENSION_2; j++) {
                longs[i][j] = 1L;
            }
        }
        System.out.println("初始化完毕....");

        //一行一行的计算，内存连续
        long sum = 0L;
        long start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int i = 0; i < DIMENSION_1; i++) {//DIMENSION_1=1024*1024
                for (int j=0;j<DIMENSION_2;j++){//6
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time1:"+(System.currentTimeMillis()-start));
        System.out.println("sum1:"+sum);

        //一列一列的计算，内存不连续
        sum = 0L;
        start = System.currentTimeMillis();
        for (int r = 0; r < RUNS; r++) {
            for (int j=0;j<DIMENSION_2;j++) {//6
                for (int i = 0; i < DIMENSION_1; i++){//1024*1024
                    sum+=longs[i][j];
                }
            }
        }
        System.out.println("spend time2:"+(System.currentTimeMillis()-start));
        System.out.println("sum2:"+sum);
    }
}
