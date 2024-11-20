public class Parser {
    public Token[] tokens;
    public int currentIndex;
    public int tokenLength;

    public Parser(Token[] tokens, int tokenLength) {
        this.tokens = tokens;
        this.currentIndex = 0;
        this.tokenLength = tokenLength;
    }

    private Token currentToken() {
        if (currentIndex < tokenLength) {
            return tokens[currentIndex];
        }
        return new Token(TokenType.EOF, "EOF");
    }

    private void consume(TokenType type) {
        if (currentToken().type == type) {
            currentIndex++;
        } else {
            throw new RuntimeException("Expected token type " + type + " but got " + currentToken().type);
        }
    }

    public ASTNode parse() {
        return parseExpression();
    }

    private ASTNode parseExpression() {
        ASTNode left = parseTerm();
        while (currentToken().type == TokenType.PLUS || currentToken().type == TokenType.MINUS) {
            String operator = currentToken().value;
            consume(currentToken().type);
            ASTNode right = parseTerm();
            left = new ASTNode(operator, left, right);
        }
        return left;
    }

    private ASTNode parseTerm() {
        ASTNode left = parseFactor();
        while (currentToken().type == TokenType.MUL || currentToken().type == TokenType.DIVIDE) {
            String operator = currentToken().value;
            consume(currentToken().type);
            ASTNode right = parseFactor();
            left = new ASTNode(operator, left, right);
        }
        return left;
    }

    private ASTNode parseFactor() {
        Token token = currentToken();
        if (token.type == TokenType.NUMBER) {
            consume(TokenType.NUMBER);
            return new ASTNode(token.value);
        } else if (token.type == TokenType.LPAREN) {
            consume(TokenType.LPAREN);
            ASTNode node = parseExpression();
            consume(TokenType.RPAREN);
            return node;
        } else {
            throw new RuntimeException("Unexpected token: " + token);
        }
    }
}
