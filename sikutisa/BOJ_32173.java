import java.util.*;
import java.io.*;

public class BOJ_32173 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        String[] inputs = br.readLine().split(" ");
        for(int i = 0; i < N; ++i) {
            arr[i] = Long.parseLong(inputs[i]);
        }

        long result = 0;
        long sum = 0;

        for(int i = 0; i < N; ++i) {
            result = Math.max(arr[i] - sum, result);
            sum += arr[i];
        }

        System.out.println(result);
    }
}