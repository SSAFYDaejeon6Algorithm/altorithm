package day0614;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b1967_트리의지름 {
    static int N, maxNode, max;
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];

        // 트리 초기화
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[p].add(new Node(c, w));
            tree[c].add(new Node(p, w));
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        visited = new boolean[N + 1];
        visited[1]= true; // 가장 긴 노드 탐색할 시작 노드
        dfs(1, 0);

//        System.out.println(maxNode);

        visited = new boolean[N + 1];
        visited[maxNode] = true;
        dfs(maxNode, 0);


        System.out.println(max);
    }

    static void dfs(int start, int sum) {

        if (max < sum) {
            max = sum;
            maxNode = start;
        }

        for (int i = 0; i < tree[start].size(); i++) {
            int node = tree[start].get(i).n;
            int weight = tree[start].get(i).w;
            if(visited[node]) continue;
            visited[node] = true;
            dfs(node, sum + weight);
        }
    }
    static class Node {
        int n;
        int w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }
}
