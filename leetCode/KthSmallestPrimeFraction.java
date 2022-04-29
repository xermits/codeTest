package LeetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {
	//https://leetcode.com/problems/k-th-smallest-prime-fraction/
	public static void main(String[] args){
		
		KthSmallestPrimeFraction a = new KthSmallestPrimeFraction();
		
		int[] output = a.kthSmallestPrimeFraction(new int[]{1, 7}, 1);
		
		System.out.println(Arrays.toString(output));
		
	}
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
    	PriorityQueue<Node> minQueue = new PriorityQueue<Node>((x,y)->Double.compare(x.value, y.value));
    	boolean[][] taken = new boolean[arr.length][arr.length];
    	
    	enqueIfAvailable(minQueue, taken, arr, 0, arr.length - 1);
    	
    	Node curNode = null;
    	while(!minQueue.isEmpty()){
    		curNode = minQueue.poll();
    		
    		if(--k == 0){
    			break;
    		}
    		
    		enqueIfAvailable(minQueue, taken, arr, curNode.smallIndex + 1, curNode.bigIndex);
    		enqueIfAvailable(minQueue, taken, arr, curNode.smallIndex, curNode.bigIndex - 1);
    	}
    	
    	if(k == 0){
            return new int[]{arr[curNode.smallIndex], arr[curNode.bigIndex]};    		
    	}
    	return new int[]{0,0};
    }
    
    private void enqueIfAvailable(PriorityQueue<Node> minQueue, boolean[][] taken, int[] arr, int smallIndex, int bigIndex) {
    	if(smallIndex > bigIndex){
    		return;
    	}
    	
		if(taken[smallIndex][bigIndex]){
			return;
		}

		minQueue.add(new Node(smallIndex, bigIndex, (double)arr[smallIndex] / arr[bigIndex]));
		taken[smallIndex][bigIndex] = true;
	}

	class Node{
    	public int smallIndex;
    	public int bigIndex;
    	public double value;
		public Node(int smallIndex, int bigIndex, double value) {
			this.smallIndex = smallIndex;
			this.bigIndex = bigIndex;
			this.value = value;
		}
    	
    }

}
