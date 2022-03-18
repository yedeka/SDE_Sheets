package com.tuf.days.day2.arrays;

public class Repeat_Missing_Numbers {
    private static int[] findRepeatMissing_Brute_Force(int[] nums){
        int[] result = new int[2];
        //Use counting sort with an auxillary array.
        int[] aux = new int[nums.length + 1];
        for(int i=0; i<nums.length; i++){
            aux[nums[i]] += 1;
        }
        // Now iterate over aux array to find out missing and extra number
        for(int i=1; i< aux.length; i++){
            if(aux[i] > 1){
                result[0] = i;
            } else if(aux[i] == 0){
                result[1] = i;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[] nums = new int[]{3, 1, 2, 5, 3};
        int[] result = findRepeatMissing_Brute_Force(nums);
        System.out.println(result[0]+", "+result[1]);
    }
}
