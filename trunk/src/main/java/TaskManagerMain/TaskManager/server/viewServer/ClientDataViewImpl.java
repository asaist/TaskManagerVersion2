package TaskManagerMain.TaskManager.server.viewServer;

import TaskManagerMain.TaskManager.common.entity.*;
import TaskManagerMain.TaskManager.common.service.Parser;
import TaskManagerMain.TaskManager.common.service.TextDao;
import TaskManagerMain.TaskManager.server.controllerServer.TaskManagerController;
import TaskManagerMain.TaskManager.server.controllerServer.TaskManagerControllerImpl;
import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModel;
import TaskManagerMain.TaskManager.server.modelServer.TaskManagerModelImpl;

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
            /*while (!client.isClosed()) {
                System.out.println("Server reading from channel");
                String entry = input.readUTF();
                String[] entrys = entry.split(";");
                modelServer.addTask((Task) parser.parse(entry));
                controllerServer.addTask(entrys[0],entrys[1], entrys[2], entrys[3], entrys[4], entrys[5], entrys[6], entrys[7], entrys[8]);
            }

            input.close();
            client.close();*/


            //DataObject.Action action=null;
            Object entity;

            while (!client.isClosed()) {
                //DataObject полностью
                //DataObject dto = new DataObjectImpl(input);//заменить на чтение из потока когда найдем

                /*action = dto.getAction();
                entity= dto.getEntity();*/

                String entry = input.readUTF();
                String[] entrys = entry.split(";");
                String action = entrys[0];
                switch(action){
                   /* case "DELETE":controllerServer.deleteTask((Task)entity);
                    break;
                    case "CREATE":
                       /* if (entity instanceof Task){
                            Task task = (Task)entity;
                            controllerServer.addTask(task.getTaskName(),task.getDescription(),task.getDeadlineYear(),task.getDeadlineMonth(),task.getDeadlineDay(),task.getDeadlineHour(),task.getPriority(),task.getStatus(),task.getSubtask());

                        }else if (entity instanceof Assignee){
                            Assignee assignee=(Assignee) entity;
                            controllerServer.addAssignee(assignee.getName(),assignee.getLastName(),assignee.getLastName());
                        }
                        break;*/
                    case "UPDATE":
                       // Task task = (Task)entity;
                        //controllerServer.updateTask(task);
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

               /* if(dto.equals("quit")){
                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - "+ dto + " - OK");
                    out.flush();
                    Thread.sleep(3000);
                    break;
                }

                out.writeUTF("Server reply - "+ dto + " - OK");
                System.out.println("Server Wrote message to client.");
                out.flush();*/
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
