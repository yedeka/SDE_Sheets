package com.tuf.days.day2.arrays;

public class Rotate_Image {
    // Brute force way of rotating by creating a dummy matrix.
    // TC -> O(N*N)
    // SC -> O(N*N) Since we use a dummy matrix to sotre the results
    private static int[][] rotateMatrix_Brute_Force(int[][] matrix){
        int length = matrix.length;
        int[][] dummy = new int[length][length];

        // Logic for rotation - Very simple since we are moving over each row column of every element becomes row of the element in dummy matrix and
        // n-i-1 becomes the column in the dummy matrix.
        for(int i=0; i<length; i++){
            for(int j=0; j<length; j++){
                dummy[j][length-i-1] = matrix[i][j];
            }
        }
        return dummy;
    }

    // in place rotation done in 2 steps
    // Step 1 - First transpose the matrix - Can be done using diagonal traversal touching every row when gap > 0
    // Step 2 - Reverse rows of transposed matrix
    private static void optimizedInPlace(int[][] matrix){
        int cols = matrix[0].length;
        // Step 1 - Perform transpose of matrix using gap strategy.
        for(int gap =1; gap<matrix.length; gap++){
            for(int i=0,j=gap; j < matrix.length; i++,j++){
                if(i != j){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
        // Step 2 - Now reverse every row of the transposed matrix to rotate the matrix.
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][cols-j-1];
                matrix[i][cols-j-1] = temp;
            }
        }
    }


    private static void printMatrix(int[][] matrix){
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        int[][] matrix = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] rotated = rotateMatrix_Brute_Force(matrix);
        //printMatrix(rotated);
        optimizedInPlace(matrix);
        printMatrix(matrix);
    }
}
