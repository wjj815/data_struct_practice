package org.example.demo.leetcode.stack.monotonous;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {


    /**
     * 496. 下一个更大元素 I
     * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
     *
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
     *
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     *
     * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for(int i = nums2.length - 1; i >= 0; i--) {
            // 比高低
            while(!stack.isEmpty() && stack.peek() <= nums2[i]) {
                // 遇见高的，则栈内矮的全出去
                stack.pop();
            }
            // 存放答案
            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            // 入栈
            stack.push(nums2[i]);
        }

        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }


    /**
     *
     * 503. 下一个更大元素 II
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     *
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     *
     *比如输入一个数组[2,1,2,4,3]，你返回数组[4,2,4,-1,4]。拥有了环形属性，最后一个元素 3 绕了一圈后找到了比自己大的元素 4。
     * 这个问题肯定还是要用单调栈的解题模板，但难点在于，比如输入是[2,1,2,4,3]，对于最后一个元素 3，如何找到元素 4 作为 Next Greater Number。
     *
     * 对于这种需求，常用套路就是将数组长度翻倍：
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {

        Stack<Integer> stack = new Stack<>();

        int[] res = new int[nums.length];

//        利用循环数组的技巧来模拟数组长度翻倍的效果
        for(int i = 2 * nums.length - 1; i >= 0; i--) {

            while(!stack.isEmpty() && stack.peek() <= nums[i % nums.length]) {
                stack.pop();
            }
            if(i < nums.length) {
                res[i] = stack.isEmpty() ? -1 : stack.peek();
            }
            stack.push(nums[i % nums.length]);
        }
        return res;
    }


    /**
     * 316. 去除重复字母
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * @param s 输入："bcabc"
     *
     * @return 输出："abc"
     */
    public String removeDuplicateLetters(String s) {
        // 使用 StringBuilder 来维护单调栈
        StringBuilder res = new StringBuilder();
        // 记录字符是否已访问过
        boolean[] via = new boolean[128];
        // 记录每个字符出现的次数
        int[] count = new int[128];

        char[] chars = s.toCharArray();
        // 统计每个字符的个数
        for (char c : chars) {
            count[c]++;
        }

        for (int i = 0; i < chars.length; i++) {

            char c = chars[i];
            // 缩减
            count[c]--;
            // 如果res 中已存在该字符则跳过（去重）
            if(via[c]) continue;
            // 如果当前字符 c 比 res 的最后一个字符小
            while(res.length() > 0
                    && (res.charAt(res.length() - 1)) > c) {
                // 如果res 的最后一个字符在后面不会出现了，则停止回删
                if(count[res.charAt(res.length() - 1)] == 0) {
                    break;
                }
                //回删 res 中的字符，直到 res 的最后一个字符比当前字符小
                via[res.charAt(res.length() - 1)] = false;
                res.deleteCharAt(res.length() - 1);

            }
            // 添加 c 到 res 中
            res.append(c);
            // 标记该字符已添加到 res 中
            via[c] = true;
        }

        return res.toString();
    }


    /**
     *
     * 321. 拼接最大数
     * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
     *
     * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
     *
     * 说明: 请尽可能地优化你算法的时间和空间复杂度。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/create-maximum-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }



}
