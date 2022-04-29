package LeetCode;

import java.util.Arrays;

public class SearchInsertPosition {
	//https://leetcode.com/problems/search-insert-position/
	public static void main(String[] args){
	
		SearchInsertPosition a = new SearchInsertPosition();
		
		//int answer = a.searchInsert(new int[] {1,3,5,6}, 5);
		int answer = a.searchInsert(new int[] {1,3,5,6}, 2);
		//int answer = a.searchInsert(new int[] {1,3,5,6}, 7);
		
		
		System.out.println(answer);
				
	}
	
	public int searchInsert(int[] nums, int target) {
		int answer = Arrays.binarySearch(nums, target);
		
		if(answer < 0){
			return answer * -1 - 1;
		}
		return answer;
    }
}
