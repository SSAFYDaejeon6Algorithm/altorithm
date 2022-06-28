package programmers;

public class P타겟넘버 {

	static int gnumbers [], gtarget, answer = 0;
	public static void main(String[] args) {
		int arr[] = {1,1,1,1,1};
		int a = solution(arr, 3);
		System.out.println(a);
	}

	static public int solution(int[] numbers, int target) {
		gnumbers = numbers;
		gtarget = target;
		dfs(numbers[0], 1);
		dfs(numbers[0] * -1, 1);
        
        return answer;
    }
	
	static void dfs(int sum, int idx) {
		if(idx == gnumbers.length) {
			if(sum == gtarget) answer++;
			return;
		}
		dfs(sum + gnumbers[idx], idx+1);
		dfs(sum - gnumbers[idx], idx+1);
	}
}
