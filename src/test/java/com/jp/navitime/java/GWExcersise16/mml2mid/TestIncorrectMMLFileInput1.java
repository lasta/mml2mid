package com.jp.navitime.java.GWExcersise16.mml2mid;

import org.junit.Test;

/**
 * Created by lasta on 2016/05/01.
 */
public class TestIncorrectMMLFileInput1 {
    @Test
    public void main() throws Exception {
        mmlFileInput();
    }

    @Test
    public void mmlFileInput() throws Exception {
        final String INPUT_FILE = "./src/test/java/com/jp/navitime/java/GWExcersise16/mml2mid/pict.png";
        final String[] CmdArgs = {"-i", INPUT_FILE, "-o", "hoge.mid"};
        Main.main(CmdArgs);
    }
}