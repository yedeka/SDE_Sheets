package com.tuf.days.day1.arrays;

public class Next_Greater_Permutation {
    private static int findCeilIndex(int[] num){
        int index = -1;
        for(int i = num.length - 2; i>=0; i--){
            if(num[i]<num[i+1]){
                index = i;
            }
        }
        return index;
    }
    private static int findSwapIndex(int[] nums, int number){
        int swapIndex = -1;
        for(int i=nums.length - 1; i>=0; i--){
            if(nums[i] > number){
                swapIndex = i;
                break;
            }
        }
        return swapIndex;
    }
    private static void reverseArray(int[] num, int reverseIndex){
        int left = reverseIndex, right = num.length - 1;
        while(left < right){
            int temp = num[left];
            num[left] = num[right];
            num[right] = temp;
            left++;
            right--;
        }
    }
    private static int[] nextGreaterPermutation(int[] num){
        // Step 1 - Start from LSB and try to find a number that is not greater than it's right neighbor
        int ceilIndex = findCeilIndex(num);
        if(ceilIndex != -1){ // We got the number that is not maximum permutation of the number
        // Step 2 - Now find out the index of the number that is just greater than the number at ceil index.
        int smallerNumber = num[ceilIndex];
        int swapindex = findSwapIndex(num, smallerNumber);
        // Step 3 - Swap the ceil index with seap index
        num[ceilIndex] = num[swapindex];
        num[swapindex] = smallerNumber;
        // Step 4 - Reverse the array from ceilIndex + 1
        reverseArray(num, ceilIndex+1);
        }
        return num;
    }
    public static void main(String[] args){
        int[] number = new int[]{1, 3, 2};
        number = new int[]{4, 5, 2, 8, 7, 6, 4, 1};
        int[] permutation = nextGreaterPermutation(number);
        for(int element: permutation){
            System.out.print(element+" ");
        }
        System.out.println();
    }
}
