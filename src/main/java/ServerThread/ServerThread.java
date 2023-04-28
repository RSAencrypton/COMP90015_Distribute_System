package ServerThread;

import MsgManager.MsgManager;
import Protocol.MsgBase;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ServerThread extends Thread{
    public Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            while (true) {
                MsgBase msg = (MsgBase) inputStream.readObject();
                System.out.println(msg.protoName);

                if (msg.protoName.equals("MsgLogIn")||msg.protoName.equals("MsgRegister")) {
                    Method method = MsgManager.class.getMethod(msg.protoName, MsgBase.class, Socket.class);
                    Object[] args = new Object[]{msg, socket};
                    Object res = method.invoke(null, args);
                    outputStream.writeObject((MsgBase)res);
                    outputStream.flush();
                }else if(msg.protoName.equals("MsgLogOut")) {
                    Method method = MsgManager.class.getMethod(msg.protoName, MsgBase.class);
                    method.invoke(null, msg);
                }else {
                    Method method = MsgManager.class.getMethod(msg.protoName, MsgBase.class);
                    System.out.println("here");
                    if (method != null) {
                        Object res = method.invoke(null, msg);
                        outputStream.writeObject((MsgBase)res);
                        outputStream.flush();
                    }else {
                        System.out.println("Method not found");
                    }
                }
            }
        }catch (Exception e) {
            System.out.println("socket closed" + e.getMessage());
        }
    }
}
