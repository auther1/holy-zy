package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements AutoCloseable{
    //用于创房间的玩家列表
    static ArrayList<Player> playerList;

    static Room room;
    ServerSocket serverSocket;

    public void start() throws IOException {
        System.out.println("服务器启动");
        serverSocket = new ServerSocket(8848);
        ExecutorService executor = Executors.newCachedThreadPool();
        //只开一个房间，只能一遍游戏
        room = new Room();
        playerList = new ArrayList<>();
        //持续监听，为新连接的用户创建套接字

        while (true) {
            Socket socket = serverSocket.accept();
            Player player = new Player(socket);
            System.out.println("一个用户已连接");
            //为套接字创建线程，交由线程池管理
            MyThread thread = new MyThread(player);
            executor.submit(thread);
        }
    }

    /*在线程中收到匹配消息后调用
    将一个用户加入列表，列表中存在三个人则开始游戏
     */
    public static synchronized void joinRoom(Player player) {
        playerList.add(player);
        //设置玩家序号
        player.setIndex(playerList.size());
        System.out.println("一个玩家加入房间，当前房间大小" + playerList.size());
        if (playerList.size() == 3) {
            room.addPlayer(playerList);
            //直接给三个人发牌
            Room.distributePoker();
            //room.start();
        }
    }

    //关服
    @Override
    public void close() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            System.out.println("关服出现异常");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        try(Server server=new Server()){
            server.start();
        }
    }
}
