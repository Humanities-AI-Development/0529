package util;
//import bean.*;

//import dao.*;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
//      SendMailTest.javaを参考に
//      書籍の購入情報を【ログインしているユーザのメールアドレスに送る】SendMail.javaを新たに作成する。
//
//      ※SendMail.javaの動作確認をする時は、userinfoテーブルで設定している各ユーザのメールアドレスを、
//      ご自分のメールアドレスに変更して実行すること。
//      （ログインしているユーザのid、パスワードと一緒に、メールアドレスもセッションで管理しているはずなので）
//
//      ※提出時は、SendMailTest.javaを削除してから提出すること。
       
        public String body;
        public String email;
       
        //コンストラクタ(初期設定をおこなう------------------
       
                public SendMail(String body,String email) {
                        this.body = body;
                        this.email = email;
                       

                }
               
                       
        public void second() {//サーバとの設定＆メール送信メソッド
               
                try {
                       
                        Properties props = System.getProperties();
                       

                        // SMTPサーバのアドレスを指定（今回はxserverのSMTPサーバを利用）
                        props.put("mail.smtp.host", "sv5215.xserver.jp");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.starttls.enable", "true");
                        props.put("mail.smtp.port", "587");
                        props.put("mail.smtp.debug", "true");
                       
                       
                        Session session = Session.getInstance(
                                props,
                                new javax.mail.Authenticator() {
                                        protected PasswordAuthentication getPasswordAuthentication() {
                                                //メールサーバにログインするメールアドレスとパスワードを設定
                                                return new PasswordAuthentication("test.sender@kanda-it-school-system.com", "kandaSender202208");
                                        }
                                }
                        );
                       
                       
               
                        MimeMessage mimeMessage = new MimeMessage(session);

                        // 送信元メールアドレスと送信者名を指定
                        mimeMessage.setFrom(new InternetAddress("test.sender@kanda-it-school-system.com", "神田IT School", "iso-2022-jp"));

                        // 送信先メールアドレスを指定（ご自分のメールアドレスに変更）
                        mimeMessage.setRecipients(Message.RecipientType.TO, email);
                       

                        // メールのタイトルを指定
                        mimeMessage.setSubject("ここにタイトル", "iso-2022-jp");

                        // メールの内容を指定
                        mimeMessage.setText(body, "iso-2022-jp");
                       
                       

                        // メールの形式を指定
                        mimeMessage.setHeader("Content-Type", "text/plain; charset=iso-2022-jp");

                        // 送信日付を指定
                        mimeMessage.setSentDate(new Date());

                        // 送信します
                        Transport.send(mimeMessage);

                        // 送信成功
                        System.out.println("送信に成功しました。");

                } catch (Exception e) {
                        // e.printStackTrace();
                        System.out.println("送信に失敗しました。\n" + e);
                }
        }
}