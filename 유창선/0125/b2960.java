import java.util.Scanner;

public class b2960 {
    public static void main(String[] args) {
        int n, k;
        int cnt = 0;
        int result = 0;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        boolean[] arr = new boolean[n+1];

        for (int i=2; i<=n; i++) {
            if(!arr[i]) {

                for (int j=i; j<=n; j+=i) {
                    if (arr[j]) continue;  // 이미 지워진거라면 건너뛰기
                    arr[j] = true;
                    cnt++;
                    if (cnt == k) {
                        result = j;
                        break;
                    }
                }
            }
            if (result != 0) break;
        }

        System.out.println(result);
    }
}
