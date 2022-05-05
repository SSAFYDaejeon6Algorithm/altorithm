import java.io.*;
import java.util.*;


class Solution {

    static boolean[] visited;
    static int max;
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        visited = new boolean[11];
        max = Integer.MIN_VALUE;
        subset(n, info, 0, 0, answer);

        if (max == Integer.MIN_VALUE) {
            answer = new int[] {-1};
        }
        return answer;
    }

    static void update(int[] newAnswer, int[] answer) {
        for (int i = 10; i >= 0; i--) {
            if (newAnswer[i] > answer[i]) { // 원래보다 작은 점수에 맞은 화살이 더 많다면
                System.arraycopy(newAnswer, 0, answer, 0, newAnswer.length);
                break;
            } else if (newAnswer[i] < answer[i])
                break;
        }
    }

    static void subset(int n, int[] info, int pos, int sum, int[] answer) {

        if (pos > 10) {

            int apeach = 0, lion = sum;
            for (int i = 0; i <= 10; i++) {
                if (!visited[i] && info[i] > 0) apeach += 10 - i;
            }
            int dif = lion - apeach;
            if (apeach >= lion) return; // 어파치가 라이언의 점수보다 같거나 큰 경우 return;
            if (dif < max) return; // 둘이 최대 차이가 갱신이 안되는 경우 return


            int[] newAnswer = new int[11];
            for (int i = 0; i <= 10; i++) { // 맞춘 위치 확인
                if (visited[i]) newAnswer[i] = info[i] + 1;
                else newAnswer[i] = 0;
            }

            if (max == dif) { // max와 sum이 같은 경우는 낮은 점수를 더 많이 맞힌 경우가 우선적임
                update(newAnswer, answer);
            } else if (dif > max) {
                System.arraycopy(newAnswer, 0, answer, 0, newAnswer.length);
            }
            if (n != 0) answer[10] = n; // 화살이 남아 있으면 못 맞춘 것이기 때문에 넣어줌
            max = dif;
            return;

        }

        if (info[pos] + 1 <= n) {
            visited[pos] = true;
            subset(n - (info[pos] + 1), info, pos + 1, sum + (10 - pos), answer);
        }
        visited[pos] = false;
        subset(n, info, pos + 1, sum, answer);
    }
}