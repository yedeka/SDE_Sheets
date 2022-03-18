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

    private static int[] usingBitWise(int[] nums){
        int xor1;
        int set_bit_no;

        //Step 1 - take XOR of array elements with each other.
        xor1 = nums[0];
        for(int i=1; i<nums.length; i++){
            xor1 = xor1 ^ nums[i];
        }
        // Step 2 - Now take XOR of 1 to N with the answer of step 1
        for(int i=1; i<=nums.length; i++){
            xor1 = xor1 ^ i;
        }
        // Step 3 - This answer has XOR of missing number and repeating number.
        // Now set the rightmost XOR bit
        set_bit_no = xor1 & ~(xor1 - 1);
        // Step 4 - Now divide the array numbers into 2 sets with rightmost set bit as 1 and 0
        int x=0, y = 0;
        for(int i=0; i<nums.length; i++){
            if((nums[i] & set_bit_no) != 1){
                x = x ^ nums[i]; // we take XOR here itself since we need it in future for
            }
            else {
                y = y ^ nums[i]; // we take XOR here itself since we need it in future for
            }
        }
        // Step 5 - Now take XOR of the two numbers with matching numbers from 1...N
        for(int i=1; i<=nums.length; i++){
            if((i & set_bit_no) != 1){
                x = x ^ i; // we take XOR here itself since we need it in future for
            }
            else {
                y = y ^ i; // we take XOR here itself since we need it in future for
            }
        }

        return new int[]{x, y};
    }



    public static void main(String[] args){
        int[] nums = new int[]{3, 1, 2, 5, 3};
        // Counting sort usage
        int[] result = findRepeatMissing_Brute_Force(nums);
        System.out.println(result[0]+", "+result[1]);
        result = usingSeries(nums);
        System.out.println(result[0]+", "+result[1]);
        result = usingBitWise(nums);
        System.out.println(result[0]+", "+result[1]);
    }
}
