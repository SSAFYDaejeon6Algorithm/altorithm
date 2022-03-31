import java.util.*;
class Solution {

    public static int[] solution(int[] fees, String[] records) {


        int maxTime = 23 * 60 + 59;
        int basicTime = fees[0], basicFee = fees[1], unitTime= fees[2], unitFee = fees[3];
        int[] cars = new int[10000];
        Arrays.fill(cars, -1);
        int[] parkingTime = new int[10000];
        boolean[] carNumber = new boolean[10000];
        int carCnt = 0;
        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record, " ");
            String timeString = st.nextToken();
            int time = Integer.parseInt(timeString.substring(0, 2)) * 60 + Integer.parseInt(timeString.substring(3));
            int car = Integer.parseInt(st.nextToken());
            if (!carNumber[car]) { // 차 갯수 세주기
                carNumber[car] = true;
                carCnt++;
            }
            if (cars[car] != -1) { // Out(출차)라면
                int timeResult = time - cars[car];
                cars[car] = -1;
                parkingTime[car] += timeResult;
            } else { // In 이라면
                cars[car] = time;
            }
        }

        for (int i = 0; i < 10000; i++) { // 출차 기록되지 않은 차 23:59에 나감
            if (cars[i] != -1) {
                parkingTime[i] += maxTime - cars[i];
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10000; i++) { // 돈 계산
            if (parkingTime[i] == 0) continue;
            if (parkingTime[i] <= basicTime) { // 기본 요금
                list.add(basicFee);
            } else {
                int extraTime = (parkingTime[i] - basicTime) % unitTime != 0 ?
                        (parkingTime[i] - basicTime) / unitTime + 1
                        : (parkingTime[i] - basicTime) / unitTime;
                int total = basicFee + extraTime * unitFee;
                list.add(total);
            }
        }

        int[] answer = new int[carCnt];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
