import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Query {

  int x1, y1, x2, y2;
  String target;

  public Query(int x1, int y1, int x2, int y2, String target) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    this.target = target;
  }
}

public class Main {

  int n, q;
  char[][] A;
  Query[] Q;
  int[][][] prefixSumR; //0 -> aa, 1 -> ab, 2 -> ba, 3 -> bb
  int[][][] prefixSumC;

  public void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());
    A = new char[n + 1][n + 1];
    prefixSumR = new int[4][n + 1][n + 1];
    prefixSumC = new int[4][n + 1][n + 1];

    for (int i = 1; i <= n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 1; j <= n; j++) {
        A[i][j] = st.nextToken().charAt(0);
      }
    }

    q = Integer.parseInt(br.readLine());
    Q = new Query[q];

    for (int i = 0; i < q; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      String target = st.nextToken();
      Q[i] = new Query(x1, y1, x2, y2, target);
    }
  }

  public void solve() {
    setPrefixSumR();
    setPrefixSumC();
    StringBuilder answer = new StringBuilder();
    int val = 0;

    for (int i = 0; i < q; i++) {
      Query query = Q[i];
      if (query.target.equals("aa")) {
        val = prefixSumR[0][query.x2][query.y2] - prefixSumR[0][query.x1 - 1][query.y2] - prefixSumR[0][query.x2][query.y1] + prefixSumR[0][query.x1 - 1][query.y1];
        val += prefixSumC[0][query.x2][query.y2] - prefixSumC[0][query.x1][query.y2] - prefixSumC[0][query.x2][query.y1 - 1] + prefixSumC[0][query.x1][query.y1 - 1];
      } else if (query.target.equals("ab")) {
        val = prefixSumR[1][query.x2][query.y2] - prefixSumR[1][query.x1 - 1][query.y2] - prefixSumR[1][query.x2][query.y1] + prefixSumR[1][query.x1 - 1][query.y1];
        val += prefixSumC[1][query.x2][query.y2] - prefixSumC[1][query.x1][query.y2] - prefixSumC[1][query.x2][query.y1 - 1] + prefixSumC[1][query.x1][query.y1 - 1];
      } else if (query.target.equals("ba")) {
        val = prefixSumR[2][query.x2][query.y2] - prefixSumR[2][query.x1 - 1][query.y2] - prefixSumR[2][query.x2][query.y1] + prefixSumR[2][query.x1 - 1][query.y1];
        val += prefixSumC[2][query.x2][query.y2] - prefixSumC[2][query.x1][query.y2] - prefixSumC[2][query.x2][query.y1 - 1] + prefixSumC[2][query.x1][query.y1 - 1];
      } else {
        val = prefixSumR[3][query.x2][query.y2] - prefixSumR[3][query.x1 - 1][query.y2] - prefixSumR[3][query.x2][query.y1] + prefixSumR[3][query.x1 - 1][query.y1];
        val += prefixSumC[3][query.x2][query.y2] - prefixSumC[3][query.x1][query.y2] - prefixSumC[3][query.x2][query.y1 - 1] + prefixSumC[3][query.x1][query.y1 - 1];
      }

      answer.append(val).append("\n");
    }

    System.out.println(answer);
  }

  public void setPrefixSumR() {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        calculateR(i, j);
        for (int k = 0; k < 4; k++) {
          prefixSumR[k][i][j] += prefixSumR[k][i][j - 1];
        }
      }
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        for (int k = 0; k < 4; k++) {
          prefixSumR[k][i][j] += prefixSumR[k][i - 1][j];
        }
      }
    }
  }

  public void calculateR(int i, int j) {
    if (A[i][j - 1] == 'a' && A[i][j] == 'a') {
      prefixSumR[0][i][j] = 1;
    } else if (A[i][j - 1] == 'a' && A[i][j] == 'b') {
      prefixSumR[1][i][j] = 1;
    } else if (A[i][j - 1] == 'b' && A[i][j] == 'a') {
      prefixSumR[2][i][j] = 1;
    } else if (A[i][j - 1] == 'b' && A[i][j] == 'b') {
      prefixSumR[3][i][j] = 1;
    }
  }

  public void calculateC(int i, int j) {
    if (A[i - 1][j] == 'a' && A[i][j] == 'a') {
      prefixSumC[0][i][j] = 1;
    } else if (A[i - 1][j] == 'a' && A[i][j] == 'b') {
      prefixSumC[1][i][j] = 1;
    } else if (A[i - 1][j] == 'b' && A[i][j] == 'a') {
      prefixSumC[2][i][j] = 1;
    } else if (A[i - 1][j] == 'b' && A[i][j] == 'b') {
      prefixSumC[3][i][j] = 1;
    }
  }

  public void setPrefixSumC() {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        calculateC(i, j);
        for (int k = 0; k < 4; k++) {
          prefixSumC[k][i][j] += prefixSumC[k][i][j - 1];
        }
      }
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        for (int k = 0; k < 4; k++) {
          prefixSumC[k][i][j] += prefixSumC[k][i - 1][j];
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {

    Main m = new Main();
    m.init();
    m.solve();
  }
}