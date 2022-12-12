public class B22251_빌런호석 {
	static int N; // N층까지 가능
	static int K; // K자리수
	static int P; // P개 반전
	static String X; // 현재 X층
	static int[][] light = { { 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 0, 1, 0 }, { 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1 }, { 0, 1, 1, 1, 0, 1, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 0, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 1이상 N이하의 수 되게
		K = sc.nextInt(); // K자리수
		P = sc.nextInt(); // P개 반전
		X = sc.next(); // 현재 X층
		int ans = 0;
		String XX = "";
		
		for(int i=0; i<K-X.length(); i++) XX+="0";
		XX+=X;
		
		for(int i=1; i<=N; i++) {
			String F = Integer.toString(i);
			String FF = "";
			int diffCnt = 0;
			
			for(int j=0; j<K-F.length(); j++) FF+="0";
			FF+=F;
			
			if(XX.equals(FF)) continue;
			
			for(int j=0; j<K; j++) {
				int curX = XX.charAt(j) - '0';
				int curF = FF.charAt(j) - '0';
				
				for(int k=0; k< 7; k++) {
					if(light[curX][k] != light[curF][k]) diffCnt++;
				}
			}
			
			if(diffCnt <= P) ans++;
		}
		
		System.out.println(ans);
	}
}
