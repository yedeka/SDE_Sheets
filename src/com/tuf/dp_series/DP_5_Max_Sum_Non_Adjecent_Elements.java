package com.tuf.dp_series;

public class DP_5_Max_Sum_Non_Adjecent_Elements {
    private static int findSumNonAdjecentSums_recursive(int[] nums, int index){
        if(index == 0){
            return nums[index];
        }
        if(index < 0){
            return 0;
        }
        int pickSum = nums[index] + findSumNonAdjecentSums_recursive(nums, index - 2);
        int nonPickSum = findSumNonAdjecentSums_recursive(nums, index - 1);
        return Math.max(pickSum, nonPickSum);
    }

    private static int findSumNonAdjecentSums_memoized(int[] nums, int[] dp ,int index){
        if(index == 0){
            return nums[index];
        }
        if(index < 0){
            return 0;
        }
        if(dp[index] != 0){
            return dp[index];
        }
        int pickSum = nums[index] + findSumNonAdjecentSums_recursive(nums, index - 2);
        int nonPickSum = findSumNonAdjecentSums_recursive(nums, index - 1);

        return dp[index] = Math.max(pickSum, nonPickSum);
    }
    private static int findSumNonAdjecentSums_tabulation(int[] nums){
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i=2; i< nums.length; i++){
            dp[i] = Math.max(nums[i] + dp[i-2], dp[i-1]);
        }
        return dp[nums.length-1];
    }
    private static int findSumNonAdjecentSums_tabulation_optimized(int[] nums){
        int secondPrev = nums[0];
        int prev = Math.max(nums[0], nums[1]);

        for(int i=2; i< nums.length; i++){
            int current = Math.max(nums[i] + secondPrev, prev);
            secondPrev = prev;
            prev = current;
        }
        return prev;
    }




    public static void main(String[] args){
        int[] nums = new int[]{2, 1, 4, 9};
        System.out.println("Maximum sum of non adjecent elements => "+findSumNonAdjecentSums_recursive(nums, nums.length-1));
        System.out.println("Maximum sum of non adjecent elements using memoization => "+findSumNonAdjecentSums_memoized(nums, new int[nums.length], nums.length-1));
        System.out.println("Maximum sum of non adjecent elements using tabulation => "+findSumNonAdjecentSums_tabulation(nums));
        System.out.println("Maximum sum of non adjecent elements using optimized tabulation => "+findSumNonAdjecentSums_tabulation_optimized(nums));
    }
}
