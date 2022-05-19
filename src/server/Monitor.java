package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import controllers.DataController;
import controllers.RuleController;

import entities.Data;
import entities.Rule;
import mail.Mail;

import models.DataClient;

import server.events.*;
import server.interfaces.SocketListenerI;

public class Monitor implements SocketListenerI {
    public static void main(String[] args) {
        int port = getPortProperty(args);
        Server server = new Server(port);
        server.start();
    }

    public static int getPortProperty(String[] args) {
        try {
            Properties properties = new Properties();
            String fileName = args[0];
            String propertyPort = args[1];
            properties.load(new FileInputStream(fileName));
            String port = properties.getProperty(propertyPort);
            return Integer.parseInt(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onClientConnect(EventConnection e) {

    }

    @Override
    public void onClientDisconnect(EventDisconnection e) {

    }

    @Override
    public void onSendMessage(EventSend e) {

    }

    @Override
    public void onReceiveMessage(EventReceive e) {
        DataClient dataClient = e.getDataClient();
        String id = dataClient.getId();
        float temperature = dataClient.getTemperature();
        float humidity = dataClient.getHumidity();
        System.out.println("id: " + id);
        System.out.println("temperatura: " + temperature);
        System.out.println("humedad: " + humidity);
        Data data = new Data(null, id, temperature, humidity);
        saveData(data);
        List<Rule> rules = getRules();
        String mail = "";
        String message = "";
        for (int i = 0; i < rules.size(); i++) {
            if (temperature >= rules.get(i).getRi() && temperature <= rules.get(i).getRf()) {
                message = rules.get(i).getMessage();
                mail = rules.get(i).getMail();
            }
        }
        Mail.sendMail(mail, message);
    }
    
    private void saveData(Data data) {
        DataController dataController = new DataController();
        dataController.create(data);
    }
    
    private List<Rule> getRules() {
        RuleController ruleController = new RuleController();
        return ruleController.getAll();
    }
}

