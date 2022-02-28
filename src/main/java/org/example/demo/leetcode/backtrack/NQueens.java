package org.example.demo.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     *
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        // '.'表示空， 'Q'表示皇后， 初始化空棋盘
        List<String> board = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < n; i++) {
            line.append(".");
        }
        for (int i = 0; i < n; i++) {
            board.add(line.toString());
        }
        List<List<String>> res = new ArrayList<>();
        backTrack(board, 0, res);
        return res;
    }


    /**
     * 路径： board中小于 row 的那些行都已经成功放置了皇后
     * 选择列表：第 row 行的所有列都是放置皇后的选择
     * 结束条件： row 超过 board 的最后一行
     * @param board 棋盘
     * @param row 行
     * @param res 结果
     */
    private void backTrack(List<String> board, int row, List<List<String>> res) {
        // 触发结束条件
        if(row == board.size()) {
            res.add(new ArrayList<>(board));
            return;
        }

        int n = board.get(row).length();

        for (int col = 0; col < n; col++) {

            if(!isValid(board, row, col)) {
                continue;
            }
            // 放置皇后
            board.set(row, modifyString(board.get(row), col, 'Q'));
            backTrack(board, row + 1, res);
            // 撤销皇后
            board.set(row, modifyString(board.get(row), col, '.'));
        }

    }

    public String modifyString(String str, int i, char c) {
        char[] chars = str.toCharArray();
        chars[i] = c;
        return new String(chars);
    }


    /**
     * 是否可以在 board[row][col]放置皇后？
     * @return
     */
    public boolean isValid(List<String> board, int row, int col) {
        int n = board.size();

        // 检查列是否有皇后相互冲突
        for (int i = 0; i < row; i++) {
            if(board.get(i).charAt(col) == 'Q'){
                return false;
            }
        }

        // 检查左上方是否有皇后互相冲突
        int x = row - 1, y = col - 1;
        while(x  >= 0 && y >= 0) {
            if(board.get(x).charAt(y) == 'Q'){
                return false;
            }
            x--;
            y--;
        }
        // 检查右上方是否有皇后互相冲突
        x = row - 1;
        y = col + 1;
        while(x >= 0 && y < n) {
            if(board.get(x).charAt(y) == 'Q'){
                return false;
            }
            x--;
            y++;
        }

        return true;
    }

}
