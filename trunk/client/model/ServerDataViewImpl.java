package client.model;

import client.view.ClientTaskManagerView;
import common.entity.Entity;
import common.service.GenericDao;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Observable;

public class ServerDataViewImpl implements GenericDao {
    DataInputStream in;
    DataOutputStream out;
    Entity response;


    public ServerDataViewImpl() {


        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
             DataInputStream ois = new DataInputStream(socket.getInputStream());)

        {

            System.out.println("Client connected to socket.");
            System.out.println();
            System.out.println("Client writing channel = oos & reading channel = ois initialized.");


            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();


            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);


            while (!socket.isOutputShutdown()) {

// ждём консоли клиента на предмет появления в ней данных
                if (br.ready()) {

// данные появились - работаем
                    System.out.println("Client start writing in channel...");
                    Thread.sleep(1000);
                    String clientCommand = br.readLine();

// пишем данные с консоли в канал сокета для сервера
                    oos.writeUTF(clientCommand);
                    oos.flush();
                    System.out.println("Clien sent message " + clientCommand + " to server.");
                    Thread.sleep(1000);
// ждём чтобы сервер успел прочесть сообщение из сокета и ответить

// проверяем условие выхода из соединения
                    if (clientCommand.equalsIgnoreCase("quit")) {

// если условие выхода достигнуто разъединяемся
                        System.out.println("Client kill connections");
                        Thread.sleep(2000);

// смотрим что нам ответил сервер на последок перед закрытием ресурсов
                        if (ois.read() > -1) {
                            System.out.println("reading...");
                            String in = ois.readUTF();
                            System.out.println(in);
                        }

// после предварительных приготовлений выходим из цикла записи чтения
                        break;
                    }

// если условие разъединения не достигнуто продолжаем работу
                    System.out.println("Client sent message & start waiting for data from server...");
                    Thread.sleep(2000);

// проверяем, что нам ответит сервер на сообщение(за предоставленное ему время в паузе он должен был успеть ответить)
                    if (ois.read() > -1) {

// если успел забираем ответ из канала сервера в сокете и сохраняем её в ois переменную,  печатаем на свою клиентскую консоль
                        System.out.println("reading...");
                        String in = ois.readUTF();
                        System.out.println(in);
                    }
                }
            }
// на выходе из цикла общения закрываем свои ресурсы
            System.out.println("Closing connections & channels on clentSide - DONE.");


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Integer create(Entity newInstance) {
        try {
            out.writeUTF(newInstance.toString());//
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (response == null) {
            // response = in.readUTF();
        }
        return response.getId();
    }

    @Override
    public Entity read(Integer id) {
        return null;
    }

    @Override
    public void update(Entity transientObject) {

    }

    @Override
    public void delete(Entity persistentObject) {

    }

    @Override
    public List<Entity> readAll() {
        try {
            out.writeUTF("getList<Entity>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Entity> response1 = (List<Entity>) response;
        while (response1 == null) {
            // response1 = (List<Entity>) in.readUTF();
        }
        return response1;

    }

    @Override
    public void checkFile() {

    }
}
