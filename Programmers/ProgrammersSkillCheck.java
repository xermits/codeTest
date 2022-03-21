import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ProgrammersSkillCheck {

	public static void main(String[] args){
//		System.out.println(Arrays.toString(new ProgrammersSkillCheck().level1Problem1(new int [] {5, 9, 7, 10}, 5)));		
//		System.out.println(new ProgrammersSkillCheck().level2Problem1(78));
//		
//		System.out.println(new ProgrammersSkillCheck().level2Problem2(new int [] {3,0,6,1,5}));
//		System.out.println(new ProgrammersSkillCheck().level2Problem2(new int [] {0,0,0,0,0}));
//		System.out.println(new ProgrammersSkillCheck().level2Problem2(new int [] {7,7,7,7,7}));
		
		//System.out.println(new ProgrammersSkillCheck().level3Problem1(6,4,6,2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
		
		System.out.println(new ProgrammersSkillCheck().level3Problem2(8,2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
		System.out.println(new ProgrammersSkillCheck().level3Problem2(8,2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"}));
	}
	
	public String level3Problem2(int n, int k, String[] cmd) {		
		LinkedListNode[] nodes = new LinkedListNode[n];
		
		LinkedListNode head = new LinkedListNode(-1);
		nodes[0] = new LinkedListNode(0);
		head.Next = nodes[0];
		nodes[0].Prev = head;

		for(int i = 1; i < n; ++i){
			nodes[i] = new LinkedListNode(i);
			nodes[i].Prev = nodes[i-1];
			nodes[i-1].Next = nodes[i];			
		}
		
		ArrayList<LinkedListNode> removed = new ArrayList<>();
		
		LinkedListNode currentNode = nodes[0];
		for(int i = 0; i < k; ++i){
			currentNode = currentNode.Next;
		}
		
		
		for(String c : cmd){
			String[] splitted = c.split("\\s");
			
			switch(splitted[0]){
			case "U":
				for(int i = 0; i < Integer.parseInt(splitted[1]); ++i){
					if(currentNode.Prev != null && currentNode.Prev != head){
						currentNode = currentNode.Prev;
					}
				}
				break;
			case "D":
				for(int i = 0; i < Integer.parseInt(splitted[1]); ++i){
					if(currentNode.Next != null){
						currentNode = currentNode.Next;
					}
				}
				break;
			case "C":
				removed.add(currentNode);
				currentNode.Prev.Next = currentNode.Next;
				if(currentNode.Next != null){
					currentNode.Next.Prev = currentNode.Prev;
					currentNode = currentNode.Next;
				}else{
					currentNode = currentNode.Prev;
				}
				break;
			case "Z":
				LinkedListNode revived = removed.remove(removed.size()-1);
				revived.Prev.Next = revived;
				if(revived.Next != null){
					revived.Next.Prev = revived;
				}
			default:
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		currentNode = head.Next;
		int expectIndex = 0;
		while(currentNode != null){
			for(; expectIndex < currentNode.Index; expectIndex++){
				sb.append("X");
			}
			sb.append('O');
			currentNode = currentNode.Next;
			expectIndex++;
		}
		
		for(; expectIndex < n; expectIndex++){
			sb.append("X");
		}
		
		return sb.toString();
	}
	
	class LinkedListNode{
		public LinkedListNode(int index){
			this.Index = index;
		}
		public int Index;
		public LinkedListNode Prev = null;
		public LinkedListNode Next = null;
	}
	
	public static final int DEFAULT_MAX_VAL = 2000000 + 1;
	
    public int level3Problem1(int n, int s, int a, int b, int[][] fares) {
    	
    	int[][] minDist = floydWarshall(n, fares);
    	
    	int minVal = DEFAULT_MAX_VAL;
    	for(int i = 1; i <= n; ++i){
    		minVal = Math.min(minVal, minDist[i][s] + minDist[i][a] + minDist[i][b]);    		
    	}
        
        return minVal;
    }
	
    private int[][] floydWarshall(int n, int[][] nodes) {
    	int[][] returnMinDist = new int[n+1][n+1];
    	
    	for(int i = 1 ; i <= n; ++i){
    		for(int j = 1 ; j <= n; ++j){
    			returnMinDist[i][j] = i==j ? 0: DEFAULT_MAX_VAL;
    		}
    	}
    	
    	for(int[] node : nodes){
    		returnMinDist[node[0]][node[1]] = node[2];
    		returnMinDist[node[1]][node[0]] = node[2];
    	}
    	
    	for(int k = 1; k <= n; ++k){
    		for(int i = 1; i <= n; ++i){
    			for(int j = 1; j <= n; ++j){
    				returnMinDist[i][j] = Math.min(returnMinDist[i][j], returnMinDist[i][k] + returnMinDist[k][j]);    				
    			}
    		}
    	}    	
    			
    	return returnMinDist;
	}

	public int[] level1Problem1(int[] arr, int divisor) {
        ArrayList<Integer> returnList = new ArrayList<Integer>();
        for(int value : arr){
            if( value%divisor == 0){
                returnList.add(value);
            }
        }
        
        Collections.sort(returnList);
        
        if(returnList.size() > 0){        	        	
        	return returnList.stream().mapToInt(Integer::intValue).toArray();
        }
        
        return new int[] {-1};
    }
    
    public int level1Problem2(int[] a, int[] b) {
        int answer = 0;

        for(int i = 0; i < a.length; ++i){
            answer += a[i]*b[i];
        }

        return answer;
    }
    
    public int level2Problem1(int n) {
    	//find first zero from last nonzero
    	
    	if(n == 0){
    		return -1;
    	}
    	
    	int val = n;
    	
    	int firstBitPos = 0;
    	while((val & 0x01) == 0x00){
    		val = val >> 1;
    		firstBitPos++;
    	}
    	
    	int continuousBitCnt = 0;
    	while((val & 0x01) == 0x01){
    		val = val >> 1;
    		continuousBitCnt++;
    	}
    	
    	int returnVal = n >> firstBitPos + continuousBitCnt << firstBitPos + continuousBitCnt;
    	
    	returnVal = returnVal | (0x01 << firstBitPos + continuousBitCnt);
    	
    	for(int i = 0 ; i< continuousBitCnt - 1; ++i){
    		returnVal = returnVal | (0x01 << i); 
    	}
    	
    	return returnVal;
    }
    
    public int level2Problem2(int[] citations) {
    	Arrays.sort(citations);
    	
		int i = 0;
		while(i < citations.length && citations[i] < citations.length - i ){
			i++;
		}

		return citations.length - i;
    }    
}
