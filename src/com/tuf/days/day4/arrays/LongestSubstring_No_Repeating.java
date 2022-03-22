package com.tuf.days.day4.arrays;

import java.util.HashSet;

public class LongestSubstring_No_Repeating {
    // Better solution
    // Time Coimplexity - O(2*n) in case of repeating characters we will have left pointer traverse the same characters again until the duplicate character is removed.
    private static int findLongestNoRepeat(String strInput){
        int longestLegnth = 0, l=0 ;
        char[] chars = strInput.toCharArray();
        HashSet<Character> visitedSet = new HashSet<>();

        for(int r=0; r<chars.length; r++){
            char current = chars[r];
            while(visitedSet.contains(current)){
                l ++;
                visitedSet.remove(current);
            }
            longestLegnth = Math.max(longestLegnth, r - l+1);
            visitedSet.add(current);
        }
        return longestLegnth;
    }
    public static void main(String[] args){
        String strInput = "abcaabcdba";
        System.out.println("Better Approach longest length => "+findLongestNoRepeat(strInput));
    }
}
