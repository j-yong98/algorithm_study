import java.util.*;

class Solution {
    private int[] left = new int[17];
    private int[] right = new int[17];
    private int[] value;
    private int[] visited = new int[1 << 17];
    int n;
    int answer = 1;
    
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        
        value = Arrays.copyOf(info, n);
        for(int i = 0; i < n - 1; ++i) {
            int a = edges[i][0];
            int b = edges[i][1];
            
            if(left[a] < 0) {
                left[a] = b;
            } else {
                right[a] = b;
            }
        }
        
        dfs(1);
        
        return answer;
    }
    
    private void dfs(int state) {
        if(visited[state] == 1) {
            return;
        }
        
        visited[state] = 1;
        
        int wolves = 0, num = 0;
        for(int i = 0; i < n; ++i) {
            if((state & (1 << i)) != 0) {
                ++num;
                wolves += value[i];
            }
        }
        
        if(2 * wolves >= num) {
            return;
        }
        
        answer = Math.max(answer, num - wolves);
        
        for(int i = 0; i < n; ++i) {
            if((state & (1 << i)) == 0) {
                continue;
            }
            
            if(left[i] != -1) {
                dfs(state | (1 << left[i]));
            }
            if(right[i] != -1) {
                dfs(state | (1 << right[i]));
            }
        }
    }
}