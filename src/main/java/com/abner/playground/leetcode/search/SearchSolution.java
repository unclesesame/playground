package com.abner.playground.leetcode.search;

public class SearchSolution {

    public static void main(String[] args) {
        SearchSolution solution = new SearchSolution();
        solution.findPeakElement(new int[]{1,2,1,3,5,6,4}); // output: 1或5， 因为数字2和6都是峰值
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
