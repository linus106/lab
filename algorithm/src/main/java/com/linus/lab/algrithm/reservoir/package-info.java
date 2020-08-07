package com.linus.lab.algrithm.reservoir;

/*
蓄水池抽样算法
其目的在于从包含 n 个项目的集合 S 中随机选取 k 个样本
其中 n 为一很大或未知的数量，尤其适用于不能把所有 n 个项目都存放到内存的情况。


算法描述：
1、在内存中开辟一块大小为k空间，为num[]。
2、将集合S的前k个元素先放入到num[]中。
3、遍历S集合中k+1及之后的元素 "尝试" 替换进num[]中，替换规则：对第i个元素，通过随机数x in [0,i)，保证有k/i的概率进入数组，替换num[x]的位置

以保证对于S中的任意元素，其最终留在num[]的概率均相等且等于k/n


算法理解：
对于前k个元素，一开始必然在num[]中，所以概率为1。
当出现了第k+1个元素的时候，其 不 被替换的概率是: k/k+1
当出现了第k+2个元素的时候，其 不 被替换的概率是: k+1/k+2
以此类推i ：i-1/i
其概率为 :  1 * k/k+1 * k+1/k+2 * .............  * n-1/n = k/n

对于k+1及后边的第i个元素，要留到最后，要保证：1、能进到num[]; 2、不被替换出去
进入到num[]的概率：k/i
不被替换出去的概率：i/i+1 * i+1/i+2 * .............  * n-1/n = i/n
最终概率 = k/i * i/n = k/n

因此对于S中的任意元素，每个元素的最终留在num[]的概率都是k/n


时间复杂度:O(n) 空间复杂度O(k)
 */