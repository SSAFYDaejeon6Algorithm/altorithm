package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S4358_ecology {
	
	static class Tree implements Comparable<Tree>{
		String name;
		Tree(String s){
			this.name = s;
		}
		@Override
		public int compareTo(Tree o) {
			int size = name.length();
			for(int i=0; i<size; i++) {
				if(this.name.charAt(i)==o.name.charAt(i)) {
					if(i==size-1) return -1;
					else if(i+1==o.name.length()) return 1;
					else continue;
				}else return this.name.charAt(i) - o.name.charAt(i);
			}
			return -1;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Tree> list = new ArrayList<>();
		Map<String, Integer> count = new HashMap<>();
		int cnt = 0;
		String s = br.readLine();
		while(true) {
			if(count.containsKey(s)) count.put(s, count.get(s)+1);
			else {
				list.add(new Tree(s));
				count.put(s, 1);
			}
			cnt++;
			if((s=br.readLine())==null || s.length()==0) break;
		}
		Collections.sort(list);
		for(Tree t : list) {
			System.out.printf("%s %.4f\n", t.name, 100.0*count.get(t.name)/cnt, 4);
		}
	}

}
