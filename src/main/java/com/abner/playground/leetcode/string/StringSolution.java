package com.abner.playground.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringSolution {
    public static void main(String[] args) {
        StringSolution solution = new StringSolution();
        solution.findAnagrams("cbaebabacd","abc");
    }

    //No.438 找到字符串中所有字母异位词  给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引
    public List<Integer> findAnagrams(String s, String p) {

        int sLen = s.length();
        int pLen = p.length();

        Map<Character, Integer> pCountMap = new HashMap<>();
        //统计p中个字母出现次数
        for(int i =0; i<pLen; i++){
            char c = p.charAt(i);
            pCountMap.put(c, pCountMap.getOrDefault(c, 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        //滑动窗口遍历s 并统计窗口内各字母出现次数
        Map<Character, Integer> windowCountMap = new HashMap<>();
        for(int i=0; i<pLen; i++){
            windowCountMap.put(s.charAt(i), windowCountMap.getOrDefault(s.charAt(i), 0) + 1);
        }
        if(windowCountMap.equals(pCountMap)){
            res.add(0);
        }
        for(int i=1; i<=sLen-pLen; i++){
            if(windowCountMap.get(s.charAt(i-1)) > 1){
                windowCountMap.put(s.charAt(i-1),  windowCountMap.get(s.charAt(i-1))- 1);
            }else{
                windowCountMap.remove(s.charAt(i-1));
            }
            windowCountMap.put(s.charAt(i+pLen-1),  windowCountMap.getOrDefault(s.charAt(i+pLen-1), 0) + 1);
            if(windowCountMap.equals(pCountMap)){
                res.add(i);
            }
        }
        return res;
    }
}
