enum NodeType{
    OP, NUMBER
}
public class ASTNode{
    public String value;
    public ASTNode left;
    public ASTNode right;
    public ASTNode(String value, ASTNode left, ASTNode right){
        this.value = value;
        this.left = left;
        this.right = right;
    }
    public ASTNode(String value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
    public String toString(){
        if(left==null && right==null){
            return value;
        }else{
            return "("+left.toString()+" "+value+" "+right.toString()+")";
        }
    }
    public void printAST(){
        System.out.println("AST : "+this.toString());
    }
}