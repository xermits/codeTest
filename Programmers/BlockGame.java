import java.util.ArrayList;
import java.util.HashSet;

public class BlockGame {
	//Problems : https://programmers.co.kr/learn/courses/30/lessons/42894	
	class Position{
		public Position(int row, int col){
			this.Row = row;
			this.Col = col;
		}
		
		public int Row;
		public int Col;
	}
	
	class Block{
		public Block(int no, Position startPos, boolean isWide, Position missing1, Position missing2){
			this.BlockNo = no;
			this.IsWide = isWide;
			this.StartPos = startPos;
			this.Missing1 = missing1;
			this.Missing2 = missing2;
		}
		
		public int BlockNo;
		public boolean IsWide;
		public Position StartPos;
		public Position Missing1;
		public Position Missing2;
	}
	
	public static void main(String[] args){
		BlockGame s = new BlockGame();
		int answer = s.solution(new int[][] {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,4,0,0,0},{0,0,0,0,0,4,4,0,0,0},{0,0,0,0,3,0,4,0,0,0},{0,0,0,2,3,0,0,0,5,5},{1,2,2,2,3,3,0,0,0,5},{1,1,1,0,0,0,0,0,0,5}});
		System.out.println(answer);
	}
	
	public int solution(int[][] board) {
		int n = board.length;
        ArrayList<Block> initialBlockList = scanRemovableBlocks(board, n);
        ArrayList<Block> blockList = new ArrayList<Block>(initialBlockList);
        
        int[] nonZeroRowIndex = makeNonzeroRowIndex(board, n);
        
        while(true){
        	ArrayList<Block> afterBlockList = new ArrayList<Block>();
            for(Block block : blockList){
                if(removeIfPossible(block, board, nonZeroRowIndex) == false){
                    afterBlockList.add(block);
                }else{
                	nonZeroRowIndex = makeNonzeroRowIndex(board, n);
                }
            }
            if(afterBlockList.size() == blockList.size()){
                break;
            }
            blockList = afterBlockList;
        }
    
        return initialBlockList.size() - blockList.size();
    }

	private int[] makeNonzeroRowIndex(int[][] board, int n) {
		int[] returnVal = new int[n];
		
		for(int col = 0; col < n; ++col){
			returnVal[col] = n;
			for (int row = 0; row < n; ++row){
				if(board[row][col] != 0){
					returnVal[col] = row;
					break;
				}
			}
		}
		
		return returnVal;
	}

	private boolean removeIfPossible(Block block, int[][] board, int[] nonZeroRowIndex) {
		if(nonZeroRowIndex[block.Missing1.Col] <= block.Missing1.Row){
			return false;
		}
		
		if(nonZeroRowIndex[block.Missing2.Col] <= block.Missing2.Row){
			return false;
		}

		for(int row = 0; row < (block.IsWide ? 2 : 3); ++row){
			for(int col = 0; col < (block.IsWide ? 3 : 2); ++col){
				board[block.StartPos.Row + row][block.StartPos.Col + col] = 0;
			}			
		}

		return true;
	}

	private ArrayList<Block> scanRemovableBlocks(int[][] board, int n) {
		ArrayList<Block> returnList = new ArrayList<Block>();
		HashSet<Integer> processed = new HashSet<Integer>();
		
		for(int row = 0; row < n; ++row){
			for(int col = 0; col < n; ++col){
				if(board[row][col] == 0){
					continue;
				}
				if(processed.contains(board[row][col])){
					continue;
				}
				
				processed.add(board[row][col]);
				
				Block removableBlock = getBlockIfRemovable(board, row, col, n);
				
				if(removableBlock != null){
					returnList.add(removableBlock);
				}
			}
		}
		return returnList;
	}

	private Block getBlockIfRemovable(int[][] board, int row, int col, int n) {
		int blockNo = board[row][col];

		if(col+1 < n && board[row][col+1] == blockNo){
			return null;
		}
		
		if(col + 2 < n && board[row+1][col+2] == blockNo){
			return new Block(blockNo, new Position(row, col), true, new Position(row, col + 1), new Position(row, col + 2));
		}
		
		if(col - 2 >= 0 && board[row+1][col-2] == blockNo){
			return new Block(blockNo, new Position(row, col - 2), true, new Position(row, col - 2), new Position(row, col - 1));
		}
		
		if(col - 1 >= 0 && col + 1 < n && board[row+1][col-1] == blockNo  && board[row+1][col+1] == blockNo){
			return new Block(blockNo, new Position(row, col - 1), true, new Position(row, col - 1), new Position(row, col + 1));
		}
		
		if(row + 2 < n && board[row+2][col] == blockNo){
			if(col + 1 < n && board[row+2][col+1] == blockNo){
				return new Block(blockNo, new Position(row, col), false, new Position(row, col + 1), new Position(row + 1, col + 1));
			}
			
			if(col - 1 >= 0 && board[row+2][col-1] == blockNo){
				return new Block(blockNo, new Position(row, col-1), false, new Position(row, col - 1), new Position(row + 1, col - 1));
			}
		}
		return null;
	}	
}
