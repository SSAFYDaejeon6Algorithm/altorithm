package day0426;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import java.util.Map;
import java.util.PriorityQueue;

public class b4358_생태학 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        PriorityQueue<Tree> pq = new PriorityQueue<>();

        int cnt = 0;
        while (true) {
            String line = br.readLine();
            if (line == null || line.equals("")) break;
            cnt++;
            line = line.replace("\n", "");
            if (map.containsKey(line))
                map.replace(line, map.get(line) + 1);
            else
                map.put(line, 1);
        } // end cnt;

        for (String s : map.keySet()) {
            pq.add(new Tree(s, map.get(s)));
        }


        while (!pq.isEmpty()) {
            Tree t = pq.poll();
            System.out.printf("%s %.4f\n", t.name, 100 * (double) t.cnt / cnt);
        }


    }

    public static class Tree implements Comparable<Tree> {
        String name;
        int cnt;

        public Tree(String name, int cnt) {
            this.name = name;
            this.cnt = cnt;
        }


        @Override
        public int compareTo(Tree o) {
            String me = this.name;
            String other = o.name;
            int index = 0;
            while (index < me.length() && index < other.length()) {
                int res = me.charAt(index) - other.charAt(index);
                if (res != 0) return res;
                index++; // 같다면 다음 문자열 비교
            }

            return this.name.length() - o.name.length();
        }
    }
}
