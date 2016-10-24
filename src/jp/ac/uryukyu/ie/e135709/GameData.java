package jp.ac.uryukyu.ie.e135709;

import java.util.ArrayList;
import java.util.List;

/*
mainクラスでファイルの読み込み,書き込み,またセーブデータに書き込み
GameDataはゲームの一時的な処理に関連したオブジェクト
CsvDataはcsvファイルの処理に関連したオブジェクト
SaveDataはSaveData.txtに関連した処理用オブジェクト,主にレコードモード時に利用


GameDataオブジェクト        (         成績          )
(開発中)・一致数 + 不一致数 → 一致率 + タイム → ランク → メッセージ
(開発終了)・不一致だった文字リスト

csvオブジェクト
・CSVの読み込み
・ファイル名 + 識別番号
・全文字数 → 難易度  ,前回ファイル更新日
  ・これらの要素は先頭に記録
  ・識別番号は初回ファイル読み込み時にランダムに生成され,名前変更時にSaveDataファイルを見失わないようにする。(SaveDataオブジェクトに渡す)
  ・難易度は全文字数から算出する

SaveDataオブジェクト
・識別番号 + ファイル名をディクショナリー型のようなもので保存。
・識別番号に紐付けられた成績(タイム,一致率,ランク)を記録及び閲覧

やりたいこと
・メインメニューからの切り替え機能(ゲーム選択 → ゲームプレイ と SaveDataから見たいレコードの選択 → レコードの閲覧(過去スコア＋ハイスコア)
・時間をロードしてメインメニューで挨拶するとkawaii
・成績アップロード機能

問題
publicクラスが全部warningになってる件←どうやらパッケージ内からしかアクセスできないみたい...謎
*/

/**
 * This project name is JavaTypingGame.
 * Created by e135709 on 2016/10/21.
 */

public class GameData{
    /**
     * matchNum 一致した文字数
     * missMatchNum 一致しなかった文字数
     * matchRate 一致率
     * missInputs 一致しなかった文字(ArrayList)
     *
     */
    private int matchNum, missMatchNum, allCharNum;
    private double matchRate;
    private List<String> missInputs = new ArrayList<>();



    //以下アクセサメソッド
    //セッター
    public void setMatchNum(int _matchNum){
        this.matchNum += _matchNum;
    }
    public void setMissMatchNum(int _missMatchNum){
        this.missMatchNum += _missMatchNum;
    }
    public void setMissInputs(String _input){
        this.missInputs.add(_input);
    }

    //ゲッター
    public int getMatchNum(){
        return this.matchNum;
    }
    public int getMissMatchNum(){
        return this.missMatchNum;
    }
    public double getMatchRate() { return this.matchRate; }
    public List<String> getMissInputs(){
        return this.missInputs;
    }

    //MatchRateを算出するインスタンスメソッド
    public void setMatchRate() {
        this.matchRate = (double) this.matchNum / (double) (this.matchNum + this.missMatchNum);
    }

}