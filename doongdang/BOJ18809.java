import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Coordinate {

  int x;
  int y;
  boolean isGreen;
  boolean isRed;
  int time;

  public Coordinate(int x, int y, boolean isGreen, boolean isRed, int time) {
    this.x = x;
    this.y = y;
    this.isGreen = isGreen;
    this.isRed = isRed;
    this.time = time;
  }
}

class Tuple {

  boolean isRed;
  boolean isGreen;
  int time;

  public Tuple(boolean isRed, boolean isGreen, int time) {
    this.isRed = isRed;
    this.isGreen = isGreen;
    this.time = time;
  }
}

public class Main {

  int N, M, G, R;
  int canGrowEarthCnt;
  int answer;
  int[] dx = {-1, 0, 1, 0};
  int[] dy = {0, -1, 0, 1};
  int[][] garden;
  boolean[] isSelectedIndex;
  Coordinate[] selectedGreens;
  Coordinate[] selectedReds;
  List<Coordinate> growthEarths;
  Tuple[][] gardenForCheck;

  public void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    G = Integer.parseInt(st.nextToken());
    R = Integer.parseInt(st.nextToken());
    garden = new int[N][M];
    selectedGreens = new Coordinate[G];
    selectedReds = new Coordinate[R];
    growthEarths = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < M; j++) {
        garden[i][j] = Integer.parseInt(st.nextToken());

        if (garden[i][j] == 2) {
          growthEarths.add(new Coordinate(i, j, false, false, 0));
        }
      }
    }

    canGrowEarthCnt = growthEarths.size();
    isSelectedIndex = new boolean[canGrowEarthCnt];

    for (int i = 0; i < G; i++) {
      selectedGreens[i] = new Coordinate(-1, -1, true, false, 0);
    }

    for (int i = 0; i < R; i++) {
      selectedReds[i] = new Coordinate(-1, -1, false, true, 0);
    }
  }

  public void solve() {
    setCombinationForGreen(0, 0);

    System.out.println(answer);
  }

  private void setCombinationForGreen(int cnt, int curIdx) {
    if (cnt == G) {
      setCombinationForRed(0, 0);
      return;
    }

    for (int i = curIdx; i < canGrowEarthCnt; i++) {
      if (isSelectedIndex[i]) {
        continue;
      }

      isSelectedIndex[i] = true;
      selectedGreens[cnt].x = growthEarths.get(i).x;
      selectedGreens[cnt].y = growthEarths.get(i).y;
      setCombinationForGreen(cnt + 1, i + 1);
      selectedGreens[cnt].x = -1;
      selectedGreens[cnt].y = -1;
      isSelectedIndex[i] = false;
    }
  }

  private void setCombinationForRed(int cnt, int curIdx) {
    if (cnt == R) {
      int flowers = bfs();
      answer = Math.max(answer, flowers);
      return;
    }

    for (int i = curIdx; i < canGrowEarthCnt; i++) {
      if (isSelectedIndex[i]) {
        continue;
      }

      isSelectedIndex[i] = true;
      selectedReds[cnt].x = growthEarths.get(i).x;
      selectedReds[cnt].y = growthEarths.get(i).y;
      setCombinationForRed(cnt + 1, i + 1);
      selectedReds[cnt].x = -1;
      selectedReds[cnt].y = -1;
      isSelectedIndex[i] = false;
    }
  }

  private int bfs() {
    int cnt = 0;
    gardenForCheck = new Tuple[N][M];

    Queue<Coordinate> queue = new LinkedList<>();

    for (Coordinate selectedGreen : selectedGreens) {
      queue.offer(selectedGreen);
      gardenForCheck[selectedGreen.x][selectedGreen.y] = new Tuple(false, true, 0);
    }

    for (Coordinate selectedRed : selectedReds) {
      queue.offer(selectedRed);
      gardenForCheck[selectedRed.x][selectedRed.y] = new Tuple(true, false, 0);
    }

    while (!queue.isEmpty()) {
      Coordinate cur = queue.poll();

      if(gardenForCheck[cur.x][cur.y] != null && gardenForCheck[cur.x][cur.y].time == Integer.MAX_VALUE) continue;

      for (int i = 0; i < 4; i++) {
        int nx = cur.x + dx[i];
        int ny = cur.y + dy[i];
        int nTime = cur.time + 1;
        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
        if(garden[nx][ny] == 0) continue;

        Tuple nextGround = gardenForCheck[nx][ny];
        if(nextGround == null) {
          gardenForCheck[nx][ny] = new Tuple(cur.isRed, cur.isGreen, nTime);
          queue.offer(new Coordinate(nx, ny, cur.isGreen, cur.isRed, nTime));
          continue;
        }

        if(nextGround.time != nTime) continue;
        if((nextGround.isGreen && cur.isRed) || (nextGround.isRed && cur.isGreen)) {
          cnt++;
          nextGround.time = Integer.MAX_VALUE;
        }
      }
    }

    return cnt;
  }

  public static void main(String[] args) throws Exception {
    Main m = new Main();
    m.init();
    m.solve();
  }
}