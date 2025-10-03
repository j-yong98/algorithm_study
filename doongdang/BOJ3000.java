import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	목표 : 좌표 평면에 점 N개가 있을 때, 빗변을 제외한 나머지 두 변이 좌표축에 평행한 직각삼각형을 이루는 점 3개를 고르는 경우의 수 구하기

	조건 : 3 ≤ N ≤ 100,000
	      1 ≤ X,Y ≤ 100,000

	접근 :
	      방안 1. Bruteforce로 접근 시, 100,000C3으로 시간초과

	      방안 2. 빗변을 제외한 나머지 두 변이 좌표축에 평행하기 위한 3점의 조건은 다음과 같다.
	             (x1, y1), (x2, y1), (x1, y2)
               이를 이용해 평행 축 1개를 고르고 나머지 축을 찾기 -> 100,000C2로 시간초과

        방안 3. 좌표 축에 평행한 두 변이 만나는 점을 선정하고, 해당 점과 x좌표, y좌표가 같은 점의 개수를 곱하면 된다.
*/

class Coordinate {

  int x;
  int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

public class Main {

  int N;
  int[] countOfX;
  int[] countOfY;
  Coordinate[] coordinates;

  public void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    countOfX = new int[100_001];
    countOfY = new int[100_001];
    coordinates = new Coordinate[N];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int X = Integer.parseInt(st.nextToken());
      int Y = Integer.parseInt(st.nextToken());
      countOfX[X]++;
      countOfY[Y]++;
      coordinates[i] = new Coordinate(X, Y);
    }
  }

  public void solve() {
    long answer = 0;

    for (Coordinate coordinate : coordinates) {
      int curX = coordinate.x;
      int curY = coordinate.y;

      answer += (long) (countOfX[curX] - 1) * (countOfY[curY] - 1);
    }

    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    Main m = new Main();
    m.init();
    m.solve();
  }
}