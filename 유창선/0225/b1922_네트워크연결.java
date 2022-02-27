package day0225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class b1922_네트워크연결 {
    static int N, M;
    static Edge[] edgeList;
    static int[] parents;
    static int cnt;
    static int totalCost;
    static class Edge implements Comparable<Edge>{
        int from, to, cost;
        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static void makeSet() {
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }
    static int findSet(int a) {
        if (parents[a] == a) return a;
        return parents[a] = findSet(parents[a]);
    }


    static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    static void kruskal(BufferedReader br) throws IOException {
        StringTokenizer st = null;
        makeSet();
        int computerCnt = 1;
        edgeList = new Edge[M];
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList[m] = new Edge(from, to, cost);
        }
        Arrays.sort(edgeList);

        for (int i = 0; i < M; i++) {
            if (union(edgeList[i].from, edgeList[i].to)) { // 연결 안된 컴퓨터만
                computerCnt++; // 연결된 컴퓨터 수 늘리기
                totalCost += edgeList[i].cost; // 연결되었으면 비용 추가
            }
            if (computerCnt == N) break; // 모두 연결 되었다면
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        kruskal(br);
        System.out.println(totalCost);
    }

}
