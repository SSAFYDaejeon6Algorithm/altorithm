package day0705;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b2660_회장뽑기 {
    public static int N, peopleCnt, minScore = Integer.MAX_VALUE;
    public static ArrayList<Integer>[] adjList; // 친구 관계 표현하는 인접 리스트
    public static ArrayList<Integer> result; // 회장 후보들 담는 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new ArrayList<>();
        adjList = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if (from == -1 || to == -1) break;
            adjList[from].add(to);
            adjList[to].add(from);
        } // end input

        // bfs 돌리기
        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        // 정렬해서 출력
        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(" ").append(peopleCnt).append("\n");
        for (int item : result) {
            sb.append(item).append(" ");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb.toString());

    }

    public static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        boolean[] visited = new boolean[N + 1]; // 시작 회원 무조건 방문 체크
        visited[start] = true;
        int score = -1; // 현재 점수
        while (!q.isEmpty()) {
            score++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int now = q.poll(); // 현재 회원
                for (int next : adjList[now]) { // 현재 회원 친구들
                    if (visited[next]) continue;
                    visited[next] = true; // 방문 체크
                    q.add(next);
                }
            }
        }

        if (score < minScore) { // 점수가 최소라면
            minScore = score;
            result.clear();
            result.add(start);
            peopleCnt = 1;
        } else if (score == minScore) { // 점수가 같다면
            result.add(start);
            peopleCnt++;
        }
    }
}
