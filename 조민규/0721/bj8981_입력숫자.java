package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj8981_입력숫자 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = stoi(br.readLine());
        int[] inputs = new int[N];
        int[] X = new int[100];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++){
            inputs[i] = stoi(st.nextToken());
        }

        int from = 0, value = 0;
        for(int i = 0 ; i < N ;  i++){
            int tmp = inputs[i];

            from = (value + from) % N;
            value = X[from];
            while(value > 0){
                from = (from + 1) % N;
                value = X[from];
            }
            X[from] = value = tmp;
        }

        System.out.println(N);
        int idx = 0;
        while(X[idx] != 0){
            System.out.print(X[idx++] + " ");
        }
        System.out.println();
        System.out.println(Arrays.toString(X));
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}
