package day0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b11725_트리의부모찾기 {
    static int[] parents;
    static Queue<Integer> q;
    static  boolean[] visited;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>(); // 인접 리스트로 (인접 배열 메모리 초과남..)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        parents[1] = 1;

        for (int i = 0; i  <= N; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) { // 연결된 값 추가
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }
        q = new LinkedList<>();
        q.offer(1);
        visited = new boolean[N + 1];
        visited[1] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i : list.get(cur)) {
                if (!visited[i]  && parents[i] == 0) { // 부모 노드 없다면 현재 값이 얘네의 부모값이 됨
                    q.offer(i);
                    visited[i] = true;
                    parents[i] = cur;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
