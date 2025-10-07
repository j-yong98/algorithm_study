import java.util.*;
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<Integer> numsList = new ArrayList<>();
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            numsList.add(nums[i]);
        }

        numsList.sort(Integer::compare);

        Set<Node> answer = new HashSet<>();
        for (int i = 0; i < numsList.size(); i++) {
            int a = numsList.get(i);
            for (int j = i + 1; j < numsList.size(); j++) {
                int b = numsList.get(j);
                int k = j + 1;
                int l = numsList.size() - 1;
                while (k < l) {
                    int c = numsList.get(k);
                    int d = numsList.get(l);
                    long sum = (long) a + b + c + d;
                    if (sum == target) {
                        answer.add(new Node(a,b,c,d));
                    }

                    if (sum < target) {
                        k++;
                    } else {
                        l--;
                    }
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Node n: answer) {
            ans.add(n.toList());
        }
        return ans;
    }
    static class Node {
        int a, b, c, d;

        public Node(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        public List<Integer> toList() {
            return List.of(a, b, c, d);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            Node n = (Node) o;
            return this.a == n.a && this.b == n.b && this.c == n.c && this.d == n.d;
        }
    }
}
