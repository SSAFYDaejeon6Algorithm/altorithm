package bj;

import java.io.*;
import java.util.*;

public class p_1089 {

	static char NUMBERS[][] = {
			{ '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#' },
			{ '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '#', '#', '.', '.', '#', '#', '#', '#', '#', '.', '.', '#', '#', '#' },
			{ '#', '#', '#', '.', '.', '#', '#', '#', '#', '.', '.', '#', '#', '#', '#' },
			{ '#', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '#', '#', '#', '.', '.', '#', '#', '#', '.', '.', '#', '#', '#', '#' },
			{ '#', '#', '#', '#', '.', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#' },
			{ '#', '#', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#', '.', '.', '#' },
			{ '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#' },
			{ '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '.', '#', '#', '#', '#' } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// input lamps
		String inp[] = new String[5];
		for (int i = 0; i < 5; i++)
			inp[i] = br.readLine();

		Number[] nums = new Number[N];
		for (int n = 0; n < N; n++) {
			// String to char array
			char[] blubs = new char[15];
			for (int i = 0, k = 0; i < 5; i++)
				for (int j = 4 * n; j < 4 * n + 3; j++, k++)
					blubs[k] = inp[i].charAt(j);
		
			nums[n] = new Number(blubs);
		}
		
		// calculate total 
		for(int n = 0; n < N; n++) {
			if(nums[n].useables.size() == 0) {
				// can't represent any number
				System.out.println(-1);
				return;
			}
		}
		
		// calculate average
		double average = 0;
		for(int n = 0; n < N; n++) {
			double sum = 0.0;
			for(double i : nums[n].useables)
				sum += i * Math.pow(10, N - n - 1); 
			sum /= nums[n].useables.size();
			
			average += sum;
		}
		
		System.out.printf("%.5f", average);
	}

	static class Number {
		char[] blubs;
		ArrayList<Integer> useables = new ArrayList<>();

		Number(char[] blubs) {
			this.blubs = blubs;
			// add number that can turn on
			for(int i = 0; i < 10; i++)
				if(isUseable(i))
					useables.add(i);
		}
		
		boolean isUseable(int i) {
			for(int j = 0; j < 15; j++)
				if(blubs[j] == '#' && NUMBERS[i][j] == '.')
					return false;
			return true;
		}
	}
}
