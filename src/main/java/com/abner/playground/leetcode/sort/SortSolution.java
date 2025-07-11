package com.abner.playground.leetcode.sort;

public class SortSolution {
    public static void main(String[] args) {

    }

    //No.414 第三大的数，要求o(n) 实现， 如果没有返回最大的数
    public int thirdMax(int[] nums) {
        long first=Long.MIN_VALUE;
        long second=Long.MIN_VALUE;
        long third=Long.MIN_VALUE;
        for(int i=0; i< nums.length; i++){

            if(nums[i] > first){
                third = second;
                second = first;
                first = nums[i];
            }else if(nums[i] > second && nums[i] < first){
                third = second;
                second = nums[i];
            }else if(nums[i] > third && nums[i] < second){
                third = nums[i];
            }
        }
        return third == Long.MIN_VALUE? (int)first : (int) third;
    }
}
