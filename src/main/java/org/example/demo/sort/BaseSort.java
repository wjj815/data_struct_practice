package org.example.demo.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseSort {

    private static SortContext sortContext;

    /**
     * 排序花费的时间
     */
    private long tookTime;

    public static void setSortContext(SortContext sortContext) {
        BaseSort.sortContext = sortContext;
    }


    public long getTookSortedTime() {
        return tookTime;
    }

    /**
     * 获取待排序的测试数据
     * @return
     */
    public int[] getArrayData() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int arrLength = sortContext.getArrLen();
        System.out.println("当前设置的测试数据数据长度为: " + sortContext.getArrLen());
        int[] arr = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            arr[i] = random.nextInt(arrLength);
        }
        return arr;
    }

    /**
     * 排序的名称
     * @return
     */
    protected abstract String getName();

    public void run() {
        System.out.printf("\n\n开始 [ %s ] 排序:\n", getName());
        int[] arrayData = getArrayData();
        int[] trueSortedArr = Arrays.copyOf(arrayData, arrayData.length);
        Arrays.sort(trueSortedArr);
        if(sortContext.isPrintArr()) {
            System.out.println("未排序前的数组数据:\n" + Arrays.toString(arrayData));
            System.out.println("-----------");
        }
        long start = System.currentTimeMillis();
        sort(arrayData);

        if(sortContext.isPrintArr()) {
            System.out.println("排序后的数组数据:\n" + Arrays.toString(arrayData));
            System.out.println("-----------");
        }
        tookTime = System.currentTimeMillis() - start;
        System.out.printf("排序完成，共耗时: %d ms\n", tookTime);
        System.out.println("-----------");
        System.out.println("校验排序是否正确: ");
        boolean validate = validate(trueSortedArr, arrayData);
        if(!validate) {
            throw new SortException("排序结果有误！");
        }
        System.out.println("排序结果正确！！！");
    }

    /**
     * 校验数组是否成功排序，使用 Java 自带 API Arrays.sort()
     * @return
     */
    public boolean validate(int[] truedArr, int[] arr) {
        return Arrays.equals(truedArr, arr);
    }

    /**
     * 具体排序方法
     * @param arr
     */
    protected abstract void sort(int[] arr);

}
