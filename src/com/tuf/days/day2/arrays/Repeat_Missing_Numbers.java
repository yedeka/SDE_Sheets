package com.tuf.days.day2.arrays;

public class Repeat_Missing_Numbers {
    // Time complexity - O(N)
    // Space complexity - O(N) -  we need an auxilary array to store counting sort.
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

    // Time complexity - o(N)
    // Space complexity - O(1) since we dont take any extra space.
    private static int[] usingSeries(int[] nums){
        int length = nums.length;
        long sum = (length * (length + 1))/2;
        long squareSum = sum * (2*length + 1)/3;

        for(int i=0; i<length; i++){
            sum -= nums[i];
            squareSum -= (nums[i] * nums[i]);
        }

        long missing = (sum + (squareSum / sum))/2;
        long repeating = missing - sum;

        int[] result = new int[]{(int)repeating, (int)missing};
        return result;
    }



    public static void main(String[] args){
        int[] nums = new int[]{3, 1, 2, 5, 3};
        // Counting sort usage
        int[] result = findRepeatMissing_Brute_Force(nums);
        System.out.println(result[0]+", "+result[1]);
        result = usingSeries(nums);
        System.out.println(result[0]+", "+result[1]);

    }
}
