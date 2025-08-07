package com.abner.playground.leetcode.greedy;


import java.util.*;



public class GreedySolution {

    public static void main(String[] args) {
        //int[] nums = {1, 2, 2, 1, 1, 3};
        String s = "a good   example";
        GreedySolution solution = new GreedySolution();
        // double result = solution.findMaxAverage(nums, 4);
        //solution.reverseWords(s);
        //solution.decodeString("3[a]2[bc]");
        //int ret = solution.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1});
        //int ret = solution.compress(new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'});
        //solution.permute(new int[]{0,1,2});
        //System.out.println(ret);
    }

    //No.134 加油站
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //如果x到达不了y+1，那么x-y之间的点也不可能到达y+1，因为中间任何一点的油都是拥有前面的余量的，所以下次遍历直接从y+1开始
        int n = gas.length;
        int i=0;
        //从0号加油站开始遍历，如果不能到达y+1，直接从y+1开始尝试
        while  (i<n){
            int sumOfGas = 0;
            int sumOfCost = 0;
            int count = 0;//记录走过了多少个加油站
            while (count < n){
                int j = (i+count)%n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas){
                    break;
                }
                count++;
            }
            if(count == n){
                return i;
            }else{
                i = i + count + 1;
            }
        }
        return -1;
    }

    //No.452 用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points) {
        //按左侧排序， 按右侧则是a[1], b[1]
        Arrays.sort(points, (o1,o2) -> Integer.compare(o1[0], o2[0]));
        int n = points.length;
        //发现一个重叠 -> n-1
        for(int i=1; i<points.length; i++){
            //排序后 后一位的左侧小于等于 前一位的右侧，说明存在重叠
            if(points[i][0] <= points[i-1][1]){
                n--;
                //下一轮只比右侧，因此只更新右侧即可
                points[i][1] = Math.min(points[i][1], points[i-1][1]);
            }
        }
        return n;
    }


    private void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }




    public int compress(char[] chars) {
        int n = chars.length;
        int left = 0;
        int right =0;
        while(right < n){
            char curr = chars[right];
            int count =0;
            while(right < n && chars[right] == curr){
                count ++;
                right++;
            }
            chars[left++] = curr;
            if(count > 1){
                char[] counter = (count +"").toCharArray();
                for(char c: counter){
                    chars[left++] = c;
                }
            }
        }
        return left;
    }





    public boolean increasingTripletGreedy(int[] nums) {
        int n = nums.length;
        if(n < 3 ) return false;

        int first=nums[0];
        int second = Integer.MAX_VALUE;
        int i=1;
        while(i < n){
            if(nums[i] > second) return true;
            if(nums[i] > first) {
                second = nums[i];
            }else {
                first = nums[i];
            }
            i++;
        }
        return false;
    }

    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if(n < 3) return false;

        int[] leftMin = new int[n];
        leftMin[0] = 0;

        int[] rigthMax = new int[n];
        rigthMax[n-1] = nums[n-1];

        for(int i=1; i<n; i++){
            leftMin[i] = Math.min(leftMin[i-1],nums[i]);
        }

        for(int j=n-2; j>=0; j--){
            rigthMax[j] = Math.max(rigthMax[j+1], nums[j]);
        }

        for(int i=1; i<n-1; i++){
            if(nums[i] > leftMin[i-1] && nums[i] < rigthMax[i+1]) return true;
        }
        return false;
    }


    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];

        left[0] = 1;
        for(int i=1; i<n; i++){
            left[i] = left[i-1] * nums[i-1];
        }

        right[n-1] = 1;
        for(int j=n-2; j>=0 ; j--){
            right[j] = right[j+1] * nums[j+1];
        }

        int[] ans = new int[n];
        for(int z=0; z<n; z++){
            ans[z] = left[z] * right[z];
        }

        return ans;
    }


    public int eraseOverlapIntervals(int[][] intervals) {

        if(intervals.length == 0) return 0;

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int n = intervals.length;
        int right = intervals[0][1];
        int ans=1;

        for(int i=1; i<n ; i++){
            if(intervals[i][0] > right){
                ans++;
                right = intervals[i][1];
            }
        }

        return n-ans;
    }

    public String decodeString(String s) {
            Deque<Integer> countStack = new ArrayDeque<>(); // 存储数字
            Deque<String> stringStack = new ArrayDeque<>(); // 存储字符串
            String currentString = ""; // 当前解码字符串
            int k = 0; // 当前的倍数

            for (char ch : s.toCharArray()) {
                if (Character.isDigit(ch)) {
                    k = k * 10 + (ch - '0'); // 处理多位数
                } else if (ch == '[') {
                    // 遇到 '['，将当前的字符串和数字推入各自的栈
                    countStack.push(k);
                    stringStack.push(currentString);
                    currentString = ""; // 重置当前字符串
                    k = 0; // 重置倍数
                } else if (ch == ']') {
                    // 遇到 ']'，解码
                    StringBuilder temp = new StringBuilder(stringStack.pop());
                    int repeatTimes = countStack.pop();
                    for (int i = 0; i < repeatTimes; i++) {
                        temp.append(currentString); // 重复当前字符串
                    }
                    currentString = temp.toString(); // 更新当前字符串
                } else {
                    // 如果是字母，直接加到当前字符串
                    currentString += ch;
                }
            }

            return currentString;
    }

    public String reverseWords(String s) {
       s = s.trim();
       String[] strArray = s.split(" ");
       Deque<String> deque = new ArrayDeque<>();
       for(String str: strArray){
           if(!str.trim().isEmpty()){
               deque.push(str.trim());
           }
       }
       StringBuilder stringBuilder = new StringBuilder();
       while(!deque.isEmpty()){
           stringBuilder.append(deque.pollLast());
           stringBuilder.append(" ");
       }
       return stringBuilder.toString().trim();
    }


    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        int n = digits.length();
        if (n == 0) return result;

        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));
        result = map.get(digits.charAt(0));
        if (n == 1) return result;

        int i = 1;
        while (i < n) {
            List<String> temp = new ArrayList<>();
            for (String s1 : result) {
                for (String s2 : map.get(digits.charAt(i))) {
                    temp.add(s1 + s2);
                }
            }
            result = temp;
            i++;
        }
        return result;
    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int a: asteroids){
            boolean alive = true;
            while(alive && !deque.isEmpty() && a < 0 && deque.peek() > 0){
                if(Math.abs(deque.peek()) == Math.abs(a) ){
                    deque.pop();
                    alive = false;
                }else if(Math.abs(deque.peek()) > Math.abs(a) ){
                    alive = false;
                }else{
                    deque.pop();
                }
            }
            if(alive){
                deque.push(a);
            }
        }


        int[] ans = new int[deque.size()];
        int i=0;
        while(!deque.isEmpty()){
            ans[i] = deque.pollLast();
            i++;
        }
        return ans;
    }

    public String removeStars(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for(char c: s.toCharArray()){
            if(c == '*'){
                deque.pop();
            }else{
                deque.push(c);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while(!deque.isEmpty()){
            char c = deque.pollLast();
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<List<Integer>, Integer> countMap = new HashMap<>();

        for(int[] row: grid){
            List<Integer> array = new ArrayList();
            for(int i: row){
                array.add(i);
            }
            countMap.put(array, countMap.getOrDefault(array, 0)+1);
        }

        int ans = 0;
        for(int col=0; col< n; col++){
            List<Integer> array = new ArrayList<>();
            for(int row=0; row<n; row++){
                array.add(grid[row][col]);
            }
            if(countMap.containsKey(array)){
                ans += countMap.get(array);
            }
        }
        return ans;
    }


    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int j = stack.pop();
                answer[j] = i - j;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()){
            answer[stack.pop()] = 0;
        }

        return answer;
    }





    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2; i<=n; i++){
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[n];
    }

    public int tribonacci(int n) {
        if(n == 0) return 0;
        if(n <= 2) return 1;
        int p=0, q=0, r=1, s=1;

        for(int i =3; i<=n; i++){
            p = q;
            q = r;
            r = s;
            s = p+q+r;
        }

        return s;

    }

    private int guess(int num){
        return 0;
    }

    public int guessNumber(int n) {
        int left = 1;
        int right =n;
        int mid =0;
        int res = -1;
        while(left < right){
            mid = left + (right -left) / 2;
            int re = guess(mid);
            if(re == 0){
                return mid;
            }else if(re == -1){
                right = mid -1;
            }else if(re == 1){
                left = mid + 1;
            }
        }
        return res;
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        for (int value : nums1) {
            set1.add(value);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int value : nums2) {
            set2.add(value);
        }

        Set<Integer> set1Result = new HashSet<>();
        for (int value : nums1) {
            if (!set2.contains(value)) set1Result.add(value);
        }

        Set<Integer> set2Result = new HashSet<>();
        for (int value : nums2) {
            if (!set1.contains(value)) set2Result.add(value);
        }

        List result = new ArrayList();
        result.add(set1Result.toArray());
        result.add(set2Result.toArray());
        return result;
    }

    public boolean uniqueOccurrences(int[] arr) {

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++){
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        Set<Integer> hashSet = new HashSet<>(map.values());
        return hashSet.size() == map.values().size();
    }

    public int pivotIndex(int[] nums) {
        int n = nums.length;
        //if(n==1) return 0;
        int sum=0;

        for(int i=0; i<n; i++){
            sum+= nums[i];
        }

        int leftSum = 0;
        int rightSum = sum;
        for(int i=0; i<n; i++){

            if(i == 0) {
                leftSum = 0;
                rightSum = sum  - nums[i];
            }else{
                leftSum += nums[i-1];
                rightSum -= nums[i];
            }
            if(leftSum == rightSum) return i;
        }

        return -1;
    }


    public int largestAltitude(int[] gain) {
        int sum =0;
        int max =0;
        int n = gain.length;
        int[] result = new int[n+1];
        result[0] = 0;
        for(int i=0; i< n ; i++){
            result[i+1] = sum + gain[i];
            sum = result[i+1];
            max = Math.max(max, result[i+1]);
        }
        return max;

    }



    public boolean canPlaceFlowers(int[] flowerbed, int n) {
       int m = flowerbed.length;
       for(int i=0; i< m; ++i){
           if((i==0 || flowerbed[i-1] == 0) && flowerbed[i] == 0 &&  (i == m-1 || flowerbed[i+1] == 0)){
               flowerbed[i] = 1;
               if(--n == 0) return true;
            }
       }
       return false;
    }

    public boolean isSubsequence(String s, String t) {


        int slen = s.length();
        int tlen = t.length();
        if(slen > tlen) return false;
        if(s.isEmpty()) return true;

        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int i=0, j=0;
        while(i < slen && j<tlen){
           if(sArray[i] != tArray[j]){
               i++;
           }
           j++;
        }
        return i == slen;
    }

    public String reverseVowels(String s) {
        List<Character> list = Arrays.asList('A','E','I','O','U','a','e','i','o','u');
        Set<Character> vowelSet = new HashSet<>(list);

        Stack<Character> vowelStack = new Stack<>();

        char[] charArray = s.toCharArray();

        for(int i=0; i< charArray.length; ++i){
            if(vowelSet.contains(charArray[i])){
                vowelStack.push(charArray[i]);
            }
        }

        for(int i=0; i< charArray.length; ++i){
            if(vowelSet.contains(charArray[i])){
                charArray[i] = vowelStack.pop();
            }
        }

        return String.valueOf(charArray);
    }


    public boolean test(char ch){
        return "AEIOUaeiou".indexOf(ch) >= 0;
    }



    public double findMaxAverage(int[] nums, int k) {
        int sum=0;
        int n=nums.length;
        for(int i=0;  i< k; ++i){
            sum += nums[i]; //2
        }
        int maxSum=sum; //2
        for(int i=1; i<=n-k; ++i){
            sum = sum - nums[i-1] + nums[i+k-1];
            maxSum = Math.max(sum,  maxSum);
        }

        return 1.0*maxSum/k;
    }

    public int maxOperations(int[] nums, int k){
        Arrays.sort(nums);
        int right = nums.length -1;
        int left = 0;
        int count= 0;
        while(left < right){
            if(k == nums[left] + nums[right]) {
                count++;
                left++;
                right--;
            }else if(k < nums[left] + nums[right]) {
                right--;
            }else {
                left++;
            }
        }
        return count;
    }


    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        for(int i=0; i< candies.length;++i){
            if(isMax(candies, candies[i] + extraCandies)){
                result.add(true);
            }else{
                result.add(false);
            }
        }
        return result;
    }

    private boolean isMax(int[] num, int k){
        for(int i=0; i < num.length ; ++i){
            if(k < num[i])
                return false;
        }
        return true;
    }

    public String gcdOfStrings(String str1, String str2){
        int i=0;
        int min = Math.min(str1.length(), str2.length());

        while(i < min && str1.charAt(i) == str2.charAt(i)){
            i++;
        }

        if(i==0) {
            return "";
        }else{
            return str1.substring(0,i);
        }
    }

    public String mergeAlternately(String word1, String word2){
        StringBuilder builder = new StringBuilder();
        int min = Math.min(word1.length(), word2.length());
        int i=0; int j=0;
        while(i < min){
            builder.append(word1.charAt(i));
            builder.append(word2.charAt(i));
            i++;
        }
        if(word1.length() > min){
            builder.append(word1.substring(min));
        }
        if(word2.length() > min){
            builder.append(word2.substring(min));
        }
        return builder.toString();
    }


    public int longestConsetive(int[] nums){
        Set<Integer> numSet = new HashSet<Integer>();
        for(Integer num: nums){
            numSet.add(num);
        }

        int count=0;
        for(int i=0; i < nums.length; i++){
             if(numSet.contains(nums[i] - 1)) {
                 count = 1;
             }else{
                 int j = 1;
                 while(numSet.contains(nums[i]+j)){
                     j++;
                 }
                 if(j > count){
                     count = j;
                 }
             }
        }
        return count;
    }

    public List<List<String>> getResult(String[] strs){

        Map<String, List<String>> resultMap = new HashMap<>();
        for(String str: strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            if(!resultMap.containsKey(chars.toString())){
                List list = new ArrayList<>();
                list.add(str);
                resultMap.put(chars.toString(), list); //<aet, eat
            }else{
                List existingList = resultMap.get(chars.toString());
                existingList.add(str);
            }
        }

        return new ArrayList<> (resultMap.values());
    }

    public void moveZeros(int[] nums){
        //left pointer point to the number to be handeled, right pointer point to non-zero value.

        int left=0, right=0, n=nums.length;

        while(right < n){
            if(nums[right] != 0){
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    private void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right]= temp;
    }



    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> hashTable = new HashMap<>();
        for(int i=0; i < nums.length; i++){
            if(hashTable.containsKey(target - nums[i])){
                return new int[]{i, hashTable.get(target - nums[i])};
            }else{
                hashTable.put(nums[i], i);
            }
        }
        return new int[0];
    }

    public int singleNumber(int[] nums){
        return 0;
    }

    int num = 100;
    public int sum(int num){
        if(num == 1)
            return 1;
        return num + sum(num -1);
    }

    public int subarraySum(int[] nums, int k){
        int len = nums.length;
        int result=0;
        for(int i=0; i< len; i++){
            int sum = 0;
            for(int j=i; j < len; j++){
                sum += nums[j];
                if(sum == k){
                    result++;
                    break;
                }
            }
        }
        return result;
    }
}
