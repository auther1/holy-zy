package Java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;

public class Client {
    static Socket socket;
    private static BufferedWriter writer;
    private static BufferedReader reader;
    public static User player=null;

    //记得修改ip地址！！！
    static {
        try {
            socket=new Socket("192.168.54.72",8848);

            writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            System.out.println("Client类初始化出现异常");
            e.printStackTrace();
        }
    }


    public static String receiveMessage(){
        try{
            return reader.readLine().split("\\$")[0];
        } catch (IOException e) {
            System.out.println("客户端收消息出现异常");
            throw new RuntimeException(e);
        }
    }

    public static void sendMessage(String message) throws IOException {
        System.out.println("客户端是否连接"+socket.isConnected());
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
    public static void handleReceivedMessage(String message) {
        System.out.println("客户端收到消息"+message);
        String[] messageList=message.split("#");
        String head=messageList[0];
        if(head.equals("loginSuccess")){
            LoginController.isSuccess(0);

            try {
                Parent root = FXMLLoader.load(InterfaceController.class.getResource("../Fxml/Interface.fxml"));
                Stage stage = new Stage();
                stage.setTitle("欢乐斗地主");
                stage.setScene(new Scene(root, 1000, 650));
//                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if(head.equals("loginPasswordError")){
            LoginController.isSuccess(1);
        }
        if(head.equals("loginNoAccount")){
            LoginController.isSuccess(2);
        }
        if(head.equals("registerSuccess")){
            RegisterController.isSuccess(1);
            try {
                Parent root = FXMLLoader.load(InterfaceController.class.getResource("../Fxml/Login.fxml"));
                Stage stage = new Stage();
                stage.setTitle("欢乐斗地主");
                stage.setScene(new Scene(root, 800, 600));
//                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(head.equals("registerExists")){
            RegisterController.isSuccess(0);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

    }
}
