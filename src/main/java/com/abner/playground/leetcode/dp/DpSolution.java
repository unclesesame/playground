package com.abner.playground.leetcode.dp;

public class DpSolution {

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        solution.rob(new int[]{2,7,9,3,1}); // output:12 偷0号,2号,4号房屋，得到最高金额4
        solution.minCostClimbingStairs(new int[]{10,15,20}); //ouput: 15
    }

    //No.42 接雨水
    public int trapByColumn(int[] height) {
        int n = height.length;
        int res = 0;
        //按列求每列可接多少雨水，第0列和第n-1列无法接雨水，直接跳过
        for(int i=1; i<n-1; i++){
            //求i列左侧最高的列
            int highestLeft = 0;
            for(int l=0; l <i; l++){
                highestLeft = Math.max(height[l], highestLeft);
            }
            //求i列右侧最高的列  (每轮都要算一遍左右两侧的最高列，可以用DP优化)
            int highestRight =0;
            for(int r=i+1; r <n; r++){
                highestRight = Math.max(height[r], highestRight);
            }

            //求左右两个测最矮的列
            int shorter = Math.min(highestLeft, highestRight);

            //若i列高度小于矮列，则可接雨水 矮列高-i列高

            if(height[i] < shorter) {
                res += shorter - height[i];
            }
            //若i列高度大于等于矮列，则不可接雨水
        }
        return res;
    }

    public int trapDP(int[] height) {
        int n = height.length;
        int res = 0;

        //max_left[i] 表示第i列左侧最高列
        int[] max_left = new int[n];
        max_left[0] = 0;
        for(int i=1; i<n; i++){
            max_left[i] = Math.max(max_left[i-1], height[i-1]);
        }

        //max_right[i] 表示第i列右侧最高列
        int[] max_right = new int[n];
        max_right[n-1] = 0;
        for(int j=n-2; j>0; j--){
            max_right[j] = Math.max(max_right[j+1], height[j+1]);
        }

        for(int i=1; i<n-1; i++){
            int shorter = Math.min(max_left[i], max_right[i]);
            //若i列高度小于矮列，则可接雨水 矮列高-i列高
            if(height[i] < shorter) {
                res += shorter - height[i];
            }
            //若i列高度大于等于矮列，则不可接雨水
        }
        return res;
    }

    //No.279 完全平方数  给你一个整数 n ，返回 和为 n 的完全平方数的最少数量
    public int numSquares(int n) {
        //以15=9+4+1+1 为例， 这些数字，比如是[1, 根号下15]之前的取值 枚举这些数，假设当前枚举j，那么还需要凑i-j*j
        //所以子问题和原问题类似，只是规模表小。
        //所以是dp问题  dp[i] = 1 + min(dp[i-j*j]) j取[1,根号下i]
        int[] f = new int[n+1];
        for(int i=1; i<=n; i++){
            int minN = Integer.MAX_VALUE;
            for(int j=1; j*j <=i; j++){
                minN = Math.min(minN, f[i-j*j]);
            }
            f[i] =  minN + 1;
        }
        return f[n];
    }

    //No.72 编辑距离 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数, 可以word1执行三种操作
    //插入一个字符， 删除一个字符， 替换一个字符
    public int minDistance(String word1, String word2) {
        int m= word1.length();
        int n= word2.length();

        //处理空串
        if(m*n == 0){
            return m+n;
        }

        //定义状态dp[i][j]表示word1 前i个字符转换到word2 前j个字符需要的最少步数
        int[][] dp = new int[m+1][n+1];

        //处理边界
        for(int i=0; i<=m; i++){
            dp[i][0] = i;
        }
        for(int j=0;j<=n; j++){
            dp[0][j] = j;
        }

        //状态转移方程
        for(int i=1; i<= m; i++){
            for(int j=1; j<= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    //No.121 买卖股票的最佳时机 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++){
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }

    //一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格
    public int maxProfitII(int[] prices) {
        int n =  prices.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i=1; i<n; i++){
            //前一天就不持有，或前一天持有但今天需要卖出
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            //前一天就持有，或前一天不持有但今天需要买入
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

    //No.714 买卖股票的最佳时机含手续费
    // prices[i]表示第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。返回获得利润的最大值
    public int maxProfitWithFee(int[] prices, int fee) {
        int n =  prices.length;
        //dp[i][0]表示第i天不持有股票的最优解
        //dp[i][1]表示第i天持有股票的最优解
        int[][] dp = new int[n][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for(int i=1; i<n; i++){
            //前一天就不持有，或前一天持有但今天需要卖出
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]- fee);
            //前一天就持有，或前一天不持有但今天需要买入
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[n-1][0];
    }

    //No.300 最长递增子序列
    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        if(n == 0) return 0;
        int[] dp = new int[n];

        //dp[i] 表示前i个数的最长子序列
        dp[0] = 1;
        int maxans = 1;

        for(int i=1; i<n; i++){
            dp[i] = 1; //每轮先复位
            for(int j=0; j<i; j++){
                //当nums[i] 有比前面0到i-1位置的数更大时, dp[i]等于前0到i-1的最长子序列+1
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxans = Math.max(maxans, dp[i]);
        }

        return maxans;
    }

    //No.309 买卖股票的最佳时机，含冷冻期，买入卖出后，隔一天才能再次买入
    public int maxProfitWithFrozenPeriod(int[] prices) {
        int n =  prices.length;
        //dp[i][0]表示第i天不持有股票的最优解
        //dp[i][1]表示第i天持有股票的最优解
        int[][] dp = new int[n][3];

        //持有股票
        dp[0][0] = -prices[0];
        //不持有股票，且在冷冻期 在第 i 天结束之后的状态。如果第 i 天结束之后处于冷冻期，那么第 i+1 天无法买入股票。
        dp[0][1] = 0;
        //不持有股票，且不在冷冻期
        dp[0][2] = 0;


        for(int i=1; i<n; i++){
            //前一天就持有股票，或前一天不持有股票且不在冷冻期需要买入股票
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] - prices[i]);
            //由于卖出动作进入冷冻期, i-1天必然持有股票
            dp[i][1] = dp[i-1][0] + prices[i];
            //说明i当天没有任何操作，没有买所以不持有，也没有卖因为没有进入冷冻期
            dp[i][2] = Math.max(dp[i-1][1], dp[i-1][2]);

        }

        return Math.max(dp[n-1][1], dp[n-1][2]);
    }

    //No.790 多米诺和托米诺平铺 两种形状的瓷砖：一种是 2 x 1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转， 给定整数 n ，返回可以平铺 2 x n 的面板的方法的数量
    public int numTilings(int n) {
        final int MOD = 1000000007;
        //针对i列共有四种被覆盖的情况 使用 dp[i][s] 表示平铺到第 i 列时，各个状态 s 对应的平铺方法数量
        int[][] dp = new int[n+1][4];
        //边界
        dp[0][3] = 1;
        for(int i=1;i<=n;i++){
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;

        }

        return dp[n][3];
    }

    //多维dp
    //No. 1143 最长公共子序列
    public int longestCommonSubsequence(String text1, String text2) {
        //定义状态 dp[i][j] 代表text1.subString(0-i) 和text3.subString(0-j)的最长公共子序列
        //状态转移方程  dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
        //处理边界
        char[] charArray1 = text1.toCharArray();
        int m = charArray1.length;
        char[] charArray2 = text2.toCharArray();
        int n = charArray2.length;

        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++){
            dp[i][0] = 0;
        }

        for(int j=0; j<n; j++){
            dp[0][j] = 0;
        }

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                if(charArray1[i] == charArray2[j]){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }

            }
        }

        return dp[m-1][n-1];
    }

    // 二维dp
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m= obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if(obstacleGrid[m-1][n-1] == 1) return 0;
        //二维dp  f[i][j] 表示从左上角(0,0)到(i,j)位置的路径数量
        int[][] f = new int[m][n];
        boolean hasObstacle = false;
        //初始化边界，首行和首列遇到障碍后均设为0，其他为1
        for(int i=0; i<m; i++){
            if( obstacleGrid[i][0] == 1 || hasObstacle){
                hasObstacle = true;
                f[i][0] = 0;
            }else{
                f[i][0] =1;
            }
        }
        hasObstacle = false;
        for(int j=0; j<n; j++){
            if( obstacleGrid[0][j] == 1 || hasObstacle){
                hasObstacle = true;
                f[0][j] = 0;
            }else{
                f[0][j] = 1;
            }
        }

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                int up = obstacleGrid[i-1][j] == 1 ? 0 : f[i-1][j];
                int left = obstacleGrid[i][j-1] == 1 ? 0 : f[i][j-1];
                f[i][j] = up + left;
            }
        }

        return f[m-1][n-1];

    }

    //二维dp
    public int uniquePaths(int m, int n) {
        //二维dp  f[i][j] 表示从左上角(0,0)到(i,j)位置的路径数量
        int[][] f = new int[m][n];

        //初始化边界，首行和首列均设为1
        for(int i=0; i<m; i++){
            f[i][0] = 1;
        }
        for(int j=0; j<n; j++){
            f[0][j] = 1;
        }
        //状态转移方程 f[i][i] = f[i-1][j] + f[i][j-1]  只能从上面或者左边一格走过来
        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                f[i][j] = f[i-1][j] + f[i][j-1];
            }
        }
        return f[m-1][n-1];
    }

    //No.198 打家劫舍  如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警， 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
    public int rob(int[] nums) {

        //1. 定义状态 dp[i]表示前i个房屋时得最优解，即偷窃的最大金额
        //2. 状态转移方程 dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1])

        int n =  nums.length;
        if(n == 1) return nums[0];
        if(n == 2) return Math.max(nums[0], nums[1]);
        int[] dp = new int[n];

        //3. 初始化基础状态--处理边界
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i =2; i<n; i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
       return dp[n-1];
    }


    //No.746 使用最小代价爬楼梯  cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶 可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        //定义状态dp[i]，表示前面i级楼梯花费的最小值

        int n = cost.length;
        //处理边界
        int[] dp = new int[n+1];
        //因为可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯
        dp[0] = 0;
        dp[1] = 0;
        //状态转移方程 dp[i] 是从前面一级楼梯或前面二级楼梯取最小cost
        for(int i=2; i<=n; i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[n];
    }
}
