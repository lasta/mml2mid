import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */

/**
 * 出力ファイル名がnullになる場合
 */

public class TestIncorrectArgs3 {

    @Test
    public void main() throws Exception {
        IncorrectArgs3();
    }
    @Test
    public void IncorrectArgs3() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml"};
        Main.main(CmdArgs);
    }
}