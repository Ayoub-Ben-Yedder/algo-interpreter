public class Interpreter {
    ASTNode ast;

    public Interpreter(ASTNode ast) {
        this.ast = ast;
    }

    public double interpret() {
        return evaluate(ast);
    }

    private double evaluate(ASTNode node) {
        if (node.left == null && node.right == null) {
            return Double.parseDouble(node.value);
        }
        double leftValue = evaluate(node.left);
        double rightValue = evaluate(node.right);
        switch (node.value) {
            case "+":
                return leftValue + rightValue;
            case "-":
                return leftValue - rightValue;
            case "*":
                return leftValue * rightValue;
            case "/":
                if (rightValue == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return leftValue / rightValue;
            default:
                throw new RuntimeException("Unknown operator: " + node.value);
        }
    }
}

