import java.io.IOException;
import java.util.Scanner;

public class b1978 {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();
        boolean[] arr = new boolean[1001];
        arr[0] = true;
        arr[1] = true;



        for (int i=2; i<=1000; i++) {

            // 소수라면 그 배수에 해당하는 수 true 바꾸기 (자기 자신은 해당 안되고 2배수부터)
            if (!arr[i]) {
                for (int j=2*i; j<=1000; j+=i) {
                    arr[j] = true;
                }
            }
        }

        int cnt = 0;
        for (int i=0; i<tc; i++) {
            int num = sc.nextInt();

            if (!arr[num]) {
                cnt++;
            }

        }

        System.out.println(cnt);
    }
}
