public class B8981_입력숫자 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int result [] = new int[N]; //입력으로 주어지는 결과값
		int input [] = new int[N]; //내가 찾아야 할 자리
		
		for(int i=0; i<N; i++) result[i] = Integer.parseInt(st.nextToken());
		
		int idx = 0;
		int val = 0;
		
		for(int i=0; i<N; i++) {
			val = result[i];
			
			//만약 idx 칸에 이미 input으로 사용되었으면 다음 칸들을 살펴보며 가능한 칸을 찾음
			if(input[idx] != 0) while(input[idx] != 0) idx = (idx+1)%N;
			
			input[idx] = val;
			idx = (val + idx) % N;
		}
		
		System.out.println(N);
		if(N <= 0) System.out.println(-1); //애초에 입력 자체가 양수로 들어오는데 이건 왜 있는지 모르겠다
		else for(int i=0; i<N; i++) System.out.print(input[i] + " ");
	}
}
