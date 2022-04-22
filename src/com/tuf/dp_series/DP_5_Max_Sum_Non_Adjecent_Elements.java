package com.tuf.dp_series;

public class DP_5_Max_Sum_Non_Adjecent_Elements {
    private static int findSumNonAdjecentSums(int[] nums, int index){
        if(index == 0){
            return nums[index];
        }
        if(index < 0){
            return 0;
        }
        int pickSum = nums[index] + findSumNonAdjecentSums(nums, index - 2);
        int nonPickSum = findSumNonAdjecentSums(nums, index - 1);
        return Math.max(pickSum, nonPickSum);
    }
    public static void main(String[] args){
        int[] nums = new int[]{2, 1, 4, 9};
        System.out.println("Maximum sum of non adjecent elements => "+findSumNonAdjecentSums(nums, nums.length-1));
    }
}
