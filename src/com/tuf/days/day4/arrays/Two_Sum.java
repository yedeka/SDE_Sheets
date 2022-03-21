package com.tuf.days.day4.arrays;

import java.util.Arrays;

public class Two_Sum {
    private static boolean find2Sum2pointer(int[] nums, int target){
        Arrays.sort(nums);
        int windowStart = 0, windowEnd = nums.length - 1;
        while(windowStart < windowEnd){
            int sum = nums[windowStart] + nums[windowEnd];
            if(sum < target){
                windowStart ++;
            } else if(sum > target){
                windowEnd --;
            } else {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[] nums = new int []{2,7,11,15};
        int target = 9;
        System.out.println("Does the array contain element with sum "+target+" => "+find2Sum2pointer(nums, target));
    }
}
