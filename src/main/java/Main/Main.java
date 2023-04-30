package Main;
import ServerUI.SGUI;

import java.net.ServerSocket;
import java.net.Socket;
import ServerThread.ServerThread;


public class Main {

    public static void main(String[] args) {
        try {
            int port = 8888;
            ServerSocket serverSocket = new ServerSocket(port);
            new SGUI();
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        }catch (Exception e) {
            System.out.println("Connect fail: " + e.getMessage());
        }
    }
}