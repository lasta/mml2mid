package com.jp.navitime.java.GWExcersise16.mml2mid;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import gnu.getopt.Getopt;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /* 引数処理 */
        HashMap<String, String> arguments = getArguments(args);
        final Path INPUT_FILE_PATH = Paths.get(arguments.get("Input"));
        final Path OUTPUT_FILE_PATH = Paths.get(arguments.get("Output"));

        /* MMLファイルをListに変換 */
        final List<String> INPUT_MML = loadFile(INPUT_FILE_PATH);
        INPUT_MML.forEach(System.out::println);

    }

    /**
     * 指定されたファイルの中身を取得する
     *
     * @param path 中身を取得するファイルのパス
     * @return 取得したファイルの中身
     */
    private static List<String> loadFile(Path path) {
        String line;
        List<String> lines = Lists.newArrayList();

        try (BufferedReader br = Files.newBufferedReader(path)) {
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
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
