public int solution(int[][] board) {
		int R = board.length;
		int C = board[0].length;
		int [][][] memo = new int[R][C][4]; //[r][c][dir]로 갔을 때 최소 비용
		int d[][] = {{1,0},{0,1},{-1,0},{0,-1}};
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) Arrays.fill(memo[i][j], Integer.MAX_VALUE);
		}
		
        Queue<Route> q = new LinkedList<>();
        memo[0][0][0] = memo[0][0][1] = memo[0][0][2] = memo[0][0][3] = 0;
        
        if(board[1][0] != 1) {
        	q.add(new Route(1,0,0));
        	memo[1][0][0] = 100;
        }if(board[0][1] != 1) {
        	q.add(new Route(0,1,1));
        	memo[0][1][1] = 100;
        }
        
        while(!q.isEmpty()) {
        	Route route = q.poll();
        	if(route.r == R-1 && route.c == C-1) continue;
        	
        	for(int i=0; i<4; i++) {
        		int nr = route.r + d[i][0];
        		int nc = route.c + d[i][1];
        		
        		if(nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] == 1) continue;
        		
        		int newcost = route.ld == i ? 100 : 600;
        		
        		if(memo[nr][nc][i] <= memo[route.r][route.c][route.ld]+ newcost) continue;
        		
        		memo[nr][nc][i] = memo[route.r][route.c][route.ld] + newcost;
        		q.add(new Route(nr, nc, i));
        	}
        }
        
        return Math.min(memo[R-1][C-1][0], Math.min(memo[R-1][C-1][1], Math.min(memo[R-1][C-1][2], memo[R-1][C-1][3])));
    }
	
	static class Route{
		int r,c,ld;
		public Route(int r, int c, int ld) {
			this.r = r;
			this.c = c;
			this.ld = ld;
		}
	}
