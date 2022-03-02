package day0304;

import java.util.*;

public class b1446_지름길 {
    static class Edge implements Comparable<Edge>{
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.to - o.to;
        }
    }
    static int N, D;
    static List<Edge>[] adjList;
    static int[] dist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        D = sc.nextInt();
        adjList = new ArrayList[10001];
        dist = new int[10001];
        for (int i = 0; i < 10001; i++) {
            adjList[i] = new ArrayList<>(); //
            dist[i] = i; // 최단 길이 초기화 (지름길을 보기 전에는 자기 자신이 최단 거리)
        }

        for (int i = 0; i < 10001; i++) {
            Collections.sort(adjList[i]); // 목적지 낮은 순으로 정렬
        }

        for (int i = 0; i < N; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            // 지름길이 더 빠를 경우 && 목적지를 넘지 않는 지름길만 추가!!
            if (to - from > weight && to <= D) adjList[from].add(new Edge(to, weight));
        } // end input

        dijstra(0);

        System.out.println(dist[D]);
    }

    static void dijstra(int start) {
        if (start > D) return;

        for (int i = 0; i < adjList[start].size(); i++) {
            int to = adjList[start].get(i).to;
            int weight = adjList[start].get(i).weight;

            if (dist[to] > dist[start] + weight) {
                dist[to] = dist[start] + weight;
            }
        }
        // 새로 최단거리 업데이트 되었다면 다음 거리도 업데이트될 것!
        if (dist[start + 1] > dist[start] + 1) dist[start + 1] = dist[start] + 1;
        dijstra(start + 1);
    }

}
