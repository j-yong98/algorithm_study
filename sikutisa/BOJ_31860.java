import java.util.*;
import java.io.*;

public class BOJ_31860 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        StringBuilder satisfied = new StringBuilder();

        int N, M, K;
        String[] inputs = br.readLine().split(" ");

        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < N; ++i) {
            int input = Integer.parseInt(br.readLine());

            pq.add(input);
        }

        int day = 0;
        int Y = 0;
        while(!pq.isEmpty()) {
            ++day;
            int cur = pq.poll();

            Y = (Y / 2) + cur;

            satisfied.append(Y).append("\n");

            cur -= M;
            if(cur > K) {
                pq.add(cur);
            }
        }

        result.append(day).append("\n").append(satisfied);
        System.out.println(result);
    }
}