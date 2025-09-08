import java.util.*;
import java.io.*;

public class BOJ3187
{
    final static int[] dy = {-1, 1, 0, 0};
    final static int[] dx = {0, 0, -1, 1};
    final static char BLANK = '.';
    final static char WALL = '#';
    final static char SHEEP = 'k';
    final static char WOLF = 'v';
    static int R, C;
    static char[][] arr;
    static boolean[][] visited;
    static int aliveWolf = 0;
    static int aliveSheep = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
                String line = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] || arr[i][j] == WALL) {
                    continue;
                }
                getWolfAndSheep(i, j);
            }
        }
        System.out.println(aliveSheep + " " + aliveWolf);
    }

    private static void getWolfAndSheep(int r, int c) {
        Deque<int[]> q = new ArrayDeque<>();
        int wolfCount = 0;
        int sheepCount = 0;

        q.add(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] now = q.pollFirst();

            if (arr[now[0]][now[1]] == WOLF) {
                wolfCount++;
            } else if (arr[now[0]][now[1]] == SHEEP) {
                sheepCount++;
            }

            for (int i = 0; i < 4; i++) {
                int ny = now[0] + dy[i];
                int nx = now[1] + dx[i];

                if (!inRange(ny, nx) || visited[ny][nx] || arr[ny][nx] == WALL) {
                    continue;
                }

                q.addLast(new int[]{ny, nx});
                visited[ny][nx] = true;
            }
        }
        if (wolfCount >= sheepCount) {
            aliveWolf += wolfCount;
        } else {
            aliveSheep += sheepCount;
        }
    }

    private static boolean inRange(int y, int x) {
        return y >= 0 && y < R && x >= 0 && x < C;
    }
}

