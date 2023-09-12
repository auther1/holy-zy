package Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;


public class NonStaticRoom {
    ArrayList<Socket> array;
    static ArrayList<Player> playerArray;

    //收到牌的次数
    static public int times=0;

    int start;
    static private BufferedWriter dos1;
    static private BufferedWriter dos2;
    static private BufferedWriter dos3;

    //当前叫地主分的最高分
    static private int maxScore=-1;
    //当前叫最高分玩家
    static private int playerOfMaxScore=-1;

    //三个人以及地主牌
    static ArrayList<String> lordCard = new ArrayList<>();
    static ArrayList<String> player1Card = new ArrayList<>();

    static ArrayList<String> player2Card = new ArrayList<>();
    static ArrayList<String> player3Card = new ArrayList<>();
    static ArrayList<String> list = new ArrayList<>();//代表一副牌
    static HashMap<String, Integer> hm = new HashMap<>();//将J~王映射成数字

    static {
        //准备牌
        //运用静态代码块，随着类的加载而加载，而且只执行一次
        //牌库
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        for (int i = 0; i < color.length; i++) {
            //i依此表示每一种花色
            for (int j = 0; j < number.length; j++) {
                //j表示每一个数字
                list.add(color[i] + number[j]);//每一张牌由花色和数字组成，一共4*13=52张牌
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

    public NonStaticRoom(){}

    //创建房间时，将三个玩家加进来
    public void addPlayer(ArrayList<Player> players){
        playerArray=players;
        dos1=playerArray.get(0).writer;
        dos2=playerArray.get(1).writer;
        dos3=playerArray.get(2).writer;
    }

    public NonStaticRoom(ArrayList<Player> playerArray) {
        array=new ArrayList<>();
        NonStaticRoom.playerArray =playerArray;
        for (int i = 0; i < 3; i++) {
            this.array.add(playerArray.get(i).getSocket());
        }
        try {
            dos1=playerArray.get(0).writer;
            dos2=playerArray.get(1).writer;
            dos3=playerArray.get(2).writer;
            //实例化输出流
//            dos1=new BufferedWriter(new OutputStreamWriter(playerArray.get(0).getSocket().getOutputStream()));
//            dos2=new BufferedWriter(new OutputStreamWriter(playerArray.get(1).getSocket().getOutputStream()));
//            dos3=new BufferedWriter(new OutputStreamWriter(playerArray.get(2).getSocket().getOutputStream()));
//            dis1=new BufferedReader(new InputStreamReader(playerArray.get(0).getSocket().getInputStream()));
//            dis2=new BufferedReader(new InputStreamReader(playerArray.get(1).getSocket().getInputStream()));
//            dis3=new BufferedReader(new InputStreamReader(playerArray.get(2).getSocket().getInputStream()));

            System.out.println("房间创建");
        } catch (Exception e) {
            System.out.println("开始游戏信息发送失败");
        }

    }
    
//    @Override
//    public void run() {
//        distributePoker();
//        selectLord();
//        sendPokerToAll();
//        //游戏结束的函数
//        closeIOStream();
//    }


    //发牌给房间中的三位用户
    public static void distributePoker() {
        //洗牌
        Collections.shuffle(list);
        //发牌
        //遍历牌库得到每一张牌
        for (int i = 0; i < list.size(); i++) {
            //i为索引
            String poker = list.get(i);
            if (i <= 2) {
                lordCard.add(poker);
                continue;
            }
            //给三个玩家轮流发牌
            if (i % 3 == 0) {
                player1Card.add(poker);
            } else if (i % 3 == 1) {
                player2Card.add(poker);
            } else {
                player3Card.add(poker);
            }
        }
        //给每个玩家的牌进行排序
        //order(lord);
        order(player1Card);
        order(player2Card);
        order(player3Card);

        //distribute#[card]$
        //startGame#索引#牌$
        try {
            String p1Poker="startGame#"+"1#"+player1Card.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ") + "$";
            String p2Poker="startGame#"+"2#"+player2Card.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ") + "$";
            String p3Poker="startGame#"+"3#"+player3Card.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ") + "$";
            dos1.write(p1Poker);
            dos1.newLine();
            dos1.flush();
            dos2.write(p2Poker);
            dos2.newLine();
            dos2.flush();
            dos3.write(p3Poker);
            dos3.newLine();
            dos3.flush();
            System.out.println(p1Poker);
            System.out.println(p2Poker);
            System.out.println(p3Poker);
        } catch (IOException e) {
            System.out.println("初始发牌失败");
            throw new RuntimeException(e);
        }
    }


    //给牌排序
    //利用牌的价值进行排序
    //参数：集合
    public static void order(ArrayList<String> list) {
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
                String color2 = o2.substring(0, 1);
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
    public static int getValue(String poker) {
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

    public static void sendToOthers(int playerIndex,String message){
        try {
            playerArray.get(playerIndex%3).writer.write(message);
            playerArray.get(playerIndex%3).writer.newLine();
            playerArray.get(playerIndex%3).writer.flush();
            playerArray.get((playerIndex+1)%3).writer.write(message);
            playerArray.get((playerIndex+1)%3).writer.newLine();
            playerArray.get((playerIndex+1)%3).writer.flush();
            System.out.println("发送"+message);
        } catch (IOException e) {
            System.out.println("向另外两人发消息异常");
            throw new RuntimeException(e);
        }
    }

    public static void checkScore(String type){
        if(type.equals("炸弹")){
            maxScore*=2;
        }
        if(type.equals("火箭")){
            maxScore*=4;
        }
    }

    //向所有人发回地主信息
    //landlord#玩家序号#地主牌$
    public static String getLandlord(){
        return "landlord#"+playerOfMaxScore+"#"+lordCard.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ");
    }
    //抢地主
    /*
     * 客户端发送该用户所抢的分值：1分2分3分
     * 创建三个变量储存这三个分值并比较大小选出最大的分值
     * 接受格式 callLandlord#[int]$
     * 分值最大者是地主并将地主牌发给地主
     * 更新地主手中的牌
     * 如果都不叫地主则重新发牌
     * 如果三个人分数相同则最后一个人当地主
     * */

    /*
    * 一号玩家先选择分数，发送选择的分数给服务器
    * 然后等待读取下一条消息calllordfinished$
    * */
    public static synchronized void selectLord(int score,int playerIndex) {
        //需要判断是否达到3次
        if(score>maxScore){
            maxScore=score;
            playerOfMaxScore=playerIndex;
        }
        String message="callLandlord#"+score+"$";
        sendToOthers(playerIndex,message);
//        String lordMsg=null;
//        if(score1!=-1&&score2!=-1&&score3!=-1){
//            if(max==score1){
//                lordMsg = "landlord#"+1+"#"+lordCard.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ");
//            } else if (max==score2) {
//                lordMsg = "landlord#"+2+"#"+lordCard.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ");
//            } else {
//                lordMsg = "landlord#"+3+"#"+lordCard.toString().replaceAll("\\[|\\]", "").replaceAll(", ", " ");
//            }
//        }
//        return lordMsg;
        //landlord#玩家序号#地主牌$
    }


    //通过index获取该用户是否是地主
    public boolean isLandlord(int playerIndex) {
        if (playerIndex == start) {
            return true;
        }
        return false;
    }

    public static void reset(){
        playerArray.clear();
        dos1=null;
        dos2=null;
        dos3=null;
        maxScore=-1;
        playerOfMaxScore=-1;
        lordCard.clear();
        player1Card.clear();
        player2Card.clear();
        player3Card.clear();
    }

    //关闭输入输出流，避免占用资源
//    public void closeIOStream() {
//        try {
//            dos1.close();
//            dis1.close();
//
//            dos2.close();
//            dis2.close();
//
//            dos3.close();
//            dis3.close();
//        } catch (IOException e) {
//            System.out.println("游戏结束关闭流失败");
//            throw new RuntimeException(e);
//        }
//    }

}

