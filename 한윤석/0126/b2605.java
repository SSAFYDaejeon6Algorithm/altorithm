import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int studentNum = sc.nextInt(); //학생 수
		int [] sequence = new int[studentNum]; //i번째 학생이 뽑은 번호
		List<Integer> line = new ArrayList<Integer>(); //줄을 선 정보
		
		//뽑은 번호 초기화
		for(int i=0; i<studentNum; i++) {
			sequence[i]=sc.nextInt(); 
		}
		
		//번호에 따라 줄 세우기
		for(int i=0; i<studentNum; i++) {
			int index = i-sequence[i]; // 학생이 설 자리 = 기존 i번째(i) - 뽑은 번호(sequence[i])
			line.add(index, i+1); //index위치에 i+1 추가
		}
		
        //출력
		for(Integer i:line)
			System.out.print(i+" ");
	}
}
