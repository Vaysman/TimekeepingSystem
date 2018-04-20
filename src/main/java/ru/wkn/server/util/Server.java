package ru.wkn.server.util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int port;
    private Context context;

    public Server(final int port, Context context) {
        this.port = port;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while(!context.isStopFlag()) {
                System.out.println("Waiting connection on port:" + port);

                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected to server");

                ClientSession clientSession = new ClientSession(clientSocket, context);
                context.getSessionsManger().addSession(clientSession);

                clientSession.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
