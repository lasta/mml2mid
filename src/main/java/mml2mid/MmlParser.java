package mml2mid;
import jdk.nashorn.internal.ir.annotations.Ignore;
import mml2mid.parser.*;

/**
 * Created by lasta on 2016/05/09.
 */
public class MmlParser implements MmlParserVisitor{
    int currentOctave = 4;

    @Override
    public Object visit(SimpleNode node, Object data) {
        // ここには来ない
        return null;
    }

    @Ignore
    @Override
    public Object visit(ASTSetOctave node, Object data) {
        char scale = (char)node.jjtGetChild(0).jjtAccept(this, null);
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
        currentOctave++;
        return null;
    }

    @Override
    public Object visit(ASTDecreaseOctave node, Object data) {
        currentOctave--;
        return null;
    }

    @Override
    public Object visit(ASTLength node, Object data) {
        String value = node.nodeValue;
        return Integer.valueOf(value);
    }

    @Override
    public Object visit(ASTDecimalNumber node, Object data) {
        String value = node.nodeValue;
        return Integer.valueOf(value);
    }
}
