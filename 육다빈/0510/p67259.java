import java.util.*;

class Solution {
    int N, answer=Integer.MAX_VALUE;
    int[] di = {-1, 0, 1, 0};
    int[] dj = {0, 1, 0, -1};
    
    void dfs(int i, int j, int[][] board, int pre_d, int cost){
        if(i == N-1 && j == N-1) {
            answer = cost;
            return;
        }
        for(int d=0; d<4; d++){
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni<0 || ni>=N ||nj<0 || nj>=N || board[ni][nj]==-1 || board[ni][nj]==1) continue;
            
            int now_c = cost;
            if(pre_d==-1 || d==pre_d) now_c += 100;
            else now_c += 600;
            if(now_c < answer){
                board[ni][nj] = -1;
                dfs(ni, nj, board, d, now_c);
                board[ni][nj] = 0;                
            }

        }
    }
    public int solution(int[][] board) {
        N = board.length;
        
        board[0][0] = -1;

        dfs(0, 0, board, -1, 0);
        
        return answer;
    }
}
