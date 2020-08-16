package com.linus.lab.algorithm.temp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：wangxiangyu
 * @date ：Created in 2020/8/15
 * https://leetcode-cn.com/problems/remove-boxes/
 */
public class RemoveBoxesBruteForce {

    public int removeBoxes(int[] boxes) {
        List<Element> list = new ArrayList<>();
        int val = boxes[0], count = 1;
        for (int i = 1; i < boxes.length; i++) {
            if (boxes[i] == val) {
                count++;
            } else {
                list.add(new Element(count, val));
                val = boxes[i];
                count = 1;
            }
        }
        list.add(new Element(count, val));

        return doRemoveBoxes(list);
    }

    public int doRemoveBoxes(List<Element> list) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() <= 2) {
            return list.stream().collect(Collectors.summingInt(e -> e.count * e.count));
        }

        int max = 0;
        for (int i = 1; i < list.size() - 1; i++) {
            int pointI = list.get(i).count * list.get(i).count;

            List<Element> copyed = new ArrayList<>(list);
            copyed.remove(i);
            if (copyed.get(i).value == copyed.get(i - 1).value) {
                copyed.set(i - 1, new Element(copyed.get(i).count + copyed.get(i - 1).count, copyed.get(i).value));
                copyed.remove(i);
            }
            int pointLeft = doRemoveBoxes(copyed);
            max = Math.max(max, pointI + pointLeft);
        }
        return max;
    }

    public static void main(String[] args) {
//        int result = new RemoveBoxesBruteForce().removeBoxes(new int[]{1,3,2,2,2,3,4,3,1});
//        int result = new RemoveBoxesBruteForce().removeBoxes(new int[]{1});
        int result = new RemoveBoxesBruteForce().removeBoxes(new int[]{3,8,8,5,5,3,9,2,4,4,6,5,8,4,8,6,9,6,2,8,6,4,1,9,5,3,10,5,3,3,9,8,8,6,5,3,7,4,9,6,3,9,4,3,5,10,7,6,10,7});
        System.out.println(result);
    }


}
class Element {

    int count;

    int value;

    public Element(int count, int value) {
        this.count = count;
        this.value = value;
    }
}


