package vac;


public class Solution_p70129_이진변환반복하기 {

	public int[] solution(String s) {
        int [] answer =new int [2];
        int changecnt=0; //몇 번의 이진변환을 거쳐야하는지
        int zerocnt=0; //제거할 0의 개수
        
        while(true){
            int onecnt=0;
            if(s.equals("1"))
            	break;
            for(int i=0; i<s.length(); ++i){
                if(s.charAt(i)=='0')
                	zerocnt++;
                else 
                	onecnt++;
            }
            s=Integer.toBinaryString(onecnt);
            changecnt++;
        }
        answer[0]=changecnt;
        answer[1]=zerocnt;

        return answer;
    }
}
