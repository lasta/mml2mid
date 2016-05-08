package mml2mid;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import gnu.getopt.Getopt;
import mml2mid.parser.ParseException;
import mml2mid.parser.TokenMgrError;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import mml2mid.parser.MmlParser;

public class Main {

    public static void main(String[] args) {
        /* 引数処理 */
//        HashMap<String, String> arguments = getArguments(args);
//        final Path INPUT_FILE_PATH = Paths.get(arguments.get("Input"));
//        final Path OUTPUT_FILE_PATH = Paths.get(arguments.get("Output"));

        load();

        /* MMLファイルをListに変換 */
//        List<String> INPUT_MML = loadFile(INPUT_FILE_PATH);
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
            while (br.readLine() != null) {
                MmlParser mmlParser = new MmlParser(br);
                mmlParser.MMLCommands();
            }
        } catch (TokenMgrError e) {
            System.out.println("字句解析エラー:" + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("構文解析エラー:" + e.getMessage());
            e.printStackTrace();
        } catch (MalformedInputException e) {
            System.out.println("バイナリファイルが入力されました");
            System.out.println("テキストファイルを指定してください");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("存在しないファイルかディレクトリが指定されました");
            System.out.println("正しいファイルを指定してください");
            e.printStackTrace();
            System.exit(1);
        }
        return lines;
    }

    private static void load() {
        String line;
        List<String> lines = Lists.newArrayList();
        String inputStr;
        while(true) {
            inputStr = null;
            System.out.print("> ");
            try {
                inputStr = new BufferedReader(new InputStreamReader(System.in)).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (inputStr.matches("exit" + ".*")) {
                System.out.println("shutdown program");
                break ;
            }
            else {
                try {
                    /* paeserの引数はInputStream型なので,String型から戻す */
                    InputStream is = null;
                    try {
                        is = new ByteArrayInputStream(inputStr.getBytes("utf-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    MmlParser mmlParser = new MmlParser(is);
                    mmlParser.MMLCommands();
                } catch (TokenMgrError ex) {
                    System.out.println("字句解析エラー:" + ex.getMessage());
                } catch (ParseException ex) {
                    System.out.println("構文解析エラー:" + ex.getMessage());
                }
            }
        }
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
