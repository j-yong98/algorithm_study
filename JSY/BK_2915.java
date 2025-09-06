import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BK_2915 {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        String R1 = st.nextToken();
        StringBuilder IX = new StringBuilder();
        StringBuilder IV = new StringBuilder();
        StringBuilder XC = new StringBuilder();
        StringBuilder XL = new StringBuilder();

        int I = 0;
        int V = 0;
        int X = 0;
        int L = 0;
        int C = 0;

        for (int i = 0; i < R1.length(); i++) {
            char temp = R1.charAt(i);
            if (temp == 'I') {
                I++;
            } else if (temp == 'V') {
                V++;
            } else if (temp == 'X') {
                X++;
            } else if (temp == 'L') {
                L++;
            } else {
                C++;
            }
        }

// 1, 2, 3, 4, 5, 6, 7, 8, 9
// I, II, III, IV, V, VI, VII, VIII, IX가 된다.
//
// 10, 20, 30, 40, 50, 60, 70, 80, 90
// X, XX, XXX, XL, L, LX, LXX, LXXX, XC가 된다.

        // IX 되는경우
//LXXI
        if(I == 1 && V == 0 && X > 0 && (C == 0 || C > 0 && X > 1) && (L == 0 || L > 0 && X > 1)){
            IX.append("IX");
        }else{
            for (int i = 0; i < I; i++) {
                IV.append("I");
            }

            if(V > 0){
                if(I == 1){
                    IV.append("V");
                }else{
                    IV.insert(0, "V");
                }
            }
        }

        if(C > 0){
            XC.append("XC");
        }else{
            for (int i = 0; i < X; i++) {
                if(i == 0 && IX.length() > 0){
                    continue;
                }
                XL.append("X");
            }

            if(L > 0){
                if(X == 1 || (X == 2 && IX.length() > 0)){
                    XL.append("L");
                }else{
                    XL.insert(0, "L");
                }
            }
        }

        String result = "";

        if(XL.length() > 0){
            result += XL.toString();
        }else{
            result += XC.toString();
        }

        if(IX.length() > 0){
            result += IX.toString();
        }else{
            result += IV.toString();
        }

        System.out.println(result);
    }
}

