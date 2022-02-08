package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//백준 1182 부분순열의 합
public class B1182 {
	static int N; //입력받을 숫자 개수
	static int S; //타겟 넘버
	static int[] input; //입력받은 수 배열
	static boolean[] check; //사용 여부
	static int answer = 0; //정답

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String NS = br.readLine();
		StringTokenizer st = new StringTokenizer(NS, " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		input = new int[N];
		check = new boolean[N];

		//입력 수 배열에 저장
		String[] inputs = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			input[i] = Integer.parseInt(inputs[i]);

		makeSubset(0);
		System.out.println(answer);
	}

	public static void makeSubset(int cnt) {
		if (cnt == N) {
			int temp = 0; //체크된 것들의 합
			boolean flag = false; //하나라도 체크한 것을 사용했는지
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					temp += input[i];
					flag = true;
				}
			}
			if (temp == S && flag) //하나라도 사용했을 때만 더해줌(S가 0일 때 대비)
				answer++;
			return;
		}

		//체크했을 때 탐색
		check[cnt] = true;
		makeSubset(cnt + 1);
		//체크 안했을 때 탐색
		check[cnt] = false;
		makeSubset(cnt + 1);
	}
}
