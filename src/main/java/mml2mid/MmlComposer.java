package mml2mid;
import jdk.nashorn.internal.ir.annotations.Ignore;
import mml2mid.parser.*;

/**
 * Created by lasta on 2016/05/09.
 */
public class MmlComposer implements MmlParserVisitor {
    int currentOctave = 4;

    @Override
    public Object visit(SimpleNode node, Object data) {
        // ここには来ない
        return null;
    }

    @Override
    public Object visit(ASTMMLCommands node, Object data) {
        return null;
    }

    @Ignore
    @Override
    public Object visit(ASTSetOctave node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTSetScale node, Object data) {
        char scale = (char)node.jjtGetChild(0).jjtAccept(this, null);
        char accidental = (char)node.jjtGetChild(1).jjtAccept(this, null);
        int length = (int)node.jjtGetChild(2).jjtAccept(this, null);
        String dots = node.jjtGetChild(3).jjtAccept(this, null).toString();
        return new Mml2MidNote(scale, accidental, length, currentOctave, 100);
    }

    @Override
    public Object visit(ASTSetRest node, Object data) {
        int length = (int)node.jjtGetChild(1).jjtAccept(this, null);
        String dots = node.jjtGetChild(2).jjtAccept(this, null).toString();
        return new Mml2MidNote('c', ' ', 0, length, 0);
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
