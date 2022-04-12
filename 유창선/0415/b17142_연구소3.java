package day0415;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b17142_연구소3 {
    static int[][] map;
    static ArrayList<Point> tmpVirus;
    static boolean[] selected;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N, M;
    static int empty;
    static int min;

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt(); // 놓을 수 있는 바이러스 수
        map = new int[N][N];
        tmpVirus = new ArrayList<>(); // 바이러스 올 수 있는 위치
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 2) tmpVirus.add(new Point(i, j));
                if (map[i][j] == 0) empty++; // 빈 공간 수 늘려주기
            }
        }

        selected = new boolean[tmpVirus.size()];
        selectVirus(0, 0);
        if (min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);

    }

    static void selectVirus(int total, int cnt) { // 선택한 바이러스 수, 선택하려는 바이러스 위치
        if (total >= M) {
            int[][] newMap = copy(map);
            int size = selected.length;
            ArrayList<Point> selectedVirus = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (!selected[i]) { // 바이러스로 선택되지 않은 위치는 0(빈칸)으로 만들어주기
                    int r = tmpVirus.get(i).r;
                    int c = tmpVirus.get(i).c;
                    newMap[r][c] = 3; // 비활성 바이러스 숫자 3
                } else {
                    selectedVirus.add(tmpVirus.get(i));
                }
            }
            min = Math.min(min, bfs(newMap, selectedVirus));

            return;
        }

        if (cnt >= tmpVirus.size()) return;

        selected[cnt] = true;
        selectVirus(total + 1, cnt + 1); // 현재 바이러스 선택
        selected[cnt] = false;
        selectVirus(total, cnt + 1); // 비선택
    }

    static int bfs(int[][] newMap, ArrayList<Point> selectedVirus) {
        int space = empty;
        if (space == 0) return 0; // 더 둘 곳 아예 없으면 바로 return ㅎ마.
        Queue<Point> q = new LinkedList<>(selectedVirus); // 초기 바이러스 위치 전부 추가
        int time = 0; // 바이러스는 0초 부터 시작 (남은 공간이 0이 되면 바로 break 하도록 바꿨음)
        while (!q.isEmpty()) {
            int size = q.size();
            time++;
            for (int s = 0; s < size; s++) {
                Point now = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                        if (newMap[nr][nc] == 0) {
                            newMap[nr][nc] = 2;
                            q.offer(new Point(nr, nc));
                            space--; // 빈 공간 줄이기
                        } else if (newMap[nr][nc] == 3) { // 비활성 바이러스는 빈공간이 아니기 때문에 안줄여도 됨.
                            newMap[nr][nc] = 2;
                            q.offer(new Point(nr, nc));
                        }
                    }
                }
            }
            if (space == 0) break; // 빈 공간 다 채웠으면 break;
        }

        return space == 0 ? time : Integer.MAX_VALUE; // 모든 칸 다 채웠으면 시간 return
    }

    static int[][] copy(int[][] map) {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }
}
