package com.tuf.days.day2.arrays;

public class Find_Duplicates {
    private static int findDiplicates(int[] nums){
        int slow = nums[0], fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        fast = nums[0];
        do{
            slow = nums[slow];
            fast = nums[fast];
        }while(slow != fast);
        return slow;
    }
    public static void main(String[] args){
        int[] nums = new int[]{2, 5, 9 ,6, 9, 3, 8, 9, 7, 1};
        System.out.println(findDiplicates(nums));
    }
}
