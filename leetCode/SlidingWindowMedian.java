package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
	/**
	 * Problems : https://leetcode.com/problems/sliding-window-median/
	 * @throws Exception 
	 */
	
	public static void main(String[] args) throws Exception{
		//double[] result = new SlidingWindowMedian().medianSlidingWindow(new int[] {1,3,-1,-3,5,3,6,7}, 3);
		double[] result = new SlidingWindowMedian().medianSlidingWindow(new int[] {2147483647,1,2,3,4,5,6,7,2147483647}, 1);
		
				
		//double[] result = new SlidingWindowMedian().medianSlidingWindow(new int[] {1,2,3,4,2,3,1,4,2}, 3);
		
		System.out.println(Arrays.toString(result));		
	}
	
	
	public double[] medianSlidingWindow(int[] nums, int k) throws Exception {		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		ArrayList<Double> result = new ArrayList<>();
		
		for(int i = 0 ; i < k; ++i){
			minHeap.add(nums[i]);
		}
		
		balanceHeap(maxHeap, minHeap);
		result.add(getMedian(maxHeap, minHeap));
		
		for(int i = k ; i < nums.length; ++i){
			int removeVal = nums[i-k];
			
			if(maxHeap.isEmpty() == false && removeVal <= maxHeap.peek()){
				maxHeap.remove(removeVal);
			}else if(minHeap.isEmpty() == false){
				minHeap.remove(removeVal);				
			}else{
				throw new Exception("Both Heap is Empty!");
			}
			
			int addVal = nums[i];
			
			Integer standardVal = getStandardVal(maxHeap, minHeap);			
			if(standardVal == null){
				maxHeap.add(addVal);
			}else if(addVal > standardVal){
				minHeap.add(addVal);
			}else{
				maxHeap.add(addVal);
			}
			
			balanceHeap(maxHeap, minHeap);
			result.add(getMedian(maxHeap, minHeap));
		}
	
		return result.stream().mapToDouble(i->i).toArray();		
	}


	private Integer getStandardVal(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
		if(maxHeap.isEmpty() == false){
			return maxHeap.peek();
		}
		
		if(minHeap.isEmpty() == false){
			return minHeap.peek();
		}
		
		return null;
	}


	private double getMedian(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {		
		if(maxHeap.size() == minHeap.size()){
			return maxHeap.peek()/2.0 + minHeap.peek()/2.0;
		}
		
		return maxHeap.peek();
	}


	private void balanceHeap(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {		
		while(true){
			if(maxHeap.size() < minHeap.size()){
				maxHeap.add(minHeap.poll());				
			}else if(maxHeap.size() > minHeap.size()+1){
				minHeap.add(maxHeap.poll());
			}else{
				break;
			}			
		}
	}	        
}
