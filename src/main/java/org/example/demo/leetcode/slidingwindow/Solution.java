package org.example.demo.leetcode.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {


    /**
     * 76. 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * <p>
     *  
     * <p>
     * 注意：
     * <p>
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s 字符串
     * @param t 全部字母的一个子串
     * @return
     */
    public String minWindow(String s, String t) {

        Map<Character, Integer> map = new HashMap<>();

        // 记录 t中字符 出现的次数
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;

        int sLen = s.length();

        // 保存结果
        int minLen = s.length() + 1;
        String res = "";

        while (right < sLen) {
            // 扩展右边界
            char c = s.charAt(right);
            right++;
            // 扩展窗口
            map.put(c, map.getOrDefault(c, 0) - 1);
            // 收缩左边界
            while (isValid(map)) {
                // 更新答案，因为 right 在上面递增过了，所以长度为(right - left)
                if (right - left < minLen) {
                    res = s.substring(left, right);
                    minLen = right - left;
                }
                c = s.charAt(left);
                // 缩减窗口
                map.put(c, map.getOrDefault(c, 0) + 1);
                left++;
            }
        }

        return res;
    }

    /**
     * 校验窗口内是否符合要求
     *
     * @param map
     * @return
     */
    public boolean isValid(Map<Character, Integer> map) {
        for (Integer value : map.values()) {
            if (value > 0) {
                return false;
            }
        }

        return true;
    }


    /**
     * 567. 字符串的排列
     * 给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutation-in-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s1
     * @param s2
     * @return s1 的排列之一是否是 s2 的子串
     */
    public boolean checkInclusion(String s1, String s2) {

        // 记录s1的子串排列（即记录s1 中每个字符出现的次数）
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 限定窗口大小
        int windowSize = s1.length();
        int left = 0, right = 0;
        int len = s2.length();
        while (right < len) {

            char c = s2.charAt(right);
            right++;
            map.put(c, map.getOrDefault(c, 0) - 1);

            // 当前窗口大于限定窗口大小，则滑动
            while (right - left > windowSize) {
                c = s2.charAt(left);
                left++;
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            // 若窗口为答案则直接返回
            if (isValid(map)) {
                return true;
            }
        }

        return false;
    }


    /**
     * 438. 找到字符串中所有字母异位词
     * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * <p>
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {

        // 记录s1的子串排列（即记录s1 中每个字符出现的次数）
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 限定窗口大小
        int windowSize = p.length();
        int left = 0, right = 0;
        int len = s.length();

        List<Integer> res = new ArrayList<>();
        while (right < len) {

            char c = s.charAt(right);
            right++;
            map.put(c, map.getOrDefault(c, 0) - 1);

            // 当前窗口大于限定窗口大小，则滑动
            while (right - left > windowSize) {
                c = s.charAt(left);
                left++;
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            // 收集答案
            if (isValid(map)) {
                res.add(left);
            }
        }

        return res;
    }


    /**
     * 3. 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> map = new HashMap<>();

        int left = 0, right = 0;
        int len = s.length();
        int res = 0;
        while (right < len) {
            char c = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            map.put(c, map.getOrDefault(c, 0) + 1);
            // 判断左侧窗口是否要收缩
            while (map.get(c) > 1) {

                char lc = s.charAt(left);
                left++;
                // 进行窗口内数据的一系列更新
                map.put(lc, map.getOrDefault(lc, 0) - 1);
            }

            if (right - left > res) {
                res = right - left;
            }

        }

        return res;
    }
}
