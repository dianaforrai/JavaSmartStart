public class Client {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();

        String expr1 = "3 + 5";
        String expr2 = "10 + 2 * 6";
        String expr3 = "100 * 2 + 12";
        String expr4 = "100 * ( 2 + 12 )"; // Parentheses not supported in this simple parser
        String expr5 = "100 * ( 2 + 12 ) / 14";

        Expression expression1 = parser.parseExpression(expr1);
        Expression expression2 = parser.parseExpression(expr2);
        Expression expression3 = parser.parseExpression(expr3);

        System.out.println(expr1 + " = " + expression1.interpret());
        System.out.println(expr2 + " = " + expression2.interpret());
        System.out.println(expr3 + " = " + expression3.interpret());
    }
}
