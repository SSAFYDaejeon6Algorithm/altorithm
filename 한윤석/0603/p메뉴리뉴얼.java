public class P메뉴리뉴얼 {
	static Map<String, Integer> map; //course개로 구성된 코스가 몇 번 호출됐는지
	static int select[]; //조합위한 배열
	static int maxVal; //course개로 구성된 코스 중 최대 호출 수
	
	String[] solution(String[] orders, int[] course) {
		ArrayList<String>answer = new ArrayList<String>();
		
        for(int i=0; i<course.length; i++) {
        	map = new HashMap<>();
        	maxVal = 0;
        	for(int j=0; j<orders.length; j++) {
        		select = new int[course[i]];
        		char [] curOrder = orders[j].toCharArray();
        		Arrays.sort(curOrder); //이걸 안 넣으면 3번째 예시 통과 못함
        		comb(0, course[i], 0, curOrder);
        	}
        	
        	//value값으로 내림정렬
        	List<String> keySet = new ArrayList<>(map.keySet());
        	keySet.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return map.get(o2).compareTo(map.get(o1));
                }
            });

            for (String key : keySet) {
            	if(map.get(key) == maxVal && maxVal > 1) answer.add(key);
            	else break;
            }
        }
        String [] arr = answer.toArray(new String[answer.size()]);
        Arrays.sort(arr);
        
        return arr;
    }
	
	static void comb(int cnt, int max, int start, char [] curOrder) {
		if(cnt == max) {
			String temp = "";
			for(int i=0; i<cnt; i++) {
				temp += curOrder[select[i]];
			}
			if(map.containsKey(temp)) {
				int tempVal = map.get(temp)+1;
				map.put(temp, tempVal);
				maxVal = Math.max(maxVal, tempVal);
			}
			else map.put(temp, 1);
			
			return;
		}
		
		for(int i=start; i<curOrder.length; i++) {
			select[cnt] = i;
			comb(cnt+1, max, i+1, curOrder);
		}
	}
}
