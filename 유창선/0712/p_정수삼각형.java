package day0712;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int h = triangle.length;
        System.out.println(h);
        int[][] map = new int[h + 1][h + 2];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                map[i + 1][j + 1] = triangle[i][j];
            }
        } // end input

        for (int i = 1; i <= h; i++) {
            for (int j = 1; j <= i; j++) {
                int left = map[i - 1][j - 1] + map[i][j];
                int right = map[i -1][j] + map[i][j];
                map[i][j] = Math.max(left, right);

                if (i == h) {
                    answer = Math.max(answer, map[i][j]);
                }
            }
        }


        return answer;
    }
}