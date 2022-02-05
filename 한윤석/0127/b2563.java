import java.util.Scanner;
public class Main {
	static boolean[][] checked = new boolean[101][101];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int total = 0;
		for (int i = 0; i < t; i++) {
			int row = sc.nextInt();
			int col = sc.nextInt();
			for (int j = row; j < row + 10; j++) {
				for (int k = col; k < col + 10; k++) {
					if (!checked[j][k]) {
						total++;
						checked[j][k]=true;
					}
				}
			}
		}
		System.out.println(total);
	}
}
