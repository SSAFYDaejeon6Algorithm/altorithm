![image](https://user-images.githubusercontent.com/28249948/178462286-8b914552-2cbe-455f-856b-afa3ba53f3be.png)

```java
package programmers;

public class P정수삼각형 {

	public static void main(String[] args) {
		int arr [][] = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
		System.out.println(solution(arr));
	}
	
	static public int solution(int[][] triangle) {
        int answer = 0;
        int size = triangle[triangle.length-1].length;
        int dp [][] = new int[size][size];
        
        dp[0][0] = triangle[0][0];
        for(int i=0; i<size-1; i++) {
        	for(int j=0; j<=i; j++) {
        		dp[i+1][j] = Math.max(dp[i][j] + triangle[i+1][j], dp[i+1][j]);
        		dp[i+1][j+1] = Math.max(dp[i][j] + triangle[i+1][j+1], dp[i+1][j+1]);
        	}
        }
        
        for(int i=0; i<size; i++) {
        	for(int j=0; j<size; j++) System.out.print(dp[i][j] + " ");
        	System.out.println();
        }
        
        for(int i=0; i<size; i++) answer = Math.max(answer, dp[size-1][i]);
        
        return answer;
    }
}
```
