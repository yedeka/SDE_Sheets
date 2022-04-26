package com.tuf.dp_series.subsequence_dp;

public class DP15_Subset_Partition_Sum {
    private static boolean targetSumSubSet_Recursive(int[]nums, int target, int index){
        if(target == 0){
            return true;
        }
        if(index == 0){
            return nums[0] == target;
        }
        boolean excludeSum = targetSumSubSet_Recursive(nums, target, index-1);
        boolean includeSum = false;
        if(target >= nums[index]){
            includeSum = targetSumSubSet_Recursive(nums, target - nums[index], index-1);
        }
        return excludeSum || includeSum;
    }

    private static boolean targetSumSubSet_memoized(int[]nums, int target, int index, int[][] dp){
        if(target == 0){
            return true;
        }
        if(index == 0){
            return nums[0] == target;
        }
        if(dp[index][target] != -1){
            return dp[index][target] == 0;
        }
        boolean excludeSum = targetSumSubSet_Recursive(nums, target, index-1);
        boolean includeSum = false;
        if(target >= nums[index]){
            includeSum = targetSumSubSet_Recursive(nums, target - nums[index], index-1);
        }
        boolean result = excludeSum || includeSum;
        if(result){
            dp[index][target] = 0;
        } else {
            dp[index][target] = 1;
        }
        return result;
    }

    private static boolean subsetPartitionSum(int[] nums, int target, boolean recursionFlag){
        int modifiedTarget = target/2;
        if(recursionFlag){
            return targetSumSubSet_Recursive(nums, modifiedTarget, nums.length-1);
        } else {
            int[][] dp = new int[nums.length-1][target+1];
            return targetSumSubSet_memoized(nums, modifiedTarget, nums.length-1, dp);
        }
    }

    private static boolean subSetPartitionSum_Tabulation(int[] nums, int target){
        int modifiedTarget = target/2;
        boolean[][] dp = new boolean[nums.length][target+1];
        // Handle base cases
        // Step 1 - for target == 0 we return true. i.e. we mark 0th column of each row as 0
        for(int i=0; i<nums.length; i++){
            dp[i][0] = true;
        }
        // Step 2 - for 0th index if nums[0] == target we mark it as true i.e. dp[0][nums[0]] = true
        if(nums[0] <= target){
            dp[0][nums[0]] = true;
        }


        for(int i=1; i<nums.length; i++){
            for(int j=1; j<dp[0].length; j++){
                boolean exclude = dp[i-1][j];
                boolean include = false;
                if(j >= nums[i]){
                    include = dp[i-1][j-nums[i]];
                }
                dp[i][j] = include || exclude;
            }
        }
        return dp[nums.length-1][target];
    }

    private static boolean subSetPartitionSum_Tabulation_space_optimized(int[] nums, int target){
        boolean[] dp = new boolean[target+1];
        // Step 1 - Base case 1 at target == 0 we return true
        dp[0] = true;
        // Step 2 - At index 0 we mark target == nums[0] as true if nums[0] <= target
        if(nums[0] <= target){
            dp[nums[0]] = true;
        }
        for(int i=1; i<nums.length; i++){
            boolean[] temp = new boolean[dp.length];
            for(int j=1; j<dp.length; j++){
                boolean excludeSum = dp[j];
                boolean includeSum = false;
                if(j >= nums[i]){
                    includeSum = dp[j-nums[i]];
                }
                temp[j] = includeSum || excludeSum;
            }
            dp = temp;
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args){
        int[] nums = new int[]{2, 3, 3, 4, 5};
        int target = 10;
        System.out.println("Can we divide the array into 2 parts of equal sum "+target+" => "+subsetPartitionSum(nums, target, true));
        System.out.println("Can we divide the array into 2 parts of equal sum with Memoization "+target+" => "+subsetPartitionSum(nums, target, true));
        System.out.println("Can we divide the array into 2 parts of equal sum with tabulation "+target+" => "+subSetPartitionSum_Tabulation(nums, target));
        System.out.println("Can we divide the array into 2 parts of equal sum with space optimized tabulation "+target+" => "+subSetPartitionSum_Tabulation_space_optimized(nums, target));
    }
}
