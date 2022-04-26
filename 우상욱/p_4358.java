package bj;

import java.io.*;
import java.util.*;

public class p_4358 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<String, Double> species = new TreeMap<>();

		Double tot = 0.0;
		String tree = br.readLine();
		while (true) {
			tot++;
			species.put(tree, species.getOrDefault(tree, 0.0) + 1);
			
			tree = br.readLine();
			if(tree == null || tree.length() == 0)
				break;
		}

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Double> entry : species.entrySet()) {
			sb.append(entry.getKey()).append(" ")
				.append(String.format("%.4f", entry.getValue() / tot * 100.0)).append("\n");
		}
		System.out.println(sb);
	}
}
