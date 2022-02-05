import java.io.InputStreamReader;
import java.util.Scanner;

public class B2960 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		int num = sc.nextInt();
		int index = sc.nextInt();
		boolean[] checked = new boolean[1001];
		int sequence = 0;

		outer: for (int i = 2; i <= num; i++) {
			if (!checked[i] && isPrime(i)) {
				for (int j = i; j <= num; j += i) {
					if(!checked[j]) {
						checked[j] = true;
						sequence++;
						if (sequence == index) {
							System.out.println(j);
							break outer;
						}
					}
					
				}
			}
		}
	}
	
	public static boolean isPrime(int num) {
		if (num < 2) return false;

		for (int i = 2; i <= Math.sqrt(num); i++)
			if (num % i == 0) return false;

		return true;
	}
}
