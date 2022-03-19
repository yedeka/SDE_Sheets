package com.tuf.days.day3.arrays;

public class Sorted_2d_Matrix_Search {
    // Time Complexity - O(n*m)
    // Space Complexity - O(1)
    private static boolean isNumberPresent(int[][] nums, int target){
        int rows = nums.length, cols = nums[0].length;
        int lo =0, hi = (rows * cols - 1);
        boolean result = false;

        while(lo <= hi){
            int mid = (hi + lo) / 2;
            int currRow = mid/cols;
            int currCol = mid%cols;
            int currentValue = nums[currRow][currCol];
            if( currentValue== target){
                result = true;
                break;
            } else if(currentValue < target){
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return result;
    }
    public static void main(String[] args){
        int[][] matrix = new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        int target = 500;
        System.out.println("Is number present => "+isNumberPresent(matrix, target));
    }
}
