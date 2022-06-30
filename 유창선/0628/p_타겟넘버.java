package day0628;

class Solution {
    static int answer =  0;
    public int solution(int[] numbers, int target) {
        subset(numbers, target, 0, 0);
        return answer;
    }

    public static void subset(int[] numbers, int target, int cnt, int flag) {
        if (cnt == numbers.length) {
            int res = 0;
            for (int i = 0; i < numbers.length; i++) {
                if ((flag & 1 << i) > 0) { // 해당 수를 뽑았다면
                    res += numbers[i];
                } else {
                    res -= numbers[i];
                }
            }

            if (res == target) answer++;
            return;
        }

        subset(numbers, target, cnt + 1, flag | 1 << cnt);
        subset(numbers, target, cnt + 1, flag);
    }
}
