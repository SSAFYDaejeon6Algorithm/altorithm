import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class b12927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String tmp = " " + line;
        char[] bulbs = tmp.toCharArray();
        int length = bulbs.length - 1;
        int cnt = 0;
        for (int i=1; i<=length; i++) {
            if (bulbs[i] == 'Y') {
                cnt++;
                for (int j=i; j<=length; j+=i) {
                    bulbs[j] = bulbs[j] == 'Y' ? 'N' : 'Y';
                }
            }
        }

        for (int i=1; i<=length; i++) {
            if (bulbs[i] == 'Y') {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(cnt);
    }
}
