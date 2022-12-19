package think;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pengzh
 * @since 2020-08-12
 * Input: "2-1-1".
 * <p>
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * Output : [0, 2]
 */
public class DivideConquer {
    public static void main(String[] args) {
        System.out.println(diffWayVal("2-1-1"));
    }

    public static List<Integer> diffWayVal(String input) {
        final List ways = new ArrayList<Integer>();
        for (int i = 0; StringUtils.isNotBlank(input) && i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWayVal(input.substring(0, i));
                List<Integer> right = diffWayVal(input.substring(i + 1));
                for (int l : left) {
                    for (int r : right) {
                        switch (c) {
                            case '+':
                                ways.add(l + r);
                                break;
                            case '-':
                                ways.add(l - r);
                                break;
                            case '*':
                                ways.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        if (ways.size() == 0) {
            ways.add(Integer.valueOf(input));
        }
        return ways;
    }
}
