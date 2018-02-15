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
        task.setDeadlineYear(fields[3]);
        task.setDeadlineMonth(fields[4]);
        task.setDeadlineDay(fields[5]);
        task.setDeadlineHour(fields[6]);
        task.setPriority(fields[7]);
        task.setStatus(fields[8]);
        task.setSubtask(fields[9]);
        // }
        return (Entity) task;
    }
}




