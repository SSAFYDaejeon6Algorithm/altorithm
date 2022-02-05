import java.util.Scanner;

public class B13300 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int studentNum = sc.nextInt(); // 참여 학생 수
		int roomLimit = sc.nextInt(); // 방 제한 인원
		int[][] info = new int[2][6]; // [성별][학년] 학생 수
		int totalRoom = 0; // 총 방의 수

		// input
		for (int i = 0; i < studentNum; i++) {
			int gender = sc.nextInt();
			int grade = sc.nextInt();
			info[gender][grade - 1]++;
		}

		// 방의 수 도출
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 6; j++) {
				//방 제한인원의 배수인 경우
				if (info[i][j] % roomLimit == 0 && info[i][j] > 0) {
					totalRoom += info[i][j] / roomLimit;
				} else if (info[i][j] % roomLimit > 0) {
					totalRoom += info[i][j] / roomLimit + 1;
				}
			}
		}
		System.out.println(totalRoom);
	}

}
