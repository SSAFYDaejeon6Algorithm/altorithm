class Solution {
	    int dx[] = {0, 0, -1, 1};
	    int dy[] = {-1, 1, 0, 0};
	    
	    public int[] solution(String[][] places) {
	        int[] answer = new int[places.length];
	        for(int p = 0; p < places.length; p++) {
	            
	            answer[p] = 1;
	            for(int i = 0; i < 25; i++) {
	                if(places[p][i / 5].charAt(i % 5) == 'P' && !isSafe(i / 5, i % 5, places[p])) {
	                    answer[p] = 0;
	                    break;
	                }
	            }
	        }
	        
	        return answer;
	    }
	    
	    boolean isSafe(int x, int y, String[] place) {
	        for(int d = 0; d < 4; d++) {
	            int nx = x + dx[d];
	            int ny = y + dy[d];
	                
	            if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
	                continue;
	            if(place[nx].charAt(ny) == 'X')
	                continue;
	            if(place[nx].charAt(ny) == 'P')
	                return false;
	            
	            for(int dd = 0; dd < 4; dd++) {
	                int nnx = nx + dx[dd];
	                int nny = ny + dy[dd];
	                
	                if(nnx < 0 || nnx >= 5 || nny < 0 || nny >= 5)
	                   continue;
	                if(nnx == x && nny == y)
	                    continue;
	                if(place[nnx].charAt(nny) == 'P')
	                    return false;
	            }
	        }
	        
	        return true;
	    }
	}
