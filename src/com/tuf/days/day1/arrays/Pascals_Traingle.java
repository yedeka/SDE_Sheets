package com.tuf.days.day1.arrays;

import java.util.ArrayList;
import java.util.List;

public class Pascals_Traingle {
    private static List<List<Integer>> generatePascals(int levels){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> rowList, pre = null;
        for(int i=0; i<levels; i++){
             rowList = new ArrayList<>();
            for(int j=0; j<=i; j++){
                if(j == 0 || j==i){
                    rowList.add(1);
                } else {
                    rowList.add(pre.get(j-1) + pre.get(j));
                }
            }
            result.add(rowList);
            pre = rowList;
        }
        return result;
    }

    private static void printPascalesTriangle(List<List<Integer>> triangle){
        for(List<Integer> triangleRow: triangle){
            for(int element: triangleRow){
                System.out.print(element+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        int N = 5;
        List<List<Integer>> pt = generatePascals(N);
        printPascalesTriangle(pt);
    }
}
