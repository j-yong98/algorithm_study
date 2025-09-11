import java.util.*;
import java.io.*;

public class BOJ_3187 {

    static int[][] board;
    static int[] mvr = {-1, 1, 0, 0};
    static int[] mvc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");
        int R = Integer.parseInt(inputs[0]);
        int C = Integer.parseInt(inputs[1]);

        board = new int[R][C];

        int sheep = 0;
        int wolves = 0;

        for(int i = 0; i < R; ++i) {
            String input = br.readLine();

            for(int j = 0; j < C; ++j) {
                char c = input.charAt(j);
                int cur = 0;

                switch (c) {
                    case '#':
                        cur = 1;
                        break;
                    case 'v':
                        cur = 2;
                        ++wolves;
                        break;
                    case 'k':
                        cur = 3;
                        ++sheep;
                        break;
                }

                board[i][j] = cur;
            }
        }

        boolean[][] visited = new boolean[R][C];

        for(int i = 0; i < R; ++i) {
            for(int j = 0; j < C; ++j) {
                if(visited[i][j] || board[i][j] == 1) {
                    continue;
                }

                int sheepCount = 0;
                int wolfCount = 0;

                visited[i][j] = true;
                Queue<Pair> queue = new ArrayDeque<>();
                queue.add(new Pair(i, j));

                while(!queue.isEmpty()) {
                    Pair cur = queue.poll();

                    if(board[cur.r][cur.c] == 2) {
                        ++wolfCount;
                    } else if(board[cur.r][cur.c] == 3) {
                        ++sheepCount;
                    }

                    for(int k = 0; k < 4; ++k) {
                        int nr = cur.r + mvr[k];
                        int nc = cur.c + mvc[k];

                        if(nr < 0 || nc < 0 || nr >= R || nc >= C) {
                            continue;
                        }

                        if(visited[nr][nc] || board[nr][nc] == 1) {
                            continue;
                        }

                        visited[nr][nc] = true;
                        queue.add(new Pair(nr, nc));
                    }
                }

                if(sheepCount > wolfCount) {
                    wolves -= wolfCount;
                } else {
                    sheep -= sheepCount;
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(sheep).append(" ").append(wolves);
        System.out.println(result);
    }

    static class Pair {
        int r, c;

        Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}