import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
	목표 : 만족도 총합의 최대값

	조건 : 1 < N < 100,000
	      1 <= s_i <= 10^9
        1 <= i <= N

	접근 : 누적합으로 구할 수 있을 듯함.
	      i번째 학생이 새치기를 했다고 가정했을 때, 누적도 총합은 s_i - sum(s1, s2 ... s_i-1)임.

*/

public class Main {
  int N;
  int[] s;
  long[] prefixSum;

  public void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    s = new int[N];
    prefixSum = new long[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      s[i] = Integer.parseInt(st.nextToken());
      prefixSum[i + 1] = prefixSum[i] + s[i];
    }
  }

  public void solve() {
    long answer = Long.MIN_VALUE;

    for (int i = 0; i < N; i++) {
      answer = Math.max(answer, (long)s[i] - prefixSum[i]);
    }

    System.out.println(answer);
  }

  public static void main(String[] args) throws Exception {
    Main m = new Main();
    m.init();
    m.solve();
  }
}