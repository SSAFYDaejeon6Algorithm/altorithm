public class P수식최대화 {

	static long answer = 0;
	static char sequence [] = {'+','-','*'}; //연산자 순서를 위한 배열
	static List<Long> nums = new LinkedList<>(); //피연산자 리스트
	static List<Character> ops = new LinkedList<>(); //연산자 리스트

	static public long solution(String expression) {
		String num = ""; //피연산자 저장용
		
		for(int i=0; i<expression.length(); i++) {
			char cur = expression.charAt(i);
			
			if(cur - '0' < 0 || cur - '0' > 9) { //연산자이면
				nums.add(Long.parseLong(num));
				num="";
				ops.add(cur);
			}else num += cur; //피연산자이면
		}
		nums.add(Long.parseLong(num));
		
		// 3개의 연산자 => 6가지의 순열 경우
		solve(0,1,2);
		solve(0,2,1);
		solve(1,0,2);
		solve(1,2,0);
		solve(2,0,1);
		solve(2,1,0);
		
		return answer;
	}
	
	static void solve(int first, int second, int third) {
		int perm [] = {first, second, third}; //순열 순서
		int nsize = nums.size(); 
		int osize = ops.size();
		List<Long> copyNums = new LinkedList<>(); //피연산자 복사 리스트
		List<Character> copyOps = new LinkedList<>(); //연산자 복사 리스트
		
		//깊은 복사
		for(int i=0; i<nsize; i++) copyNums.add(nums.get(i));
		for(int i=0; i<osize; i++) copyOps.add(ops.get(i));
		
		//순열에 따른 조회
		for(int j=0; j<3; j++) {
			osize = copyOps.size();
			for(int k=0; k<3; k++) {
				if(j == perm[k]) { //k번째 연산자가 j번째 순서일 때만 실행
					for(int i=0; i<osize; i++) {
						if(copyOps.get(i) == sequence[k]) {
							long result = calc(copyNums.get(i), copyNums.get(i+1), copyOps.get(i));
							//System.out.println(copyNums.get(i) + " " + copyNums.get(i+1) + " " +  copyOps.get(i) + " " + i);
							copyNums.remove(i);
							copyNums.remove(i);
							copyNums.add(i, result);
							copyOps.remove(i);
							i--;
							osize--;
						}
					}
				}
			}
		}
		
		answer = Math.max(answer, Math.abs(copyNums.get(0)));
	}
	
	static long calc(long a, long b, char op) {
		if(op == '*') return a*b;
		else if(op == '+') return a+b;
		else return a-b;
	}
}
