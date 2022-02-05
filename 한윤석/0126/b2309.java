import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//백준 2309 일곱난장이
public class Main {
	static List<Integer> allShorts = new ArrayList<Integer>(); //입력받은 9난쟁이 키
	static List<Integer> selected = new ArrayList<Integer>(); //현재까지 선택된 난쟁이 키
	static final int TARGET_HEIGHT = 100; //키의 합
	static final int TARGET_SIZE = 7; //난쟁이 수
	static boolean isFind = false; //정답 발견 여부

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//9난쟁이 키 정보 입력
		for (int i = 0; i < 9; i++) {
			int height = sc.nextInt();
			allShorts.add(height);
		}
		//모든 난쟁이에 대해 재귀 호출
		for (int i = 0; i < 9; i++) 
			recursive(i, 0);
	}

	/*
	 * @params 
	 * index:탐색 인덱스, sum:현재까지의 키 합
	 */
	public static void recursive(int index, int sum) {
		int currentHeight = allShorts.get(index); //선택된 인덱스의 난쟁이의 키
		selected.add(currentHeight); //선택 리스트에 추가
		sum += currentHeight; //키 합에 더함
		/*
		 * 종료 조건
		 * 키의 합이 100을 넘거나, 리스트에 담긴 수가 7개를 넘거나, 이미 정답을 찾았으면
		 */
		if (sum > TARGET_HEIGHT || selected.size() > TARGET_SIZE || isFind) {
			sum -= currentHeight; //합에서 더한 키를 다시 뺌
			if (!selected.isEmpty())
				selected.remove((selected.size() - 1)); //선택 리스트에서 제거
			return;
		} else {
			/*
			 * 정답 발견 조건
			 * 키의 합이 100이면서, 선택된 난쟁이가 7명일 때
			 */
			if (sum == TARGET_HEIGHT && selected.size() == TARGET_SIZE) {
				selected.sort(Comparator.naturalOrder()); // 키 정렬
				isFind = true; //정답 발견여부 체크
				printResult(); //정답 출력
				return;
			}
			// 아직 채워지지 않았다면 다음 인덱스부터 다시 재귀 호출
			for (int i = index + 1; i < 9; i++) {
				recursive(i, sum);
			}
			// for문을 빠져나온 요소의 값만큼 다시 빼줌
			sum -= currentHeight;
			if (!selected.isEmpty())
				selected.remove((selected.size() - 1));
		}
	}

	//정답 출력 
	public static void printResult() {
		for (Integer i : selected)
			System.out.println(i);
	}
}
