package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj2960_에라토스테네스의체 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] deleted = new boolean[N+1];
        deleted[0] = true;
        deleted[1] = true;

        // 모두 지울때까지 반복
        int current = 2; // 현재 소수
        int count = 0; // 몇번째 지워진 수
        while(true){
            // 아직 지우지 않은 수 중 가장 작은 수를 찾는다.
            for(; current < N+1 ; current++){
                if(!deleted[current]) break;
            }

            // 모든 수를 다 지웠으면 반복문 탈출
            if(current == N+1) break;

            // 선택한 수(current)가 소수인지 확인
            boolean isPrime = true;
            for(int i = 2 ; i <= current / 2 ; i++){
                if(current % i == 0){
                    isPrime = false;
                    break;
                }
            }

            // 소수일 경우 지우는 작업
            if(isPrime){
                for(int i = current ; i < N+1 ; i = i + current){
                    if(!deleted[i]){
                        deleted[i] = true;
                        count++;
                        if(count == K){
                            System.out.println(i);
                            return;
                        }
                    }
                }
            }

            // 다음 지우지 않은 수 중 가장 작은 수를 찾는다.
            while(true){
                if(!deleted[++current]) break;
            }
        }
    }
}
