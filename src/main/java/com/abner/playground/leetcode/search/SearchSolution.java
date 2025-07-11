package com.abner.playground.leetcode.search;

import java.util.Arrays;

public class SearchSolution {

    public static void main(String[] args) {
        SearchSolution solution = new SearchSolution();
        //solution.findPeakElement(new int[]{1,2,1,3,5,6,4}); // output: 1或5， 因为数字2和6都是峰值
        solution.successfulPairs(new int[]{5,1,3}, new int[]{1,2,3,4,5}, 7);
    }

    //No. 2300 咒语和药水的成功对数
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] ans = new int[n];

        //sort potions
        //给定一个数和一个数组，以及一个目标数， 返回这个数和每个数组中的数相乘大于目标数的数量
        Arrays.sort(potions);
        for(int i=0; i<n; i++){
            int index = findMinIndex(potions, (success+spells[i]-1)/spells[i]-1);
            ans[i] = m-index;
        }
        return ans;
    }

    private int findMinIndex(int[] nums, long target){
        int n = nums.length;
        int left = 0 ;
        int right = n-1;

        while(left <= right){
            int mid = (left + right)/2;
            if(nums[mid] > target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }

        return  left;
    }

    //No. 875 爱吃香蕉的珂珂  二分法
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int low =1;
        int high = 0;
        for(int pile: piles){
            high = Math.max(high, pile);
        }

        int k=high;

        while(low < high){
            int speed = (high -low)/2 + low;
            long cost = getCost(piles, speed);
            if(cost <= h){
                k = speed;
                high = speed;
            }else{
                low = speed+1;
            }
        }
        return k;
    }

    private long getCost(int[] piles, int speed) {
        long cost = 0;
        for (int pile : piles) {
            int curTime = (pile + speed - 1) / speed;
            cost += curTime;
        }
        return cost;
    }


    // 二分查找，实现logN 复杂度
    public int findPeakElement(int[] nums) {
        int left=0,right=nums.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(nums[mid]>nums[mid+1]){
                //说明此时mid为下坡路，那么有可能自己本身就是山峰，或者在下山的过程中，所以right=mid而不能等于mid-1
                right = mid;
            }else{
                left = mid+1;
                //反之说明此时mid为上坡路，既然是上坡，那么mid肯定不是山峰，所以left=mid+1（题目要求nums[i]!=nums[i+1]，所以不可能存在“平峰”的情况）
            }
        }
        return left;
    }
}
