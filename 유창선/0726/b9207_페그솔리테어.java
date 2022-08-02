package day0726;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b9207_페그솔리테어 {
    static int N;
    static char[][] map;
    static int R = 5, C = 9;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int minPin, minMove;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            map = new char[R][C];
            int pin = 0;
            minPin = Integer.MAX_VALUE;
            minMove = Integer.MAX_VALUE;

            for (int i = 0; i < R; i++) {
                String str = br.readLine();

                for (int j = 0; j < C; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == 'o') pin++;
                }
            } // end map input
            br.readLine();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'o') dfs(i, j, pin, 0);
                }
            }
            System.out.println(minPin + " " + minMove);

        }
    }


    public static void dfs(int r, int c, int pin, int move) {
        if (pin <= minPin) {
            minPin = pin;
            minMove = move;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 ||  nr >= 5 || nc < 0 || nc >= 9 || map[nr][nc] == '#' || map[nr][nc] == '.')
                continue;

            int nnr = nr + dr[d];
            int nnc = nc + dc[d];

            if (nnr < 0 || nnr >= 5 || nnc < 0 || nnc >= 9 || map[nnr][nnc] == '#' || map[nnr][nnc] == 'o')
                continue;

            map[r][c] = '.';
            map[nr][nc] = '.';
            map[nnr][nnc] = 'o';

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] == 'o') dfs(i, j, pin - 1, move + 1);
                }
            }

            map[r][c] = 'o';
            map[nr][nc] = 'o';
            map[nnr][nnc] = '.';


        }
    }


}
