import java.util.*;

class Solution {
    char[][] map;
    int[] di = {-1, 0, 1, 0};
    int[] dj = {0, 1, 0, -1};
    class People{
        int i, j;
        People(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    boolean isSafe(People start, People end){
        Queue<People> q = new LinkedList<People>();
        boolean[][] visit = new boolean[5][5];
        q.add(start);
        visit[start.i][start.j] = true;
        for(int dep=0; dep<2; dep++){
            int size = q.size();
            for(int n=0; n<size; n++){
                People now = q.poll();
                for(int d=0; d<4; d++){
                    int ni = now.i + di[d];
                    int nj = now.j + dj[d];
                    if(ni<0 || ni>=5 || nj<0 || nj>=5 || map[ni][nj]=='X' || visit[ni][nj]) continue;
                    if(ni==end.i && nj==end.j) return false;
                    visit[ni][nj] = true;
                    q.add(new People(ni, nj));
                }
            }
        }
        return true;
    }

    boolean checkRoom(String[] places){
        map = new char[5][5];
        Queue<People> queue = new LinkedList<People>();
        for(int i=0; i<5; i++){
            map[i] = places[i].toCharArray();
            for(int j=0; j<5; j++) {
                if(map[i][j]=='P') queue.add(new People(i, j));
            }
        }
        while(!queue.isEmpty()){
            People now = queue.poll();
            int size = queue.size();
            for(int i=0; i<size; i++){
                People p = queue.poll();
                if(Math.abs(now.i-p.i)+Math.abs(now.j-p.j) <= 2 && !isSafe(now, p)) 
                    return false;
                queue.add(p);
            }
        }
        return true;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i=0; i<5; i++){
            if(checkRoom(places[i])) answer[i] = 1;
            else answer[i] = 0;
        }

        return answer;
    }
}
