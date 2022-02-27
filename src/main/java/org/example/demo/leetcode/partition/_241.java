package org.example.demo.leetcode.partition;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 */
public class _241 {

    /**
     * 计算所有加括号的结果
     *
     * @param expression
     * @return
     */
    public List<Integer> diffWaysToCompute(String expression) {

        List<Integer> res = new ArrayList<>();
        int len = expression.length();
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);
            // 扫描算式 expression 中的运算符
            if (c == '+' || c == '-' || c == '*') {
                // 以运算符为中心，分割两个字符串，分别递归计算
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, len));
                // 通过子问题的结果，合成原问题的结果
                for (Integer a : left) {
                    for (Integer b : right) {
                        if (c == '+') {
                            res.add(a + b);
                        } else if (c == '-') {
                            res.add(a - b);
                        } else if (c == '*') {
                            res.add(a * b);
                        }
                    }
                }
            }
        }
        // base case
        // 如果 res为空，说明算式是一个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }

        return res;
    }

}
