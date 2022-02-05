import java.util.Scanner;
public class Main {
	static boolean[][] result = new boolean[5][5]; //결과 정보
	static int[][] game = new int[5][5]; //초기 세팅 게임판 정보
	static int bingoCnt = 0; //현재까지 빙고 수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 게임판 정보 입력
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				game[i][j] = sc.nextInt();
			}
		}
		// 사회자 정보 입력
		for (int i = 0; i < 25; i++) {
			int moderator = sc.nextInt(); //사회자가 부른 번호
			checkCurrent(moderator); 
			if (bingoCnt >= 3) { //종료 조건
				System.out.println(i + 1); 
				break;
			}
		}
	}
	// 사회자가 부른 수 체크
	public static void checkCurrent(int moderator) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (game[i][j] == moderator) {
					result[i][j] = true; // 나온 번호 여부 저장
					checkBingo(i, j); //빙고 생성 여부 체크
					return;
				}
			}
		}
	}
	// 빙고 완성 여부, 수 체크
	public static void checkBingo(int row, int col) {
		int rowCheckNum = 0; //행에 체크된 수
		int colCheckNum = 0; //열에 체크된 수
		int leftDiagonal = 0; // [0,0] ~ [4,4] 대각선 체크된 수
		int rightDiagonal = 0; // [0,4] ~ [4,0] 대각선 체크된 수
		for (int i = 0; i < 5; i++) {
			if (result[i][col]) //열 체크
				colCheckNum++;
			if (result[row][i]) //행 체크
				rowCheckNum++;
			if (row == col && result[i][i]) //대각선 체크
				leftDiagonal++;
			if (row == Math.abs(4 - col) && result[i][4 - i]) //대각선 체크
				rightDiagonal++;
		}
		//한 라인이 모두 채워졌다면 빙고 수 증가
		if (rowCheckNum == 5) 
			bingoCnt++;
		if (colCheckNum == 5)
			bingoCnt++;
		if (leftDiagonal == 5)
			bingoCnt++;
		if (rightDiagonal == 5)
			bingoCnt++;
	}
}
