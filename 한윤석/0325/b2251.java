public class B2251_물통 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int height[] = new int[3];
		boolean visit[][][] = new boolean[201][201][201]; //a높이 b높이 c높이
		int d[][] = {{0,1},{0,2},{1,0},{1,2},{2,0},{2,1}};
		TreeSet<Integer>set = new TreeSet<>();

		for(int i=0; i<3; i++) height[i] = sc.nextInt();
		
		set.add(height[2]);
		visit[0][0][height[2]] = true;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,height[2]}); //A높이 B높이 C높이
		
		while(!q.isEmpty()) {
			int [] cur = q.poll();
			
			for(int i=0; i<6; i++) {
				int from = d[i][0];
				int to = d[i][1];
				int copy [] = cur.clone();
				
				//from에서 to로 넣어도 안 넘칠 때
				if(copy[from] + copy[to] <= height[to]) {
					copy[to] += copy[from];
					copy[from] = 0;
				}else { //넘칠 때
					int want = height[to] - copy[to];
					copy[to] += want;
					copy[from] -= want;
				}
				if(!visit[copy[0]][copy[1]][copy[2]]) {
					q.add(new int[] {copy[0],copy[1],copy[2]});
					visit[copy[0]][copy[1]][copy[2]] = true;
					if(copy[0] == 0) set.add(copy[2]);
				}
			}
		}
		
		for(int i:set) System.out.print(i+ " ");
	}
}
