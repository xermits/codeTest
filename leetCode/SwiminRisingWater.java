package LeetCode;

import java.util.PriorityQueue;

public class SwiminRisingWater {
	//https://leetcode.com/problems/swim-in-rising-water/
	
	public static void main(String[] args){
		
		SwiminRisingWater c = new SwiminRisingWater();
		
		//int result = c.swimInWater(new int[][] {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}});
		
		int result = c.swimInWater(new int[][]  {{0,2},{1,3}});
		
		
		System.out.println(result);
		
	}
	
	private int height;
	private int width;
	private int[][] innerGrid;
	private boolean[][] taken;
	
    public int swimInWater(int[][] grid) {
    	this.innerGrid = grid;
    	this.height = innerGrid.length;
    	this.width = innerGrid[0].length;
    	
    	//Min값을 가지는 Heap을 만들어 이걸 Queue로 활용한다.
    	
    	PriorityQueue<Position> queue = new PriorityQueue<>((a,b) -> (Integer.compare(a.Height, b.Height)));
    	
    	this.taken = new boolean[height][width];
    	
    	int maxHeight = innerGrid[0][0];
    	
    	queue.add(new Position(grid[0][0], 0, 0));
    	taken[0][0] = true;    	
    	
    	while(!queue.isEmpty()){    		
    		Position cur = queue.poll();
    		
    		maxHeight = Math.max(maxHeight, cur.Height);
    		
    		if(cur.X == width -1 && cur.Y == height - 1){
    			return maxHeight;
    		}
    		
    		addQueueIfPossible(cur.X - 1, cur.Y, queue);
    		addQueueIfPossible(cur.X + 1, cur.Y, queue);
    		addQueueIfPossible(cur.X, cur.Y - 1, queue);
    		addQueueIfPossible(cur.X, cur.Y + 1, queue);
    	}
    	return -1;
    }
    

	private void addQueueIfPossible(int x, int y, PriorityQueue<Position> queue) {
		if(x < 0 || x >= this.width){
			return;
		}
		
		if(y < 0 || y >= this.height){
			return;
		}
		
		if(!this.taken[y][x]){
			queue.add(new Position(this.innerGrid[y][x], x, y));
			this.taken[y][x] = true;
		}
	}


	class Position{
    	public Position(int height, int x, int y){
    		this.Height = height;
    		this.X = x;
    		this.Y = y;
    	}
    	public int Height;
    	public int X;
    	public int Y;
    }
    
}
