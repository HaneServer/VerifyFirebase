# VerifyFirebase
Paperサーバーにログイン時、Firestoreのusersと連動されているか確認するプロジェクト

## 簡単な説明
Paperサーバーにログインしたら、使い方説明の画像のような構成になっているFirestoreで、
すべてのユーザーのMinecraftフィールドに、ログインしたプレイヤーのMCIDが存在したら、
そのフィールドがあるドキュメント(UserID)を取得し、 Name値とLevel値を取得しプレイヤーにメッセージ送信

## 必要要件

- OpenJDK 17
- Maven 3.8.1 (Build時に使用します)
- ※Jarを動かすのみ場合はOpenJDK 17のみで結構です。
- ※HaneServerGitHubでは通常時、Gradleを使用します。GradleでPaperAPIにてFirebase Admin SDKを使用すると、接続できないエラーが出るので今回はMavenを使用しています。

## 使い方

1. [Firebase側] Firebaseに登録し、Authにてメール認証をonにしてください
2. [Firebase側] Firestoreを作成し、下の画像のように設定してください。このプラグインを使うためには緑色の星マークがついている項目は必要です。
3. [Firebase側] FirebaseでAdmin SDKをJavaで生成してください。jsonでダウンロードできるはずです。
4. [Firebase側] ダウンロードしたjsonをサーバーのどこでもいいので設置してください。
5. [Server側] ReleaseからJarファイルをダウンロードして、PaperServerのpluginsフォルダー に設置してください。
6. [Server側] 一度実行すると、エラーが出ますが、config.ymlが生成されるので、そのconfig.ymlのPath値に、Firebaseでダウンロードしたjsonのパスを入力してください。
7. [Server側] config.ymlのURL値にユーザー登録できるWebサイトのURLを書いてください。

![FirestoreSetting](https://cdn.discordapp.com/attachments/1021003444825378847/1021003675465945118/2022-09-18_192226.png)

## Jarビルド方法

```
$ https://github.com/HaneServer/VerifyFirebase
$ cd VerifyFirebase
$ mvn clean package install
```

## 開発者

[@massa_san_](https://twitter.com/massa_san_)

## ライセンス

MIT LICENSE