static int gInfo [];
static int select[];
static int answer [];
static int gN;
static int maxGap = 0;

public int[] solution(int n, int[] info) {
  gInfo = info;
  gN = n;
      answer = new int[11];
  select = new int[n];

  comb(0);
  for(int i=0; i<11; i++) if(answer[i] != 0) return answer;

  return new int[] {-1};
}

static void comb(int cnt) {
  if(cnt == gN) {
    int [] curShoot = new int[11];
    int apeach = 0;
    int ryan = 0;
    for(int i=0; i<gN; i++) curShoot[select[i]]++;

    for(int i=0; i<11; i++) {
      if(gInfo[i] != 0 || curShoot[i] != 0) {
        if(gInfo[i] >= curShoot[i]) apeach += 10-i;
        else ryan += 10-i;
      }
    }

    if(ryan <= apeach) return;

    if(ryan - apeach >= maxGap) {
      maxGap = ryan - apeach;
      answer = curShoot;
    }

    return;
  }

  for(int i=10; i>=0; i--) {
    select[cnt] = i;
    comb(cnt+1);
  }
}
