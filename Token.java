enum TokenType {
    PLUS, MINUS, MUL, DIVIDE, LPAREN, RPAREN, NUMBER, EOF
}
public class Token {
    public TokenType type;
    public String value;
    public Token(TokenType type, String value){
        this.type = type;
        this.value = value;
    }
    public String toString(){
        return "Token("+type+","+value+")";
    }
}
