package com.linus.lab.algorithm.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author wangxiangyu
 * @Date 2020/11/9 18:40
 * @Description TODO
 */
public class CamelcaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> result = Arrays.stream(queries).map(str -> {
            return isMatch(str, pattern);
        }).collect(Collectors.toList());

        return result;
    }

    private Boolean isMatch(String query, String pattern) {
        int indexQ = 0, indexP = 0;
        while (indexQ < query.length() && indexP < pattern.length()){
            char c = query.charAt(indexQ), ch2 = pattern.charAt(indexP);
            if (c == ch2) {
                indexQ++;indexP++;
            } else {
                if (c >= 'A' && c <= 'Z') return false;
                indexQ++;
            }
        }
        if (indexP != pattern.length()) return false;
        while (indexQ < query.length()) {
            char c = query.charAt(indexQ++);
            if (c >= 'A' && c <= 'Z') return false;
        }
        return true;
    }

    private List<String> split(String str) {
        List<String> parts = new ArrayList<>();
        StringBuilder partSb = new StringBuilder();
        partSb.append(str.charAt(0));
        for (int i = 1; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                parts.add(partSb.toString());
                partSb = new StringBuilder();
            }
            partSb.append(c);
        }
        parts.add(partSb.toString());
        return parts;
    }


    public static void main(String[] args) {

        List<Boolean> result = new CamelcaseMatching().camelMatch(new String[]{"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"}, "FoBaT");
        System.out.println(result);
    }
}
