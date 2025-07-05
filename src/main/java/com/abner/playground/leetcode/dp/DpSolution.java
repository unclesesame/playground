package com.abner.playground.leetcode.dp;

public class DpSolution {

    public static void main(String[] args) {
        DpSolution solution = new DpSolution();
        solution.rob(new int[]{2,7,9,3,1}); // output:12 偷0号,2号,4号房屋，得到最高金额4
        solution.minCostClimbingStairs(new int[]{10,15,20}); //ouput: 15
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
