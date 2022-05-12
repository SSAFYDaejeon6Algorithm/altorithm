import java.util.*;
import java.io.*;

class Solution {
    static int[] dr1 = {-1, 1, 0, 0}; // step 1
    static int[] dc1 = {0, 0, -1, 1};

    static int[] dr2 = {-2, 2, 0, 0, -1, 1, 1, -1}; // step 2
    static int[] dc2 = {0, 0, -2, 2, 1, 1, -1, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        char[][] map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    map[j][k] = places[i][j].charAt(k);
                }
            }
            if (checkOne(map) && checkTwo(map)) answer[i] = 1;
        }


        return answer;
    }

    static boolean checkOne(char[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr1[d];
                        int nc = j + dc1[d];

                        if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && map[nr][nc] == 'P') {
                            return false;
                        }
                    }

                }
            }
        }
        return true;
    }

    static boolean checkTwo(char[][] map) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 'P') {
                    for (int d = 0; d < 8; d++) {
                        int nr = i + dr2[d];
                        int nc = j + dc2[d];

                        if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && map[nr][nc] == 'P') {
                            if (d >= 0 && d < 4) {
                                int difR = (nr - i) / 2;
                                int difC = (nc - j) / 2;
                                if (map[i + difR][j + difC] == 'O')
                                    return false;

                            } else if (d >=4 && d < 8) {
                                int difR = (nr - i);
                                int difC = (nc - j);
                                if (map[i + difR][j] == 'O' || map[i][j + difC] == 'O')
                                    return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
