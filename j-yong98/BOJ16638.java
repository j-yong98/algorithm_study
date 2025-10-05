import java.io.*;
import java.util.*;

public class BOJ16638 {
    static int N;
    static String exp;
    static long ans = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        exp = br.readLine().trim();

        boolean[] pick = new boolean[N];
        dfs(1, pick);
        System.out.println(ans);
    }

    static void dfs(int i, boolean[] pick) {
        if (i >= N) {
            String withParen = buildExpressionWithParentheses(pick);
            long value = evaluateInfix(withParen);
            ans = Math.max(ans, value);
            return;
        }

        dfs(i + 2, pick);

        if (i - 2 >= 1 && pick[i - 2]) return;
        pick[i] = true;
        dfs(i + 2, pick);
        pick[i] = false;
    }

    static String buildExpressionWithParentheses(boolean[] pick) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < N) {
            if (i % 2 == 0) {
                if (i + 1 < N && pick[i + 1]) {
                    sb.append('(')
                        .append(exp.charAt(i))
                        .append(exp.charAt(i + 1))
                        .append(exp.charAt(i + 2))
                        .append(')');
                    i += 3;
                } else {
                    sb.append(exp.charAt(i));
                    i += 1;
                }
            } else {
                sb.append(exp.charAt(i));
                i += 1;
            }
        }
        return sb.toString();
    }

    static long evaluateInfix(String s) {
        String postfix = toPostfix(s);
        return evalPostfix(postfix);
    }

    static int prec(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*') return 2;
        return -1;
    }

    static String toPostfix(String s) {
        Deque<Character> ops = new ArrayDeque<>();
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                out.append(ch);
            } else if (ch == '(') {
                ops.push(ch);
            } else if (ch == ')') {
                while (!ops.isEmpty() && ops.peek() != '(') out.append(ops.pop());
                if (!ops.isEmpty() && ops.peek() == '(') ops.pop();
            } else {
                while (!ops.isEmpty() && ops.peek() != '(' && prec(ops.peek()) >= prec(ch)) {
                    out.append(ops.pop());
                }
                ops.push(ch);
            }
        }
        while (!ops.isEmpty()) out.append(ops.pop());
        return out.toString();
    }

    static long evalPostfix(String p) {
        Deque<Long> st = new ArrayDeque<>();
        for (int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            if (Character.isDigit(ch)) {
                st.push((long) (ch - '0'));
            } else {
                long b = st.pop();
                long a = st.pop();
                st.push(apply(a, b, ch));
            }
        }
        return st.pop();
    }

    static long apply(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        throw new IllegalArgumentException("unknown op: " + op);
    }
}
