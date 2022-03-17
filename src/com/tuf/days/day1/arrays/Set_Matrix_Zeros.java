package com.tuf.days.day1.arrays;

public class Set_Matrix_Zeros {
    // Brute force approach of searching for zero element and then making corresponding row and column elements 0.
    // Time Complexity => O(n*m) * O(n+m)
    // O(n*m) - Worst case complexity for matrix traversal
    // O(n+m) - time complexity to traverse all rows for a column and all columns for a row.
    private static void setMatrixZeros_Brute_Force(int[][] matrix){
        int rows = matrix.length, cols = matrix[0].length;
        // We set the elements in the same row and column as -1 to avoid flagging remaining elements as 0
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols;j++){
                if(matrix[i][j] == 0){// found the zero element now set up the rows and cols to be 0
                    int row = i-1;
                    // Set all the rows above to 0
                    while(row >=0){
                        matrix[row][j] = -1;
                        row--;
                    }
                    row = i+1;
                    while(row < rows){ // Set all the rows below to be 0
                        matrix[row][j] = -1;
                        row++;
                    }
                    int col = j-1;
                    while(col >=0 ){//Set all the columns to left of current column as zero
                        matrix[i][col] = -1;
                        col --;
                    }
                    col = j+1;
                    while(col < cols ){//Set all the columns to right of current column as zero
                        matrix[i][col] = -1;
                        col ++;
                    }
                }
            }
        }
        // After populating the elements as -1 turn them to 0 in one more pass.
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(matrix[i][j] == -1){
                    matrix[i][j] = 0;
                }
            }
        }

    }
    // Non brute force way of working where we take 2 dummy arrays and just set the index of row and element in the corresponding arrays as true upon encountering zero.
    // This saves us from the additional looping logic of goung back and forth and we can set the elements as zeros in the next pass.
    // Time complexity - O(n*m)
    private static void nonBruteForceLogic(int[][] matrix){
        int rows = matrix.length, cols = matrix[0].length;
        boolean[] zeroRow = new boolean[rows];
        boolean[] zeroCol = new boolean[cols];

        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(matrix[i][j] == 0){
                    // Flag the row and column index in the dummy array to be marked as zero later.
                    zeroRow[i] = true;
                    zeroCol[j] = true;
                }
            }
        }
        // Run the loop over matrix and refer to dummy array to mark the corresponding row and column as zero.
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols; j++){
                if(zeroRow[i] || zeroCol[j]){
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // Here we use 0th column and 0th row of selected cell to be 0. In this case we need to handle the cell 0,0 separately. This can be handled by handling row 0 separately or column 0 separately using a single variable.
    // So we check if the 0th column of the row is zero then mark the variable col0 as 0.
    // Otherwise when we get matrix[i][j] == 0 then we mark [0][j] and [i][0] with 0.
    // Since we are starting from the top left corner for us to not consider the marked zeros as original zeros we will have to start iterating from bottom right corcer i.e. [m-1][n-1]
    // Time Complexity (n*m), Space complexity O(1).
     private static void setZeroes_SE(int[][] matrix) {
        int col0 = 1, rows = matrix.length, cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < cols; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 1; j--)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if (col0 == 0) matrix[i][0] = 0;
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
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        matrix = new int[][]{{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        //setMatrixZeros_Brute_Force(matrix);
        // nonBruteForceLogic(matrix);
        setZeroes_SE(matrix);
        printMatrix(matrix);

    }

}
