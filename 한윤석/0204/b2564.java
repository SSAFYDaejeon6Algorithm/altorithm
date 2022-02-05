public class B2564 {
	static class Pair {
		int dir; //동서남북
		int num; //번호
		Pair(int dir, int num) {
			this.dir = dir;
			this.num = num;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int col = sc.nextInt(); //열
		int row = sc.nextInt(); //행
		int storeNum = sc.nextInt(); //점포 개수
		int info[][]= {{0,0,0},{3,4,2},{3,4,1},{1,2,4},{1,2,3}}; //info[i][x]는 i번 구역의 [0]:인접, [1]:인접, [2]:반대편 구역번호
		int total = 0; //총 거리
		List<Pair> list = new ArrayList<Pair>(); //점포 정보 담은 리스트
		for (int i = 0; i < storeNum; i++) {
			int dir = sc.nextInt();
			int num = sc.nextInt();
			Pair p = new Pair(dir, num);
			list.add(p);
		}
		int myDir = sc.nextInt(); //내가 있는 구역
		int myNum = sc.nextInt(); //번호
		for (Pair p : list) {
			if (info[myDir][2] == p.dir) { // 나의 반대편에 있다면
				if (myDir == 1 || myDir == 2) { // 위아래 방향일 때
					total += row;
					total += Math.min(myNum + p.num, (col - myNum) + (col - p.num));
				} else { // 좌우 방향일 때
					total += col;
					total += Math.min(myNum + p.num, (row - myNum) + (row - p.num));
				}
			} else if(info[myDir][0] == p.dir || info[myDir][1] == p.dir){ // 맞닿는 면에 있다면
				switch (myDir) {
				case 1:
					if(p.dir==3)
						total+=myNum+p.num;
					else
						total+=(col-myNum)+p.num;
					break;
				case 2:
					if(p.dir==3)
						total+=myNum+(row-p.num);
					else
						total+=(col-myNum)+(row-p.num);
					break;
				case 3:
					if(p.dir==1)
						total+=myNum+p.num;
					else
						total+=(row-myNum)+p.num;
					break;
				case 4:
					if(p.dir==1)
						total+=myNum+(col-p.num);
					else
						total+=(row-myNum)+(col-p.num);
					break;
				default:
					break;
				}
			}else { //같은 라인이라면
				total+= Math.abs(myNum - p.num);
			}
		}
		System.out.println(total);
		
	}
}
