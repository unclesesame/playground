package com.abner.playground.algorithm;

class Solution {
    public int maxArea(int[] a) {
        int max = 0;
        for(int i = 0, j = a.length - 1; i < j ; ){
            int minHeight = a[i] < a[j] ? a[i ++] : a[j --];
            max = Math.max(max, (j - i + 1) * minHeight);
        }
        return max;
    }
};