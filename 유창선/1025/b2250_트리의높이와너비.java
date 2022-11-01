package day1020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class b2250_트리의높이와너비 {
    static int N, visitedIndex = 1;
    static ArrayList<ArrayList<Integer>> info = new ArrayList<>();
    static int[][] visited;
    static int[] parent;
    static int root;
    static int maxLevel = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new int[N + 1][2];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) parent[i] = i; // 부모 노드 찾기 초기화
        for (int i = 0; i <= N; i++) {
            info.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            info.get(p).add(l);
            info.get(p).add(r);
            if (l != -1) parent[l] = p;
            if (r != -1) parent[r] = p;
        } // end input
        findRoot(1);
        dfs(1, root);
        getAns();
    }

    public static void getAns () {
        int ansWidth = Integer.MIN_VALUE;
        int ansLevel = -1;
        for (int l = 1; l <= maxLevel; l++) {
            int left = Integer.MAX_VALUE;
            int right = Integer.MIN_VALUE;
            for (int i = 1; i <= N; i++) {
                if (visited[i][1] == l) { // 같은 레벨이라면
                    left = Math.min(left, i); // 맨 왼쪽 구하기
                    right = Math.max(right, i); // 맨 오른쪽 구하기
                }
            }
            if (ansWidth < right - left) {
                ansWidth = right - left;
                ansLevel = l;
            }
        }
        System.out.println(ansLevel + " " + (ansWidth + 1));

    }

    public static void dfs(int level, int parent) {
        int left = info.get(parent).get(0);
        int right = info.get(parent).get(1);
        if (left != -1) dfs(level + 1, left); // 왼쪽
        visited[visitedIndex][0] = parent;
        visited[visitedIndex++][1] = level;
        maxLevel = Math.max(level, maxLevel); // 최대 깊이 구해주기
        if (right != -1) dfs(level + 1, right); // 오른쪽
    }
    public static void findRoot(int a) {
        if (a == parent[a]) {
            root = a;
            return;
        }
        findRoot(parent[a]);

    }
}
