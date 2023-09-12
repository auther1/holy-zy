package Client.src.Java;

import java.io.*;
import java.net.Socket;

public class Client {
    static Socket socket;
    static BufferedWriter writer;
    static BufferedReader reader;

    //记得修改ip地址！！！
    static {
        try {
            socket=new Socket("127.0.0.1",8848);
            writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Client类初始化出现异常");
            e.printStackTrace();
        }
    }
    public static void sendMessage(String message) throws IOException {
        System.out.println("客户端发送信息"+message);
        writer.write(message);
        writer.newLine();
        writer.flush();
    }
    //处理并分发收到的信息
    /*
    loginSuccess$
    loginPasswordError$
    loginNoAccount$
    registerSuccess$
    registerExists$
     */
    public static void handleReceivedMessage() throws IOException {
        while (true){
            String message=Client.reader.readLine().split("\\$")[0];
            String[] messageList=message.split("#");
            String head=messageList[0];
            if(head.equals("loginSuccess")){
                LoginController.isValid(0);
            }
            if(head.equals("loginPasswordError")){
                LoginController.isValid(1);
            }
            if(head.equals("loginNoAccount")){
                LoginController.isValid(2);
            }
            if(head.equals("registerSuccess")){

            }
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String receiver;

        sendMessage("startGame$");
        receiver=reader.readLine();
        System.out.println(receiver);

        sendMessage("callLandlord#1$");
        receiver=reader.readLine();
        System.out.println(receiver);
        receiver=reader.readLine();
        System.out.println(receiver);

        sendMessage("calllandlordfinished$");
        receiver=reader.readLine();
        System.out.println(receiver);

        //首位出牌者
        sendMessage("playcard#2#不出#♥10 ♦7#2$");
        receiver=reader.readLine();
        System.out.println(receiver);
        receiver=reader.readLine();
        System.out.println(receiver);

        sendMessage("victory#2#不出#♥10 ♦7#2$");
        receiver=reader.readLine();
        System.out.println(receiver);
    }
}
