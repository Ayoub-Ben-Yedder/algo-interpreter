import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input: ");
        String input = sc.nextLine();
        Lexer lexer = new Lexer(input);
        Token tokens[] = lexer.tokenize();
        Parser parser = new Parser(tokens, lexer.tokenLength);
        ASTNode ast = parser.parse();
        Interpreter interpreter = new Interpreter(ast);
        lexer.printTokens(tokens);
        ast.printAST();
        System.out.println("Interpreted: "+interpreter.interpret());
    }
}
