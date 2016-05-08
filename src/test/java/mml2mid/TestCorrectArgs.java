package mml2mid;

import mml2mid.Main;
import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */
public class TestCorrectArgs {
    @Test
    public void main() throws Exception {
        CorrectArgs1();
    }

    @Test
    public void CorrectArgs1() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml", "-o", "hoge.mid"};
        Main.main(CmdArgs);
    }
}