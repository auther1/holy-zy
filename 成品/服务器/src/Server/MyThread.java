package Server;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class MyThread extends Thread{
    Player player;
//    BufferedReader reader;
//    BufferedWriter writer;
    public MyThread(Player player) throws IOException {
        this.player=player;
        player.reader=new BufferedReader(new InputStreamReader(player.getSocket().getInputStream()));
        player.writer=new BufferedWriter(new OutputStreamWriter(player.getSocket().getOutputStream()));
    }
    @Override
    public void run() {
        System.out.println("线程开始");
        try {
            while (true) {
                //以结尾符号$分割，获取消息
                String message= player.reader.readLine().split("\\$")[0];
                handleReceivedMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        try {
            player.writer.write(message);
            player.writer.newLine();
            player.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("发送消息出现异常");
        }
    }

    public void handleReceivedMessage(String message) {
        System.out.println("准备处理信息"+message);
        //消息分段存入列表，并获取消息头
        String[] messageList=message.split("#");
        String head=messageList[0];
        //收到登录信息 login#[mail]#[password]$
        if(head.equals("login")){
            try {
                int result=Login.check(messageList);
                switch (result){
                    case 0:
                        player.writer.write("loginSuccess$");
                        player.writer.newLine();
                        player.writer.flush();
                        player.setUserMail(messageList[1]);
                        //player.setUserName();
                        break;
                    case 1:
                        player.writer.write("loginPasswordError$");
                        player.writer.newLine();
                        player.writer.flush();
                        break;
                    case 2:
                        player.writer.write("loginNoAccount$");
                        player.writer.newLine();
                        player.writer.flush();
                        break;
                }
                //player.writer.write("loginSuccess$");
            } catch (IOException e) {
                System.out.println("返回登录查询结果出现异常");
                throw new RuntimeException(e);
            } catch (SQLException e) {
                System.out.println("操作数据库出现异常");
                throw new RuntimeException(e);
            }
        }
        //收到注册信息
        if(head.equals("register")){
            try {
                if(Register.register(messageList)){
                    player.writer.write("registerSuccess$");
                    player.writer.newLine();
                    player.writer.flush();
                }else{
                    player.writer.write("registerExists$");
                    player.writer.newLine();
                    player.writer.flush();
                }
            } catch (SQLException e) {
                System.out.println("注册时出现数据库异常");
                throw new RuntimeException(e);
            } catch (IOException e) {
                System.out.println("返回注册信号时出现异常");
                throw new RuntimeException(e);
            }
        }

        //接收startGame$
        //发出startGame#索引#牌$
        if(head.equals("startGame")){
            Server.joinRoom(player);
        }

        //接收callLandlord#分$
        if(head.equals("callLandlord")){
            //一个玩家的线程中调用Room类中的抢地主函数
            Room.selectLord(Integer.parseInt(messageList[1]),player.getIndex());
        }

        if(head.equals("calllandlordfinished")){
            try {
                String landlord=Room.getLandlord();
                player.writer.write(landlord);
                player.writer.newLine();
                player.writer.flush();
                System.out.println("地主为"+landlord);
            } catch (IOException e) {
                System.out.println("给自己发回抢地主结果失败");
                throw new RuntimeException(e);
            }
        }

        //playcard#出牌者的索引#牌类型#牌 牌 ...#出牌数$
        if(head.equals("playcard")){
            Room.times++;
            System.out.println(Room.times);
            Room.checkScore(messageList[2]);
            Room.sendToOthers(player.getIndex(),message+"$");
        }

        //victory#true/false#类型#牌#出牌数$
        if(head.equals("victory")){
            try {
                String msg="victory#"+player.isLandlord()+"#"+messageList[2]+"#"+messageList[3]+"#"+messageList[4]+"$";
                Room.sendToOthers(player.getIndex(),msg);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("发送胜利消息时数组越界");
                e.printStackTrace();
            }
        }
    }
}
