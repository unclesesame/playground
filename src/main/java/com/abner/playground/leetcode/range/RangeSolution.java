package com.abner.playground.leetcode.range;

import java.util.ArrayList;
import java.util.List;

public class RangeSolution {

    //No.228 汇总区间  有序无重复数组 nums = [0,1,2,4,5,7] 输出 ["0->2","4->5","7"]
    public List<String> summaryRanges(int[] nums) {

        List<String> ans = new ArrayList<>();
        int n = nums.length;
        int i=0;
        int low, high;
        while(i < n){
            //low 和 high记录区间起点和终点
            low = i ;
            i++;
            while (i < n && nums[i] == nums[i-1] + 1){
                i++;
            }
            high = i-1;
            StringBuilder sb = new StringBuilder(nums[low]);
            if(low < high){
                sb.append("->").append(nums[high]);
            }
            ans.add(sb.toString());
        }

        return ans;
    }
}
