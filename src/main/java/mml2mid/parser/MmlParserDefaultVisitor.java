/* Generated By:JavaCC: Do not edit this line. MmlParserDefaultVisitor.java Version 6.0_1 */
package mml2mid.parser;

public class MmlParserDefaultVisitor implements MmlParserVisitor{
  public Object defaultVisit(SimpleNode node, Object data){
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTMMLCommands node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTSetOctave node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTSetScale node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTSetRest node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTIncreaseOctave node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTDecreaseOctave node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTLength node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTDecimalNumber node, Object data){
    return defaultVisit(node, data);
  }
}
/* JavaCC - OriginalChecksum=7cfe8e3f468908b55da307310a1e42b5 (do not edit this line) */
