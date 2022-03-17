package day0315;

import java.util.Scanner;

public class b20055_컨베이어벨트위의로봇 {
    static int N, K, cnt, stage;
    static int[] belt;
    static boolean[] robotPos;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        belt = new int[2*N + 1];
        robotPos = new boolean[N + 1];

        for (int i = 1; i <= 2 * N; i++) {
            belt[i] = sc.nextInt();
        }

        while (cnt < K) {
            stage++;
            belt = moveBelt(); // 한 칸 회전
            moveRobot(); // 로봇 이동
            putRobot(); // 로봇 두기
        }

        System.out.println(stage);
    }

    static int[] moveBelt() {
        int[] copyBelt = new int[2*N + 1];
        // 벨트 위치 이동
        for (int i = 1; i <= 2*N; i++) {
            copyBelt[(i % (2*N)) + 1] = belt[i];
        }
        // 벨트 이동하며 로봇 위치도 이동
        for (int i = N - 1; i >= 1; i--) {
            if (robotPos[i]) {
                robotPos[i + 1] = true;
                robotPos[i] = false;
                if (i + 1 == N) robotPos[i + 1] = false; // 로봇 내리는 위치라면 로봇 제거
            }
        }
        return copyBelt;
    }

    static void moveRobot() {
        for (int i = N - 1; i >= 1; i--) {
            if (robotPos[i] && !robotPos[i + 1] && belt[i + 1]-- > 0 ) {
                robotPos[i + 1] = true;
                robotPos[i] = false;
                if (i + 1 == N) robotPos[i + 1] = false; // 로봇 내리는 위치라면 로봇 제거
                if (belt[i + 1] == 0) cnt++; // 내구도가 0이 되어버렸다면 cnt 증가
            }
        }
    }

    static void putRobot() {
        if (belt[1]-- > 0) robotPos[1] = true; // 로봇 두기
        if (belt[1] == 0) cnt++; // 내구도가 0이 되어버렸다면 cnt 증가
    }
}
