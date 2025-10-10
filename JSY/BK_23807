import java.io.*;
import java.util.*;

public class BK_23807 {
    static int N, M, P, start, end;
    static long result;
    static List<List<int[]>> list;
    static Set<Integer> point;
    static final int INF = Integer.MAX_VALUE;

    // p 개수 만큼 순열로 다익 돌리기?

    public static void main(String[] args) throws IOException {
        init();
        dijk(start);

        if (result == INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static void dijk(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);

        int startCnt = point.contains(s) ? 1 : 0;
        pq.offer(new Node(s, 0, startCnt));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.weight >= result) continue;

            // 종료 조건
            if (now.to == end && now.cnt >= 3) {
                result = Math.min(result, now.weight);
                return;
            }

            for (int[] next : list.get(now.to)) {
                int nextNode = next[0];
                int w = next[1];

                int nextCnt = now.cnt + (point.contains(nextNode) ? 1 : 0);

                int newWeight = now.weight + w;
                if (newWeight < result) {
                    pq.offer(new Node(nextNode, newWeight, nextCnt));
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(from).add(new int[]{to, weight});
            list.get(to).add(new int[]{from, weight});
        }

        st = new StringTokenizer(bf.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        P = Integer.parseInt(st.nextToken());

        point = new HashSet<>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < P; i++) {
            point.add(Integer.parseInt(st.nextToken()));
        }

        result = INF;
    }

    static class Node {
        int to, weight, cnt;
        public Node(int to, int weight, int cnt) {
            this.to = to;
            this.weight = weight;
            this.cnt = cnt;
        }
    }
}
