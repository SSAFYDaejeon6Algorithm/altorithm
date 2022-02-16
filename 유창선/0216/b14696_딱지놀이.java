package day0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b14696_딱지놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 1; n <= N; n++) {
            int[] a = new int[5];
            int[] b = new int[5];
            StringTokenizer st= new StringTokenizer(br.readLine());
            a[0] = Integer.parseInt(st.nextToken()); // A 총 모양 수

            for (int i = 0; i < a[0]; i++) {
                a[Integer.parseInt(st.nextToken())]++;
            }

            st = new StringTokenizer(br.readLine());
            b[0] = Integer.parseInt(st.nextToken()); // B 총 모양 수
            for (int i = 0; i < b[0]; i++) {
                b[Integer.parseInt(st.nextToken())]++;
            }

            for (int i = 4; i > 0; i--) {
                if (a[i] > b[i]) {
                    System.out.println("A");
                    break;
                }
                else if (a[i] < b[i]) {
                    System.out.println("B");
                    break;
                }
                if (i == 1) System.out.println("D");
            }
        }
    }
}
