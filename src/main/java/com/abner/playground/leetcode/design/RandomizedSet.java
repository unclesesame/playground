package com.abner.playground.leetcode.design;

import java.util.*;

//No.380 O(1)时间插入，删除和获取随机元素
public class RandomizedSet {

    List<Integer> nums; //存放num
    Map<Integer, Integer> indices; //用于0（1）查询
    Random random; //随机取数

    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        indices = new HashMap<Integer, Integer>();
        random = new Random();
    }

    public boolean insert(int val) {
        if(indices.containsKey(val)) return false;
        int index = nums.size();
        nums.add(val);
        indices.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if(!indices.containsKey(val)) return false;
        int index = indices.get(val);//获取待删数在位置
        //删除数组中间节点的时间复杂度不是o(1),可以用数组最后一个元素替代待删元素，然后将最后一个元素删除
        int last = nums.get(nums.size()-1);
        nums.set(index, last);
        indices.put(last, index);
        nums.remove(nums.size()-1);//数组中删除最后一个元素 时间复杂度才是o(1)
        indices.remove(val); //hashMap中删除
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}
