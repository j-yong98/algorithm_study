import java.util.*;
import java.io.*;

public class BOJ_3000 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long result = 0;

        int N = Integer.parseInt(br.readLine());

        int[] x = new int[N];
        int[] y = new int[N];

        for(int i = 0; i < N; ++i) {
            String[] inputs = br.readLine().split(" ");

            int tx = Integer.parseInt(inputs[0]);
            int ty = Integer.parseInt(inputs[1]);

            x[i] = tx;
            y[i] = ty;
        }

        HashMap<Integer, Integer> xCount = new HashMap<>();
        HashMap<Integer, Integer> yCount = new HashMap<>();

        for(int i = 0; i < N; i++) {
            Integer countX = xCount.get(x[i]);
            Integer countY = yCount.get(y[i]);

            if(countX == null) {
                xCount.put(x[i], 1);
            } else {
                xCount.put(x[i], countX + 1);
            }

            if(countY == null) {
                yCount.put(y[i], 1);
            } else {
                yCount.put(y[i], countY + 1);
            }
        }

        for(int i = 0; i < N; i++) {
            long xValue = xCount.get(x[i]) - 1;
            long yValue = yCount.get(y[i]) - 1;

            result += xValue * yValue;
        }
        
        System.out.println(result);
    }
}