package day0405;

import java.util.*;

public class b2565_전깃줄 {

    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr = new int[501];
        int[] LIS = new int[501];
        int maxIndex = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int index = sc.nextInt();
            arr[index] = sc.nextInt();
            maxIndex = Math.max(index, maxIndex); // 가장 마지막에 오는 전깃줄 번호
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= maxIndex; i++) { // 처음부터 가장 마지막 전깃줄까지
            if(arr[i] == 0) continue; // 빈 전깃줄 칸이면 continue
            LIS[i] = 1;
            for (int j = 1; j < i; j++) {
                if (arr[j] == 0) continue; // 빈 전깃줄 칸이라면 continue
                if (arr[j] < arr[i] && LIS[i] < LIS[j] + 1) LIS[i] = LIS[j] + 1; // 최장 증가 부분 수열
            }
            max = Math.max(max, LIS[i]);
        }
        int result = N - max; // 전체 전깃줄 - 안겹치는 전깃줄 최대 = 최소 겹치는 전깃줄 수 (제거됨)
        System.out.println(result);
    }

}
