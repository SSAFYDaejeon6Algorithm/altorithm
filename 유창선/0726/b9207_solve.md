# b9207_페그솔리테어

- 주요 알고리즘 : **DFS**
- 풀이:
  1. 핀을 찾으면 DFS를 시작한다.
  2. 조건에 체크를 하며 핀을 이동 시킨다.
    - 핀은 수평, 수직 방향으로 인접한 핀을 뛰어넘어서 그 핀의 다음 칸으로 이동하는 것만 허용된다.
    - 인접한 핀의 다음 칸은 비어있어야 하고 그 인접한 핀은 제거된다.
  3. 핀이 이동된 상태에서 다시 DFS 돌리기 반복
