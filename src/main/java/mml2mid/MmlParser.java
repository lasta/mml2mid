package mml2mid;
import mml2mid.parser.*;

/**
 * Created by lasta on 2016/05/09.
 */
public class MmlParser implements MmlParserVisitor{
    @Override
    public Object visit(SimpleNode node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSetOctave node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSetScale node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSetRest node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTIncreaseOctave node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDecreaseOctave node, Object data) {
        return null;
    }
}
