import java.util.*;

public class ExpressionParser {

    public Expression parseExpression(String expression) {
        // Tokenize by spaces
        String[] tokens = expression.split(" ");
        Stack<Expression> values = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                values.push(new NumberExpression(Integer.parseInt(token)));
            } else if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
                    Expression right = values.pop();
                    Expression left = values.pop();
                    values.push(applyOperator(operators.pop(), left, right));
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            Expression right = values.pop();
            Expression left = values.pop();
            values.push(applyOperator(operators.pop(), left, right));
        }

        return values.pop();
    }

    private int precedence(String operator) {
        if (operator.equals("*") || operator.equals("/")) return 2;
        else return 1; // + or -
    }

    private Expression applyOperator(String operator, Expression left, Expression right) {
        switch (operator) {
            case "+": return new AdditionExpression(left, right);
            case "-": return new SubtractionExpression(left, right);
            case "*": return new MultiplicationExpression(left, right);
            case "/": return new DivisionExpression(left, right);
            default: throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }
}
