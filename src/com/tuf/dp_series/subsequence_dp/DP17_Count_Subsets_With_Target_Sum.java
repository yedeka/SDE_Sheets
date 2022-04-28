package com.tuf.dp_series.subsequence_dp;

import java.util.Arrays;

public class DP17_Count_Subsets_With_Target_Sum {
    private static int count_subsets_zero_sum_recursive(int[] nums, int target, int index){
        // Base case 1 - sum == 0
        if(target == 0){
            return 1;
        }
        //Base case 2 - We have reached the alst element i.e. index == 0
        if(index == 0){
            if(nums[0] == target){
                return 1;
            }
            return 0;
        }
        // Now iterate over the array by either including or excluding the element
        int excludeElement = count_subsets_zero_sum_recursive(nums, target, index - 1);
        int includeElement = 0;
        if(nums[index] <= target){
            includeElement = count_subsets_zero_sum_recursive(nums, target-nums[index], index - 1);
        }
        return excludeElement + includeElement;
    }

    private static int count_subsets_zero_sum_memoization(int[] nums, int target, int index, int[] dp){
        // Base case 1 - sum == 0
        if(target == 0){
            return dp[index] = 1;
        }
        //Base case 2 - We have reached the alst element i.e. index == 0
        if(index == 0){
            if(nums[0] == target){
                return dp[0] = 1;
            }
            return dp[0] = 0;
        }
        if(dp[index] != -1){
            return dp[index];
        }
        // Now iterate over the array by either including or excluding the element
        int excludeElement = count_subsets_zero_sum_recursive(nums, target, index - 1);
        int includeElement = 0;
        if(nums[index] <= target){
            includeElement = count_subsets_zero_sum_recursive(nums, target-nums[index], index - 1);
        }
        return dp[index] = excludeElement + includeElement;
    }

    private static int count_subsets_target_sum_tabulation(int[] nums, int target){
        int n = nums.length, m = target+1;
        int[][] dp = new int[n][m];
        // Handle base cases - Case 1 for any index sum of 0 gives a count of 1 i.e. 0th column of all rows is 1
        for(int i=0; i<n; i++){
            dp[i][0] = 1;
        }
        // Base case 2 - At index 0 for sum of nums[0] count is 1
        dp[0][nums[0]] = 1;
        // Now start iterating over the array
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                int notTaken = dp[i-1][j];
                int taken = 0;
                if(nums[i]<=j){
                    taken = dp[i-1][j-nums[i]];
                }
                dp[i][j] = notTaken + taken;
            }
        }
        return dp[n-1][m-1];
    }

    private static int count_subsets_target_sum_tabulation_spaceOptimized(int[] nums, int target){
        int n = nums.length, m = target+1;
        int[] dp = new int[m];
        // Handle base cases - Case 1 for any index sum of 0 gives a count of 1 i.e. 0th column of all rows is 1
        for(int i=0; i<n; i++){
            dp[0] = 1;
        }
        // Base case 2 - At index 0 for sum of nums[0] count is 1
        dp[nums[0]] = 1;
        // Now start iterating over the array
        for(int i=1; i<n; i++){
            int[]temp = new int[m];
            for(int j=0; j<m; j++){
                int notTaken = dp[j];
                int taken = 0;
                if(nums[i]<=j){
                    taken = dp[j-nums[i]];
                }
                temp[j] = notTaken + taken;
            }
            dp = temp;
        }
        return dp[m-1];
    }

    private static int count_subsets_zero_sum(int[] nums, int target, boolean recursive){
        int index = nums.length-1;
        if(recursive){
            return count_subsets_zero_sum_recursive(nums, target, index);
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, -1);
        return count_subsets_zero_sum_memoization(nums, target, index, dp);
    }
    public static void main(String[] args){
        int[] nums = new int[]{1, 2, 2, 3};
        int target = 3;
        nums = new int[]{0, 0, 1};
        target = 1;

        System.out.println("Count of subsets with target sum recursive => "+count_subsets_zero_sum(nums, target, true));
        System.out.println("Count of subsets with target sum using memoization => "+count_subsets_zero_sum(nums, target, false));
        System.out.println("Count of subsets with target sum using tabulation => "+count_subsets_target_sum_tabulation(nums, target));
        System.out.println("Count of subsets with target sum using tabulation space optimized => "+count_subsets_target_sum_tabulation_spaceOptimized(nums, target));
    }
}
