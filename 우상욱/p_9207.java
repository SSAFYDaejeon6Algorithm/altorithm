package bj;

import java.io.*;
import java.util.*;

public class p_9207 {

	static int board[][] = new int[5][9];
	static int dx[] = { 0, -1, 0, 1 }, dy[] = { -1, 0, 1, 0 };
	static int min_pin = Integer.MAX_VALUE, min_movement = Integer.MAX_VALUE;	// answer
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); 
		
		StringBuilder sb = new StringBuilder();
		
		while(N-- != 0) {
			min_pin = Integer.MAX_VALUE;
			min_movement = Integer.MAX_VALUE;
			
			// input board
			for(int i = 0; i < 5; i++) {
				String line = br.readLine();
				for(int j = 0; j < 9; j++) {
					board[i][j] = line.charAt(j);
				}
			}
			
			backtrack(0);
			
			// print result
			sb.append(min_pin).append(" ").append(min_movement).append("\n");
			// for blank line
			br.readLine();
		}
		
		System.out.println(sb);
	}

	
	static void backtrack(int move) {
		if(isCompleted()) {
			// count pin
			int cnt_pin = 0;
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 9; j++) {
					if(board[i][j] == 'o')
						cnt_pin++;
				}
			}
			
			if(cnt_pin < min_pin) {
				min_pin = cnt_pin;
				min_movement = move;
			} else if(cnt_pin == min_pin){
				min_movement = Math.min(min_movement, move);
			}
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == 'o') {
					for(int d = 0; d < 4; d++) {
						if(move(i, j, d, true)) {
							backtrack(move + 1);
							move(i, j, d, false);
						}
					}
				}
			}
		}
	}
	
	// move pin forward or roll back 
	static boolean move(int x, int y, int dir, boolean isForward) {
		int nx = x + dx[dir], ny = y + dy[dir];
		int nnx = x + dx[dir] * 2, nny = y + dy[dir] * 2;
		
		if(isForward) {
			// forward
			if(isMoveable(x, y, dir)) {
				board[x][y] = board[nx][ny] = '.';
				board[nnx][nny] = 'o';
				return true;
			} else {
				return false;
			}
		} else {
			// backward
			board[x][y] = board[nx][ny] = 'o';
			board[nnx][nny] = '.';
			return true;
		}
	}
	
	static boolean isMoveable(int x, int y, int dir) {
		int nx = x + dx[dir], ny = y + dy[dir];
		int nnx = x + dx[dir] * 2, nny = y + dy[dir] * 2;
		
		if(nx < 0 || ny < 0 || nnx < 0 || nny < 0)
			return false;
		if(nx >= 5 || ny >= 9 || nnx >= 5 || nny >= 9)
			return false;
		if(board[nx][ny] == '#' || board[nnx][nny] == '#')
			return false;
		if(board[nx][ny] != 'o' || board[nnx][nny] != '.')
			return false;
		
		return true;
	}
	
	static boolean isCompleted() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 9; j++) {
				if(board[i][j] == 'o') {
					for(int d = 0; d < 4; d++) {
						if(isMoveable(i, j, d))
							return false;
					}
				}
			}
		}
		return true;
	}
	
	// for debugging
	static void printBoard() {
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.printf("%4d", board[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
