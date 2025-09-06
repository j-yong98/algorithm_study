import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BK_27879 {
    static String[][] arr;
    static int[][][] arrR, arrC;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new String[N + 1][N + 1];
        arrR = new int[N + 1][N + 1][4];
        arrC = new int[N + 1][N + 1][4];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = st.nextToken();
            }
        }

        // 조합별 위치 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                String temp = arr[i][j - 1] + arr[i][j];
                String temp1 = arr[i - 1][j] + arr[i][j];

                if (temp.equals("aa")) {
                    arrR[i][j][0] = 1;
                } else if (temp.equals("ab")) {
                    arrR[i][j][1] = 1;
                } else if (temp.equals("ba")) {
                    arrR[i][j][2] = 1;
                } else if (temp.equals("bb")) {
                    arrR[i][j][3] = 1;
                }

                if (temp1.equals("aa")) {
                    arrC[i][j][0] = 1;
                } else if (temp1.equals("ab")) {
                    arrC[i][j][1] = 1;
                } else if (temp1.equals("ba")) {
                    arrC[i][j][2] = 1;
                } else if (temp1.equals("bb")) {
                    arrC[i][j][3] = 1;
                }

                for (int k = 0; k < 4; k++) {
                    arrR[i][j][k] += arrR[i][j - 1][k];
                    arrC[i][j][k] += arrC[i][j - 1][k];
                }
            }
        }

        // 전체 누적합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 0; k < 4; k++) {
                    arrR[i][j][k] += arrR[i - 1][j][k];
                    arrC[i][j][k] += arrC[i - 1][j][k];
                }
            }
        }

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            String targetStr = st.nextToken();
            int targetNum = -1;

            if (targetStr.equals("aa")) {
                targetNum = 0;
            } else if (targetStr.equals("ab")) {
                targetNum = 1;
            } else if (targetStr.equals("ba")) {
                targetNum = 2;
            } else if (targetStr.equals("bb")) {
                targetNum = 3;
            }

            int result = 0;

            // 가로
            result += arrR[x2][y2][targetNum] - arrR[x2][y1][targetNum] - arrR[x1 - 1][y2][targetNum] + arrR[x1 - 1][y1][targetNum];
            // 세로
            result += arrC[x2][y2][targetNum] - arrC[x2][y1 - 1][targetNum] - arrC[x1][y2][targetNum] + arrC[x1][y1 - 1][targetNum];

            System.out.println(result);
        }
    }

    private static void printArr(int[][][] arr, int target){
        for (int j = 1; j <= N ; j++) {
            for (int k = 1; k <= N; k++) {
                System.out.print(arr[j][k][target] + " ");
            }
            System.out.println();
        }
    }
}
