# S12852. 1로 만들기 2

> **[문제](https://www.acmicpc.net/problem/12852)**
> 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/abb64b96-cfd5-43de-8959-7696b7524180/Untitled.png)

> **제출 코드**
> 

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/abeece38-f74f-4729-9c27-96d081103eea/Untitled.png)

- 사용 알고리즘 : `DP`

1부터 시작해서, 각 숫자가 만들어지는 최소 연산 횟수를 순차적으로 계산하고 저장하여 풀이했다.

어떠한 숫자에서 1로 향하기 위해 시행할 수 있는 연산은 세 종류뿐이다. 이를 역으로 이용해서, 각 연산들을 현재 숫자에서 시행했을 때 나올 최소 연산 횟수들을 비교하여, 가장 최소인 수만 저장하면 된다.이 방식으로 작은 수부터 큰 수까지 연산 횟수를 계산해서, 최종 숫자의 계산값을 출력하면 최종 연산횟수를 구할수 있다.

그러나 그 연산이 어느 숫자들을 거쳐왔는지는 동시에 확인할 수 없다고 생각하여, 다시 큰 수부터 작은수로 저장된 값을 역추적하며 출력했다. 이 방법보다 더 나은 방법이 있을지도 모르겠다.

```java
package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S12852_makeOne2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		for(int i=2; i<=N; i++) {
			int min = Integer.MAX_VALUE;
			if(i%3==0) min = Math.min(min, dp[i/3]+1);
			if(i%2==0) min = Math.min(min, dp[i/2]+1);
			min = Math.min(min, dp[i-1]+1);
			dp[i] = min;
		}
		System.out.println(dp[N]);
		
		System.out.print(N);
		int now = N;
		while(now != 1) {
			int min = Integer.MAX_VALUE;
			int next = -1;
			if(now%3==0 && min>dp[now/3]) {
				min = dp[now/3];
				next = now/3;
			}
			if(now%2==0 && min>dp[now/2]) {
				min = dp[now/2];
				next = now/2;
			}
			if(min>dp[now-1]) {
				min = dp[now-1];
				next = now-1;
			}
			now = next;
			System.out.print(" " + next);
		}
	}

}
```
