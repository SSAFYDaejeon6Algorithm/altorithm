package programmers;

import java.util.*;

public class 파일명_정렬 {

	public static void main(String[] args) {
		System.out.println(new Solution()
				.solution(new String[] { "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG" }));
	}

	static class Solution {
		public String[] solution(String[] files) {
			FileName[] filenames = new FileName[files.length];
			for (int n = 0; n < files.length; n++) {
				int i, j;
				String head, num;
				
				// slice head
				for (i = 0; i < files[n].length(); i++)
					if ('0' <= files[n].charAt(i) && files[n].charAt(i) <= '9') // is number
						break;
				head = files[n].substring(0, i);

				// slice number
				for (j = i; j < files[n].length(); j++)
					if ('0' > files[n].charAt(j) || files[n].charAt(j) > '9') // is not number
						break;
				num = files[n].substring(i, j);
				
				filenames[n] = new FileName(files[n], head, num, n);
			}
			
			Arrays.sort(filenames);

			// change to String[]
			String[] answer = new String[files.length];
			for(int i = 0; i < answer.length; i++)
				answer[i] = filenames[i].original;
			return answer;
		}

		class FileName implements Comparable<FileName> {
			String head, original;
			int num, idx;

			FileName(String original, String head, String num, int idx) {
				this.original = original;
				this.head = head.toUpperCase();
				this.idx = idx;

				// remove forward 0
				int i;
				for (i = 0; i < num.length() - 1; i++)
					if (num.charAt(i) != '0')
						break;
				this.num = Integer.parseInt(num.substring(i));
			}

			@Override
			public int compareTo(FileName o) {
				// compare head
				int cond1 = this.head.compareTo(o.head);
				if (cond1 == 0) {
					// compare integer and index
					int cond2 = Integer.compare(this.num, o.num);
					if (cond2 == 0)
						return Integer.compare(this.idx, o.idx);
					else
						return cond2;
				} else
					return cond1;
			}
		}
	}
}
