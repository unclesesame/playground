package com.abner.playground.leetcode.hash;

import java.util.*;
import java.util.stream.Collectors;

public class HashSolution {

    public static void main(String[] args) {
        HashSolution solution = new HashSolution();
        int ans = solution.longestConsecutive(new int[]{1});
        System.out.println(ans);
    }

    //No. 128 最长连续序列 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度
    //要求时间复杂读O(n)
    public int longestConsecutive(int[] nums) {

        Set<Integer> hashSet = new HashSet<>();
        for(int num: nums){
            hashSet.add(num);
        }

        int max = 0;

        for(int num: hashSet){
            //如果存在num-1, 从num-1开始的才是最长序列
            if(!hashSet.contains(num - 1)){
                int x = num;
                int count=1;
                while(hashSet.contains(x+1)){
                    x++;
                    count++;
                }
                max = Math.max(count, max);
            }

        }
        return max;

    }

    public List<List<String>> groupAnagrams(String[] strs) {
        //按字母排序存入hashset
        Map<String, List<String>> hashMap = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for(String str: strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sortedStr = String.valueOf(charArray);
            List<String> value = hashMap.getOrDefault(sortedStr, new ArrayList<>());
            value.add(str);
            hashMap.put(sortedStr, value);
        }

        return new ArrayList<>(hashMap.values());
    }
}
