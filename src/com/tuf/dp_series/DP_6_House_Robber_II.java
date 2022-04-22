package com.tuf.dp_series;

public class DP_6_House_Robber_II {
    private static int robBasic_memoization(int lo, int n, int[] nums){
        if(n == lo){
            return nums[lo];
        }
        if(n < lo){
            return 0;
        }
        int includeCost = nums[n] + robBasic_memoization(lo, n-2, nums);
        int excludeCost = robBasic_memoization(lo, n-1, nums);

        return Math.max(includeCost, excludeCost);
    }

    private static int robBasic_tabulation(int lo, int n, int[] nums){
        int[] dp = new int[nums.length];
        dp[lo] = nums[lo];
        dp[lo+1] = Math.max(nums[lo], nums[lo+1]);

        for(int i = lo+2; i<=n; i++){
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[n];
    }


    public static int rob(int[] nums) {
        int includeFirst = robBasic_memoization(0, nums.length - 2, nums);
        int excludeFirst = robBasic_memoization(1, nums.length - 1, nums);
        return Math.max(includeFirst, excludeFirst);
    }

    public static int rob_tabulation(int[] nums) {
        int includeFirst = robBasic_tabulation(0, nums.length - 2, nums);
        int excludeFirst = robBasic_tabulation(1, nums.length - 1, nums);
        return Math.max(includeFirst, excludeFirst);
    }


    public static void main(String[] args){
        int[] treasure = new int[]{1, 2, 3, 1};
        System.out.println("Maximum treasure from robbing houses "+rob_tabulation(treasure));
    }
}
