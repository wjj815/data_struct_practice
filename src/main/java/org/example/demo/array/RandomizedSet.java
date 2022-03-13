package org.example.demo.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RandomizedSet {

    // 存储 值 与在 list中索引的映射
    HashMap<Integer, Integer> map;
    // 存储值的集合
    List<Integer> list;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        // 获取待删元素在 list 中的索引
        Integer index = map.get(val);
        // 获取 list 中最后一个元素的值
        Integer lastElem = list.get(list.size() - 1);
        // 将 list 的最后一个元素放到 index
        list.set(index, lastElem);
        // 更新map 中最后一个元素的索引值
        map.put(lastElem, index);
        // 删除
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    public int getRandom() {
        // Math.random 取值为[0,1)
        return list.get((int) (list.size() * Math.random()));
    }
}
