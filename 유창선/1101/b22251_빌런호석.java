package day1101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b22251_빌런호석 {
    static int N, K, P, X;
    static int[][] leds = {
            {1, 1, 1, 0, 1, 1, 1}, // 0
            {0, 0, 1, 0, 0, 1, 0}, // 1
            {1, 0, 1, 1, 1, 0, 1}, // 2
            {1, 0, 1, 1, 0, 1, 1}, // 3
            {0, 1, 1, 1, 0, 1, 0}, // 4
            {1, 1, 0, 1, 0, 1, 1}, // 5
            {1, 1, 0, 1, 1, 1, 1}, // 6
            {1, 0, 1, 0, 0, 1, 0}, // 7
            {1, 1, 1, 1, 1, 1, 1}, // 8
            {1, 1, 1, 1, 0, 1, 1}}; // 9
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        String now = init(X);

        for (int i = 1; i <= N; i++) {
            String target = init(i);
            if (now.equals(target)) continue; // 자기 본인이라면 continue
            int onOff = 0;
            for (int k = 0; k < K; k++) { // 자리 수
                if (now.charAt(k) == target.charAt(k)) continue; // 숫자 같다면 continue
                for (int j = 0; j < 7; j++) { // led 켜진 위치 체크
                    if (leds[now.charAt(k) - '0'][j] != leds[target.charAt(k) - '0'][j]) onOff++;
                }
                if (onOff > P) break;
            }
            if (onOff <= P) cnt++;
        }
        System.out.println(cnt);
    }

    public static String init(int n) {
        String tmp = String.valueOf(n);
        int tmpLen = tmp.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K - tmpLen; i++) {
            sb.append('0');
        }
        sb.append(tmp);
        return sb.toString();
    }
}
