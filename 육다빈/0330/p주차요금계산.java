package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution_parkingFee {
	
	static class Car implements Comparable<Car>{
		int cnum, time;
		Car(int cnum, int time){
			this.cnum = cnum;
			this.time = time;
		}
		@Override
		public int compareTo(Car o) {
			return this.cnum - o.cnum;
		}
	}

    public static int[] solution(int[] fees, String[] records) {
        int[] answer;
        Map<Integer, Integer> parking = new HashMap<>();
        Map<Integer, Car> result = new HashMap<>();
        for(String s : records) {
        	String[] rec = s.split(" ");
        	String[] tmp = rec[0].split(":");
        	int time = Integer.parseInt(tmp[0])*60+Integer.parseInt(tmp[1]);
        	int cnum = Integer.parseInt(rec[1]);
        	if(rec[2].equals("IN")) {
        		parking.put(cnum, time);
        	}else {
        		int total_time = time - parking.get(cnum);
        		if(result.containsKey(cnum)) result.get(cnum).time += total_time;
    			else result.put(cnum, new Car(cnum, total_time));
        		parking.remove(cnum);
        	}
        }
        for(int key : parking.keySet()) {
        	int total_time = 24*60-1 - parking.get(key);
        	if(result.containsKey(key)) result.get(key).time += total_time;
			else result.put(key, new Car(key, total_time));
        }
        List<Car> ans = new ArrayList<>();
        for(int key : result.keySet()) ans.add(result.get(key));
        Collections.sort(ans);
        answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++) {
        	answer[i] = fees[1] + (ans.get(i).time-fees[0]+fees[2]-1)/fees[2] * fees[3];
        	if(answer[i]<fees[1]) answer[i] = fees[1];
        }
        return answer;
    }
    
    public static void main(String[] args) {
		int[] fees = {180, 5000, 10, 600};
		String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT",
				"07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT",
				"22:59 5961 IN", "23:00 5961 OUT"};
		System.out.println(Arrays.toString(solution(fees, records)));
	}
}
