package day0218;

import java.util.Scanner;

public class b8320_직사각형을만드는방법 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = n;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = i; i * j <= n; j++) {
                sum++;
            }
        }
        System.out.println(sum);
    }
}
