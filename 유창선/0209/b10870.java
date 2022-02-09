import java.util.Scanner;

// 피보나치 수 5
public class b10870 {
    static int res;
    static int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibo(n-1) + fibo(n-2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        res = fibo(n);
        System.out.println(res);

    }
}
