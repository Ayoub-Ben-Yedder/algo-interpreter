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
            currentChar = '\0'; // Null character indicates end of input
        } else {
            currentChar = input.charAt(readPosition);
        }
        position = readPosition;
        readPosition++;
    }
    public Token nextToken(){
        skipWhitespace();
        Token token;
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
            default:
                token = new Token(TokenType.NUMBER, readNumber());
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
    private boolean isDigit(char c){
        return (c>='0' && c<='9');
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
