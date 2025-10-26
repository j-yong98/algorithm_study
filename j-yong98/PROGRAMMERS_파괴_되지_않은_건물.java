public class PROGRAMMERS_파괴_되지_않은_건물 {
    class Solution {
        int[][] sum;
        int n, m;
        public int solution(int[][] board, int[][] skill) {
            n = board.length;
            m = board[0].length;
            sum = new int[n + 1][m + 1];

            for (int i = 0; i < skill.length; i++) {
                int[] s = skill[i];
                int r1 = s[1], c1 = s[2];
                int r2 = s[3], c2 = s[4];
                int val = s[0] == 1 ? -s[5] : s[5];

                sum[r1][c1] += val;
                sum[r1][c2 + 1] += -val;
                sum[r2 + 1][c2 + 1] += val;
                sum[r2 + 1][c1] += -val;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    sum[i][j] += sum[i][j - 1];
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    sum[j][i] += sum[j - 1][i];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    board[i][j] += sum[i][j];
                }
            }
            int answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] > 0) {
                        answer++;
                    }
                }
            }
            return answer;
        }
    }
}
