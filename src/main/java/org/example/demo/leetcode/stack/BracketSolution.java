package org.example.demo.leetcode.stack;

import java.util.*;

public class BracketSolution {

    /**
     * 括号是否有效
     *
     * @param str
     * @return
     */
    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        char[] left = {'{', '(', '['};
        char[] right = {'}', ')', ']'};
        // 保存左括号
        Set<Character> leftBracket = new HashSet<>(left.length);
        // 保存左括号与右括号的映射
        Map<Character, Character> bracketPair = new HashMap<>(left.length);
        for (int i = 0; i < left.length; i++) {
            leftBracket.add(left[i]);
            bracketPair.put(right[i], left[i]);
        }

        for (int i = 0, len = str.length(); i < len; i++) {
            char c = str.charAt(i);
            if (leftBracket.contains(c)) {
                stack.push(c);
            } else if (!stack.isEmpty() && bracketPair.get(c) == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }


    /**
     * 只有满足下面几点之一，括号字符串才是有效的：
     * <p>
     * 它是一个空字符串，或者
     * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
     * 它可以被写作 (A)，其中 A 是有效字符串。
     * 输入：s = "())"
     * 输出：1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        /**
         * 核心思路是以左括号为基准，通过维护对右括号的需求数need，来计算最小的插入次数。
         *
         *
         * 2、算法为什么返回res + need？
         *
         * 因为res记录的左括号的插入次数，need记录了右括号的需求，当 for 循环结束后，若need不为 0，那么就意味着右括号还不够，需要插入。
         *
         * 比如说s = "))("这种情况，插入 2 个左括号之后，还要再插入 1 个右括号，使得s变成"()()()"，才是一个合法括号串。
         */
        int res = 0, need = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // 对右括号的需求 + 1
                need++;
            } else if (c == ')') {
                // 对右括号的需求 - 1
                need--;

                /* 1、当need == -1 的时候意味着什么？

                 *因为只有遇到右括号)的时候才会need--，need == -1 意味着右括号太多了，所以需要插入左括号。

                 *比如说s = "))" 这种情况，需要插入 2 个左括号，使得s变成 "()()"，才是一个合法括号串。*/
                if (need == -1) {
                    need = 0;
                    // 需要插入一个左括号
                    res++;
                }

            }
        }

        /* 2、算法为什么返回res + need？
         *
         * 因为res记录的左括号的插入次数，need记录了右括号的需求，当 for 循环结束后，若need不为 0，那么就意味着右括号还不够，需要插入。
         *
         * 比如说s = "))("这种情况，插入 2 个左括号之后，还要再插入 1 个右括号，使得s变成"()()()"，才是一个合法括号串。

         */
        return res + need;
    }


    /**
     * 1541. 平衡括号字符串的最少插入次数
     * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
     * <p>
     * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
     * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
     * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
     * <p>
     * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
     * <p>
     * 请你返回让 s 平衡的最少插入次数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-insertions-to-balance-a-parentheses-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    int minInsertions(String s) {
        // res 记录插入数， need 记录右括号的需求量
        int res = 0, need = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                need += 2;

//                当遇到左括号时，若对右括号的需求量为奇数，需要插入 1 个右括号。
                // 如果对 右括号的需求为 偶数
                if (need % 2 == 1) {
                    // 插入一个右括号
                    res++;
                    // 对右括号的需求 - 1
                    need--;
                }
            }


            if (c == ')') {
                need--;

                if (need == -1) {
                    // 插入一个左括号
                    res++;
                    // 增加一个右括号的需求量
                    need = 1;
                }
            }
        }
        return res + need;
    }
}
