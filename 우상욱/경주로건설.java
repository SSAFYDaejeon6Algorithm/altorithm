class Solution {
    
    int dx[] = {0, 0, -1, 1};
    int dy[] = {-1, 1, 0, 0};
    
    int visited[][];
    int answer;
    int N;
    
    public int solution(int[][] board) {
        N = board.length;
        
        visited = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        
        answer = Integer.MAX_VALUE;
        visited[0][0] = 0;
        Queue<Node> q = new LinkedList<>();
        if(board[0][1] == 0) {
             q.add(new Node(0, 1, 1, 100));
            visited[0][1] = 100;
        }
        if(board[1][0] == 0) {
            q.add(new Node(1, 0, 3, 100));
            visited[1][0] = 100;
        }
       
        while(!q.isEmpty()) {
            Node n = q.poll();
            int cost = n.cost;
            
            if(n.x == N - 1 && n.y == N - 1)
                answer = Math.min(answer, cost);
            
            for(int d = 0; d < 4; d++) {
                int nx = n.x + dx[d];
                int ny = n.y + dy[d];
                int ncost = (n.dir == d ? cost + 100 : cost + 600);
                
                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;   
                if(board[nx][ny] == 1)
                    continue;
                if(visited[nx][ny] < ncost)
                    continue;
                   
                visited[nx][ny] = ncost;
                q.add(new Node(nx, ny, d, ncost));
            }
        }
        
        return answer;
    }
    
    class Node {
        int x, y, dir, cost;
        
        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}
