/* Generated By:JavaCC: Do not edit this line. ExprParserVisitor.java Version 6.0_1 */
package codezine.expr.parser;

public interface ExprParserVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTStart node, Object data);
  public Object visit(ASTAdd node, Object data);
  public Object visit(ASTSub node, Object data);
  public Object visit(ASTInteger node, Object data);
}
/* JavaCC - OriginalChecksum=fab710f14a5b69c33ab3815962d02df9 (do not edit this line) */