package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class b16928_뱀과사다리게임 {
    static int[] ls;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ls = new int[101];
        int lCnt = Integer.parseInt(st.nextToken());
        int sCnt = Integer.parseInt(st.nextToken());

        for (int i = 0; i < lCnt + sCnt; i++) {
            st = new StringTokenizer(br.readLine());
            ls[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        System.out.println(bfs(1));
    }

    public static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int cnt = 1;
        boolean[] visited = new boolean[101];
        visited[1] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int now = q.poll();

                for (int i = 1; i <= 6; i++) {
                    int next = now + i;
                    if (next == 100) return cnt;
                    if (next > 100) continue;
                    if (!visited[next]) {
                        if (ls[next] != 0) { // 사다리나 뱀 타기
                            q.add(ls[next]);
                        } else {
                            q.add(next);
                        }
                        visited[next] = true;
                    }
                }
            }
            cnt++;
        }
        return 0;
    }
}

