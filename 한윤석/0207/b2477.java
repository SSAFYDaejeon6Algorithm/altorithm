import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int melonPERm2 = sc.nextInt(); //면적당 참외 수
		int width = 0; //육각형 너비
		int height = 0; //육각형 높이
		int dummyWidth = 0; //버릴 너비
		int dummyHeight = 0; //버릴 높이
		List<Info> info = new ArrayList<Info>(); //입력정보

		for (int i = 0; i < 6; i++) {
			int dir = sc.nextInt();
			int len = sc.nextInt();
			Info currentInfo = new Info(dir, len);
			info.add(currentInfo);

			//현재 정보의 다음정보 설정
			if (i > 0) {
				if (i == 5) //마지막 정보일 경우 다음 정보를 맨처음 정보로 설정 
					currentInfo.next = info.get(0);
				info.get(i - 1).next = currentInfo; //현재 정보 = 이전 정보의 next
			}

			// 육각형 너비와 높이 갱신
			if (dir == 1 || dir == 2) {
				width = Math.max(width, len);
			} else {
				height = Math.max(height, len);
			}
		}

		for (Info i : info) {
			//현재 방향이 동쪽이면서 다음 정보가 남쪽이다 => 버릴 사각형 정보
			if (i.dir == 1 && i.len < width && i.next.dir == 3) {
				dummyWidth = i.len;
				dummyHeight = i.next.len;
				break;
			} else if (i.dir == 2 && i.len < width && i.next.dir == 4) {
				dummyWidth = i.len;
				dummyHeight = i.next.len;
				break;
			} else if (i.dir == 3 && i.len < height && i.next.dir == 2) {
				dummyWidth = i.next.len;
				dummyHeight = i.len;
				break;
			} else if (i.dir == 4 && i.len < height && i.next.dir == 1) {
				dummyWidth = i.next.len;
				dummyHeight = i.len;
				break;
			}
		}
		System.out.println((width * height - dummyWidth * dummyHeight) * melonPERm2);
	}

	static class Info {
		int dir; //동서남북
		int len; //길이
		Info next; //연결할 다음 정보

		Info(int dir, int len) {
			this.dir = dir;
			this.len = len;
		}
	}
}
