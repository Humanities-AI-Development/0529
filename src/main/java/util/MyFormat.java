package util;
import java.text.NumberFormat;

//【MyFormatクラス】
//1) 引数に受け取った金額データを「￥付き、３桁カンマ区切り」の形式に変換するインスタンスメソッド「moneyFormat」を定義します。
//
//
//メソッド名 :       moneyFormat
//戻り値   :       String　金額表示文字列(￥XXX,XXX)
//引数    :       int price　価格データ
//処理内容  :       ①JavaAPIに用意されたDecimalFormatクラス、またはNumberFormatクラスを利用して引数に受け取った価格データの形式を変換します。
//②変換する形式は「￥付き、３桁カンマ区切り」とします。

public class MyFormat {
       
        public String moneyFormat(int price) {

               
            NumberFormat curFormat = NumberFormat.getCurrencyInstance(); //通貨
            String update_price = curFormat.format(price);//通貨＆区切り
           
            return update_price;
        }

}