//백준 2960. 에라토스테네스의 체

package b220125;

import java.io.IOException;
import java.util.Scanner;

public class b2960 {

	 public static void main(String[] args) throws IOException {
		 
		Scanner sc = new Scanner(System.in); //입력파트
    	int n = sc.nextInt(); //MAX
    	int k = sc.nextInt(); //K번째 지워진 수
    	
    	boolean[] num = new boolean[n+1]; //n+1인 이유: 0부터 시작하므로 +1해야함
    	
    	int count = 0; //몇번째로 지웠는지 확인하기 위한 용도
    	for(int i = 2; i<=n; i++) {//2부터 MAX까지
    		for(int j = i; j<=n; j+=i) {//i부터  MAX까지 i값 x
    			if(num[j] == false) { //아직 안지워진 수가 있다?
        			count++; 
        			num[j] = true; //지워진 후 true로 바꿔버리기
    			}
    			if(count == k) {
    				System.out.println(j);
    				System.exit(0);
    			}
    		}
    	}
    }
}