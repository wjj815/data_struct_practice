package org.example.demo.sort;

@FunctionalInterface
public interface Sortable<T> {


    /**
     *  排序方法
     * @param t 排序对象
     */
    void sort(T t);
}
