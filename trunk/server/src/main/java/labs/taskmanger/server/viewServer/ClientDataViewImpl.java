package labs.taskmanger.server.viewServer;

import labs.taskmanger.common.service.Parser;
import labs.taskmanger.common.service.TextDao;
import labs.taskmanger.server.controllerServer.TaskManagerController;
import labs.taskmanger.server.modelServer.TaskManagerModel;
import labs.taskmanger.common.entity.Entity;

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;



public class ClientDataViewImpl implements TaskManagerView {
    boolean flag = false;
    DataInputStream in;
    DataOutputStream out;
    TaskManagerController controller;
    TaskManagerModel model;
    List <Entity> entities = new ArrayList<>();


    public ClientDataViewImpl(TaskManagerController controller, TaskManagerModel model) {
        this.controller = controller;
        this.model = model;

    }

    @Override
    public void updateViewTextConsole(String textConsole) {

    }

    @Override
    public void displayModels(TaskManagerModel model) {
        try {
            out.write(Integer.valueOf(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createView() {

        int port = 1234;

        try (ServerSocket ss = new ServerSocket(port)) {

            System.out.println("Waiting for a client...");

            Socket client = ss.accept(); //сокет общения с клиентом
            System.out.println("Got a client :) ... Finally,someone saw me through all the cover");

            InputStream sin = client.getInputStream();
            OutputStream sout = client.getOutputStream();

            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);

            // канал записи в сокет
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            System.out.println("DataOutputStream  created");

            // канал чтения из сокета
            DataInputStream input = new DataInputStream(client.getInputStream());
            System.out.println("DataInputStream created");
            Parser parser = new Parser();

            Object entity;

            while (!client.isClosed()) {


                String entry = input.readUTF();
                String[] entrys = entry.split(";");
                String action = entrys[0];
                switch(action){

                    case "UPDATE":

                        break;
                    case "UPLOAD":
                        TextDao textDao = new TextDao();
                        entities = textDao.readAll();
                        for (Entity entity1 : entities) {
                            out.writeUTF(entity1.toString());
                            System.out.println("Server Wrote message to client.");
                        }
                        break;
                }

                System.out.println("The dumb client just sent me this action: " + action);
                System.out.println("I'm sendng it back...");


            }
            // если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сам сокет общения на стороне сервера!
            client.close();

            // потом закрываем сокет сервера который создаёт сокеты общения
            // хотя при многопоточном применении его закрывать не нужно
            // для возможности поставить этот серверный сокет обратно в ожидание нового подключения

            System.out.println("Closing connections & channels - DONE.");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Observable o, Object arg) {
        TaskManagerModel model = (TaskManagerModel) o;
        displayModels(model);
    }

}
