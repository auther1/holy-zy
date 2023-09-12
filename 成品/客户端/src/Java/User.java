package Java;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class User {
    private String MyPlayerEmail;//玩家名称，到时候可视化显示，用JLabel
    public int number=17;
    public int numberleft=17;
    public int numberright=17;
    private int player2ID;//用户2
    private int player3ID;//用户3
    public int playerID;//玩家ID，用于和服务器对接,识别唯一身份
    public int lordID;//地主id
    public ArrayList<String>handCards=new ArrayList<>();//持有的手牌
    public PokeGroup pokeGroup=new PokeGroup();
    private boolean isLandlord;//是否是地主
    private int score;//玩家开局所选择的分数
    private int gameRoomID;//房间 号
    private boolean isConnected;//是否在线
    private boolean isRoom;//是否在房间里
    private Socket connection;//和服务器进行连接
    public PokeGroup LastpokeGroup=new PokeGroup();//承接上家出的牌的PokeGroup类
    public PokeGroup LastLastpokeGroup=new PokeGroup();
    private ArrayList<String> LastPlayCards;//上家出的牌

    // 构造方法
    public User() {

        this.connection=Client.socket;


    }
    //接收服务器所分发的手牌
    //startGame#索引#牌$
    public void receiveCardsFromServer(String card)
    {


        //按照空格分割
        String[]remaining=card.split(" ");
        //将remaining中的数据存入cards中
        for (String item : remaining) {
            handCards.add(item);
        }

    }


    //点击创建房间的按钮
    public void createRoom()
    {
        //点击按钮后向服务器发送创建房间的信息
        String createRoom="createRoom";
        sendDataToServer(createRoom);
        String receiveMessage=receiveDataFromServer();
    }

    // 连接服务器
    public void connectToServer(String serverIP, int port) {
        // 创建Socket连接
        // 进行连接操作
        // 设置connection和isConnected字段
        try {
            connection = new Socket(serverIP, port);
            isConnected = true;
            // 进行连接操作
        } catch (IOException e) {
            System.out.println("连接服务器失败: " + e.getMessage());
        }
    }

    // 发送数据到服务器
    public void sendDataToServer(String data) {
        // 利用连接发送数据给服务器
        try {
            OutputStream outputStream = connection.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);
            writer.println(data);
        } catch (IOException e) {
            System.out.println("发送数据失败: " + e.getMessage());
        }
    }

    // 接收服务器数据
    public String receiveDataFromServer() {
        String receivedData = null;
        BufferedReader reader = null;
        try {
            connection.setSoTimeout(500000);
            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            receivedData = reader.readLine();
        } catch (IOException e) {
            System.out.println("接收数据失败: " + e.getMessage());
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    System.out.println("关闭输入流失败: " + e.getMessage());
//                }
//            }
        }
        return receivedData;
    }

    // 玩家叫地主
    public void callLandlord() {
        // 发送叫地主指令到服务器
        String callCommand = "CallLandlord";
        sendDataToServer(callCommand);
    }

    // 玩家抢地主
    public void grabLandlord() {
        // 发送抢地主指令到服务器
        String grabCommand = "GrabLandlord";
        sendDataToServer(grabCommand);
    }
    // 出牌
    public void playCards(ArrayList<String> cards) {
        // 发送出牌指令和所出的牌到服务器
        StringBuilder playCommandBuilder = new StringBuilder("PlayCards");
        for (String card : cards) {
            playCommandBuilder.append(card).append(" ");
        }
        String playCommand = playCommandBuilder.toString();
        sendDataToServer(playCommand);
    }
    public void playCardsNew()
    {
        //pokeGroup.removeAll();
        sendDataToServer("PlayCards"+"#"+MyPlayerEmail+"#"+pokeGroup.getpokegroup()+"#"+pokeGroup.getCountNumber()+"$");
    }
    public void ReceiveLastPlayCard()
    {
        String poke=receiveDataFromServer();
        LastpokeGroup.add(poke);
    }

    public void selectCards(String poke)
    {
        //选中的牌
       // pokeGroup.removeAll();
        pokeGroup.add(poke);
    }
    public void clickedPlayCards()
    {
        int i=pokeGroup.compareTo(LastpokeGroup);
        if(i==1)
        {
            //playCardsNew(pokeGroup.getpokegroup());
            LastpokeGroup.removeAll();
        }
        else {
            System.out.println("出牌失败，不能出牌！！！");//前端再改
            LastpokeGroup.removeAll();
        }
    }
    //点击抢一分的按钮
    public void clickedOneScore()
    {
        score=1;
        String oneScore="callLandlord"+"#"+score+"$";
        sendDataToServer(oneScore);
    }
    //点击抢两分的按钮
    public void clickedTwoScore()
    {
        score=2;
        String twoScore="callLandlord"+"#"+score+"$";
        sendDataToServer(twoScore);
    }
    //点击抢三分的按钮
    public void clickedThreeScore()
    {
        score=3;
        String threeScore="callLandlord"+"#"+score+"$";
        sendDataToServer(threeScore);
    }
    public void clickedZeroScore()
    {
        score=0;
        String zeroScore="callLandlord"+"#"+score+"$";
        sendDataToServer(zeroScore);
    }
    //点击开始游戏按钮
    public void clickedStartGame()
    {
        String startGame="startGame$";
        sendDataToServer(startGame);
        System.out.println(startGame);
        //然后再转到新界面：游戏界面
        //代码待完善


    }
    //点击退出房 间 按钮（待定）
    public void exitRoom()
    {

    }

}
