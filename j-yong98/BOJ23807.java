import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ23807 {

    static final long MAX = Long.MAX_VALUE / 4;
    static int N, M;
    static int X, Z;
    static int P;
    static List<Integer> p = new ArrayList<>();
    static List<List<Node>> edges = new ArrayList<>();
    static List<long[]> minPath = new ArrayList<>();
    static long[] xPath;
    static long ans = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.get(u).add(new Node(v, w));
            edges.get(v).add(new Node(u, w));
        }
        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < P; i++) {
            p.add(Integer.valueOf(st.nextToken()));
        }
        for (int i = 0; i < P; i++) {
            minPath.add(dij(p.get(i)));
        }
        xPath = dij(X);
        comb(0, new int[3], new boolean[P]);
        System.out.println(ans >= MAX ? -1 : ans);
    }

    private static void comb(int n, int[] arr, boolean[] visited) {
        if (n == 3) {
            ans = Math.min(ans,
                    xPath[p.get(arr[0])] +
                    minPath.get(arr[0])[p.get(arr[1])] +
                    minPath.get(arr[1])[p.get(arr[2])] +
                    minPath.get(arr[2])[Z]
            );
            return;
        }

        for (int i = 0; i < P; i++) {
            if (visited[i]) {
                continue;
            }
            arr[n] = i;
            visited[i] = true;
            comb(n + 1, arr, visited);
            visited[i] = false;
        }
    }

    private static long[] dij(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(a.w, b.w));
        long[] dist = new long[N + 1];
        Arrays.fill(dist, MAX);

        dist[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.w > dist[now.v]) {
                continue;
            }

            for (Node edge : edges.get(now.v)) {
                if (dist[edge.v] <= now.w + edge.w) {
                    continue;
                }
                dist[edge.v] = now.w + edge.w;
                pq.add(new Node(edge.v, dist[edge.v]));
            }
        }
        return dist;
    }

    static class Node {

        int v;
        long w;

        public Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }

}
