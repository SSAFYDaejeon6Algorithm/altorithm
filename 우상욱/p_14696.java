package bj;

import java.util.*;
import java.io.*;

public class p_14696 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	// Rounds
		
		for(int n = 0; n < N; n++) {
			int ddak_A[] = new int[5];
			int ddak_B[] = new int[5];
			
			// A inputs
			int A = sc.nextInt();
			for(int a = 0; a < A; a++)
				ddak_A[sc.nextInt()]++;
			// B inputs			
			int B = sc.nextInt();
			for(int b = 0; b < B; b++)
				ddak_B[sc.nextInt()]++;
			
			char winner = 'D';
			for(int i = 4; i >= 1; i--) {
				if(ddak_A[i] > ddak_B[i]) {
					winner = 'A';
					break;
				} else if (ddak_A[i] < ddak_B[i]) {
					winner = 'B';
					break;
				}
			}
			
			// print result
			System.out.println(winner);
		}
	}
}
