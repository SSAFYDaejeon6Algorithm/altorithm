package day0308;

import java.util.Scanner;

public class b20364_부동산다툼 {

    static boolean[] land;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int Q = sc.nextInt();

        land = new boolean[N + 1];

        for (int i = 0; i < Q; i++) {
            int wishLand = sc.nextInt(); // 오리가 원하는 땅
            int moving = wishLand; // 점유했는지 가보는 변수
            int firstMetLand = 0; // 첫번째로 점유한 땅 저장하기 위한 변수
            while (moving != 1) { // 원하는 땅 -> 출발점까지 오면서 확인
                if (land[moving]) { // 점유했다면 점유 땅 번호 업데이트
                    firstMetLand = moving;
                }
                moving /= 2;
            }

            if (firstMetLand == 0) { // 아무도 점유하지 않았다면 내땅
                land[wishLand] = true;
            }
            System.out.println(firstMetLand);
        }
    }
}
