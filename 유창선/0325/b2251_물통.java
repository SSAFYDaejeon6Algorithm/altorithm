package day0325;

import java.util.Scanner;

public class b2251_물통 {

    static int[] water = new int[3];
    static int[] bottleSize = new int[3];
    static int[] from = {0, 0, 1, 1, 2, 2};
    static int[] to = {1, 2, 0, 2, 0, 1};
    static boolean[][] checked;
    static boolean[] waterChecked;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        checked = new boolean[201][201];
        waterChecked = new boolean[201];
        for (int i = 2; i >= 0; i--) {
            bottleSize[i] = sc.nextInt();
        }

        water[0] = bottleSize[0]; // 처음 물통 가득 찼음

        perm(water);

        for (int i = 0; i <= 200; i++) {
            if (waterChecked[i]) System.out.print(i+" ");
        }

    }

    static void perm(int[] water){
        if (checked[water[1]][water[2]]) return;
        if (water[2] == 0) waterChecked[water[0]] = true;

        checked[water[1]][water[2]] = true;

        for (int i = 0; i < 6; i++) {
            int fromB = from[i]; // 출발 물병
            int toB = to[i]; // 담는 물병
            if (water[fromB] == 0) continue;
            int[] tmpWater = water.clone(); // 값이 바뀌기 때문에 저장해뒀다가 dfs() 갔다오면 사용


            int available = bottleSize[toB] - water[toB]; // 담는 물병에서 더 담을 수 있는 물
            if (available >= water[fromB]) { // 여유있는 경우
                water[toB] += water[fromB];
                water[fromB] -= water[fromB];
            } else { // 넘치는 경우
                water[fromB] -= available;
                water[toB] += available;
            }

            perm(water);
            water = tmpWater.clone(); // 값 바뀌기 전인 tmpWater 사용
        }
    }


}
