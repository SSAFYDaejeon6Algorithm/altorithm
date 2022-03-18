package day0318;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b1347_미로만들기 {
    static int N, ni = 50, nj = 50, dir = 2;
    static int maxI = 50;
    static int maxJ = 50;
    static int minI = 50;
    static int minJ = 50;
    static char[][] map;
    static int[] di = {-1, 0, 1, 0}; // up right down left
    static int[] dj = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        map = new char[101][101];
        for (int i = 0; i < 101; i++) {
            Arrays.fill(map[i], '#');
        }
        // printMap();
        int len = line.length();
        map[ni][nj] = '.'; // 출발점 찍어주기
        for (int i = 0; i < len; i++) {
            char now = line.charAt(i);

            if (now == 'L') dir = (dir - 1) < 0 ? (dir -1 + 4) % 4  : (dir - 1) % 4; // 왼쪽 바라보면 방향 -1
            else if (now == 'R') dir = (dir + 1) % 4; // 오른쪽 바라보면 방향 +1
            else if (now == 'F') {
                ni = ni + di[dir];
                nj = nj + dj[dir];
                map[ni][nj] = '.';
                // 끝 범위 구하기
                maxI = Math.max(maxI, ni);
                maxJ = Math.max(maxJ, nj);
                minI = Math.min(minI, ni);
                minJ = Math.min(minJ, nj);
            }
        }
//        printMap();
        for (int i = minI; i <= maxI; i++) {
            for (int j = minJ; j <= maxJ; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    static void printMap() {
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
