package day0412;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b17471_게리맨더링 {
    static int[][] adjMatrix;
    static int[] population;
    static boolean[] selected;
    static int N;
    static int min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        adjMatrix = new int[N + 1][N + 1];
        population = new int[N + 1];
        selected = new boolean[N + 1];

        min = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            population[i] = sc.nextInt(); // 구역 인구 수 담기
        }

        for (int i = 1; i <= N; i++) {
            int C = sc.nextInt();
            for (int c = 1; c <= C; c++) {
                int a = sc.nextInt();
                adjMatrix[i][a] = 1;
            }
        } // end input
        set_bit();
        if (min == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(min);
    }


    static void set_bit() {

        for (int i = 0; i < (1 << N); i++) { // N개의 지역으로 만들 수 있는 부분집합의 수 (ex: N = 3이면  1 << N = 8)
            selected = new boolean[N + 1];
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) > 0) { // 비트 연산을 사용하여 a 지역인 부분 체크 (ex: i = 3이면 11 이기 때문에 1, 2 지역 선택됨)
                    selected[j + 1] = true;
                }
            }

            int aCnt = 0,bCnt = 0;
            for (int k = 1; k <= N; k++) {
                if (selected[k]) {
                    aCnt++;
                } else {
                    bCnt++;
                }
            }

            int peopleA = bfs(aCnt, true);
            int peopleB = bfs(bCnt, false);

            if (peopleA > 0 && peopleB > 0) { // 두개 지역이 각각 연결이 성공했을 때에만 인구수 차이 계산
                min = Math.min(min, Math.abs(peopleA - peopleB));
            }

        }
    }


    static int bfs(int cCnt, boolean start) {
        Queue<Integer> q = new LinkedList<>();
        int index = 0; // 맨 처음 시작점이 어디인지
        int cnt = 0; // 다 연결되어 있는지 세기 위한 cnt
        int people = 0;
        for (int i = 1; i <= N; i++) {
            if (selected[i] == start) {  // a 구역이라면 맨 처음 true 값, b 구역이라면 맨 처음 false 값
                index = i;
                break;
            }
        }
        boolean[] visited = new boolean[N + 1]; // 연결 체크
        visited[index] = true;
        people += population[index];
        cnt++;
        q.add(index);
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && adjMatrix[now][i] == 1 && selected[i] == start) { // 방문한적 없는지, 연결됐는지, 같은 선거구인지
                    visited[i] = true;
                    people += population[i];
                    cnt++;
                    q.add(i);

                }
            }
        }

        return cnt == cCnt ? people : 0;
    }


}

