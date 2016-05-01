package com.jp.navitime.java.GWExcersise16.mml2mid;

import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */
public class MainTest {
    @Test
    public void main() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml", "-o", "hoge.mid"};
        Main.main(CmdArgs);

//        IncorrectArgs1();
//        IncorrectArgs2();
        IncorrectArgs3();
    }

    @Test
    public void IncorrectArgs1() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml", "-o"};
        Main.main(CmdArgs);
    }

    @Test
    public void IncorrectArgs2() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml", "-s", "hoge.mid"};
        Main.main(CmdArgs);
    }

    @Test
    public void IncorrectArgs3() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml"};
        Main.main(CmdArgs);
    }
}