package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj16928_뱀과사다리게임 {

    public static class Point {
        int from;
        int to;
    }

    public static int ans = 0;
    public static Point[] points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = stoi(st.nextToken());
        int S = stoi(st.nextToken());
        points = new Point[R+S];

        for(int i = 0 ; i < R+S ; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point();
            points[i].from = stoi(st.nextToken());
            points[i].to = stoi(st.nextToken());
        }


        bfs();
        System.out.println(ans-1);
    }

    public static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[101];

        queue.add(1);
        visited[1] = true; // 1은 시작점이니 방문처리

        while(!queue.isEmpty()){
            int size = queue.size(); // for문 인자는 가변적인거 말고 꼭 고정적인 변수로..
            for(int t = 0 ; t < size ; t++){
                int now = queue.poll();
                if(now == 100) return;

                for(int dice = 1 ; dice <= 6 ; dice++) {
                    int next = now + dice;

                    if (next > 100) break; // 100 초과일 때
                    if (visited[next]) continue; // 이미 방문한 지점일 때

                    for (Point p : points) { // 현재 지점에 사다리나 뱀이 있는지 확인
                        if (now == p.from) {
                            visited[next] = true;
                            next = p.to;
                            if (visited[next]) continue;
                        }
                    }
                    queue.add(next);
                    visited[next] = true;
                }
            }
            ans++;
        }
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}
