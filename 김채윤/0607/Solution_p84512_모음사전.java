package vac;

public class Solution_p84512_모음사전 {
	public int solution(String word) {
		char[] arr = { 'A', 'E', 'I', 'O', 'U' };
		int ans = 0;
		int num = 0;

		for (int i = 0; i < word.length(); i++) {
			if (i == 0)
				num = 781;
			if (i == 1)
				num = 156;
			if (i == 2)
				num = 31;
			if (i == 3)
				num = 6;
			if (i == 4)
				num = 1;
			
			char imsiword = word.charAt(i);
			for (int j = 0; j < 5; j++) {
				if (Character.compare(imsiword, arr[j]) == 0) {
					if (j == 0) ans = ans + 1;
					else ans = ans + (1 + num * j);
				}
			}
		}
		return ans;
	}
}