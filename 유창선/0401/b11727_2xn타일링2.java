package day0401;

import java.util.Scanner;

public class b11727_2xn타일링2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            arr[i] = (arr[i - 1] + arr[i -2] * 2) % 10007;
        }

        System.out.println(arr[n]);
    }
}
