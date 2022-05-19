package client;

import models.DataClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket socket = new Socket(ip, 5000);
            while (true) {
                Scanner reader = new Scanner(System.in);
                System.out.println("Escribe el destinatario:");
                String id = reader.nextLine();
                System.out.println("Escribe la temperatura:");
                int temperature = Integer.parseInt(reader.nextLine());
                System.out.println("Escribe la humedad:");
                int humidity = Integer.parseInt(reader.nextLine());
                OutputStream outputStream = socket.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(new DataClient(id, temperature, humidity));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

