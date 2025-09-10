import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  int N, M, K;
  int[] locations;
  List<Integer> answerCandidate;
  StringBuilder answer;

  public void init() throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K =  Integer.parseInt(st.nextToken());
    locations = new int[K];
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < K; i++) {
      locations[i] = Integer.parseInt(st.nextToken());
    }

    answerCandidate = new LinkedList<>();
  }

  public void solve() {
    binarySearch();
    System.out.println(answer);
  }

  private void binarySearch() {
    int low = 0;
    int high = N;

    while (low <= high) {
      int mid = (low + high) / 2;

      if(canBeLocated(mid)) {
        buildAnswer();
        low = mid + 1;
      }else{
        high = mid - 1;
      }
    }
  }

  private boolean canBeLocated(int len) {
    int currentSelectedIndex = 0;
    int lastSelectedIndex = 0;
    int cnt = M;

    answerCandidate.clear();

    while (currentSelectedIndex < locations.length) {
      if(cnt == 0) {
        break;
      }

      if(currentSelectedIndex == 0) {
        answerCandidate.add(1);
        cnt--;
      }else{
        if(locations[currentSelectedIndex] - locations[lastSelectedIndex] < len) {
          answerCandidate.add(0);
        }else{
          lastSelectedIndex = currentSelectedIndex;
          answerCandidate.add(1);
          cnt--;
        }
      }
      currentSelectedIndex++;
    }

    int rest = 0;

    if(cnt == 0) {
      rest = K - answerCandidate.size();
    }else {
      rest = cnt;
    }

    for (int i = 0; i < rest; i++) {
      answerCandidate.add(0);
    }

    return cnt == 0;
  }

  private void buildAnswer() {
    answer = new StringBuilder();
    for (Integer i : answerCandidate) {
      answer.append(i);
    }
  }

  public static void main(String[] args) throws Exception {
    Main m = new Main();
    m.init();
    m.solve();
  }
}