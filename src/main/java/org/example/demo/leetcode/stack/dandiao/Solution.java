package org.example.demo.leetcode.stack.dandiao;

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
}
