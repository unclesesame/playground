package com.abner.playground.leetcode.heap;

public class HeapSolution {
    public static void main(String[] args) {

    }

    //No.215 数组中的第K个最大元素  基于快排的方案
    public int findKthLargest(int[] nums, int k) {
        int l = 0;
        int n = nums.length;
        int r =n-k;
        return quickSort(nums, l, r, n-k);
    }

    private int quickSort(int[] nums, int l, int r, int k){
        if(l==r) return nums[k];
        int index = partition(nums,l, r);
        if(index < k) return quickSort(nums, index+1, r, k);
        else return quickSort(nums, l, index-1, k);
    }

    private static int partition(int[] array, int low, int high){
        int key = array[low];//基准元素，第一个元素
        while(low < high){
            while(low < high && key<=array[high]){
                high--;
            }
            array[low] = array[high];
            while(low < high && key>=array[low]){
                low++;
            }
            array[high] = array[low];
        }
        array[high] = key;
        return high;
    }

    private void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] =a[j];
        a[j] = temp;
    }
}
