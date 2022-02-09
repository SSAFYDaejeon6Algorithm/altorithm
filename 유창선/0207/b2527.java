import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b2527 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int p1 = Integer.parseInt(st.nextToken());
            int q1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int q2 = Integer.parseInt(st.nextToken());

            String res = "a";

            if (p1 < x2 || p2 < x1 || q2 < y1 || q1 < y2) { // 안겹치는 경우
                res = "d";
            } else {
                if (p1 == x2) { // 오른쪽 x 값 같을 때
                    res = "b";
                } else if (x1 == p2) { // 왼쪽 x 값이 같을 때
                    res = "b";
                } else if (q1 == y2) {
                    res = "b";
                } else if (y1 == q2) {
                    res = "b";
                }

                if ((p1==x2 && q1 ==y2) || (p1==x2 && y1 == q2) || (x1 == p2 && y1 == q2) || (x1 == p2 && q1 == y2)) {
                    res = "c";
                }
            }
            System.out.println(res);
        }
    }
}