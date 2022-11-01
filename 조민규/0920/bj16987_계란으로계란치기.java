package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj16987_계란으로계란치기 {

    static class Egg {
        int 내구도;
        int 무게;

        public Egg(int 내구도, int 무게) {
            this.내구도 = 내구도;
            this.무게 = 무게;
        }
    }

    static int N, ans=0;
    static Egg[] egg;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = stoi(st.nextToken());
        egg = new Egg[N];
        for(int i = 0 ; i < N ; i++){
            st = new StringTokenizer(br.readLine());
            egg[i] = new Egg(stoi(st.nextToken()), stoi(st.nextToken()));
        }

        play(0,0);
        System.out.println(ans);
    }

    // idx : 현재 계란, broke : 깨진 계란 수
    public static void play(int idx, int broke){
        if(idx == N){
            ans = Math.max(ans, broke);
            return;
        }

        if(egg[idx].내구도 <= 0 || broke == N-1){
            play(idx+1, broke);
            return;
        }

        int tmp = broke;
        for(int i = 0 ; i < N ; i++){
            if(i == idx || egg[i].내구도 <= 0) continue;

            egg[idx].내구도 -= egg[i].무게;
            egg[i].내구도 -= egg[idx].무게;

            if(egg[i].내구도 <= 0){
                broke++;
            }
            if(egg[idx].내구도 <= 0){
                broke++;
            }

            play(idx+1, broke);

            egg[idx].내구도 += egg[i].무게;
            egg[i].내구도 += egg[idx].무게;
            broke = tmp;
        }
    }

    public static int stoi(String s){
        return Integer.parseInt(s);
    }
}
