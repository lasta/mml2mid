package com.jp.navitime.java.GWExcersise16.mml2mid;

import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */
public class TestIncorrectArgs2 {
    @Test
    public void main() throws Exception {
        IncorrectArgs2();
    }
    @Test
    public void IncorrectArgs2() throws Exception {
        final String[] CmdArgs = {"-i", "hoge.mml", "-s", "hoge.mid"};
        Main.main(CmdArgs);
    }
}