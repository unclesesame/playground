package com.abner.playground.leetcode.hash;

import java.util.*;
import java.util.stream.Collectors;

public class HashSolution {

    public static void main(String[] args) {

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
