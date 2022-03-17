package com.tuf.days.day1.arrays;

import java.util.Arrays;

public class Sort_0_1_2 {
    // Time complexity O(nlogn), Space complexity O(1)
    private static void sort012_BruteForce(int[] nums){
        Arrays.sort(nums);
    }

    // Time Complexity - O(N) - 2 * O(N) - Since we iterate through array twice.
    // Space Complexity - O(1) - No extra space is used.
    private static void better_counting_sort(int[] nums){
        //Step 1 - Count the number of 0s, 1s and 2s
        int cnt0 = 0, cnt1 = 0, cnt2 = 0;
        for(int i=0; i<nums.length; i++){
            int val = nums[i];
            if(val == 0){
                cnt0 += 1;
            } else if(val == 1){
                cnt1 += 1;
            } else {
                cnt2 += 1;
            }
        }
        // Step 2 - Now iterate through array and place the 0, 1 and 2
        int index = 0;
        while(cnt0 -- > 0){
            nums[index++] = 0;
        }
        while(cnt1 -- > 0){
            nums[index++] = 1;
        }
        while(cnt2 -- > 0){
            nums[index++] = 2;
        }
    }

    private static void optimized_dutch_national_flag(int[] nums){
        int lo = 0, mid = 0, hi = nums.length - 1;
        while(mid <= hi){
            int midElement = nums[mid];

            switch(midElement){
                case 0: { // Swap nums[lo] and nums[mid] and lo++, mid++
                    int temp = nums[lo];
                    nums[lo] = nums[mid];
                    nums[mid] = temp;
                    lo++;
                    mid++;
                    break;
                }
                case 1: {
                    mid ++;
                    break;
                }
                case 2:{ // swap nums[mid] and nums[hi] and decrement hi
                    int temp = nums[hi];
                    nums[hi] = nums[mid];
                    nums[mid] = temp;
                    hi--;
                    break;
                }
            }
        }
    }

    public static void main(String[] args){
        int[] nums = new int[]{2,0,2,1,1,0};
        optimized_dutch_national_flag(nums);

        for(int i: nums){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
