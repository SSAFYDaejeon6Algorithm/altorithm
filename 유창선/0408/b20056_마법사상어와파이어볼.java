package day0408;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class b20056_마법사상어와파이어볼 {

    static class Fire {
        int r, c, m, s, d;

        Fire(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

    }
    static final String SAME = "0246";
    static final String DIFF = "1357";
    static int N, M, K;
    static ArrayList<Fire>[][] map;
    static ArrayList<Fire> fList;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // map 크기
        M = sc.nextInt(); // 파이어볼 갯수
        K = sc.nextInt(); // 이동 수

        map = new ArrayList[N][N];
        fList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        } // init map

        for (int i = 0; i < M; i++) {
            int r = sc.nextInt() - 1;
            int c = sc.nextInt() - 1;
            int m = sc.nextInt();
            int s = sc.nextInt();
            int d = sc.nextInt();

            Fire f = new Fire(r, c, m, s, d);
            map[r][c].add(f);
            fList.add(f);
        } // end input

        for (int k = 0; k < K; k++) {
            shoot();
        }

        System.out.println(sum());

    }

    static void shoot() {
        move();
        split();
    }

    static void move() { // 파이어볼 전부 이동
        ArrayList<Fire>[][] newMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        } // init newMap

        for (Fire f : fList) {
            int nr = (f.r + (dr[f.d] * f.s % N) + N) % N;
            int nc = (f.c + (dc[f.d] * f.s % N) + N) % N;


            f.r = nr;
            f.c = nc;
            newMap[nr][nc].add(f); // 새로운 칸에 넣어주기
        }
        map = newMap; // 위치 새로운 맵으로 업데이트
    }

    static void split() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() < 2) continue; // 2개 미만이면 continue

                int sumM = 0;
                int sumS = 0;
                int cnt = 0;
                int[] checkEO = new int[2]; // 전부 짝수나 홀수인지 체크

                for (int s = 0; s < fList.size(); s++) { // Fire 리스트에서 찾은 좌표 값인 fire 찾음.
                    Fire now = fList.get(s);
                    if (now.r == i && now.c == j) {
                        cnt++;
                        sumM += now.m;
                        sumS += now.s;
                        checkEO[now.d % 2]++;
                        fList.remove(s); // 해당 Fire 삭제
                        s--; // 삭제되었으니 리스트 당겨지는 것 반영
                    }
                }

                String direction = "";
                if (checkEO[0] > 0 && checkEO[1] > 0) direction = DIFF;
                else direction = SAME;
                map[i][j].clear(); // 기존 좌표 지우기
                int m = Math.floorDiv(sumM, 5);
                int s = Math.floorDiv(sumS, cnt);

                if (m == 0) continue; // 질량 0 되면 넣어줄 필요 없음

                for (int k = 0; k < 4; k++) {
                    Fire newFire = new Fire(i, j, m, s, direction.charAt(k) - '0');
                    map[i][j].add(newFire);
                    fList.add(newFire);
                } // 좌표에 4개로 나눠진 파이어볼 넣어줌

            }
        }
    }

    static int sum() {
        int sum = 0;
        for (Fire f : fList) {
            sum += f.m;
        }
        return sum;
    }
}
