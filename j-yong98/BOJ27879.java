import java.io.*;
import java.util.*;

public class BOJ27879 {

    static final String[] QUERY = {"aa", "ab", "ba", "bb"};
    static int N, Q;
    static char[][] a;

    static int[][][] prefH;
    static int[][][] prefV;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        a = new char[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                a[i][j] = st.nextToken().charAt(0);
            }
        }

        prefH = new int[4][N + 1][N + 1];
        prefV = new int[4][N + 1][N + 1];

        // 4 * 1000 * 1000 = 4_000_000
        for (int k = 0; k < 4; k++) {
            String pat = QUERY[k];

            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    int h = 0;
                    if (c - 1 >= 1) {
                        if (a[r][c - 1] == pat.charAt(0) && a[r][c] == pat.charAt(1)) h = 1;
                    }
                    int v = 0;
                    if (r - 1 >= 1) {
                        if (a[r - 1][c] == pat.charAt(0) && a[r][c] == pat.charAt(1)) v = 1;
                    }

                    prefH[k][r][c] = prefH[k][r][c - 1] + prefH[k][r - 1][c] - prefH[k][r - 1][c - 1] + h;
                    prefV[k][r][c] = prefV[k][r][c - 1] + prefV[k][r - 1][c] - prefV[k][r - 1][c - 1] + v;
                }
            }
        }

        StringBuilder out = new StringBuilder();
        Q = Integer.parseInt(br.readLine());
        for (int qi = 0; qi < Q; qi++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            String q = st.nextToken();
            int idx = getIdx(q);

            long ans = 0;
            if (c1 + 1 <= c2) {
                ans += rectSum(prefH[idx], r1, c1 + 1, r2, c2);
            }
            if (r1 + 1 <= r2) {
                ans += rectSum(prefV[idx], r1 + 1, c1, r2, c2);
            }

            out.append(ans).append('\n');
        }
        System.out.print(out);
    }

    static int getIdx(String q) {
        for (int i = 0; i < 4; i++) if (q.equals(QUERY[i])) return i;
        return -1;
    }

    static int rectSum(int[][] pref, int r1, int c1, int r2, int c2) {
        return pref[r2][c2] - pref[r1 - 1][c2] - pref[r2][c1 - 1] + pref[r1 - 1][c1 - 1];
    }
}
