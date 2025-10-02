import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ3000 {

    static int N;
    static int[][] arr;
    static Map<Integer, Integer> countX = new HashMap<>();
    static Map<Integer, Integer> countY = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(str[0]);
            arr[i][1] = Integer.parseInt(str[1]);
            countX.put(arr[i][0], countX.getOrDefault(arr[i][0], 0) + 1);
            countY.put(arr[i][1], countY.getOrDefault(arr[i][1], 0) + 1);
        }

        long sum = 0;
        for (int i = 0; i < N; i++) {
            sum += (long) (countX.get(arr[i][0]) - 1) * (countY.get(arr[i][1]) - 1);
        }
        System.out.println(sum);
    }
}
