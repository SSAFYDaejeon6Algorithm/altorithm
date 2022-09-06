package day0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1826_연료채우기 {
    static int N, dest, nowGas, nowPos, count;
    static ArrayList<Station> stationList = new ArrayList<>();
    static PriorityQueue<Station> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            stationList.add(new Station(a, b));
        }
        st = new StringTokenizer(br.readLine());
        dest = Integer.parseInt(st.nextToken());
        nowGas = Integer.parseInt(st.nextToken());

        findRes();
        System.out.println(count);

    }

    public static void findRes() {
        while (nowPos + nowGas  < dest) { // 목적지 도착하기 전까지
            int idx = 0;
            while(idx < stationList.size()) { // 주유소 리스트 중 갈 수 있는 것만
                // 현재 위치 < 주유소 위치 (뒤에 있는 주유소는 갈 필요가 없음) && 현재 위치 + 기름양 >= 주유소 위치
                if (nowPos < stationList.get(idx).pos && nowPos + nowGas >= stationList.get(idx).pos) {
                    pq.add(stationList.get(idx));
                    stationList.remove(idx);
                }else idx++;
            } // end inner while

            if (pq.isEmpty()) {
                count = -1;
                return;
            }

            Station next = pq.poll();
            nowGas = nowGas - (next.pos - nowPos) + next.gas; // 현재 기름 양 업데이트 (주유소까지 오면서 남은 기름 + 주요소 기름)
            nowPos = next.pos; // 현재 위치 업데이트
            count++;
            while(!pq.isEmpty()){
                stationList.add(pq.poll());
            }
        } // end outer while

    }

    public static class Station implements Comparable<Station> {
        public int pos;
        public int gas;

        public Station(int a, int b) {
            this.pos = a;
            this.gas = b;
        }

        @Override
        public int compareTo(Station o) {
            return o.gas - gas;
        }
    }
}
