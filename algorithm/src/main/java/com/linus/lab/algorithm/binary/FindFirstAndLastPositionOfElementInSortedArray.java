package com.linus.lab.algorithm.binary;

/**
 * @Author wangxiangyu
 * @Date 2020/12/1 15:51
 * @Description TODO
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {

    public int[] searchRange(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    public int[] binarySearch(int[] nums, int target, int l, int r) {
        if (l > r) {
            return new int[]{-1, -1};
        } else {
            int mid = (l + r) / 2;
            if (target > nums[mid]) {
                return binarySearch(nums, target, mid + 1, r);
            } else if (target < nums[mid]) {
                return binarySearch(nums, target, l, mid - 1);
            } else {
                int first = searchFirst(nums, target, l, mid - 1);
                int last = searchLast(nums, target, mid + 1, r);
                return new int[]{first == -1 ? mid : first, last == -1 ? mid : last};
            }
        }
    }

    public int searchFirst(int[] nums, int target, int l, int r) {
        if (l > r) return -1;
        int mid = (l + r) / 2;
        if (target == nums[mid]) {
            int s = searchFirst(nums, target, l, mid - 1);
            return s == -1 ? mid : s;
        } else {
            return searchFirst(nums, target, mid + 1, r);
        }
    }

    public int searchLast(int[] nums, int target, int l, int r) {
        if (l > r) return -1;
        int mid = (l + r) / 2;
        if (target == nums[mid]) {
            int s = searchLast(nums, target, mid + 1, r);
            return s == -1 ? mid : s;
        } else {
            return searchLast(nums, target, l, mid - 1);
        }
    }

    public static void main(String[] args) {

    }
}
