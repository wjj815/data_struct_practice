package org.example.demo.leetcode.unionFind;

/**
 * 并查集结构
 */
public class UnionFindSet {

    // 记录 连通分量
    private int count;
    // 节点 x 的节点是 parent[x], 存储若干颗树
    private final int[] parent;
    // 记录树的重量
    private int[] size;


    /**
     * 构造函数， n 为图的节点总数
     *
     * @param n
     */
    public UnionFindSet(int n) {
        // 一开始互不连通
        this.count = n;
        // 父节点指针初始指向自己
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * 连同 x 和 y
     *
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        // 小树接到大树下面，较平衡
        if(size[x] > size[y]) {
            parent[y] = x;
            size[x] += size[y];
        } else {
            parent[x] = y;
            size[y] += size[x];
        }
        // 将两棵树合并为一颗
        parent[rootX] = rootY;
        // 两个分量合二为一
        count--;

    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }


    /**
     * @param x 某个节点 x
     * @return x 的根节点
     */
    private int find(int x) {
        // 根节点的 parent[x] = x
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    /**
     * @return 当前的连通分量个数
     */
    public int count() {
        return count;
    }

}
