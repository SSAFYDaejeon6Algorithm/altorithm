package day0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b16918_봄버맨 {
    static int R, C, N;
    static int[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                if (line.charAt(j) == '.') map[i][j] = 0; // 빈칸은 0으로
                else map[i][j] = 2; // 1초 지난 상태의 남은 초 넣어줌
            }
        } // end input
        int time = 1; // 1초 지난 상태로 시작
        while (time != N) {
            /*-------------------현재 초에서 다음 시간 넘어가는 상태------------------*/
            boolean[][] visited = new boolean[R][C];
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] >= 2) map[i][j]--; // 폭탄 남은 시간 3초 -> 2초, 2초 -> 1초 가는 경우
                    else if(map[i][j] == 1) { // 현재 1초 남았다면 폭발되는 칸임 (1초 -> 0초 되는 중이기 떄문)
                        map[i][j] = 0;
                        for (int d = 0; d < 4; d++) {
                            int nearR = i + di[d];
                            int nearC = j + dj[d];
                            // 옆에 칸이 유효범위이고 && 1초 -> 0초 가는 칸이 아닌 경우에만
                            // (옆칸이 1초 -> 0초로 가는 칸인데 0으로 바꿔버리면
                            //  빈칸(0)인줄 알고 폭탄 설치하는 칸인줄 알고 착각하기 때문)
                            if (nearR >= 0 && nearR < R && nearC >= 0 && nearC < C && map[nearR][nearC] != 1) {
                                visited[nearR][nearC] = true; // 1초 -> 0초로 바뀐 칸은 현재 초가 0초가 아니기 때문에 표시
                                map[nearR][nearC] = 0;
                            }
                        }
                    }
                    // 현재 0초였다면 (visited == false && map[i][j] == 0)
                    // 폭탄 설치해야하는 칸임. (0초 -> 3초)
                    else if (!visited[i][j] && map[i][j] == 0) map[i][j] = 3;
                    visited[i][j] = true;
                }
            }
            /*-----------------------다음 초로 다 넘어감------------------------------*/
            time++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i =0 ; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 0) sb.append('.');
                else sb.append('O');
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());


    }
}
