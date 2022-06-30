// dfs풀이

class Solution {
    static int cnt = 0;
    public int solution(int[] numbers, int target) {
        
        dfs(0, 0, numbers, target);
   
        return cnt;
    }
    
    static void dfs(int idx, int sum, int[] numbers, int target){
        if(idx == numbers.length){
            if(sum == target) {
                cnt++;
            }
            return;
        }
        dfs(idx+1, sum + numbers[idx], numbers, target);
        dfs(idx+1, sum - numbers[idx], numbers, target);
    }
}


// bfs풀이
import java.util.*;
class Solution {
    static Queue<tNum> q = new LinkedList<>();
    public int solution(int[] numbers, int target) {
        int answer = 0;
        q.offer(new tNum (numbers[0], 0)); // + 
        q.offer(new tNum (-1 * numbers[0], 0)); // -
        
        while(!q.isEmpty()){
            tNum tnum = q.poll();
            tnum.idx += 1;
            
            if(tnum.idx < numbers.length){
                q.offer(new tNum(tnum.num + numbers[tnum.idx], tnum.idx)); // + 
                q.offer(new tNum(tnum.num - numbers[tnum.idx], tnum.idx)); // -
            }
            
            if(tnum.idx == numbers.length){
                if(tnum.num == target) answer++;
            }
        }
        return answer;
    }
    
    static class tNum{
        int num, idx;
        tNum(int num, int idx){
            this.num = num;
            this.idx = idx;
        }
    }
}