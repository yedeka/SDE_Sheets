package com.tuf.days.day1.arrays;

public class Stock_Buy_Sell {
    private static int findOptimumProfit(int[] nums){
        int min = nums[0], maxProfit = 0;
        for(int i=1; i<nums.length; i++){
            int current = nums[i];
            min = Math.min(min, current);
            maxProfit = Math.max(maxProfit, current - min);
        }
        return maxProfit;
    }
    public static void main(String[] args){
        int[] nums = new int[]{7,1,5,3,6,4};
        System.out.println("Maximum profit from stocks => "+findOptimumProfit(nums));
        nums = new int[]{7,6,4,3,1};
        System.out.println("Maximum profit from stocks => "+findOptimumProfit(nums));
    }
}
