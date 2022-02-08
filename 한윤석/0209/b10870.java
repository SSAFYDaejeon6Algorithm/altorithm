import java.util.Scanner;

//백준 10870 피보나치 수 5
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int [] arr = new int [21];
		int N = sc.nextInt();
		arr[0]=0; arr[1]=1;
		
		//i번째 피보나치 수는 i-1번 + i-2번
		if(N >= 2)
		for(int  i=2; i<=N; i++) 
			arr[i] = arr[i-1]+arr[i-2];
		
		System.out.println(arr[N]);
	}

}
