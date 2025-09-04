import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class BOJ2915 {
    static int N;
    static char[] arr;
    static char[] temp;
    static int ans = Integer.MAX_VALUE;
    static String ansStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        N = arr.length;
        temp = new char[N];
        f(0, 0);
        System.out.println(ansStr);
    }

    private static void f(int n, int visited) {
        if (n == N) {
            int res = parseNum(temp);
            if (ans > res) {
                ans = res;
                ansStr = new String(temp);
            }
            return;
        }

        for (int i = 0; i < N; i++) {
            if ((visited & (1 << i)) != 0) {
                continue;
            }
            temp[n] = arr[i];
            f(n + 1, visited | (1 << i));
        }
    }

    private static int parseNum(char[] src) {
        Map<String, Integer> map1 = Map.of(
                "I", 1, "II", 2, "III", 3, "IV", 4, "V", 5, "VI", 6, "VII", 7, "VIII", 8, "IX", 9
        );
        Map<String, Integer> map2 = Map.of(
                "X", 10, "XX", 20, "XXX", 30, "XL", 40, "L", 50, "LX", 60, "LXX", 70, "LXXX", 80, "XC", 90
        );

        int idx = -1;
        for (int i = 0; i < N; i++) {
            if (src[i] == 'I' || src[i] == 'V') {
                idx = i;
                break;
            }
        }

        String ones;
        int num = 0;
        if (idx >= 0) {
            ones = String.valueOf(src, idx, N - idx);
            Integer v1 = map1.get(ones);
            if (v1 == null) {
                return Integer.MAX_VALUE;
            }
            num += v1;
        } else {
            idx = N;
        }

        if (idx > 0) {
            String tens = String.valueOf(src, 0, idx);
            Integer v2 = map2.get(tens);
            if (v2 == null) {
                return Integer.MAX_VALUE;
            }
            num += v2;
        }
        return num;
    }

}

