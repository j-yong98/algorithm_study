import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ31860 {

    static int N, M, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for (int i = 0; i < N; i++) {
            int d = Integer.parseInt(br.readLine());
            pq.add(d);
        }
        int prev = 0;
        int days = 0;
        List<Integer> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            int d = pq.poll();

            int s = getSatisfaction(prev, d);
            list.add(s);
            prev = s;

            d -= M;
            if (d > K) {
                pq.add(d);
            }
            days++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(days).append("\n");
        list.forEach(integer -> sb.append(integer).append("\n"));
        System.out.println(sb);
    }

    private static int getSatisfaction(int p, int d) {
        return (p / 2) + d;
    }

}
