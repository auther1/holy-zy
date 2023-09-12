package Java;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.*;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class InterfaceController extends Application {

    public User player;
    public int i=1;
    public ArrayList<String>selectedcards=new ArrayList<>();
    private ArrayList<ImageView> selectedCards = new ArrayList<>();
    @FXML
    private ImageView card1, card2, card3, card4, card5, card6, card7, card8, card9, card10,
            card11, card12, card13, card14, card15, card16, card17, card18, card19, card20,cardNo;
    @FXML
    private ImageView card38, card39, card40, card41, card42, card43, card44, card45, card46, card47,
            card48, card49, card50, card51, card52, card53, card54;
    @FXML
    private ImageView
            card21, card22, card23, card24, card25, card26, card27, card28, card29, card30,
            card31, card32, card33, card34, card35, card36, card37;
    @FXML
    private ToggleButton noButton;
    @FXML
    private ToggleButton playButton;
    @FXML
    private Label timeLable;

    @FXML
    private HBox playField;
    @FXML
    private HBox owerField;

    @FXML
    private HBox player3Field;

    @FXML
    private HBox player2Field;

    @FXML
    private HBox play3Field;
    @FXML
    private HBox play2Field;

    @FXML
    private Button startButton;

    @FXML
    private Button callLord1;
    @FXML
    private Button callLord3;
    @FXML
    private ToggleButton callLord2;
    @FXML
    private ToggleButton noCallLord;



    @FXML
    private ImageView  play1Clock;
    @FXML
    private ImageView  play2Clock;
    @FXML
    private ImageView  play3Clock;


    @FXML
    private ImageView player1IsLord;
    @FXML
    private ImageView player2IsLord;
    @FXML
    private ImageView player3IsLord;
    @FXML
    private TextField remainCards3;
    @FXML
    private TextField remainCards2;

    @FXML
    private ImageView cardLord1;

    @FXML
    private ImageView cardLord2;

    @FXML
    private ImageView cardLord3;
    @FXML
    private ImageView bujiao;

    @FXML
    private ImageView famerLose;

    @FXML
    private ImageView famerWin;

    @FXML
    private ImageView lordLose;

    @FXML
    private ImageView lordwin;

    @FXML
    private ImageView oneFen;

    @FXML
    private ImageView threeFen;

    @FXML
    private ImageView twoFen;



    private static int COUNTDOWN_SECONDS = 30; // 定义倒计时的总秒数
    private Timeline timeline; // 定义一个Timeline对象来控制倒计时
    public  ImageView[] imageArray =  new ImageView[19];
    public  ImageView[] imageArray2 =  new ImageView[19];
    public  ImageView[] imageArray3 =  new ImageView[19];
    public  ImageView[] imageArray4;
    CountDownLatch countDownLatch=new CountDownLatch(1);

    public  ImageView[] imageArray5;

    static  ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> player1 = new ArrayList<>();
    private ArrayList<String> player2 = new ArrayList<>();
    private ArrayList<String> player3 = new ArrayList<>();
    private ArrayList<String> lord = new ArrayList<>();
    static HashMap<String, Integer> hm = new HashMap<>();

    static {
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        for (String s : color) {
            //i依此表示每一种花色
            for (String value : number) {
                //j表示每一个数字
                list.add(s + value);//每一张牌由花色和数字组成，一共4*13=52张牌

            }
        }
        list.add("_小王");
        list.add("_大王");
        //指定牌的价值
        //牌上的数字到Map集合中判断是否存在
        //存在，获取价值
        //不存在，本身的数字就是价值
        hm.put("J", 11);
        hm.put("Q", 12);
        hm.put("K", 13);
        hm.put("A", 14);
        hm.put("2", 15);
        hm.put("小王", 16);
        hm.put("大王", 17);


    }

    //洗牌界面
    public void initialize() {
        // 将FXML文件中的Image变量存储在数组中
        imageArray = new ImageView[]{card1, card2, card3, card4, card5, card6, card7, card8, card9, card10,
                card11, card12, card13, card14, card15, card16, card17,null,null,null};

        imageArray2 = new ImageView[]{card21, card22, card23, card24, card25, card26, card27, card28, card29, card30,
                card31, card32, card33, card34, card35, card36, card37,null,null,null};

        imageArray3 = new ImageView[]{card38, card39, card40, card41, card42, card43, card44, card45, card46, card47,
                card48, card49, card50, card51, card52, card53, card54,null,null,null};
        imageArray4 = new ImageView[]{card18,card19,card20};

        imageArray5 = new ImageView[]{cardLord1,cardLord2,cardLord3};

        //judgeLandOwer("player1IsLord");
        owerField.setSpacing(-50);
        player2Field.setSpacing(-10);
        player3Field.setSpacing(-10);
//        remainCardNumber(remainCards2,player2);
//        remainCardNumber(remainCards3,player3);
//        player2Play(player2);
//        player3Play(player3);

//        animateShuffling(imageArray,player.handCards,0);

//        judgeLandOwer();

        callLord1.setVisible(false);
        callLord3.setVisible(false);
        callLord2.setVisible(false);
        noCallLord.setVisible(false);
    }


    //准备牌
    //运用静态代码块，随着类的加载而加载，而且只执行一次
    //牌库
    Thread backmusicThread;
    public InterfaceController() {

        player=new User();
        // 创建并启动新线程
        backmusicThread = new Thread(() -> MusicPlayer.playmusicforever("../music/background.wav"));
        backmusicThread.start();
        //看牌


    }

    public void lookPoker(String name, ArrayList<String> list) {
        System.out.print(name + ": ");
        for (String poker : list) {
            System.out.print(poker + " ");
        }
        System.out.println();
    }

    //利用牌的价值进行排序
    //参数：集合
    public void order(ArrayList<String> list) {
        Collections.sort(list, new Comparator<String>() {
            //Array.sort(插入排序+二分查找）
            @Override
            public int compare(String o1, String o2) {
                //o1:表示当前要插入到有序序列的牌
                //o2:表示已经在有序序列中存在的牌

                //负数：o1小 插入到后面
                //正数：o1大 插入到后面
                //0：o1的数字跟o2的数字是一样的需要按花色再次排序

                //1.计算o1的花色和价值
                String color1 = o1.substring(0, 1);
                int value1 = getValue(o1);

                //2.计算o2的花色和价值
                String color2 = o1.substring(0, 1);
                int value2 = getValue(o2);

                //3.比较o1和o2的价值
                int i = value2 - value1;

                return i == 0 ? color1.compareTo(color2) : i;
            }
        });

    }

    //计算牌的价值
    //参数：牌
    //返回值：价值
    public int getValue(String poker) {
        String number = poker.substring(1);
        //拿着数字到map集合中判断是否存在
        //存在，获取价值
        //不存在，类型转换
        if (hm.containsKey(number)) {
            return hm.get(number);
        } else {
            return Integer.parseInt(number);
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Fxml/Interface.fxml"));
        primaryStage.setTitle("欢乐斗地主");
        primaryStage.setScene(new Scene(root, 1000, 650));
//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void handleCardClick(MouseEvent mouseEvent) {
        ImageView card = (ImageView) mouseEvent.getSource(); // 获取点击的牌
        if (selectedCards.contains(card)) {
            selectedCards.remove(card);
            // 取消选中状态
            card.setStyle("-fx-translate-y: -0;");
            int cardnumber=Integer.parseInt(card.getId().substring(4));
            String cardstring=player.handCards.get(cardnumber-1);
            player.pokeGroup.remove(cardstring);
            player.pokeGroup.isLegal();
            System.out.println(player.pokeGroup.getpokegroup());
        } else {
            selectedCards.add(card);
            // 选中状态
            card.setStyle("-fx-translate-y: -20;");
            System.out.println(card.getId());
            selectedcards.add(card.getId());
            int cardnumber=Integer.parseInt(card.getId().substring(4));
            String cardstring=player.handCards.get(cardnumber-1);
            player.pokeGroup.add(cardstring);
            player.pokeGroup.isLegal();

            System.out.println(player.pokeGroup.getpokegroup());
        }
    }

    //点击出牌按钮
    public void handlePlayButtonClick(MouseEvent mouseEvent) {
        System.out.println(player.pokeGroup.getpokegroup()+player.pokeGroup.value);

        if(player.pokeGroup.isLegal()){
            System.out.println("pokegroup"+player.pokeGroup.getpokegroup());
            System.out.println("last"+player.LastpokeGroup.getpokegroup());
            System.out.println("lastlast"+player.LastLastpokeGroup.getpokegroup());
            if(player.pokeGroup.compareTo(player.LastpokeGroup)==1||(player.LastpokeGroup.type.equals("不出")&&player.LastLastpokeGroup.type.equals("不出"))

                    ||(player.LastpokeGroup.type.equals("不出")&&player.pokeGroup.compareTo(player.LastLastpokeGroup)==1)) {
                if(player.number==player.pokeGroup.pokegroup.size()){
                    player.sendDataToServer("victory#" + player.playerID + "#" + player.pokeGroup.getpokegroup() + "#" + player.pokeGroup.getCountNumber() + "$");
                    System.out.println("玩家"+player.playerID+"victory");
                    if(player.playerID==player.lordID){

                        Platform.runLater(() -> {
                            playField.getChildren().clear();
                            playField.getChildren().add(lordwin);
                            lordwin.setVisible(true);
                        });


                    }
                    else{

                        Platform.runLater(() -> {
                            playField.getChildren().clear();
                            playField.getChildren().add(famerWin);
                            famerWin.setVisible(true);
                        });
                        System.out.println("农民赢了罗欣也是傻逼");
                    }
                    Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/"+player.pokeGroup.basicType+".wav"));
                    musicThread.start();
                    backmusicThread.interrupt();
                    Thread fineshmusicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/win.wav"));
                    fineshmusicThread.start();

                }
                else{
                player.sendDataToServer("playcard#" + player.playerID + "#" + player.pokeGroup.getpokegroup() + "#" + player.pokeGroup.getCountNumber() + "$");
                System.out.println("已发送");
                System.out.println("playcard#" + player.playerID + "#" + player.pokeGroup.getpokegroup() + "#" + player.pokeGroup.getCountNumber() + "$");
                }

                player.number=player.number-player.pokeGroup.pokegroup.size();
                if(player.number==1){
                Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/就剩一张牌啦.wav"));
                musicThread.start();
                }
                else{
                    Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/"+player.pokeGroup.basicType+".wav"));
                    musicThread.start();
                }
                player.pokeGroup.removeAll();
                buttonHidden();
                playField.getChildren().clear();
                for (int i = 0; i < selectedCards.size(); i++) {
                    ImageView card = selectedCards.get(i);
                    card.setFitWidth(80);
                    card.setFitHeight(80);
                    playField.setSpacing(-20);
                    playField.getChildren().addAll(card);
                }
                selectedCards.clear();
                System.out.println("牌组已清空");
                System.out.println("pokegroup"+player.pokeGroup.getpokegroup());
                System.out.println("last"+player.LastpokeGroup.getpokegroup());
                System.out.println("last"+player.LastLastpokeGroup.getpokegroup());
                countDownLatch.countDown();
            }
            else{
                System.out.println("违规出牌");
            }

        }
            else{
                System.out.println("违规出牌");
            }


        System.out.println("点击出牌");

    }

    //点击取消按钮
    public void handleNoButtonClick(MouseEvent mouseEvent) {

        if(player.LastpokeGroup.type.equals("不出")&&player.LastLastpokeGroup.type.equals("不出")){
            System.out.println("不可不出，请出牌");


        }
        else{
            player.sendDataToServer("playcard#"+player.playerID+"#不出# #0$");
            System.out.println("playcard#"+player.playerID+"#不出# #0$");
            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/不出.wav"));
            musicThread.start();
            countDownLatch.countDown();
            playField.getChildren().addAll(cardNo);
            cardNo.setVisible(true);
            buttonHidden();
            TimeOut();
        }
        System.out.println("点击不出");



    }

    private void TimeOut() {
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), event -> {
            cardNo.setVisible(false);
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private void buttonShow(){
//        noButton.toFront();
//        playButton.toFront();
        noButton.setVisible(true);
        playButton.setVisible(true);
    }

    private void buttonHidden(){
//        noButton.toBack();
//        playButton.toBack();
        noButton.setVisible(false);
        playButton.setVisible(false);
    }

    //洗牌动画
    private void animateShuffling(ImageView[] imageArray,  ArrayList<String> player,int temp) {
        int delay = 150; // 延迟1秒显示一张牌
        int duration = 5000; // 动画播放时间为0.5秒
        Timeline timeline = new Timeline();
        if (temp == 0) {
            for (int i = 0; i <player.size(); i++) {
                String imagePath = String.format("image/%s.png", player.get(i));
                System.out.println(imagePath);
                Image image = new Image(imagePath);
                KeyValue keyValue = new KeyValue(imageArray[i].imageProperty(), image);
                KeyFrame keyFrame = new KeyFrame(Duration.millis((i + 1) * delay), keyValue);
                timeline.getKeyFrames().add(keyFrame);

            }

        }
        else{
            for (int i = 0; i <player.size(); i++) {
                String imagePath = "image/rear.png";
                Image image = new Image(imagePath);
                KeyValue keyValue = new KeyValue(imageArray[i].imageProperty(), image);
                KeyFrame keyFrame = new KeyFrame(Duration.millis((i + 1) * delay), keyValue);
                timeline.getKeyFrames().add(keyFrame);
            }

        }
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        timeline.play();
    }

    //三张牌的显示
    private void  showThreeCard(){
        for (int i = 0 ; i<=2;i++){
            String imagePath = String.format("image/%s.png", lord.get(i));
            Image image = new Image(imagePath);
            imageArray5[i].setImage(image);

        }
    }

    private void addThreeCard(ImageView[] addImageArray,ArrayList<String> player){
        int index = 0;
        for (int i = 17;i <20;i++){
            System.out.println(addImageArray.length);
            addImageArray[i] = imageArray4[index];
            player.add(i, lord.get(index));
            index++;
            System.out.println(addImageArray[i].getId());
        }
        lookPoker("玩家2",player);
        order(player);
        lookPoker("玩家2",player);
    }

    private void showCard(ArrayList<String> player, int i, HBox playField) {
        String imagePath = String.format("image/%s.png", player.get(i));
        Image image = new Image(imagePath);
        ImageView card = new ImageView();
        card.setImage(image);
        card.setFitWidth(80);
        card.setFitHeight(70);
        card.setRotate(270);
        playField.setSpacing(-50);
        playField.getChildren().addAll(card);
    }

    //判断谁是地主
    private void  judgeLandOwer(String player){
        if(player.equals("player1IsLord")){
            player1IsLord.setVisible(true);
            showThreeCard();
            addThreeCard(imageArray,player1);
        }else if(player.equals("player2IsLord")){
            player2IsLord.setVisible(true);
            showThreeCard();
            addThreeCard(imageArray2,player2);
        }else {
            player3IsLord.setVisible(true);
            showThreeCard();
            addThreeCard(imageArray3,player3);
        }

    }

    //用户2从服务器获取牌打牌
    private void  player2Play(ArrayList<String> player2Data) {
        playCard(player2Data, play2Field);
    }
    //用户3从服务器获取牌打牌
    private void player3Play(ArrayList<String> player3Data) {
        playCard(player3Data, play3Field);
    }

    private void playCard(ArrayList<String> playerData, HBox playField) {
        playField.getChildren().clear();
        System.out.println(playerData.size());
        for (int i = 0; i < playerData.size(); i++) {
            showCard(playerData, i, playField);

        }


    }

    //叫1分按钮
    public void CallLord1(MouseEvent mouseEvent) {
        player.clickedOneScore();
        System.out.println("1分");
        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/1分.wav"));
        musicThread.start();

        playField.getChildren().add(oneFen);
        oneFen.setVisible(true);
        countDownLatch.countDown();
        callLord1.setVisible(false);
        callLord3.setVisible(false);
        callLord2.setVisible(false);
        noCallLord.setVisible(false);
    }
    //叫2分按钮
    public void CallLord2(MouseEvent mouseEvent) {
        player.clickedTwoScore();
        System.out.println("2分");
        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/2分.wav"));
        musicThread.start();
        playField.getChildren().add(twoFen);
        twoFen.setVisible(true);
        countDownLatch.countDown();
        callLord1.setVisible(false);
        callLord3.setVisible(false);
        callLord2.setVisible(false);
        noCallLord.setVisible(false);
    }
    //叫3分按钮
    public void CallLord3(MouseEvent mouseEvent) {
        player.clickedThreeScore();
        System.out.println("3分");
        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/3分.wav"));
        musicThread.start();
        playField.getChildren().add(threeFen);
        threeFen.setVisible(true);
        countDownLatch.countDown();
        callLord1.setVisible(false);
        callLord3.setVisible(false);
        callLord2.setVisible(false);
        noCallLord.setVisible(false);

    }
    //不抢按钮
    public void NoCallLord(MouseEvent mouseEvent) {
        player.clickedZeroScore();
        System.out.println("不抢");
        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/不叫.wav"));
        musicThread.start();
        playField.getChildren().add(bujiao);
        bujiao.setVisible(true);
        countDownLatch.countDown();
        callLord1.setVisible(false);
        callLord3.setVisible(false);
        callLord2.setVisible(false);
        noCallLord.setVisible(false);
    }

    //倒计时显示
    public void remainCardNumber(TextField remainCards,int index){
        String number = String.valueOf(index);
        remainCards.setText("     "+number);
    }

    public void qiangdizhu() throws InterruptedException {
        Thread music = new Thread(() -> MusicPlayer.playmusiconce("../music/洗牌.wav"));
        music.start();
        while (i <= 3) {
            if (player.playerID != i) {
                if((player.playerID-1-i)%3==0){
                    System.out.println("左侧玩家叫地主");
                    Platform.runLater(() -> {
                        play3Clock.setVisible(true);
                    });}
                else if((player.playerID+1-i)%3==0){
                    System.out.println("右侧玩家叫地主");
                    Platform.runLater(() -> {
                        play2Clock.setVisible(true);
                    });}

                String b = player.receiveDataFromServer(); // 接收消息
                // 显示
                // 按钮显示
                if (b.endsWith("$")) {
                    b = b.substring(0, b.length() - 1);
                }
                ArrayList<String> datalist = new ArrayList<>();
                String[] splitArray = b.split("#");
                Collections.addAll(datalist, splitArray);
                String score=datalist.get(1);
                System.out.println("玩家"+i+score+"分");
                if((player.playerID-1-i)%3==0){
                    System.out.println("左侧玩家叫地主");

                    Platform.runLater(() -> {
                        if(score.equals("1")){
                            play3Field.getChildren().add(oneFen);
                            oneFen.setVisible(true);
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/1分.wav"));
                            musicThread.start();
                        }
                        else if(score.equals("2")){
                            play3Field.getChildren().add(twoFen);
                            twoFen.setVisible(true);
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/2分.wav"));
                            musicThread.start();
                        }
                        else if(score.equals("3")){
                            play3Field.getChildren().add(threeFen);
                            threeFen.setVisible(true);
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/3分.wav"));
                            musicThread.start();
                        }
                        else if(score.equals("0")){
                            play3Field.getChildren().add(bujiao);
                            bujiao.setVisible(true);
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/不叫.wav"));
                            musicThread.start();
                        }

                    });}
                else if((player.playerID+1-i)%3==0){
                    System.out.println("右侧玩家叫地主");

                Platform.runLater(() -> {
                    if(score.equals("1")){
                        play2Field.getChildren().add(oneFen);
                        oneFen.setVisible(true);
                        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/1分.wav"));
                        musicThread.start();
                    }
                    else if(score.equals("2")){
                        play2Field.getChildren().add(twoFen);
                        twoFen.setVisible(true);
                        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/2分.wav"));
                        musicThread.start();
                    }
                    else if(score.equals("3")){
                        play2Field.getChildren().add(threeFen);
                        threeFen.setVisible(true);
                        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/3分.wav"));
                        musicThread.start();
                    }
                    else if(score.equals("0")){
                        play2Field.getChildren().add(bujiao);
                        bujiao.setVisible(true);
                        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/不叫.wav"));
                        musicThread.start();
                    }

                });}
                Platform.runLater(() -> {
                    play2Clock.setVisible(false);
                    play3Clock.setVisible(false);
                });

            } else if (player.playerID == i) {
                System.out.println("轮到我了");
                // 按钮显示
                Platform.runLater(() -> {
                    play1Clock.setVisible(true);
                });
                callLord1.setVisible(true);
                callLord3.setVisible(true);
                callLord2.setVisible(true);
                noCallLord.setVisible(true);
                countDownLatch.await();
                Platform.runLater(() -> {
                    play1Clock.setVisible(false);
                });
                System.out.println("回复运行");

            }
            i++;
        }
        System.out.println("抢地主线程结束");
        player.sendDataToServer("calllandlordfinished");
        //获取地主索引
        String b= player.receiveDataFromServer();
        //player.receiveCardsFromServer();
        if (b.endsWith("$")) {
            b = b.substring(0, b.length() - 1);
        }
        System.out.println(b);


        ArrayList<String> blist = new ArrayList<>();
        String[] bsplitArray = b.split("#");
        Collections.addAll(blist, bsplitArray);
        player.lordID=Integer.parseInt(blist.get(1));

        i=player.lordID;
        String[] lordcard=blist.get(2).split(" ");
        Collections.addAll(lord,lordcard);
        lookPoker("底牌", lord);
        showThreeCard();
        if(player.playerID==player.lordID){
            player.number=20;
            player1IsLord.setVisible(true);
            addThreeCard(imageArray,player.handCards);
            for (int t = 0;t < 3 ;t++) {
                imageArray4[t].setVisible(true);
            }
            System.out.println(player.handCards.size());
            animateShuffling(imageArray,player.handCards,0);
        }
        else if((player.playerID-1-player.lordID)%3==0){
            player3IsLord.setVisible(true);
            player.numberleft=20;
        }
        else if((player.playerID+1-player.lordID)%3==0){
            player2IsLord.setVisible(true);
            player.numberright=20;
        }
        i=player.lordID;
        System.out.println("i已重置为"+i);


        Thread playgameThread = new Thread(() -> {
            try {
                playgame();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        playgameThread.start();
    }
    private void playgame() throws InterruptedException{
        Platform.runLater(() -> {
            playField.getChildren().clear();
            play2Field.getChildren().clear();
            play3Field.getChildren().clear();
        });
        System.out.println("开始游戏！");

        player.LastpokeGroup.isLegal();
        player.LastLastpokeGroup.isLegal();
        while(true){
            System.out.println("当前玩家"+i);
            if(i==player.playerID){

                Platform.runLater(() -> {
                    playField.getChildren().clear();
                    play1Clock.setVisible(true);
                });
                buttonShow();
                countDownLatch=new CountDownLatch(1);
                countDownLatch.await();
                buttonHidden();
                Platform.runLater(() -> {
                    play1Clock.setVisible(false);
                });



            }
            else{
                if((player.playerID-1-i)%3==0){
                    System.out.println("左侧玩家出牌");
                    Platform.runLater(() -> {
                        play3Field.getChildren().clear();
                        play3Clock.setVisible(true);
                    });}
                else if((player.playerID+1-i)%3==0){
                    System.out.println("右侧玩家出牌");
                    Platform.runLater(() -> {
                        play2Field.getChildren().clear();
                        play2Clock.setVisible(true);
                    });}

                String b= player.receiveDataFromServer();
                //player.receiveCardsFromServer();
                System.out.println(b);
                if (b.endsWith("$")) {
                    b = b.substring(0, b.length() - 1);
                }
                ArrayList<String> bdatalist = new ArrayList<>();
                String[] splitArray = b.split("#");
                Collections.addAll(bdatalist, splitArray);
                System.out.println("需播放"+bdatalist.get(2));
                Thread musicthread = new Thread(() -> MusicPlayer.playmusiconce("../music/"+bdatalist.get(2)+".wav"));
                musicthread.start();
                if(bdatalist.get(0).equals("playcard")){
                    if((player.playerID-1-i)%3==0){
                        ArrayList<String>cards=new ArrayList<>();//牌arraylist
                        Collections.addAll(cards,bdatalist.get(3).split(" "));
                        int cardnumber=cards.size();//牌的数量
                        //左侧显示
                        player.numberleft-=cardnumber;
                        if(player.numberleft==1){
                            Thread thread = new Thread(() -> MusicPlayer.playmusiconce("../music/就剩一张牌啦.wav"));
                            thread.start();
                        }
                        Platform.runLater(() -> {
                            remainCardNumber(remainCards3, player.numberleft);
                        });

                        if(bdatalist.get(2).equals("不出")){
                            Platform.runLater(() -> {

                                play3Field.getChildren().add(cardNo);
                                cardNo.setVisible(true);
                                play3Clock.setVisible(false);
                            });
                        }
                        else{
                        Platform.runLater(() -> {

                            playCard(cards, play3Field);
                            play3Clock.setVisible(false);
                        });}



                    }
                    else if((player.playerID+1-i)%3==0){

                        ArrayList<String>cards=new ArrayList<>();//牌arraylist
                        Collections.addAll(cards,bdatalist.get(3).split(" "));
                        int cardnumber=cards.size();//牌的数量
                        //右侧显示
//                        收到消息，牌的数量是上面这个cardnumber
                        player.numberright-=cardnumber;
                        if(player.numberright==1){
                            Thread thread = new Thread(() -> MusicPlayer.playmusiconce("../music/就剩一张牌啦.wav"));
                            thread.start();
                        }
                        Platform.runLater(() -> {
                            remainCardNumber(remainCards2, player.numberright);
                        });

                        if(bdatalist.get(2).equals("不出")){
//                            右侧显示不出牌
                            Platform.runLater(() -> {
                                play2Field.getChildren().clear();
                                play2Field.getChildren().add(cardNo);
                                cardNo.setVisible(true);
                                play2Clock.setVisible(false);
                            });
                        }
                        else{
                        Platform.runLater(() -> {
                            play2Field.getChildren().clear();
                            playCard(cards, play2Field);
                            play2Clock.setVisible(false);
                        });}


                }
                    player.LastLastpokeGroup.removeAll();
                    player.LastLastpokeGroup.pokegroup.addAll(player.LastpokeGroup.pokegroup);
                    player.LastLastpokeGroup.isLegal();
                    player.LastpokeGroup.removeAll();
                    player.LastpokeGroup.addPro(bdatalist.get(3));
                    player.LastpokeGroup.isLegal();
                    System.out.println("pokegroup"+player.pokeGroup.getpokegroup());
                    System.out.println("last"+player.LastpokeGroup.getpokegroup());
                    System.out.println("last"+player.LastLastpokeGroup.getpokegroup());

            }
                if(bdatalist.get(0).equals("victory")){
                    if((player.playerID-1-i)%3==0){
                        ArrayList<String>cards=new ArrayList<>();//牌arraylist
                        Collections.addAll(cards,bdatalist.get(3).split(" "));
                        int cardnumber=cards.size();//牌的数量
                        //左侧显示
                        player.numberleft-=cardnumber;
                        Platform.runLater(() -> {
                            remainCardNumber(remainCards3, player.numberleft);
                        });
                        Platform.runLater(() -> {
                            playCard(cards, play3Field);
                        });



                    }
                    else if((player.playerID+1-i)%3==0){
                        ArrayList<String>cards=new ArrayList<>();//牌arraylist
                        Collections.addAll(cards,bdatalist.get(3).split(" "));
                        int cardnumber=cards.size();//牌的数量
                        //右侧显示
                        player.numberright-=cardnumber;
                        Platform.runLater(() -> {
                            remainCardNumber(remainCards2, player.numberright);
                        });
                        Platform.runLater(() -> {
                            playCard(cards, play2Field);
                        });


                    }
                    if(bdatalist.get(1).equals("true")){
                        if(player.lordID==player.playerID){
                            System.out.println("地主获胜");
                            System.out.println("我赢了");
                            Platform.runLater(() -> {
                                playField.getChildren().clear();
                                playField.getChildren().add(lordwin);
                                lordwin.setVisible(true);
                            });
                            backmusicThread.interrupt();
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/win.wav"));
                            musicThread.start();

                        }
                        else{
                            System.out.println("地主获胜");
                            System.out.println("我输了");
                            Platform.runLater(() -> {
                                playField.getChildren().clear();
                                playField.getChildren().add(famerLose);
                                famerLose.setVisible(true);
                            });
                            backmusicThread.interrupt();
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/lose.wav"));
                            musicThread.start();

                        }

                    }
                    else if(bdatalist.get(1).equals("false")){
                        if(player.lordID==player.playerID){
                            System.out.println("农民获胜");
                            System.out.println("我输了");
                            Platform.runLater(() -> {
                                playField.getChildren().clear();
                                playField.getChildren().add(lordLose);
                                lordLose.setVisible(true);
                            });
                            backmusicThread.interrupt();
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/lose.wav"));
                            musicThread.start();

                        }
                        else{
                            System.out.println("农民获胜");
                            System.out.println("我赢了");
                            Platform.runLater(() -> {
                                playField.getChildren().clear();
                                playField.getChildren().add(famerWin);
                                famerWin.setVisible(true);
                            });
                            backmusicThread.interrupt();
                            Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/win.wav"));
                            musicThread.start();

                        }
                    }

                break;
                }

        }
            i++;
            if(i==4){
                i=i-3;
            }
            System.out.println(i);
        }
    }

    public void startGame(MouseEvent mouseEvent) throws InterruptedException {
        Thread musicThread = new Thread(() -> MusicPlayer.playmusiconce("../music/嘚.wav"));
        musicThread.start();
        startButton.setVisible(false);
        player.clickedStartGame();
        String a= player.receiveDataFromServer();
        //player.receiveCardsFromServer();
        if (a.endsWith("$")) {
            a = a.substring(0, a.length() - 1);
        }
        System.out.println(a);//接收到的消息
        ArrayList<String> datalist = new ArrayList<>();
        String[] splitArray = a.split("#");
        Collections.addAll(datalist, splitArray);
        player.playerID=Integer.parseInt(datalist.get(1));
        player.receiveCardsFromServer(datalist.get(2));
        for (String str : player.handCards) {
            System.out.println(str);
        }
        animateShuffling(imageArray,player.handCards,0);
        System.out.println("刷新");
        lookPoker("玩家1", player.handCards);
        System.out.println("显示");

        System.out.println("我的id"+player.playerID);
        Thread qiangdizhuThread = new Thread(() -> {
            try {
                qiangdizhu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        qiangdizhuThread.start();




    }

}

