import java.util.*;
import java.io.*;

public class BOJ_1508 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);
        int K = Integer.parseInt(inputs[2]);

        inputs = br.readLine().split(" ");
        int[] positions = new int[K];
        for(int i = 0; i < K; ++i) {
            positions[i] = Integer.parseInt(inputs[i]);
        }

        int start = 0;
        int end = N;

        while(start <= end) {
            int mid = (start + end) >> 1;

            StringBuilder cur = new StringBuilder();
            cur.append("1");
            int selected = 1;
            int prev = 0;

            for(int i = 1; i < K; ++i) {
                if(positions[i] - positions[prev] >= mid) {
                    cur.append("1");
                    ++selected;
                    prev = i;

                    if(selected == M) {
                        break;
                    }
                } else {
                    cur.append("0");
                }
            }

            while(cur.length() < K) {
                cur.append("0");
            }

            if(selected == M) {
                start = mid + 1;
                result = cur;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}
