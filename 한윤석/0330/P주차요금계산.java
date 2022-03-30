
	
 int [] Solution(int [] fees, String [] records) {
		int [] answer;
		int cnt =0 ;
		Map<String, Integer> timeInfo = new TreeMap<>(); // 차량번호, 누적 이용 분 
		Map<String, String> carInfo = new HashMap<>(); // 차량번호, 입차정보
		
		for(int i=0; i<records.length; i++) {
			String [] splits = records[i].split(" ");
			String [] outTime = splits[0].split(":");
			
			if(splits[2].equals("IN")) {
				timeInfo.putIfAbsent(splits[1], 0);
				carInfo.putIfAbsent(splits[1], splits[0]);
			}else if(splits[2].equals("OUT")) {
				String [] inTime = carInfo.get(splits[1]).split(":");
				int inTotal = Integer.parseInt(inTime[0])*60 + Integer.parseInt(inTime[1]);
				int outTotal = Integer.parseInt(outTime[0])*60 + Integer.parseInt(outTime[1]);
				int spendTime = outTotal - inTotal;
				
				timeInfo.put(splits[1], timeInfo.get(splits[1]) + spendTime);
				carInfo.remove(splits[1]);
			}
		}
		
		Set<String> carInfoKeyset = carInfo.keySet();
		for(String s : carInfoKeyset) {
			String [] inTime = carInfo.get(s).split(":");
			int inTotal = Integer.parseInt(inTime[0])*60 + Integer.parseInt(inTime[1]);
			int outTotal = 23*60 + 59;
			int spendTime = outTotal - inTotal;
			
			timeInfo.put(s, timeInfo.get(s) + spendTime);
		}
		
		Set<String> timeInfoKeyset = timeInfo.keySet();
		answer = new int[timeInfo.size()];
		
		for(String s: timeInfoKeyset) {
			int spendTime = timeInfo.get(s);
			
			if(spendTime <= fees[0]) answer[cnt++] = fees[1];
			else {
				spendTime -= fees[0];
				int spendMoney = spendTime % fees[2] == 0 ? (spendTime / fees[2]) * fees[3] : (spendTime / fees[2] + 1) * fees[3];
				spendMoney += fees[1];
				 answer[cnt++] = spendMoney;
			}
		}
		
		return answer;
	}

