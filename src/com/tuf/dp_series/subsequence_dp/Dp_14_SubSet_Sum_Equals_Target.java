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
            return nums[index] == target;
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
    }
}
