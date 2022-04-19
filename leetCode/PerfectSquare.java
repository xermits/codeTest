package LeetCode;

public class PerfectSquare {
	
	//https://leetcode.com/problems/perfect-squares/
	
	public static void main(String[] args){
		
		int result = new PerfectSquare().numSquares(13);
		
		System.out.println(result);
		
	}
	
	private int[] memo;
	
	public int numSquares(int n) {
		this.memo = new int[n+1];
		
		return numSquaresRecursive(n);
    }
	
	private int numSquaresRecursive(int n){
		if(n == 1){
			return 1;
		}
		
		if(this.memo[n] != 0){
			return this.memo[n];
		}
		
		int minCount = Integer.MAX_VALUE;
		
		for(int i = 1; i*i <= n; ++i){
			if(n - i*i == 0){
				minCount = 1;
				break;
			}
			
			minCount= Math.min(minCount, numSquaresRecursive(n - i*i) + 1);
		}
		
		this.memo[n] = minCount;
		
		return minCount;
		
	}
	
}
