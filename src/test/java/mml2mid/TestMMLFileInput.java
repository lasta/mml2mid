package mml2mid;

import mml2mid.Main;
import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */
public class TestMMLFileInput {
    @Test
    public void main() throws Exception {
        mmlFileInput();
    }

    @Test
    public void mmlFileInput() throws Exception {
        final String INPUT_FILE = "./src/test/java/com/jp/navitime/java/GWExcersise16/mml2mid/test.mml";
        final String[] CmdArgs = {"-i", INPUT_FILE, "-o", "hoge.mid"};
        Main.main(CmdArgs);
    }
}