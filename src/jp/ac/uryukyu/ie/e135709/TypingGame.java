package jp.ac.uryukyu.ie.e135709;

import java.util.ArrayList;
import java.util.List;
//import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;

/**
 * This project name is JavaTypingGame.
 * Created by e135709 on 2016/10/21.
 * @version 0.3
 */

public class TypingGame {

    /**
     * メインメソッドです。
     * @param args ,ナニコレ
     */
    public static void main(String[] args) {
        String input;
        //Scanner in = new Scanner(System.in);
        String[] testInputs = {"Java","Python","aaa","HTML","CSS","JavaScript"};

        GameData gameData = new GameData();

        //ファイルが存在するかどうか確認する
        File file = new File("ansTexts.csv");
        //System.out.println(file.getAbsolutePath() +"だよ！");
        if(!checkFile(file)){ return; }

        /*
        //ファイル作成テスト
        File newFile = new File("newFile.txt");
        try{
            newFile.createNewFile();
        }catch(IOException e){
            System.out.println(e);
        }
        */

        //csvファイルを読み込み,ArrayList<String>クラスで保存
        List<String> ansTexts = readCSV(file);

        System.out.println("以下を入力してください");

        int i;

        //入力を回す
        for(i = 0; i < ansTexts.size(); i++){
            System.out.print(ansTexts.get(i) +":");
            input = testInputs[i];
            //input = in.nextLine();
            gameData = comparison(input, ansTexts.get(i), gameData);
        }

        //結果発表
        gameData.setMatchRate();

        System.out.println("一致数:"+ gameData.getMatchNum() +"  不一致数:"+ gameData.getMissMatchNum());
        System.out.println("一致率:"+ gameData.getMatchRate());
        System.out.println("間違えた文字は");
        List<String> x = gameData.getMissInputs();
        for(i = 0; i < (gameData.getMissInputs()).size(); i++){
            System.out.println(x.get(i));
        }
    }

    //Fileが存在するか,ファイル以外ではないか,読み取り可能かを調べboolean型を返すメソッド
    private static boolean checkFile(File file){
        try {
            if (!file.exists()) {
                System.out.println("ファイルが存在しません");
                return false;
            } else if(!file.isFile()){
                System.out.println(file.getName() +"は通常ファイルではありません");
                return false;
            } else if (!file.canRead()) {
                System.out.println("ファイルの読み込みが出来ません");
                return false;
            }
        }catch(SecurityException e) {
            System.out.println("セキュリティによりアクセスを拒否されました\n"+ e.getMessage());
        }

        return true;
    }

    //Fileクラスのcsvファイルを「,」を区切り文字として読み込み,String[]で返す関数
    private static List<String> readCSV(File file){
        List<String> ansTexts = new ArrayList<>();
        try{
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            StringTokenizer token;

            //1行ずつ読み取る
            while ((line = br.readLine()) != null) {
                token = new StringTokenizer(line,",");
                while (token.hasMoreTokens()){
                    ansTexts.add(token.nextToken());
                }
            }
            //開けたら閉じる
            br.close();
        }catch(IOException e){
            System.out.println("何らかのエラーが発生しました\n"+ e.getMessage());
        }
        return ansTexts;
    }

    //一致数と不一致数と不一致だった入力文字を返すメソッド
    private static GameData comparison(String input, String ansText, GameData gameData) {
        int n, i;
        int difValue = (input.length() - ansText.length());
        int matchNum = 0, missMatchNum = 0;

        if(difValue < 0){
            difValue *= -1;
            n = input.length();
        }else{
            n = ansText.length();
        }

        for(i=0; i<n; i++){
            if(input.charAt(i) == ansText.charAt(i)) {
                matchNum += 1;
            }else{
                missMatchNum += 1;
            }
        }

        if(difValue != 0){
            missMatchNum += difValue;
            gameData.setMissInputs(input);
        }

        gameData.setMatchNum(matchNum);
        gameData.setMissMatchNum(missMatchNum);

        return gameData;
    }

    //説明
    private static void Result(List<String> gameData){
        System.out.println("全文字数は");
    }
}