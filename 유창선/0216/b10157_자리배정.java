package day0216;

import java.util.Scanner;

public class b10157_자리배정 {
    // 0: up, 1: right, 2: down, 3: left
    static int [][] dir = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int C = sc.nextInt();
        int R = sc.nextInt();
        int K = sc.nextInt();
        int[][] map = new int[R][C];

        int nowi = R - 1;
        int nowj = 0;
        int d = 0;
        int num = 1;

        while (true) {
            map[nowi][nowj] = num;

            if (num == K) {
                System.out.println( (nowj + 1) + " " + (R - nowi));
                System.exit(0);
            }

            if (num >= R * C) break;

            int nexti = nowi + dir[d][0];
            int nextj = nowj + dir[d][1];

            if (nexti <0 || nexti >= R || nextj < 0 || nextj >= C || map[nexti][nextj] != 0) {
                d++;
                d %= 4;
                continue;
            }

            nowi = nexti;
            nowj = nextj;
            num++;
        }

        System.out.println(0);
    }
}
