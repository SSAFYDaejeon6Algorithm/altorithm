import java.util.Scanner;

public class b1182 {
    static int cnt;
    static void subset(int[] arr, int n, int s, int cur, int sum, int sumCnt) {
        if (cur == n) {
            if (sumCnt==0) return; // 양수인 부분 수열이 아닌 경우
            if (sum == s) cnt++;
            return;
        }
        subset(arr, n, s, cur+1, sum + arr[cur], sumCnt+1);
        subset(arr, n, s, cur+1, sum, sumCnt);
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }

        subset(arr, n, s, 0, 0, 0);
        System.out.println(cnt);
    }
}
