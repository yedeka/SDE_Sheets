package com.tuf.dp_series.subsequence_dp;

import java.util.Arrays;

public class Dp_14_SubSet_Sum_Equals_Target {
    private static boolean subsetSumEqTargetRecursive(int[] nums, int index, int target){
        // Step 1 - Base Case
        if(target == 0){
            return true;
        }
        if(index == 0){
            return nums[index] == target;
        }
        // Step 2 - Handle the case of selection and noSelection
        boolean noSelection = subsetSumEqTargetRecursive(nums, index - 1, target);
        boolean selection = false;
        if(target >= nums[index]){
            selection = subsetSumEqTargetRecursive(nums, index-1, target - nums[index]);
        }
        return noSelection || selection;
    }

    private static boolean subsetSumEqTargetRecursive_memoized(int[] nums, int index, int target, int[][] dp){
        // Step 1 - Base Case
        if(target == 0){
            return true;
        }
        if(index == 0){
            return nums[0] == target;
        }
        if(dp[index][target] != -1){
            return dp[index][target] == 0 ? true: false;
        }
        // Step 2 - Handle the case of selection and noSelection
        boolean noSelection = subsetSumEqTargetRecursive(nums, index - 1, target);
        boolean selection = false;
        if(target >= nums[index]){
            selection = subsetSumEqTargetRecursive(nums, index-1, target - nums[index]);
        }
        dp[index][target] = noSelection || selection == true ? 0 : 1;
        return noSelection || selection;
    }

    private static boolean subsetSumEqTargetTabulation(int[] nums, int target){
        boolean[][] dp = new boolean[nums.length][target+1];
        // Step 1 - Handle the base case we return true if target == 0, since target is on columns we will have to iterate over all rows to make 0th column true.
        for(int i=0; i<nums.length; i++){
            dp[i][0] = true;
        }
        // Step 1 -  Second base case at index 0 make the column == target true
        dp[0][nums[0]] = true;
        // Step 2 - Iterate over DP array to populate recurrence relation.
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                boolean notTake = dp[i-1][j];
                boolean take = false;
                if(j >= nums[i]){
                    take = dp[i-1][j-nums[i]];
                    dp[i][j] = take || notTake;
                }
            }
        }
        return dp[nums.length - 1][target];
    }

    private static boolean subsetSumEqTargetTabulation_Optimized(int[] nums, int target){
        boolean[] dp = new boolean[target+1];
        // Step 1 - Handle the base case we return true if target == 0, since target is on columns we will have to iterate over all rows to make 0th column true.
        dp[0] = true;
        // Step 2 - Iterate over DP array to populate recurrence relation.
        for(int i=1; i<nums.length; i++){
            boolean[] temp = new boolean[target+1];
            temp[0] = true;
            for(int j=1; j<dp.length; j++){
                boolean notTake = dp[j];
                boolean take = false;
                if(j >= nums[i]){
                    take = dp[j-nums[i]];
                    temp[j] = take || notTake;
                }
            }
            dp = temp;
        }
        return dp[target];
    }

    public static void main(String[] args){
        int[] nums = new int[]{2, 3, 1, 1};
        int target = 4;
        boolean isSubsetSumTarget = subsetSumEqTargetRecursive(nums, nums.length - 1, target);
        System.out.println("Does the array have a subset sum equal to target recursive => "+isSubsetSumTarget);
        int[][] dp = new int[nums.length][target+1];
        for(int[] rows: dp){
            Arrays.fill(rows, -1);
        }
        boolean isSubSetSumTarget_memoized = subsetSumEqTargetRecursive_memoized(nums, nums.length-1, target, dp);
        System.out.println("Does the array have a subset sum equal to target memoized => "+isSubSetSumTarget_memoized);
        System.out.println("Does the array have a subset sum equal to target tabulation => "+subsetSumEqTargetTabulation(nums, target));
        System.out.println("Does the array have a subset sum equal to target tabulation space optimized=> "+subsetSumEqTargetTabulation_Optimized(nums, target));
    }
}
