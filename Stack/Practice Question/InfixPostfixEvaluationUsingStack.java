import java.util.*;

public class InfixPostfixEvaluationUsingStack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Infix Expression:");
        String infix = sc.nextLine();

        String postfix = infixToPostfix(infix);
        System.out.println("Postfix Expression: " + postfix);

        int result = evaluatePostfix(postfix);
        System.out.println("Evaluation Result: " + result);
    }

   
    private static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

           
            if (Character.isWhitespace(ch)) continue;

            
            if (Character.isDigit(ch)) {
                StringBuilder num = new StringBuilder();

                while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                    num.append(infix.charAt(i));
                    i++;
                }
                i--; // step back

                postfix.append(num).append(" ");
            }

            // 🔹 Opening bracket
            else if (ch == '(') {
                stack.push(ch);
            }

            // 🔹 Closing bracket
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop()).append(" ");
                }
                if (!stack.isEmpty()) stack.pop(); // remove '('
            }

            // 🔹 Operator
            else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(ch);
            }
        }

        // 🔹 Remaining operators
        while (!stack.isEmpty()) {
            postfix.append(stack.pop()).append(" ");
        }

        return postfix.toString().trim();
    }

    // 🔹 Postfix Evaluation
    private static int evaluatePostfix(String postfix) {
        Stack<Integer> stack = new Stack<>();

        for (String token : postfix.split(" ")) {

            // Number
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            }

            // Operator
            else {
                int b = stack.pop();
                int a = stack.pop();

                switch (token.charAt(0)) {
                    case '+':
                        stack.push(a + b);
                        break;

                    case '-':
                        stack.push(a - b);
                        break;

                    case '*':
                        stack.push(a * b);
                        break;

                    case '/':
                        if (b == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        stack.push(a / b);
                        break;
                }
            }
        }

        return stack.pop();
    }

    
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

   
    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}