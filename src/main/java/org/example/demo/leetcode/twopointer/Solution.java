package org.example.demo.leetcode.twopointer;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 双指针相关的题目解法记录
 */
public class Solution {


    /**
     * 870 优势洗牌（类似田忌赛马）
     *给定两个大小相等的数组 A 和 B，A 相对于 B 的优势可以用满足 A[i] > B[i] 的索引 i 的数目来描述。
     *
     * 返回 A 的任意排列，使其相对于 B 的优势最大化。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/advantage-shuffle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1 [2,7,11,15]
     * @param nums2 [1,10,4,11]
     * @return [2,11,7,15]
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {

        // 使用优先队列来存储 nums2的优势顺序, 降序排序
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a1,a2) -> a2[1] - a1[1]);

        for (int i = 0; i < nums2.length; i++) {
            priorityQueue.offer(new int[]{i, nums2[i]});
        }

        // 给 num1 升序排序
        Arrays.sort(nums1);
        int[] res = new int[nums1.length];
        // nums1[left] 是最小值，nums1[right] 是最大值
        int l = 0, r = nums1.length - 1;
        while(!priorityQueue.isEmpty()) {
            int[] pair = priorityQueue.poll();
            // v 是 nums2 中的最大值，i 是对应索引
            int i = pair[0], v = pair[1];
            if(nums1[r] > v) {
                // 如果 nums1[right] 能胜过 v，那就自己上
                res[i] = nums1[r];
                r--;
            } else {
                // 否则用最小值混一下，养精蓄锐
                res[i] = nums1[l];
                l++;
            }
        }

        return res;
    }

    /**
     * 26. 删除有序数组中的重复项
     * 给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
     *
     * 由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 k 个元素，那么 nums 的前 k 个元素应该保存最终结果。
     *
     * 将最终结果插入 nums 的前 k 个位置后返回 k 。
     *
     * 不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 类似 80. 删除有序数组中的重复项 II （可秒杀）
     *
     * @param nums 数组
     * @param n 保留几个重复的
     * @return
     */
    public int removeDuplicates(int[] nums, int n) {
        if(nums.length <= n) return nums.length;
        int slow = n, fast = n;

        while(fast < nums.length) {
            if(nums[slow - n] != nums[fast]) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     *
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;

        while(fast < nums.length) {

            /**
             * 注意这里和有序数组去重的解法有一个重要不同，我们这里是先给nums[slow]赋值然后再给slow++，
             * 这样可以保证nums[0..slow-1]是不包含值为val的元素的，最后的结果数组长度就是slow
             */
            if(nums[slow] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }

    /**
     * 283. 移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        int p = removeElement(nums, 0);
        while(p < nums.length) {
            nums[p] = 0;
        }
    }
}
