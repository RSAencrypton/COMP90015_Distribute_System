package ServerThread;

import MsgManager.MsgManager;
import Protocol.MsgBase;
import Main.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ServerThread extends Thread{
    private Socket socket;
    private static ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    String inputStr;
    MsgBase msg;

    public ServerThread(Socket socket) {
        try {
            this.socket = socket;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void init() {
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            inputStr = null;
            msg = null;
        }catch (Exception e) {
            System.out.println("init fail: " + e.getMessage());
        }
    }

    public static void SendMsg(MsgBase msg) {
        try {
            if (outputStream != null) {
                outputStream.writeObject(msg);
                outputStream.flush();
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        init();
        try {
            while (true) {
                MsgBase msg = (MsgBase) inputStream.readObject();
                System.out.println(msg.protoName);

                Method method = MsgManager.class.getMethod(msg.protoName, MsgBase.class);
                Object[] args = {msg, socket};
                Object res = method.invoke(null, msg);
            }
        }catch (Exception e) {
            System.out.println("socket closed" + e.getMessage());
        }
    }
}
