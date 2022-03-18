//백준 20055 컨베이어 벨트 위의 로봇 골드5
public class B20055 {
	
	static int N,K; //벨트 길이, 내구도 0 인 칸이 K개 이상
	static int rail[]; //i번 칸의 내구도
	static boolean robot[]; //i번칸에 로봇이 있는지 여부
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		rail = new int[2*N];
		robot = new boolean[N]; //로봇은 0~N-1번 까지만 존재하므로 N만큼만 할당
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<2*N; i++) rail[i] = Integer.parseInt(st.nextToken());

		while(countZero() < K) {
			ans++;
			rotateRail();
			moveRobot();
			putRobot();
		}
		
		System.out.println(ans);
	}
	
	//레일 위의 내구도가 0인 칸의 수를 리턴
	static int countZero() {
		int temp = 0;
		for(int i=0; i<2*N; i++) if(rail[i] == 0) temp++;
		return temp;
	}
	
	//모든 레일의 칸과 로봇의 위치를 한 칸씩 오른쪽으로 회전. N-1위치에 존재하는 로봇을 컨베이어에서 뺌
	static void rotateRail() {
		int prev = 0;
		int next = rail[2*N-1];
		for(int i=0; i<2*N; i++) {
			prev = rail[i];
			rail[i] = next;
			next= prev;
		}
		
		for(int i=N-1; i>=0; i--) {
			if(i == N-1 && robot[i]) robot[i]=false;
			if(robot[i] && !robot[i+1]) {
				robot[i+1] = robot[i];
				robot[i] = false;
			}
		}
		if(robot[N-1]) robot[N-1]=false;
	}
	
	//이동 가능한 로봇 회전
	static void moveRobot() {
		for(int i=N-1; i>=0; i--) {
			if(i == N-1) robot[i]=false;
			else {
				if(robot[i] && !robot[i+1] && rail[i+1] > 0) {
					robot[i+1]=true;
					robot[i]=false;
					rail[i+1]--;
				}
			}
		}
	}
	
	//0번 칸에 로봇 놓음
	static void putRobot() {
		if(rail[0] > 0) {
			rail[0]--;
			robot[0]=true;
		}
	}
}
