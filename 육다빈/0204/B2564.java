package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2564_security {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int width = Integer.parseInt(line.split(" ")[0]);
		int height = Integer.parseInt(line.split(" ")[1]);
				
		int T = Integer.parseInt(br.readLine());
		int[][] target = new int[T][3];		//0:방향	1:거리  2:현위치로부터 최소거리

		for(int t=0; t<T; t++) {
			line = br.readLine();
			target[t][0] = Integer.parseInt(line.split(" ")[0]);
			target[t][1] = Integer.parseInt(line.split(" ")[1]);
		}
		
		line = br.readLine();
		int secu_dir = Integer.parseInt(line.split(" ")[0]);	//경비원 방향
		int secu_dis = Integer.parseInt(line.split(" ")[1]);	//경비원 거리
		int result = 0;
		
		for(int t=0; t<T; t++) {
			int sum;
			if((2.5-target[t][0])*(2.5-secu_dir) > 0) {	//타겟과 같은 축일때
				
				if(target[t][0] == secu_dir) {				//타겟과 같은 라인
					target[t][2] = Math.abs(secu_dis-target[t][1]);
				}else if(2.5-secu_dir > 0)	{				//타겟과 다른라인 - 남북
					sum = target[t][1] + secu_dis;
					target[t][2] = height + Math.min(sum, width*2-sum);
				}else {										//타겟과 다른라인 - 동서
					sum = target[t][1] + secu_dis;
					target[t][2] = width + Math.min(sum, height*2-sum);
				}
				
			}else {										//타겟과 다른 축일때
				int tmp = secu_dir + target[t][0];		//방향 합
				if(tmp == 4) {	//방향 1, 3
					target[t][2] = secu_dis + target[t][1];
				}else if(tmp == 6) { //방향 3, 4
					target[t][2] = height + width - secu_dis - target[t][1];
				}else {
					switch(secu_dir) {
					case 1:
						target[t][2] = width - secu_dis + target[t][1];
						break;
					case 2:
						target[t][2] = height + secu_dis - target[t][1];
						break;
					case 3:
						target[t][2] = height - secu_dis + target[t][1];
						break;
					case 4:
						target[t][2] = width + secu_dis - target[t][1];
						break;
					}
				}
			}
			
			result += target[t][2];
		}
		System.out.println(result);
		
	}

}
