package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj25307_시루의백화점구경 {

    static class Point{
        int x,y,distance;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static int N, M, K;
    static int[][] map;
    static Point start; // 시작점

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    public static void show(int[][] arr){
        for(int i = 0 ; i < arr.length ; i++){
            for(int j = 0 ; j < arr[i].length ; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }

    public static boolean isValid(int ni, int nj){
        return ni >= 0 && ni < N && nj >= 0 && nj < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken()); // 세로 길이
        M = stoi(st.nextToken()); // 가로 길이
        K = stoi(st.nextToken()); // 마네킹 거리
        List<Point> mannequin = new ArrayList<>(); // 장애물 저장 리스트
        map = new int[N][M]; // 백화점
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++){
                map[i][j] = stoi(st.nextToken());
                if(map[i][j] == 4){ // 시루의 시작점일 경우
                    start = new Point(i,j);
                } else if(map[i][j] == 3){ // 마네킹일 경우
                    mannequin.add(new Point(i,j));
                }
            }
        }

        // 마네킹 + K거리만큼들을 벽(1)로 바꾸어준다.
        for(Point m : mannequin){
            map[m.x][m.y] = 1;
            for(int ni = m.x - K ; ni <= m.x + K ; ni++){
                for(int nj = m.y - K ; nj <= m.y + K ; nj++){
                    if(isValid(ni, nj) && (Math.abs(ni-m.x) + Math.abs(nj-m.y) <= K) && map[ni][nj] == 0){
                        map[ni][nj] = 1;
                    }
                    if(isValid(ni, nj) && (Math.abs(ni-m.x) + Math.abs(nj-m.y) <= K) && map[ni][nj] == 2){ // 도착지가 마네킹 사정거리일때
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        // 백트래킹
        System.out.println(-bfs());
    }

    public static int bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start.x, start.y, 0));

        map[start.x][start.y] = -1;

        while (!queue.isEmpty()){
            Point now = queue.poll();

            for(int d = 0 ; d < 4 ; d++){
                int nexti = now.x + di[d];
                int nextj = now.y + dj[d];

                if(isValid(nexti, nextj) && map[nexti][nextj] == 2){ // 도착
                    return now.distance-1;
                }

                if(isValid(nexti, nextj) && map[nexti][nextj] == 0){ // 아무것도 없는 칸
                    map[nexti][nextj] = now.distance-1;
                    queue.add(new Point(nexti, nextj, now.distance-1));
                }
            }
        }
        return 1;
    }
}
