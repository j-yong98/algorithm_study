import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BK_3187 {
    static int N, M, wolf, sheep;
    static char[][] arr;

    static char WOLF = 'v', SHEEP = 'k', WALL = '#', BLANK = '.';

    static boolean[][] visited;
    static int[] delx = {0, 0, 1, -1};
    static int[] dely = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            String temp = st.nextToken();

            for (int j = 0; j < M; j++) {
                arr[i][j] = temp.charAt(j);
            }
        }

        // . 빈칸, # 울타리, v 늑대, k 양
        // 늑대, 양은 사방으로만 이동가능
        // 단, 울타리로 막히지 않은 영역에는 양과 늑대가 없으며 양과 늑대는 대각선으로 이동할 수 없다. -> 울타리 있어도 늑대나 양이 있으면 이동 가능
        // 늑대, 양 각각 살아남은 숫자 출력
        wolf = 0;
        sheep = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(!visited[i][j] && arr[i][j] != '#'){
                    count(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf);
    }

    private static void count(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;

        int tempW = 0;
        int tempS = 0;

        while (!q.isEmpty()){
            int[] now = q.poll();
            int nowX = now[0];
            int nowY = now[1];

            if(arr[nowX][nowY] == SHEEP){
                tempS++;
            }else if(arr[nowX][nowY] == WOLF){
                tempW++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = nowX + delx[i];
                int ny = nowY + dely[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && arr[nx][ny] != WALL){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        if(tempW >= tempS){
            wolf += tempW;
        }else{
            sheep += tempS;
        }
    }
}
