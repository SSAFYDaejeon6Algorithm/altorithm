import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        // 1. dp 값 저장할 배열 생성
        int depth = triangle.length;
        int[][] dp = new int[depth][depth];
        
        // 2. 맨 윗줄에서부터 순차적으로 누적 최대값 저장
        dp[0][0] = triangle[0][0];
        for(int i=1; i<depth; i++){
            dp[i][0] = dp[i-1][0] + triangle[i][0]; // 왼쪽 가장자리
            dp[i][i] = dp[i-1][i-1] + triangle[i][i]; // 오른쪽 가장자리
            for(int j=1; j<i; j++) dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j]; // 안쪽
        }
        
        // 3. 마지막 줄에서의 최댓값 구하기
        int answer = -1;
        for(int num : dp[depth-1]) answer = Math.max(answer, num);
        return answer;
    }
}
