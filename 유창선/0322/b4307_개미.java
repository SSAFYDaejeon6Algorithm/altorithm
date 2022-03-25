package day0322;

import java.util.Scanner;

public class b4307_개미 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for (int t = 1; t <= TC; t++) {
            int length = sc.nextInt();
            int antCnt = sc.nextInt();
            int ant = 0;

            int max = Integer.MIN_VALUE; // 개미마다 떨어지는데 가장 오래 걸리는 개미 찾기
            int min = Integer.MIN_VALUE; // 개미마다 빨리 떨어지는 시간 중 가장 오래걸리는 개미 찾기 (전부 떨어져야하기 때문)
            for (int i = 0; i < antCnt; i++) {
                ant = sc.nextInt();
                int left = ant;
                int right = length - ant;
                max = Math.max(max, left);
                max = Math.max(max, right);

                int eachMin = Math.min(left, right);
                min = Math.max(min, eachMin);
            }
            System.out.println(min + " " + max);
        }
    }
}
