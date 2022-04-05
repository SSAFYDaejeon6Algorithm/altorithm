package day0405;

import java.util.Scanner;

public class b10844_쉬운계단수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[101][10];

        if (N == 1) { // 1 일 경우는 바로 출력
            System.out.println(9);
            return;
        }
        for (int i = 0; i <= 9; i++) { // 1일때 전부 1로 채우기
            arr[1][i] = 1;
        }
        for (int i = 2; i <= N; i++) {
            arr[i][0] = arr[i-1][1]; // 0은 전 단계 숫자 1로 시작하는 애한테 가져옴
            for (int j = 1; j < 9; j++) {
                arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1]) % 1000000000; // 1 ~ 8은 전 단계 양 옆 숫자 값 더함.
            }
            arr[i][9] = arr[i-1][8]; // 9는 전 단계 8로 시작하는 애한테 가져옴
        }
        int sum = 0;
        for (int i = 1; i <= 9; i++) { // 1 ~ 9 합 더함
            sum += arr[N][i];
            sum %= 1000000000;
        }
        System.out.println(sum);
    }
}
