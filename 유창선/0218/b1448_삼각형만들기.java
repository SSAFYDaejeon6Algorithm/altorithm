package day0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b1448_삼각형만들기  {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        for (int i = n - 1;  i > 1; i--) {
            int a = arr[i];
            int b = arr[i - 1];
            int c = arr[i - 2];

            if (b + c > a) {
                System.out.println(b+c+a);
                return;
            }
        }
        System.out.println(-1);
    }
}
