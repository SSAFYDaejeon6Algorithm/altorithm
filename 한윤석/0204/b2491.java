import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N]; //수 담을 배열
		int incTemp = 1; //증가수열 현재까지의 누적 수
		int max = 1; //최장 수열의 길이
		int decTemp = 1; //감소수열 현재까지의 누적 수
		
		for(int i=0; i<N; i++) {
			arr[i] = sc.nextInt();
		}
    //감소수열 탐색
		for (int i = 0; i < N - 1; i++) {
			if (arr[i] >= arr[i + 1]) { //작거나 같다면 감소수열의 수 +1
				decTemp++;
			} else {
				decTemp=1;
			}
			max = Math.max(max, decTemp);
		}
    
    //증가수열 탐색
		for (int i = 0; i < N - 1; i++) {
			if (arr[i] <= arr[i + 1]) {
				incTemp++;
			} else {
				incTemp=1;
			}
			max = Math.max(max, incTemp);
		}
		System.out.println(max);
	}
}
