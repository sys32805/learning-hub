
import java.util.Stack;

class basic_calculator {
    public int calculate(String s) {
        Stack<Integer> num = new Stack<>();
        Stack<Character> operator = new Stack<>();

        int num1, num2;
        int length = s.length();
        int i = 0;

        while (i < length) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int numStart = i;
                while (i < length && Character.isDigit(s.charAt(i))) {
                    i++;
                }
                num.push(Integer.parseInt(s.substring(numStart, i)));
                continue; // Continue to avoid incrementing i again
            } else if (c == ' ') {
                i++;
                continue; // Skip spaces
            } else {
                if (c == '*' || c == '/') {
                    i++;
                    while (i < length && s.charAt(i) == ' ') {
                        i++;
                    }
                    int numStart = i;
                    while (i < length && Character.isDigit(s.charAt(i))) {
                        i++;
                    }
                    num2 = Integer.parseInt(s.substring(numStart, i));
                    num1 = num.pop();
                    if (c == '*') {
                        num.push(num1 * num2);
                    } else {
                        num.push(num1 / num2);
                    }
                    continue; // Continue to avoid incrementing i again
                } else {
                    operator.push(c);
                }
            }
            i++;
        }

        while (!operator.isEmpty()) {
            char op = operator.remove(0);
            num1 = num.remove(0);
            num2 = num.remove(0);
            if (op == '-') {
                num.add(0, num1 - num2);
            } else if (op == '+') {
                num.add(0, num1 + num2);
            }
        }

        return num.isEmpty() ? 0 : num.pop();
    }

    public static void main(String[] args) {
        basic_calculator ob = new basic_calculator();
        System.out.println(ob.calculate("3*3 + 2*2"));
        System.out.println(ob.calculate(" 12 + 13 * 15 "));
        System.out.println(ob.calculate("3 - 4 * 3"));
    }
}
