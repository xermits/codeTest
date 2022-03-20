import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ProgrammersSkillCheck {

	public static void main(String[] args){
		System.out.println(Arrays.toString(new ProgrammersSkillCheck().level1Problem1(new int [] {5, 9, 7, 10}, 5)));		
		System.out.println(new ProgrammersSkillCheck().level2Problem1(78));
		
		System.out.println(new ProgrammersSkillCheck().level2Problem2(new int [] {3,0,6,1,5}));
		System.out.println(new ProgrammersSkillCheck().level2Problem2(new int [] {0,0,0,0,0}));
		System.out.println(new ProgrammersSkillCheck().level2Problem2(new int [] {7,7,7,7,7}));
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
