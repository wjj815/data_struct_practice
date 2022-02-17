package org.example.demo.search;

/**
 * 升序数组中常见的二分查找
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,4,4,4,4,6,7,8};
        BinarySearch b = new BinarySearch();

        System.out.println(b.binarySearch(arr, 4));
        System.out.println(b.leftBound(arr, 4));
        System.out.println(b.rightBound(arr, 4));

    }

    /**
     *
     * @param arr 数组
     * @param target 目标数
     * @return 下标
     */
    public int binarySearch(int[] arr, int target) {

        int l = 0;
        int r = arr.length - 1;
        // 搜索范围为 arr[l, r]
        while(l <= r) {
            int m = l + (r - l) / 2;
            // target 在 arr[m + 1, r]
            if(arr[m] < target) {
                l = m + 1;
            }
            // target 在 arr[l, m - 1]
            else if(arr[m] > target) {
                r = m - 1;
            } else {
                return m;
            }
        }
        // 搜索范围为空
        return -1;
    }

    /**
     * 搜索目标数的左边界
     * @param arr 数组
     * @param target 目标数
     * @return 目标数在数组中的最左侧下标
     */
    public int leftBound(int[] arr, int target) {
        // 搜索区间为arr[l, r]
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(arr[m] < target) {
                // 搜索区间 arr[mid + 1, right]
                l = m +  1;
            } else if(arr[m] > target) {
                // 搜索区间 arr[l, m - 1]
                r = m  - 1;
            } else {
                // 收缩右侧边界
                r  = m - 1;
            }
        }

        // 检查出界情况
        if(l >= arr.length || arr[l] != target) {
            return - 1;
        }
        return l;
    }

    /**
     * 搜索目标数的右边界
     * @param arr 数组
     * @param target 目标数
     * @return 目标数右侧边界下标
     */
    public int rightBound(int[] arr, int target) {
        // 搜索区间为arr[l, r]
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(arr[m] < target) {
                // 搜索区间 arr[mid + 1, right]
                l = m +  1;
            } else if(arr[m] > target) {
                // 搜索区间 arr[l, m - 1]
                r = m - 1;
            } else {
                // 收缩左侧侧边界
                l  = m + 1;
            }
        }

        // 检查出界情况
        if(r < 0 || arr[r] != target) {
            return - 1;
        }
        return r;
    }
}
