package LeetCode;

public class StudentAttendenceRecord2 {
	//https://leetcode.com/problems/student-attendance-record-ii/
	public static void main(String[] args){
		System.out.println(new StudentAttendenceRecord2().checkRecord(10101));
	}
	
    static final int MODULO = 1000000007;
    public int checkRecord(int n) {
        int[][][] result = new int[n+1][2][3];
        
        result[0][0][0]= 1;
        for(int i = 0 ; i < n; ++i){
            for(int absCnt = 0; absCnt <= Math.min(i,1); ++absCnt){
                for(int lateCnt = 0; lateCnt <= Math.min(i, 2); ++lateCnt){
                    if(lateCnt < 2){
                        result[i+1][absCnt][lateCnt+1] = (result[i+1][absCnt][lateCnt+1] + result[i][absCnt][lateCnt])%MODULO;

                    }
                    
                    if(absCnt < 1){
                        result[i+1][absCnt+1][0] = (result[i+1][absCnt+1][0] + result[i][absCnt][lateCnt])%MODULO;
                    }
                    
                    result[i+1][absCnt][0] = (result[i+1][absCnt][0] + result[i][absCnt][lateCnt])%MODULO;
                }
            }            
        }
        return (((result[n][0][0] + result[n][0][1])%MODULO + (result[n][0][2] + result[n][1][0])%MODULO)%MODULO + (result[n][1][1] + result[n][1][2])%MODULO)%MODULO;
    }
	
}
