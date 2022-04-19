package LeetCode;

public class MonotonicArray {
	//https://leetcode.com/problems/monotonic-array/
	
	public static void main(String[] args){
		boolean result = new MonotonicArray().isMonotonic(new int[] {1,1,1});
		
		System.out.println(result);
	}
	
	public boolean isMonotonic(int[] nums) {
		//초기값과 다른 값이 나올때까지 forward한다.
		int firstValue = nums[0];
		
		int secondIndex;
		for(secondIndex = 1; secondIndex < nums.length; ++secondIndex){
			if(firstValue != nums[secondIndex]){
				break;
			}
		}
		
		if(secondIndex == nums.length){
			return true;
		}
		
		int prevValue = nums[secondIndex]; 
				
		if(nums[secondIndex] > firstValue){
			for(int i = secondIndex + 1; i < nums.length; ++i){
				if(nums[i] < prevValue){
					return false; 
				}
				prevValue = nums[i];
			}
			return true;			
		}else{
			for(int i = secondIndex + 1; i < nums.length; ++i){
				if(nums[i] > prevValue){
					return false; 
				}
				prevValue = nums[i];
			}
			return true;
		}		
	}
}
