static int d[][] = {{1,0},{0,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

public int [] solution(String[][] places) {
		 int[] answer = new int[5];
		 
		 for(int i=0; i<5; i++) {
			 boolean isRight = true;
			 outer:for(int j=0; j<5; j++) {
				 for(int k=0; k<5; k++) {
					 if(places[i][j].charAt(k) == 'P') {
						 for(int dd=0; dd<4; dd++) {
							 int nr = j + d[dd][0];
							 int nc = k + d[dd][1];
							 
							 if(!isValid(nr,nc)) continue;
							 
							 if(places[i][nr].charAt(nc) == 'P') {
								 isRight = false;
								 break outer;
							 }
							 
							 int nrr = j + d[dd][0]*2;
							 int ncc = k + d[dd][1]*2;
							 
							 if(!isValid(nrr, ncc)) continue;
							 
							 if(places[i][nrr].charAt(ncc) == 'P' && (places[i][nr].charAt(nc) != 'X')) {
								 isRight = false;
								 break outer;
							 }
						 }
						 
						 for(int dd=4; dd<8; dd++) {
							 int nr = j + d[dd][0];
							 int nc = k + d[dd][1];
							 
							 if(!isValid(nr,nc)) continue;
							 
							 if(places[i][nr].charAt(nc) == 'P' && (places[i][j].charAt(nc) != 'X' || places[i][nr].charAt(k) != 'X')) {
								 isRight = false;
								 break outer;
							 }
						 }
					 }
				 }
			 }
			 if(isRight) answer[i] = 1;
		 }
		 
	     return answer;
	}
	
	static boolean isValid(int nr, int nc) {
		if(nr < 0 || nc < 0 || nr >= 5 || nc >= 5) return false;
		return true;
	}
