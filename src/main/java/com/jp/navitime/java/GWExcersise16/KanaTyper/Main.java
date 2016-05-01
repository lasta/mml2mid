package com.jp.navitime.java.GWExcersise16.KanaTyper;

import com.google.common.collect.Maps;
import gnu.getopt.Getopt;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        /* 引数処理 */
        HashMap<String, String> arguments = getArguments(args);
        final String INPUT_FILE_NAME = arguments.get("Input");
        final String OUTPUT_FILE_NAME = arguments.get("Output");

        System.out.println(INPUT_FILE_NAME);
        System.out.println(OUTPUT_FILE_NAME);
    }

    private static HashMap<String, String> getArguments(String[] args) {
        HashMap<String, String> fileNames = Maps.newHashMap();
        Getopt options = new Getopt("Main", args, "i:o:");
        int optIterator;
        while ((optIterator = options.getopt()) != -1) {
            switch (optIterator) {
                case 'i':
                    fileNames.put("Input", options.getOptarg());
                    break;
                case 'o':
                    fileNames.put("Output", options.getOptarg());
                    break;
                default:
                    System.out.println("コマンド引数が不正です");
                    System.out.println("Usage: java -jar mml2mid.jar -i <InputMML.mml> -o <OutputMIDI.mid>");
                    System.exit(-1);
            }
        }

        return fileNames;
    }
}
