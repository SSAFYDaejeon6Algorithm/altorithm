public class B16987_계란으로계란치기 {
	
	static int N;
	static Egg [] eggs;
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		eggs = new Egg[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			eggs[i] = new Egg(s,w,false);
		}
		
		backtrack(0);
		System.out.println(ans);
	}
	
	// pick : 현재 내가 집은 계란의 인덱스
	static void backtrack(int pick) {
		if(pick == N) { //가장 오른쪽의 계란까지 고려가 끝났다면
			int temp = 0;
			for(int i=0; i<N; i++) if(eggs[i].isBreak) temp++;
			ans = Math.max(ans, temp);
			return;
		}
		
		boolean amIBroken = false; //pick번째 계란이 이미 깨진 계란인가
		boolean isEggExist = false; //pick 외에 깰 수 있는 계란이 있는가
		for(int i=0; i<N; i++) {
			if(eggs[pick].isBreak) {
				amIBroken = true;
				break;
			}
			
			if(i == pick || eggs[i].isBreak) continue;
			
			// 깰 수 있는 계란이 있으면 계란을 치고
			isEggExist = true;
			eggs[pick].s -= eggs[i].w;
			eggs[i].s -= eggs[pick].w;
			
			if(eggs[pick].s <= 0) eggs[pick].isBreak = true;
			if(eggs[i].s <= 0) eggs[i].isBreak = true;
			
			// 다음 계란을 집고
			backtrack(pick+1);
			
			// 끝났으면 원래대로 돌려놓음
			eggs[pick].s += eggs[i].w;
			eggs[i].s += eggs[pick].w;
			
			if(eggs[pick].s > 0) eggs[pick].isBreak = false;
			if(eggs[i].s > 0) eggs[i].isBreak = false;
		}
		
		// 내가 이미 깨져있는 계란이었거나, 나 외에 깰 수 있는 계란 없으면 내려놓고 다음 계란 집음
		if(amIBroken || !isEggExist) backtrack(pick+1);
	}
	
	static class Egg{
		int s,w;
		boolean isBreak;
		public Egg(int s, int w, boolean isBreak) {
			this.s = s;
			this.w = w;
			this.isBreak = isBreak;
		}
	}
}
