import java.util.*;
import java.io.*;

public class BOJ11796 {
    final static int MOD = 1_000_000_007;
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int l = 1, r = N + 1;
        int ans = 0;
        while (l < r) {
            int mid = (l + r) >> 1;
            int cnt = getCount(mid);
            if (cnt <= 0) {
                l = mid + 1;
            } else {
                r = mid;
                ans = cnt;
            }
        }
        ans = getCount(r);
        System.out.println(r + " " + ans);
    }

    private static int getCount(int length) {
        if (length > N) {
            return modPow(M, length);
        }

        // 초기에 문자열로 구현 했으나 메모리 BOOM -> 롤링 해시
        long B = (long) M + 1;
        long h = 0;

        for (int i = 0; i < length; i++) {
            h = h * B + arr[i];
        }

        Set<Long> set = new HashSet<>();
        set.add(h);

        long pow = 1;
        for (int i = 1; i < length; i++) pow *= B;

        for (int i = length; i < N; i++) {
            h -= (long) arr[i - length] * pow;
            h = h * B + arr[i];
            set.add(h);
        }

        int distinct = set.size();
        int total = modPow(M, length);
        int ans = total - (distinct % MOD);
        if (ans < 0) ans += MOD;
        return ans;
    }

    private static int modPow(int a, int e) {
        long res = 1, x = a;
        while (e > 0) {
            if ((e & 1) == 1) res = (res * x) % MOD;
            x = (x * x) % MOD;
            e >>= 1;
        }
        return (int) res;
    }
}

