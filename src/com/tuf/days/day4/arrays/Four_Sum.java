package com.tuf.days.day4.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class Four_Sum {
    // Time Complexity - Approximately O(n^3)
    private static ArrayList<int[]> fourSumOptimal(int[] nums, int target){
        ArrayList<int[]> result = new ArrayList<>();
        int length = nums.length;
        // Step 1 - Sort the given array
        Arrays.sort(nums);
        for(int i=0; i<length; i++){
            for(int j =i+1; j<length; j++){
                int changedTarget = target - nums[i] - nums[j];
                int lo = j+1, hi = length-1;
                while(lo < hi){
                    int sum = nums[lo] + nums[hi];
                    if(sum < changedTarget){
                        lo ++;
                    } else if(sum > changedTarget){
                        hi --;
                    } else {
                        result.add(new int[]{nums[i], nums[j], nums[lo], nums[hi]});
                        // Now perform duplicate checks after adding the number for number 3
                        while(lo < hi && nums[lo] == result.get(result.size()-1)[2]){
                            ++lo;
                        }
                        // Now perform duplicate checks after adding the number for number 3
                        while(lo < hi && nums[hi] == result.get(result.size()-1)[3]){
                            --hi;
                        }
                    }
                }
                while(j + 1 < length && nums[j] == nums[j+1]){
                    j++;
                }
            }
            while(i + 1< length && nums[i] == nums[i+1]){
                i++;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1, 1, 1, 3 ,2, 4, 3, 2, 4, 4};
        int target = 9;
        ArrayList<int[]> result = fourSumOptimal(nums, target);
        for(int[] res:  result){
            System.out.println(res[0]+", "+res[1]+", "+res[2]+", "+res[3]);
        }
    }
}
