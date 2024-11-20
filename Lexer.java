public class Lexer {
    public String input;
    public int position;
    public int readPosition;
    public char currentChar;
    private int maxTokens = 50;
    public int tokenLength;
    public Lexer(String input){
        this.input = input;
        this.position = 0;
        this.readPosition = 0;
        this.currentChar = '\0'; 
        readChar();
    }
    private void readChar(){
        if (readPosition >= input.length()) {
            currentChar = '\0';
        } else {
            currentChar = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition++;
    }
    public Token nextToken(){
        skipWhitespace();
        Token token;
        char nextChar = readPosition < input.length() ? input.charAt(readPosition) : '\0';
        switch (currentChar) {
            case '+':
                token = new Token(TokenType.PLUS, Character.toString(currentChar));
                break;
            case '-':
                token = new Token(TokenType.MINUS, Character.toString(currentChar));
                break;
            case '*':
                token = new Token(TokenType.MUL, Character.toString(currentChar));
                break;
            case '/':
                token = new Token(TokenType.DIVIDE, Character.toString(currentChar));
                break;
            case '(':
                token = new Token(TokenType.LPAREN, Character.toString(currentChar));
                break;
            case ')':
                token = new Token(TokenType.RPAREN, Character.toString(currentChar));
                break;
            case '\0':
                token = new Token(TokenType.EOF, "EOF");
                break;
            case '=':
                token = new Token(TokenType.EQ, Character.toString(currentChar));
                break;
            case '<':
                if(nextChar == '='){
                    token = new Token(TokenType.LQ, "<=");
                    readChar();
                }else if(nextChar == '-'){
                    token = new Token(TokenType.ASSIGN, "<-");
                    readChar();
                }else{
                    token = new Token(TokenType.LT, Character.toString(currentChar));
                }
                break;
            case '>':
                if(nextChar != '='){
                    token = new Token(TokenType.GT, Character.toString(currentChar));
                }else{
                    token = new Token(TokenType.GQ, ">=");
                    readChar();
                }
                break;
            case ';':
                token = new Token(TokenType.SEMI, Character.toString(currentChar));
                break;
            case ':':
                token = new Token(TokenType.COLON, Character.toString(currentChar));
                break;
            default:
                if(isDigit(currentChar)){
                    String num =  readNumber();
                    if(currentChar=='.'){
                        readChar();
                        readChar();
                        String reel = readNumber();
                        token = new Token(TokenType.NUMBER, num+'.'+reel);
                    }else{
                        token = new Token(TokenType.NUMBER, num);
                    }
                }else if(isAlpha(currentChar)){
                    String word = readString();
                    switch (word) {
                        case "algorithme":
                            token = new Token(TokenType.ALGO, word);
                            break;
                        case "TDO":
                            token = new Token(TokenType.TDO, word);
                            break;
                        case "debut":
                            token = new Token(TokenType.DEBUT, word);
                            break;
                        case "fin":
                            token = new Token(TokenType.FIN, word);
                            break;
                        case "tantque":
                            token = new Token(TokenType.TANTQUE, word);
                            break;
                        case "vrai":
                            token = new Token(TokenType.VRAI, word);
                            break;
                        case "faux":
                            token = new Token(TokenType.FAUX, word);
                            break;
                        case "si":
                            token = new Token(TokenType.SI, word);
                            break;
                        case "sinon":
                            token = new Token(TokenType.SINON, word);
                            break;
                        case "finsi":
                            token = new Token(TokenType.FINSI, word);
                            break;
                        case "et":
                            token = new Token(TokenType.ET, word);
                            break;
                        case "ou":
                            token = new Token(TokenType.OU, word);
                            break;
                        case "non":
                            token = new Token(TokenType.NON, word);
                            break;
                        case "entier":
                            token = new Token(TokenType.ENTIER, word);
                            break;
                        case "reel":
                            token = new Token(TokenType.REEL, word);
                            break;
                        case "chaine":
                            token = new Token(TokenType.CHAINE, word);
                            break;
                        case "char":
                            token = new Token(TokenType.CHAR, word);
                            break;
                        case "bool":
                            token = new Token(TokenType.BOOL, word);
                            break;
                        default:
                            token = new Token(TokenType.IDENTIFIER, word);
                            break;
                    }
                }else{
                    token = new Token(TokenType.ILLEGAL, Character.toString(currentChar));
                }
                break;
        }
        readChar();
        return token;
    }
    private void skipWhitespace(){
        while(currentChar==' ' || currentChar=='\t' || currentChar=='\n' || currentChar=='\r'){
            readChar();
        }
    }
    private String readNumber(){
        int startPos = position;
        while(isDigit(currentChar)){
            readChar();
        }
        readPosition--;
        return input.substring(startPos, position);
    }
    private String readString(){
        int startPos = position;
        while(isAlpha(currentChar)||isDigit(currentChar)){
            readChar();
        }
        readPosition--;
        return input.substring(startPos, position);
    }
    private boolean isDigit(char c){
        return (c>='0' && c<='9');
    }
    private boolean isAlpha(char c){
        return ((c>='A' && c<='Z' ) || (c>='a' && c<='z'));
    }
    public Token[] tokenize(){
        Token tokens[] = new Token[maxTokens];
        int i = 0;
        while(currentChar!='\0'){
            tokens[i] = nextToken();
            i++;
        }
        tokenLength = i;
        return tokens;
    }
    public void printTokens(Token[] tokens){
        System.out.println("Lexed Tokens");
        for(int i=0; i <tokenLength; i++){
            System.out.println(tokens[i].toString());
        }
    }
}
