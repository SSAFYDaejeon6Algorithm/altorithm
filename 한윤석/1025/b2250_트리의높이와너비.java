
public class B2250_트리의높이와너비 {

	static int N;
	static Node node [];
	static int nodeInfo [][]; //i번 노드의 [0]level, [1]열
	static int idx = 1, maxDepth = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		node = new Node[N+1];
		nodeInfo = new int [N+1][2];
		boolean hasRoot [] = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeNum = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			node[nodeNum] = new Node(left, right);
			if(left != -1) hasRoot[left]=true;
			if(right != -1) hasRoot[right]=true;
		}
		
		//루트노드 도출
		int root = 0;
		for(int i=1; i<=N; i++) if(!hasRoot[i]) root = i;
		
		solve(root, 1);
		printAns();
		
	}
	
	static void solve(int num, int depth) {
		maxDepth = Math.max(maxDepth, depth);
		if(node[num].left == -1 && node[num].right == -1) {
			nodeInfo[num][0] = depth;
			nodeInfo[num][1] = idx++;
			return;
		}
		
		//좌노드 조회
		if(node[num].left != -1) solve(node[num].left, depth+1);
		
		//루트 인덱스 기입
		nodeInfo[num][1] = idx++;
		nodeInfo[num][0] = depth;
		
		//우노드 조회
		if(node[num].right != -1) solve(node[num].right, depth+1);
		
		return;
	}
	
	static void printAns() {
		int maxLevel = 1;
		int maxVal = 0;
		int levelWidth [][] = new int [maxDepth+1][2];
		for(int i=1; i<=maxDepth; i++) {
			levelWidth[i][0] = N;
			levelWidth[i][1] = 1;
		}
		for(int i=1; i<=N; i++) {
			int level = nodeInfo[i][0];
			int nodeCol = nodeInfo[i][1];
			levelWidth[level][0] = Math.min(levelWidth[level][0], nodeCol);
			levelWidth[level][1] = Math.max(levelWidth[level][1], nodeCol);
		}
		for(int i=1; i<=maxDepth; i++) {
			int width = levelWidth[i][1] - levelWidth[i][0] + 1;
			if(maxVal < width) {
				maxLevel = i;
				maxVal = width;
			}
		}
		System.out.println(maxLevel + " " + maxVal);
	}
	
	static class Node{
		int left, right;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
