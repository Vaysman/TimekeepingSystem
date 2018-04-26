package ru.wkn.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class ClientLauncher {

    public void launch() {
        try {
            String[] args = new String[2];
            FileInputStream fileInputStream;
            Properties properties = new Properties();
            try {
                fileInputStream = new FileInputStream("src/main/resources/parameters/db-params.properties");
                properties.load(fileInputStream);
                args[0] = properties.getProperty("host");
                args[1] = properties.getProperty("port");
            } catch (IOException e) {
                e.printStackTrace();
            }
            InetAddress host = InetAddress.getByName(args[0]);
            int port = Integer.parseInt(args[1]);

            Client client = new Client(host, port);
            client.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
