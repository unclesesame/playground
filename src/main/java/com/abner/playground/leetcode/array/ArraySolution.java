package com.abner.playground.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ArraySolution {

    public static void main(String[] args) {
        ArraySolution solution = new ArraySolution();
        //solution.sortColors(new int[]{2,0,2,1,1,0});
        //solution.nextPermutation(new int[]{3,2,1}); 1,2,3 -> 输出1,3,2  2,3,1->输出3,1,2   3,2,1->输出 1,2,3
        solution.rotate(new int[]{1},0);
    }

    //No.15 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        //排序后采用双指针法，第二重循环两个指针一个从左向右，一个从右向左
        for(int i =0; i< n-2; i++){
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;
            for(int j=i+1; j< n-1; j++){
                //剪枝去重
                if(j>i+1 && nums[j] == nums[j-1]) continue;
                int k = n-1;
                while(k>j && nums[i] + nums[j] + nums[k] > 0) {
                    k--;
                }
                if(k == j) break;
                if(nums[i] + nums[j] + nums[k] == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);
                }
            }
        }

        return result;
    }

    //No.48 旋转图像 给定n*n matrix表示一个图像，请将图像原地顺时针旋转90°
    public void retate(int[][] matrix){
        int n = matrix.length;

        //旋转前的位置(row,col)
        //旋转后的位置 新row=老col, 新col=n-老row-1
        //重复操作后会是如下的循环
        //(row,col)->(col, n-row-1)->(n-row-1,n-col-1) -> (n-col-1, row) -> (row,col)
        //只需一个变量temp 存储即将被覆盖位置的原值
        //每一轮矩阵中四个点位会完成旋转，所以当n为基数时，需要n*n/4轮(即((n/2)*(n/2))，当n为奇数时，中心点位无需旋转，所以需要（n*n-1）/4轮，即((n-1)/2)*(n+1)/2)
        for(int i=0; i<n/2; i++){
            for(int j=0; j<(n+1)/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-j-1][i];
                matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = temp;
            }
        }
    }

    //No.54 螺旋矩阵  顺时针螺旋打印m*n的矩阵
    public List<Integer> spiralOrder(int[][] matrix){
        List<Integer> res = new ArrayList<>();

        if(matrix == null)
            return res;

        int m = matrix.length;
        int n = matrix[0].length;

        int total = m*n;

        //右->下->左->上
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] vistited = new boolean[m][n];

        int row=0; int col=0;
        int direction = 0;

        for(int i=0; i<total; i++) {
            res.add(matrix[row][col]);
            vistited[row][col] = true;
            int nextRow = row + directions[direction][0];
            int nextCol = col + directions[direction][1];
            //碰到已访问过的或者矩阵边界换方向
            if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n || vistited[nextRow][nextCol]) {
                direction = (direction + 1) % 4;
            }
            row += directions[direction][0];
            col += directions[direction][1];

        }

        return res;
    }


    //No.56 合并重叠区间 [[1,3],[2,6],[8,10],[15,18]] ==> [[1,6],[8,10],[15,18]]
    public int[][] merge(int[][] intervals) {
        int m = intervals.length;
        if(m == 0) return new int[0][2];
        //入参数组按左边界排序
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        int i=1;
        while(i<m){
            //从合并集中取出最后一个元素
           int[] item = merged.get(merged.size()-1);
           //右端点大于等于后序的左端点，有重叠，需要合并，把两个区间左端点最小值和右端点最大值重新放入merged
           if(item[1] >= intervals[i][0]){
               item[0] = Math.min(item[0], intervals[i][0]);
               item[1] = Math.max(item[1], intervals[i][1]);
           }else{
               merged.add(intervals[i]);
           }
           i++;
        }
        return merged.toArray(new int[merged.size()][]);
    }

    //No.73 矩阵置0 如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        //标记第一行和第一列是否有0
        boolean zeroInRow0 = false;
        boolean zeroInCol0 = false;

        //第一列是否有0
        for(int i=0; i<m; i++){
            if(matrix[i][0] == 0){
                zeroInCol0 = true;
            }
        }

        //第一行是否有0
        for(int j=0; j<n; j++){
            if(matrix[0][j] == 0){
                zeroInRow0 = true;
            }
        }

        //处理其他非首行首列
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        //处理首行首列
        for(int i=1; i<m; i++) {
            for (int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        if(zeroInCol0){
            for(int i=0; i<m; i++){
                matrix[i][0] = 0;
            }
        }

        if(zeroInRow0){
            for(int j=0; j<n; j++){
                matrix[0][j] = 0;
            }
        }
    }

    //No.26 删除语序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        int fast = 1, slow = 1;
        while (fast < n){
            //不重复
            if(nums[fast] != nums[fast -1]){
                //将不重复的数写在slow位置
                nums[slow] = nums[fast];
                //slow往后挪一位，用写下一个数
                slow++;
             }
            //继续往前遍历
            fast++;
        }
        return slow;
    }

    //No.55 跳跃游戏 一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度 判断你是否能够到达最后一个下标
    public boolean canJump(int[] nums) {
        //dp[i] 表示从起点到达i点还能超出i点的最大长度
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i=1; i<n; i++){
            dp[i] = Math.max(nums[i-1]-1, dp[i-1]-1);
            if(dp[i] < 0) return false;
        }
        if(dp[n-1] >= 0) return true;
        return false;
    }

    //No.80 删除有序数组中的重复项II
    public int removeDuplicatesII(int[] nums) {
        int n = nums.length;
        if(n <= 2) return n;

        int slow =2, fast = 2;

        while (fast < n){
            if(nums[slow -2] != nums[fast]){
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }

    //No.125验证回文数
    public boolean isPalindrome(String s) {
        int n = s.length();
        char[] charArray = s.toCharArray();
        int i = 0, j = n-1;
        while(i<=j){
            if(!Character.isAlphabetic(charArray[i]) && !Character.isDigit(charArray[i])){
                i++;
                continue;
            }
            if(!Character.isAlphabetic(charArray[j]) && !Character.isDigit(charArray[j])){
                j--;
                continue;
            }
            if(Character.toLowerCase(charArray[i]) != Character.toLowerCase(charArray[j])){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //No.189 轮转数组 将数组所有元素向有轮转k个位置
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // 避免k大于数组长度
        int prev = nums[0];
        int count = 0;
        int startIndex = 0;
        int currIndex = 0;
        //n个数都移动后停止循环
        while(count < n){
            currIndex = (currIndex+k)%n;
            int temp= nums[currIndex];
            nums[currIndex] = prev;
            prev = temp;
            count++;

            //如果currIndex又回到起点，currIndex往右一位继续上面操作
            if(startIndex == currIndex && ++currIndex < n){
                prev = nums[currIndex];
                startIndex = currIndex;
            }
        }
    }

    //No.274 H指数
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h=0; int i = citations.length-1;
        while (i>=0 && citations[i] > h){
            h++;
            i--;
        }
        return h;
    }

    //No.287 寻找重复数据. 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内, 假设 nums 只有 一个重复的整数 ，返回 这个重复的数
    public int findDuplicate(int[] nums) {

        //转换为求环形链表的入口，即No.142题
        int slow=0;
        int fast=0;

        slow=nums[slow];
        fast=nums[nums[fast]];

        while(slow != fast){
            slow = nums[slow];//slow一次一步
            fast = nums[nums[fast]];//fast一次两步
        }

        //当slow 与 fast 相遇时，额外使用一个指针 ptr。起始指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
        int pre1 = 0;
        int pre2 = slow;
        while(pre1 != pre2){
            pre1 = nums[pre1];
            pre2 = nums[pre2];
        }

        return pre1;
    }


    //No.31 下一个排列
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 从后向前找第一个升序元素i和j
        if(n==1) return;
        int i;
        for(i=n-2;i>=0; i--){
            if(nums[i] < nums[i+1]){
                break;
            }
        }

        //如果找不到，说明整个nums倒序，直接排序整个数组
        if(i>-1){
            //从右向左遍历找到第一个比nums[i]大的数，与nums[i]交换位置，并将i位置之后的所有数排序
            for(int k=n-1; k>=0; k--){
                if(nums[k] > nums[i]){
                    int temp = nums[k];
                    nums[k] = nums[i];
                    nums[i] = temp;
                    break;
                }
            }

        }
        Arrays.sort(nums, i+1, n);

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

    //No.643 子数组最大平均数I
    public double findMaxAverage(int[] nums, int k) {
        //滑动窗口长度为k
        int n = nums.length;
        int sum = 0;
        for(int i=0; i<k; i++){
            sum += nums[i];
        }
        int maxSum = sum;
        for (int i = k; i<=n; i++){
            sum = sum - nums[i-k] + nums[i];
            maxSum = Math.max(maxSum, sum);
        }
        return 1.0*maxSum/k;
    }

    //No.1456 定长子串中元音的最大数目
    public int maxVowels(String s, int k) {
        int n = s.length();
        int vowel_cout = 0;
        for(int i=0; i<k; i++){
            vowel_cout += isVowel(s.charAt(i));
        }
        int ans = vowel_cout;
        for(int i=k; i<n; i++){
            vowel_cout += isVowel(s.charAt(i)) - isVowel(s.charAt(i-k));
            ans = Math.max(ans, vowel_cout);
        }
        return ans;
    }

    private int isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ? 1:0;
    }

    //No.1004 最大连续1的个数III
    public int longestOnes(int[] nums, int k) {
        int N = nums.length;
        int res = 0;
        //对于任意右端点right，希望找到最小的左端点left，使得left和right之间不超过k个0，即为最大长度
        int left = 0, right = 0;
        int zeros = 0;
        while (right < N) {
            //记录遍历过程遇到0的个数zeros,
            if (nums[right] == 0)
                zeros ++;
            //如果zeros>k, 则记录之前0的个数等于k时的长度
            //left要移动到满足left和right闭区间只有k个0
            while (zeros > k) {
                if (nums[left] == 0){
                    zeros --;
                }
                left++;

            }
            res = Math.max(res, right - left + 1);
            right ++;
        }
        return res;
    }

    //No.1493 删除一个元素以后全为1的最长子数组
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int max = 0;
        //用一个boolean型遍历标识窗口内是否有0
        boolean hasZero = false;
        //记录上一个0的位置，left始终在上一个0的位置右侧紧邻的下一个位置，1️以保证left尽量靠左，则right-left 越长
        int lastZeroPos = 0;
        while(right < n){
            if(nums[right] == 0){
                if(hasZero){
                    left = lastZeroPos+1;
                }else{
                    hasZero = true;
                }
                lastZeroPos = right;
            }
            ////因为要删掉一个元素，所以这里没有用right-left+1
            max = Math.max(max, right - left);
            right++;
        }

        return max;
    }
}
