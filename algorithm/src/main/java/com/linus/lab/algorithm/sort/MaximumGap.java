package com.linus.lab.algorithm.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.Math.ceil;

/**
 * @Author wangxiangyu
 * @Date 2020/11/26 17:22
 * @Description TODO
 */
public class MaximumGap {

    public int maximumGapBucket(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        if (max == min) return 0;
        int bucketSize = (int) Math.ceil((double)(max - min) / (n - 1));
        int bucketNum = (int)Math.floor((double)(max - min) / bucketSize) + 1;
        int buckets[][] = new int[bucketNum][2];
        for (int[] bucket : buckets) {
            Arrays.fill(bucket, -1);
        }
        for (int num : nums) {
            int bucketIndex = (num - min) / bucketSize;
            if (buckets[bucketIndex][0] == -1) {
                buckets[bucketIndex][0] = num;
                buckets[bucketIndex][1] = num;
            } else {
                buckets[bucketIndex][0] = Math.min(buckets[bucketIndex][0], num);
                buckets[bucketIndex][1] = Math.max(buckets[bucketIndex][1], num);
            }
        }

        int res = 0, prev = -1;

        for (int i = 0; i < bucketNum; i++) {
            if (buckets[i][0] != -1) {
                if (prev != -1) res = Math.max(res, buckets[i][0] - buckets[prev][1]);
                prev = i;
            }
        }
        return res;
    }


    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        return IntStream.range(0, nums.length - 1).map(i -> nums[i + 1] - nums[i]).max().orElse(0);
    }

    public static void main(String[] args) {
        MaximumGap o = new MaximumGap();
        int res = o.maximumGapBucket(new int[]{3,6,9,1});
        System.out.println(res);
    }
}
