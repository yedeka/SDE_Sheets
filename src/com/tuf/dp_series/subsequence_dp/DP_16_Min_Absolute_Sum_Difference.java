package com.tuf.dp_series.subsequence_dp;

public class DP_16_Min_Absolute_Sum_Difference {
    private static int findMinAbsDiff(int[] nums){
        // Step 1 - First calculate the total sum of all the elements that form the maximum sum of the elements of the array.
        int totalSum = 0;
        for(int element: nums){
            totalSum += element;
        }
        // Step 2 - Now run tabulation do to populate the DP array to find out all possible combination sums of the elements
        boolean[][] dp = new boolean[nums.length][totalSum + 1];
        // Now run a tabulation DP to check for all possible sums
        // Step 2.1 - Base case at sum 0 all the indices will be true indicating given sum can be obtained
        for(int i=0; i<dp.length; i++){
            dp[i][0] = true;
        }
        // Step 2.2 - Base case at the first index mark the element equal to element at index zero to be true
        // This is true because at first element for sure the sum equal to first element can be made.
        if(nums[0] <= totalSum) {
            dp[0][nums[0]] = true;
        }
        // Step 3 - Iterate over the array to populate the subsets sum now
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                boolean notTaken = dp[i][j-1];
                boolean taken = false;
                if(j >= nums[i]){
                    taken = dp[i-1][j-nums[i]];
                }
                dp[i][j] = notTaken || taken;
            }
        }
        // Now we need to check at the last row of the DP array which is the last index for all possible sums to find out which one gives us the minimum difference
        int min = Integer.MAX_VALUE, n = nums.length;
        for(int i=0; i<= totalSum/2; i++){
            if(dp[n-1][i]){
                int elementsSum = i;
                int difference = Math.abs(i-(totalSum - i));
                min = Math.min(min, difference);
            }
        }
        return min;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println("Minimum absolute sum difference => "+findMinAbsDiff(nums));
    }
}
