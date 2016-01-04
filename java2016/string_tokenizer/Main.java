
import java.util.StringTokenizer;
import java.util.Scanner;

public class Main {
    public static void main(String []args) {
        String input = scan();
        System.out.println(parse(input));
    }

    public static String scan() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static long parse(String str) {
        final String NUMBER = "영일이삼사오육칠팔구";
        final String UNIT = "십백천만억조";
        final long [] UNIT_NUM = {10, 100, 1000, 10000, 100000000L, 1000000000000L };

        StringTokenizer st = new StringTokenizer(str, UNIT, true);

        long result = 0L;
        long num = 0L;
        long num_unit = 0L;

        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            int check = NUMBER.indexOf(token);
            if (check != -1) { // number
                num = check;
            }
            else { // unit
                check = UNIT.indexOf(token);
                if (check <= 2) { // 십백천
                    num_unit += (num == 0 ? 1 : num) * UNIT_NUM[check];
                }
                else { // 만억조
                    num_unit += num;
                    result += (num_unit == 0 ? 1 : num_unit) * UNIT_NUM[check];
                    num_unit = 0L;
                }
                num = 0L;
            }
        }

        return result + num_unit + num;
    }
}
