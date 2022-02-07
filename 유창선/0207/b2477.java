import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2477 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int ewMax = Integer.MIN_VALUE;
        int snMax = Integer.MIN_VALUE;
        // 가장 길이가 긴 두 변 앞 2칸 or 뒤 2칸쨰 배열에 빼줘야할 직사각형 변 길이가 들어있음.
        int rightIndex = 0;
        int leftIndex = 0;
        int total = 0;
        int smallTotal = 0;
        int smallRec1 = 0; // 빼줘야할 직사각형 한 변
        int smallRec2 = 0; // 빼줘야할 직사각형 나머지 한 변
        int[] arr = new int[6];


        for (int i=0; i<6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            arr[i] = length;

            if (dir == 1 || dir == 2) {
                if (ewMax < length) { // 큰 변 하나 구하기
                    ewMax = length;
                    rightIndex = i;
                }
            } else if (dir == 3 || dir ==4) {
                if (snMax < length) { // 나머지 큰 변 하나 구하기
                    snMax = length;
                    leftIndex = i;
                }
            }
        }

        // 인덱스 왼쪽 오른쪽 값 찾아주기
        // 인덱스 크기로 비교 (더 큰게 오른쪽, 작은게 왼쪽)
        if (rightIndex < leftIndex) {
            int tmp = 0;
            tmp = rightIndex;
            rightIndex = leftIndex;
            leftIndex = tmp;
        }
        // 0, 5와 같은 경우 위에서 바뀌기 때문에 다시 바꿔줌
        // 0이 오른쪽 5가 왼쪽으로
        if (leftIndex == 0 && rightIndex == 5) {
            int tmp = 0;
            tmp = rightIndex;
            rightIndex = leftIndex;
            leftIndex = tmp;
        }

        total = ewMax * snMax; // 전체 넓이 구하기
        // 빼줘야할 작은 직사각형 두 변 구해주기
        // 오른쪽 인덱스는 +2
        smallRec1 = arr[(rightIndex+2)%6];
        // 앞쪽 인덱스는 -2
        // 인덱스로 되면 뒤로 가게 처리
        if (leftIndex -2 < 0) {
            smallRec2 = arr[(leftIndex -2 + 6)];
        } else {
            smallRec2 = arr[(leftIndex - 2)];
        }


        smallTotal = smallRec1 * smallRec2;
        int res = (total - smallTotal) * num;
        System.out.println(res);
    }
}
