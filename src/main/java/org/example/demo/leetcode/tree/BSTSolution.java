package org.example.demo.leetcode.tree;

import org.example.demo.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树相关
 */
public class BSTSolution {

    TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if(root == null) return new TreeNode(val);

        if(root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if(root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }

    TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) return null;

        if(root.val == key) {
            // 找到了，进行删除
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        } else if(root.val > key) {
            // 去左子树找
            root.left = deleteNode(root.left, key);
        } else {
            // 去右子树找
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    TreeNode getMin(TreeNode node) {
        // BST 最左边就是最小的
        while(node.left != null) node = node.left;
        return node;
    }

    boolean isInBST(TreeNode root, int target) {
        if(root == null) return false;
        if(root.val == target) {
            return true;
        }
        if(root.val < target) {
            return isInBST(root.right, target);
        }

        return isInBST(root.left, target);
    }

    boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    // 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val
    private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // base case
        if(root == null) {
            return true;
        }
        // 若 root.val 不符合 max 和 min 的限制，说明 不是合法 BST
        if(min != null && root.val <= min.val) return false;
        if(max != null && root.val >= max.val) return false;
        // 限定左子树的最大值为 root.val, 右子树的最小值为 root.val
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }


    /**
     *
     * 1373. 二叉搜索子树的最大键值和
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     *
     * 二叉搜索树(BST)的定义如下：
     *
     * 任意节点的左子树中的键值都 小于 此节点的键值。
     * 任意节点的右子树中的键值都 大于 此节点的键值。
     * 任意节点的左子树和右子树都是二叉搜索树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-sum-bst-in-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        getResByTraverse(root);
        return maxSum;
    }

    /**
     *
     * @param root 根
     * @return 函数返回 int[] {isBST, min, max, sum} 四元组
     * res[0]记录以root为根的二叉树是否是 BST，若为 1 则说明是 BST，若为 0 则说明不是 BST；
     *
     * res[1]记录以root为根的二叉树所有节点中的最小值；
     *
     * res[2]记录以root为根的二叉树所有节点中的最大值；
     *
     * res[3]记录以root为根的二叉树所有节点值之和。
     */
    int[] getResByTraverse(TreeNode root) {

        // base case
        if(root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = getResByTraverse(root.left);
        int[] right = getResByTraverse(root.right);
        /*
         * 后序遍历位置-----
         * 通过 left 和 right 推导返回值
         * 并且正确更新 maxSum 变量
         */

        int[] res = new int[4];

        // 这个 if 判断以 root 为根的二叉树是不是 BST
        if(left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]) {
            // 以 root 为根的二叉树是 BST
            res[0] = 1;
            // 计算以 root 为根的这颗 BST 的最小值
            res[1] = Math.min(left[1], root.val);
            // 计算以 root 为根的这颗 BST 的最大值
            res[2] = Math.max(right[2], root.val);
            // 计算以 root为根的这颗 BST 所有节点之和
            res[3] = left[3] + right[3] + root.val;
            // 更新全局变量
            maxSum = Math.max(maxSum, res[3]);
        } else {
            // 以 root 为根的二叉树不是 BST
            res[0] = 0;
            // 其他的值都没有必要计算了， 因为用不到
        }

        return res;
    }


    /**
     * 538. 把二叉搜索树转换为累加树
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
     *
     * 提醒一下，二叉搜索树满足下列约束条件：
     *
     * 节点的左子树仅包含键 小于 节点键的节点。
     * 节点的右子树仅包含键 大于 节点键的节点。
     * 左右子树也必须是二叉搜索树。
     * 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        getSumByTraverse(root);
        return root;
    }

    int sum = 0;

    void getSumByTraverse(TreeNode root) {
        if(root == null) {
            return;
        }
        getSumByTraverse(root.right);
        // 维护累加和
        sum += root.val;
        // 将 BST 转化为累加数
        root.val = sum;
        getSumByTraverse(root.left);
    }

    /**
     * 96. 不同的二叉搜索树
     * 难度
     * 中等
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[][] memo = new int[n + 1][n + 1];
        //计算闭区间[lo, hi] 组成的 BST 个数
        return count(1, n, memo);
    }

    /**
     * 计算闭区间[lo, hi] 组成的 BST 个数
     * @param lo
     * @param hi
     * @return
     */
    int count(int lo, int hi, int[][] memo) {
        // base case
        if(lo > hi) return 1;

        if(memo[lo][hi] != 0) {
            return memo[lo][hi];
        }

        int res = 0;
        for(int i = lo; i <= hi; i++) {
            // i 的值作为根节点 root
            int left = count(lo, i - 1, memo);
            int right = count(i + 1, hi, memo);
            // 左右子树的组合数乘积就是 BST 的总数
            res += left * right;
        }
        memo[lo][hi] = res;
        return res;
    }


    /**
     * 95. 不同的二叉搜索树 II
     * 难度
     * 中等
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int lo, int hi) {
        List<TreeNode> res = new ArrayList<>();
        // base case
        if(lo > hi) {
            res.add(null);
            return res;
        }
        // 1. 穷举 root节点的所有可能
        for(int i = lo; i <= hi; i++) {
            // 2. 递归构造出左右子树的所有合法 BST
            List<TreeNode> leftNodes = generateTrees(lo, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, hi);
            // 3. 给 root 节点穷举所有左右子树的组合
            for(TreeNode left : leftNodes) {
                for(TreeNode right : rightNodes) {
                    // i 作为根节点 root的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }


    /**
     * 669. 修剪二叉搜索树
     * 难度
     * 中等
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     *
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null) return null;
        // root 为修剪的节点
        if(low > root.val) {
            return trimBST(root.right, low, high);
        }
        if(root.val > high) {
            return trimBST(root.left, low, high);
        }

        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
