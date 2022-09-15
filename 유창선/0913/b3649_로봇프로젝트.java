package day0913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b3649_로봇프로젝트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String tmp;
        while ((tmp = br.readLine()) != null && !tmp.equals("")) {
            int x = Integer.parseInt(tmp) * 10000000;
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(arr);

            int l1 = 0, l2 = 0;
            int lt = 0, rt = n - 1;

            while (lt < rt) {
                int sum = 0;
                sum = arr[lt] + arr[rt];
                if (sum == x) {
                    l1 = arr[lt];
                    l2 = arr[rt];
                    break;
                } else if (sum < x) lt++;
                else rt--;
            }
            if (l1 != 0 && l2 != 0) System.out.println("yes " + l1 + " " + l2);
            else System.out.println("danger");
        }
    }
}
