import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */
public class TestIncorrectArgs1 {
    @Test
    public void main() throws Exception {
        IncorrectArgs1();
    }

    @Test
    public void IncorrectArgs1() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml", "-o"};
        Main.main(CmdArgs);
    }
}