package com.linus.lab.algorithm.greedy;

/**
 * @Author wangxiangyu
 * @Date 2020/11/18 14:04
 * @Description TODO
 * https://leetcode-cn.com/problems/gas-station/
 */
public class GasStation {

    public int canCompleteCircuitOptOpt(int[] gas, int[] cost) {
        int total = 0, cur = 0, start = 0;
        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            total += netGas;
            cur += netGas;
            if (cur < 0) {
                start = i + 1;// i+1是下一个可能的start,start到i都可以忽略。
                cur = 0;
            }
        }
        return total >= 0 ? start: -1;//如果总的净油不为负，则一定存在这样的路线
    }

    public int canCompleteCircuitOpt(int[] gas, int[] cost) {
        int N = gas.length;
        int[] netGas = new int[N];
        for (int i = 0; i < N; i++) {
            netGas[i] = gas[i] - cost[i];
        }


        for (int start = 0; start < N; start++) {
            int sum = 0;
            for (int end = start; end < start + N; end++) {
                sum += netGas[end % N];
                if (sum < 0) {
                    start = end;//跳转到第一个到达不了的加油站
                    break;
                }
                if (end == start + N - 1) {//循环一圈了
                    return start;
                }
            }
        }
        return -1;
    }


    public int canCompleteCircuit(int[] gas, int[] cost) {
        int N = gas.length;
        int[] netGas = new int[N];
        for (int i = 0; i < N; i++) {
            netGas[i] = gas[i] - cost[i];
        }


        for (int start = 0; start < N; start++) {
            int sum = 0;
            for (int end = start; end < start + N; end++) {
                sum += netGas[end % N];
                if (sum < 0) break;
                if (end == start + N - 1) {
                    return start;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        GasStation o = new GasStation();
        int res = o.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2});
        System.out.println(res);

    }
}
