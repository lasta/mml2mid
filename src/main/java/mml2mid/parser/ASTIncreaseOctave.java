/* Generated By:JJTree: Do not edit this line. ASTIncreaseOctave.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=mml2mid.expr.BaseNode,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package mml2mid.parser;

public
class ASTIncreaseOctave extends SimpleNode {
  public ASTIncreaseOctave(int id) {
    super(id);
  }

  public ASTIncreaseOctave(MmlParser p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MmlParserVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=10704420c47b883c8e85eded997103c8 (do not edit this line) */
