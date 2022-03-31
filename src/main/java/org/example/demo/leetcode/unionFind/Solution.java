package org.example.demo.leetcode.unionFind;

public class Solution {


    /**
     * 130. 被围绕的区域
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * @param board
     */
    public void solve(char[][] board) {
        if(board.length == 0) {
            return;
        }

        int m = board.length;;
        int n = board[0].length;
        // 给 dummy 留一个额外位置
        UnionFindSet unionFindSet = new UnionFindSet(m * n + 1);
        int dummy = m * n;
        
        // 将首列和末列的 O 与 dummy 连通
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                unionFindSet.union(i * n, dummy);
            }
            
            if(board[i][n - 1] == 'O') {
                unionFindSet.union(i * n + n - 1, dummy);
            }
        }

        // 首行和末行的 O 与 dummy 连通
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') {
                unionFindSet.union(j, dummy);
            }

            if(board[m - 1][j] == 'O') {
                unionFindSet.union((m - 1) * n + j, dummy);
            }
        }
        
        // 方向数组 d 是上下左右搜索的常用手法
        int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(board[i][j] == 'O') {
                    // 将此 O 与上下左右的 O 连通
                    for(int k = 0; k < 4; k++) {
                        int x = i + d[k][0];
                        int y = j + d[k][1];
                        if(board[x][y] == 'O') {
                            unionFindSet.union(x * n + y, i * n + j);
                        }
                    }
                }
            }
        }

        // 所有不和 dummy 连通的 O， 都要被替换
        for(int i = 1; i < m - 1; i++) {
            for(int j = 1; j < n - 1; j++) {
                if(!unionFindSet.connected(dummy, i * n + j)) {
                    board[i][j] = 'X';
                }
            }
        }
    }


    /**
     * 990. 等式方程的可满足性
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     *
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param equations
     * @return
     */
    public boolean equationsPossible(String[] equations) {
        UnionFindSet unionFindSet = new UnionFindSet(26);
        // 先让相等字母形成连通分量
        for (String equation : equations) {

            if(equation.charAt(1) == '=') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                unionFindSet.union(x - 'a', y - 'a');
            }
        }

        // 检查不等关系是否打破相等关系的连通性
        for (String equation : equations) {
            if(equation.charAt(1) == '!') {
                char x = equation.charAt(0);
                char y = equation.charAt(3);
                // 如果相等关系成立，就是逻辑冲突
                if(unionFindSet.connected(x - 'a', y - 'a')) {
                    return false;
                }
            }
        }

        return true;

    }
}
