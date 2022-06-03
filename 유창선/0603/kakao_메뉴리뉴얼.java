import java.util.*;
import java.io.*;

class Solution {
    public static boolean[] visited;
    public static int maxPeople;
    public static Map<Integer, Stack<Menu>> map;
    public static class Menu {
        int peopleCnt;
        String menu;

        public Menu(int peopleCnt, String menu) {
            this.peopleCnt = peopleCnt;
            this.menu = menu;
        }
    }
    public String[] solution(String[] orders, int[] course) {

        map = new HashMap<>();
        Set<String> set = new HashSet<>();
        // 메뉴 이름 순서대로 정렬
        for (int i = 0; i < orders.length; i++) {
            orders[i] = sortString(orders[i]);
        }

        for (String order : orders) { // 사람들이 주문한 메뉴들
            for (int r : course) { // 몇개 메뉴로 구성된 코스로 할 지
                int n = order.length();
                maxPeople = Integer.MIN_VALUE;
                visited = new boolean[n];

                // 조합 만드는 부분
                if (n >= r) {
                    combi(orders, order, n, r, 0, 0);
                }
            }
        }

        // HashMap에 들어 있는 모든 메뉴 조합들 중복 없게 하기 위해 Set에 넣기
        Set<Integer> keySet = map.keySet();

        for (Integer key : keySet) {
            Stack<Menu> s = map.get(key);
            System.out.println("==============key: " + key + "================");
            while (!s.empty()) {
                Menu m = s.pop();
                System.out.println("인원: " + m.peopleCnt + "   메뉴: "+ m.menu);
                set.add(m.menu);
            }
        }

        String[] answer = new String[set.size()];
        set.toArray(answer); // Set -> Array 변환
        Arrays.sort(answer); // 정렬
        return answer;
    }

    public static void combi(String[] orders, String order, int n, int r, int start, int cnt) {
        if (cnt == r) { // nCr 중 r만큼 뽑았다면

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if (visited[i]) sb.append(order.charAt(i));
            }
            String combiMenu = sb.toString(); //완성된 조합 메뉴
            System.out.println(combiMenu);
            int peopleCnt = 1; // 이 조합의 메뉴 다른 손님 얼마나 주문했는지
            boolean isMe = true;
            for (String other : orders) {
                if (other.equals(order) &&isMe) { // 자기 자신이라면
                    isMe = false;
                    continue;
                }
                boolean isContain = true;
                for (int i = 0; i < r; i++) { // 다른 손님 메뉴에 포함되어 있는지 확인
                    int m = combiMenu.charAt(i);
                    if (other.indexOf(m) == -1) { // 포함되지 않음
                        isContain = false;
                        break;
                    }
                }

                if (isContain) peopleCnt++; // 다른 사람 메뉴에 포함되었다면 peopleCnt++
            }

            if (peopleCnt < 2) return; // 2명이상이 아니라면 바로 return

            if (!map.containsKey(r)) { // 처음 2가지, 3가지... 메뉴 course 라면
                map.put(r, new Stack<Menu>()); // 스택 생성 후
                map.get(r).push(new Menu(peopleCnt, combiMenu)); // 값 넣어주기
            } else {
                Stack<Menu> s = map.get(r);
                int beforePeopleCnt = s.peek().peopleCnt;
                if (beforePeopleCnt < peopleCnt) { // 현재 구성 course가 더 많은 사람들이 먹었다면
                    while(!s.empty()) {
                        s.pop();
                    }
                    s.push(new Menu(peopleCnt, combiMenu));
                } else if (beforePeopleCnt == peopleCnt) { // 현재 구성 course와 똑같이 먹었다면 추가
                    s.push(new Menu(peopleCnt, combiMenu));
                }
            }
            return;
        }

        if (start == n) return; // 끝까지 봤다면 return

        visited[start] = true;
        combi(orders, order, n, r, start + 1, cnt + 1); // 이번 메뉴 조합에 넣기
        visited[start] = false;
        combi(orders, order, n, r, start + 1, cnt); // 이번 메뉴 조합에 넣지 않기

    }

    public static String sortString(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);

    }
}