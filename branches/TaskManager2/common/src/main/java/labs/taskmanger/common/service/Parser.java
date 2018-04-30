package labs.taskmanger.common.service;

import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.entity.TaskImpl;

import java.io.*;

public class Parser {


    public Entity parse(String line) throws IOException {


        Task task = new TaskImpl();
        String[] fields = line.split(";");
        task.setId(Integer.parseInt(fields[0]));
        task.setTaskName(fields[1]);
        task.setDescription(fields[2]);
        task.setPriority(fields[3]);
        task.setStatus(fields[4]);
        return (Entity) task;
    }
}




