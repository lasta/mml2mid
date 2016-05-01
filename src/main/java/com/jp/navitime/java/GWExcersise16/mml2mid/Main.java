package com.jp.navitime.java.GWExcersise16.mml2mid;

import com.google.common.collect.Maps;
import gnu.getopt.Getopt;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        /* 引数処理 */
        HashMap<String, String> arguments = getArguments(args);
        final String INPUT_FILE_NAME = arguments.get("Input");
        final String OUTPUT_FILE_NAME = arguments.get("Output");

//        System.out.println(INPUT_FILE_NAME);
//        System.out.println(OUTPUT_FILE_NAME);
    }

    /**
     * コマンドライン引数を取得する
     *
     * @param args : コマンドライン引数
     * @return : 入力ファイルと出力ファイルのHashMap
     */
    private static HashMap<String, String> getArguments(String[] args) {
        HashMap<String, String> fileNames = Maps.newHashMap();
        Getopt g = new Getopt("Main", args, "i:o:");
        String arg = null;
        int optIterator;
        while ((optIterator = g.getopt()) != -1) {
            switch (optIterator) {
                case 'i':
                    arg = g.getOptarg();
                    if (StringUtils.isNotBlank(arg)) {
                        fileNames.put("Input", arg);
                    } else {
                        optionArgsError();
                    }
                    break;
                case 'o':
                    arg = g.getOptarg();
                    if (StringUtils.isNotBlank(arg)) {
                        fileNames.put("Output", arg);
                    } else {
                        optionArgsError();
                    }
                    break;
                default:
                    optionArgsError();
            }
        }

        if (fileNames.size() != 2)
            optionArgsError();

        return fileNames;
    }

    /**
     * コマンドライン引数が不正の場合、
     * System.exit(-1) で異常終了する
     */
    private static void optionArgsError() {
        System.out.println("コマンド引数が不正です");
        System.out.println("Usage: java -jar mml2mid.jar -i <InputMML.mml> -o <OutputMIDI.mid>");
        System.exit(1);
    }
}
