package com.linus.lab.algorithm.temp.aliyun;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/9/5
 */
public class Q3 {

    /**
     * @param s: a string for this game
     * @return: return whether Alice can win this game
     */
    public boolean stringGame(String s) {
        // Write your code here.


        int num[] = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }

        int nim = 0;
        boolean onlyContainsOne = true;
        for (int i = 0; i< 26;i++) {
            if (num[i] == 0) continue;
            nim ^= num[i];
            if (num[i] > 1) onlyContainsOne = false;
        }
        return (onlyContainsOne && nim != 0) || (!onlyContainsOne && nim == 0);
    }
//
//    public boolean stringGame2(String s) {
//        // Write your code here.
//
//
//        int num[] = new int[26];
//        for (int i = 0; i < s.length(); i++) {
//            num[s.charAt(i) - 'a']++;
//        }
//
//        int nim = 0;
//        boolean f = true, f1 = false;
//        for (int i = 0; i< 26;i++) {
//            if (num[i] == 0) continue;
//            nim ^= num[i];
//            if(num[i]!=1)f = false;
//            if (num[i] > 1) f1 = false;
//        }
//        return (f&&!(nim > 0)||f1&&(nim > 0 ));
////        return true;
//    }

    public static void main(String[] args) {
        System.out.println(new Q3().stringGame(""));
        System.out.println(new Q3().stringGame("a"));
        System.out.println(new Q3().stringGame("ab"));
        System.out.println(new Q3().stringGame("aab"));
        System.out.println(new Q3().stringGame("aabb"));
        System.out.println(new Q3().stringGame("abbcc"));
//        System.out.println(2 ^ 1);

    }
}
