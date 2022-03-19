package com.tuf.days.day3.arrays;

public class Majority_Element {
    // Moore's voting algoriothm
    // Time Complexity - O(N)
    // Space Complexity - O(1)
    private static int findMajorityElement(int[] nums){
        int element = nums[0], count = 1;
        for(int i=1; i<nums.length; i++){
            int current = nums[i];
            if(current == element) {
                count += 1;
            } else {
                count -= 1;
            }
            if(count == 0){
                element = current;
                count = 1;
            }
        }
        return element;
    }
    public static void main(String[] args){
        int[] nums = new int[]{4,4,2,4,3,4,4,3,2,4};
        System.out.println("Majority element in given array => "+findMajorityElement(nums));
    }
}