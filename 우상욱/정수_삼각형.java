import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int tri_size = triangle.length;
		int[][] dp = new int[tri_size][tri_size];
		
		dp[0][0] = triangle[0][0];
		
		for(int i = 0; i < tri_size - 1; i++) {
            for(int j = 0; j < triangle[i].length; j++) {
                dp[i + 1][j] = Math.max(dp[i + 1][j], triangle[i + 1][j] + dp[i][j]);
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j + 1], triangle[i + 1][j + 1] + dp[i][j]);
            }
        }
        
        int answer = 0;
        for(int i = 0; i < dp[tri_size - 1].length; i++)
            answer = Math.max(answer, dp[tri_size - 1][i]);   
        return answer;
    }
}