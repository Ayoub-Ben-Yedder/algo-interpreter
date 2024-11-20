enum TokenType {
    PLUS, MINUS, MUL, DIVIDE, LPAREN, RPAREN, NUMBER,
    EQ, NEQ, GT, GQ, LT, LQ, SEMI, COLON,
    NON, OU, ET,
    EOF, ILLEGAL,
    IDENTIFIER, ASSIGN,
    ALGO, DEBUT, FIN, TDO,
    SI, SINON, FINSI,
    TANTQUE,
    VRAI, FAUX,
    ENTIER, REEL, CHAINE, CHAR, BOOL,
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
