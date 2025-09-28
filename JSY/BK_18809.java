import java.io.*;
import java.util.*;

public class BK_18809 {

    static int N, M, G, R, result;
    static final int L = 0, W = 1, H = 2;  // 호수, 못 쓰는 땅, 배양액 가능 땅
    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};
    static int[][] ground;
    static List<Node> HList;

    public static void main(String[] args) throws IOException {
        init();
        dfs(0, G, R, new ArrayList<>());
        System.out.println(result);
    }

    // BFS 시뮬레이션
    private static void bfs(List<Node> positions) {
        Queue<Node> q = new ArrayDeque<>();
        int[][] color = new int[N][M];  // 0=방문X, 4=초록, 6=빨강, 9=꽃
        int[][] time = new int[N][M];   // 도착 시간
        int cnt = 0;

        // 시작 배양액 위치 초기화
        for (Node node : positions) {
            q.offer(new Node(node.x, node.y, node.color, 0));
            color[node.x][node.y] = node.color;
            time[node.x][node.y] = 0;
        }

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 꽃은 더 이상 확산 불가
            if (color[cur.x][cur.y] == 9) continue;

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + delx[d];
                int ny = cur.y + dely[d];

                if (!canMove(nx, ny) || ground[nx][ny] == L) continue;

                // 아직 배양액이 도착하지 않은 경우
                if (color[nx][ny] == 0) {
                    color[nx][ny] = cur.color;
                    time[nx][ny] = cur.time + 1;
                    q.offer(new Node(nx, ny, cur.color, cur.time + 1));
                }
                // 다른 색 배양액이 같은 시간에 도착한 경우 → 꽃
                else if ((color[nx][ny] == 4 && cur.color == 6 || color[nx][ny] == 6 && cur.color == 4)
                        && time[nx][ny] == cur.time + 1) {
                    color[nx][ny] = 9; // 꽃
                    cnt++;
                }
            }
        }

        result = Math.max(result, cnt);
    }

    // DFS: 배양액 뿌릴 위치 조합 생성
    private static void dfs(int index, int g, int r, List<Node> positions) {
        if (g == 0 && r == 0) {
            bfs(positions);
            return;
        }
        if (index == HList.size()) return;

        // 해당 위치를 사용하지 않는 경우
        dfs(index + 1, g, r, positions);

        // 초록 배양액 사용
        if (g > 0) {
            Node green = new Node(HList.get(index).x, HList.get(index).y, 4, 0);
            positions.add(green);
            dfs(index + 1, g - 1, r, positions);
            positions.remove(positions.size() - 1);
        }

        // 빨강 배양액 사용
        if (r > 0) {
            Node red = new Node(HList.get(index).x, HList.get(index).y, 6, 0);
            positions.add(red);
            dfs(index + 1, g, r - 1, positions);
            positions.remove(positions.size() - 1);
        }
    }

    // 초기화
    private static void init() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        ground = new int[N][M];
        HList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                int val = Integer.parseInt(st.nextToken());
                ground[i][j] = val;
                if (val == H) {
                    HList.add(new Node(i, j));
                }
            }
        }
        result = 0;
    }

    private static boolean canMove(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Node {
        int x, y, color, time;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int color, int time) {
            this.x = x;
            this.y = y;
            this.color = color;
            this.time = time;
        }
    }
}