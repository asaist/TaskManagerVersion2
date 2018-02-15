package common.service;

import common.entity.Entity;
import common.entity.Task;
import common.entity.TaskImpl;

import java.io.*;

public class Parser {


    public Entity parse(String line) throws IOException {


        Task task = new TaskImpl();
        String[] fields = line.split(";");
        // if ((fields.length == 10)) {
        task.setId(Integer.parseInt(fields[0]));
        task.setTaskName(fields[1]);
        task.setDescription(fields[2]);
        task.setPriority(fields[3]);
        task.setStatus(fields[4]);
        task.setSubtask(fields[5]);
        // }
        return (Entity) task;
    }
}




