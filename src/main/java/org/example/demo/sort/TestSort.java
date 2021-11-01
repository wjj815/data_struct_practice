package org.example.demo.sort;

public class TestSort {

    public static void main(String[] args) {

        SortConfig sortConfig = new SortConfig();
        sortConfig.setArrLen(100000);
        sortConfig.setPrintArr(false);

        SortContext sortContext = new SortContext(sortConfig);
        sortContext.addSort(new QuickSort());
        sortContext.addSort(new MergeSort());
        sortContext.addSort(new HeapSort());

        sortContext.run();
    }
}
