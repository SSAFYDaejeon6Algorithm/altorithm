import java.io.InputStreamReader;
import java.util.Scanner;
public class B1978 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(new InputStreamReader(System.in));
		int tc = sc.nextInt();
		int primeNum =0;
		
		for(int i=0; i<tc; i++) {
			int num = sc.nextInt();
			if(isPrime(num))
				primeNum++;
		}
		System.out.println(primeNum);
	}
	
	public static boolean isPrime(int num) {
		if (num < 2) return false;

		for (int i = 2; i <= Math.sqrt(num); i++)
			if (num % i == 0) return false;

		return true;
	}
}
