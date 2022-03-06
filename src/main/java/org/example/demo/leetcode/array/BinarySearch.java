package org.example.demo.leetcode.array;

public class BinarySearch {


    /**
     * 二分查找
     *
     * @param arr
     * @param target
     * @return
     */
    public int binarySearch(int[] arr, int target) {

        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 寻找最左边的target下标位置
     *
     * @param arr
     * @param target
     * @return
     */
    public int leftBoundOfTarget(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                // 向左逼近
                right = mid - 1;
            }
        }

        return arr[left] == target ? left : -1;
    }

    /**
     * 寻找最左边的target下标位置
     *
     * @param arr
     * @param target
     * @return
     */
    public int rightBoundOfTarget(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                // 向右逼近
                left = mid + 1;
            }
        }

        return arr[right] == target ? right : -1;
    }


    /**
     * 求比 target 大的第一个数
     * @param arr
     * @param target
     * @return
     */
    public int ceiling(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                // 向右逼近
                left = mid + 1;
            }
        }

        return right + 1 >= arr.length ? -1 : right + 1;
    }

    /**
     * 求比 target 小的第一个数
     * @param arr
     * @param target
     * @return
     */
    public int floor(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                // 向右逼近
                right = mid - 1;
            }
        }

        return left - 1 < 0 ? -1 : left - 1;
    }
}
