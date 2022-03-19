package com.tuf.days.day3.arrays;

import java.util.ArrayList;

public class Majority_Element_II {
    private static int[] findMajortyElements(int[] nums){
        int element1 = nums[0], count1 = 1, element2 = nums[0], count2 = 0;
        for(int i=1; i<nums.length; i++){
            int current = nums[i];
            if(current == element1){
                count1 +=1;
            } else if(current == element2){
                count2 += 1;
            } else {
                count1 --;
                count2 --;
                if(count1 == 0){
                    element1 = current;
                    count1 = 1;
                } else if(count2 == 0){
                    element2 = current;
                    count2 = 1;
                }
            }
        }

        count1 = 0;
        count2 = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] == element1){
                count1 += 1;
            } else if(nums[i] == element2){
                count2 += 1;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        if(count1 > nums.length / 3){
            result.add(element1);
        }
        if(count2 > nums.length / 3){
            result.add(element2);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    public static void main(String[] args){
        int[] nums = new int[]{11,33,33,11,33,11};
        nums = new int[]{8, 3, 4, 8, 3, 2, 1, 9};
        int[] majorityElements = findMajortyElements(nums);
        System.out.println(majorityElements[0]+", "+majorityElements[1]);
    }
}
