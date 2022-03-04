package bj;

import java.util.*;
import java.io.*;

public class p_1446 {

	static ArrayList<Shortcut> shortcuts;
	static int max_saving;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());

		// input shortcut
		shortcuts = new ArrayList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());

			// meaningless inputs
			if (end > D)
				continue;
			if (end - start < dis)
				continue;

			shortcuts.add(new Shortcut(start, end, (end - start) - dis));
		}

		Collections.sort(shortcuts);
		select_shorcut(0, 0, 0);
		
		System.out.println(D - max_saving);
	}

	static void select_shorcut(int idx, int end, int saving_dis) {
		if (idx == shortcuts.size()) {
			max_saving = Math.max(max_saving, saving_dis);
			return;
		}

		if (end <= shortcuts.get(idx).start)
			select_shorcut(idx + 1, shortcuts.get(idx).end, saving_dis + shortcuts.get(idx).saving_dis);
		select_shorcut(idx + 1, end, saving_dis);
	}

	static class Shortcut implements Comparable<Shortcut> {
		int start, end, saving_dis;

		public Shortcut(int start, int end, int saving_dis) {
			this.start = start;
			this.end = end;
			this.saving_dis = saving_dis;
		}

		@Override
		public int compareTo(Shortcut o) {
			return Integer.compare(this.start, o.start);
		}
	}
}
