package com.abner.playground.leetcode.array;

import java.lang.reflect.Array;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        solution.sortColors(new int[]{2,0,2,1,1,0});
    }

    //No.75 颜色分类 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列
    //使用整数 0、 1 和 2 分别表示红色、白色和蓝色
    //输入：nums = [2,0,2,1,1,0]  输出：[0,0,1,1,2,2]
    public void sortColors(int[] nums) {

        int n =  nums.length;
        int i=0;
        //指针p0交换0， p2交换2
        int p0 = 0;
        int p2 = n-1;

        while(i<=p2){

            //2和2交换后，继续处理
            while( i<= p2 && nums[i] == 2){
                nums[i] = nums[p2];
                nums[p2] = 2;
                p2--;
            }

            //2和0交换，继续处理0
            if(nums[i] == 0){
                nums[i] = nums[p0];
                nums[p0] = 0;
                p0++;
            }
            i++;

        }
    }

    //No. 169 返回多数元素 是指在数组中出现次数长度一半的数
    public int majorityElement(int[] nums) {
        //在一个数组中，如果某个元素的出现次数超过了数组长度的一半，那么这个元素与其他所有元素一一配对，最后仍然会剩下至少一个该元素
        int x = 0;//暂定0为众数
        int votes=0; //得票数初始化为0， 如果是众数votes+1， 不是则-1. 如果votes=0，新遍历的x假设为众数

        for(int num: nums){
            if(votes == 0)
                x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
