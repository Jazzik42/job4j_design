package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerV2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(EchoServerV2.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outServer = socket.getOutputStream();
                     BufferedReader inServer = new BufferedReader(new InputStreamReader(
                             socket.getInputStream()))) {
                    String str = inServer.readLine();
                    outServer.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (str != null) {
                        while (!(str.isEmpty())) {
                            System.out.println(str);
                            if (str.contains("Hello")) {
                                outServer.write("Hello, dear friend.\r\n".getBytes());
                            } else if (str.contains("Exit")) {
                                outServer.write("Exit\r\n".getBytes());
                                server.close();
                            } else if (str.contains("GET") && !str.contains("Hello")) {
                                outServer.write("What\r\n".getBytes());
                            }
                            str = inServer.readLine();
                        }

                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error", e);
        }
    }
}

