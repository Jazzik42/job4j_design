package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServerV2 {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream outServer = socket.getOutputStream();
                BufferedReader inServer = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()))) {
                    String str = inServer.readLine();
                    outServer.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    while (!(str.isEmpty())) {
                        System.out.println(str);
                        if (str.contains("Hello")) {
                            outServer.write("Hello, dear friend\r\n".getBytes());
                        } else if (str.contains("Exit")) {
                            server.close();
                        } else if (str.contains("What")) {
                            outServer.write("What\r\n".getBytes());
                        } else {
                            outServer.write((str + System.lineSeparator()).getBytes());
                        }
                        str = inServer.readLine();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
