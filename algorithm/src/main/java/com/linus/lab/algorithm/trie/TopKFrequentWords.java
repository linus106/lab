package com.linus.lab.algorithm.trie;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/11/9
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {
        Comparator<Map.Entry<String, Long>> comparetor = new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                if (o1.getValue().equals(o2.getValue())) return o2.getKey().compareTo(o1.getKey());
                else return o1.getValue().intValue() - o2.getValue().intValue();
            }
        };
        final Map<String, Long> map = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        PriorityQueue<Map.Entry<String, Long>> topK = new PriorityQueue<>(k, comparetor);

        for (Map.Entry<String, Long> stringLongEntry : map.entrySet()) {
            if (topK.size() < k) {
                topK.offer(stringLongEntry);
            } else if (comparetor.compare(topK.peek(), stringLongEntry) < 0) {
                topK.poll();
                topK.offer(stringLongEntry);
            }
        }

        List<String> result = new ArrayList<>();
        while (!topK.isEmpty()) {
            result.add(topK.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }

}
