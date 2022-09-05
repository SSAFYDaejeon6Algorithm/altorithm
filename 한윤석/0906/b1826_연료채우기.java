public class B1826_연료채우기 {
	
	static int L,P, ans=0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Station> stations = new PriorityQueue<Station>(); 
		PriorityQueue<Integer> fuels = new PriorityQueue<>(Collections.reverseOrder()); 
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pos = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			stations.add(new Station(pos, amount));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		while(true) {
			if(P >= L) break;
			
			while(!stations.isEmpty() && stations.peek().pos <= P) 
				fuels.add(stations.poll().amount);
				
			if(fuels.isEmpty()){
				ans = -1;
				break;
			}
			
			P += fuels.poll();
			ans++;
		}
		
		System.out.println(ans);
	}
	
	static class Station implements Comparable<Station>{
		int pos, amount;
		public Station(int pos, int amount) {
			this.pos = pos;
			this.amount = amount;
		}
		@Override
		public int compareTo(Station o) {
			return this.pos - o.pos;
		}
	}
}
