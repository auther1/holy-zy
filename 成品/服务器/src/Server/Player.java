package Server;

import java.io.*;
import java.net.Socket;


//客户端登录成功后从数据库拉取用户信息
/*
* 其中的用户状态用来标识是否可以匹配
* */
public class Player {
    private Socket socket;
    private String userName;
    private String userMail;
    private int index;
    private String userIntegral;
    private boolean isLandlord=false;
    public BufferedReader reader;
    public BufferedWriter writer;
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Player(Socket socket) throws IOException {
        this.socket=socket;
        reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public boolean isLandlord() {
        return isLandlord;
    }

    public void setLandlord(boolean landlord) {
        isLandlord = landlord;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserIntegral() {
        return userIntegral;
    }

    public void setUserIntegral(String userIntegral) {
        this.userIntegral = userIntegral;
    }

}
