package server.model;


import common.entity.Assignee;
import common.entity.Coloring;
import common.entity.Entity;
import common.entity.Task;
import common.service.GenericDao;
import common.service.JDBCDaoAssignee;
import common.service.JDBCDaoTask;
import server.view.TaskManagerView;

import java.util.*;


public class TaskManagerModelImpl extends Observable implements TaskManagerModel {


    private List<Task> tasks = new ArrayList();
    private List<Coloring> colorings = new ArrayList();
    private List<Assignee> assignees = new ArrayList();
    private final GenericDao dao;

    public TaskManagerModelImpl(GenericDao dao) {
        this.dao = dao;
    }


    public List<Assignee> getAssignees() {
        return assignees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public void create() {
        System.out.println("Запись добавлена.");
    }


    //Task
    public void addTask(Task task) {
        if (task != null) {
            checkTasks(task);
            tasks.add(task);
            JDBCDaoTask dao = new JDBCDaoTask();
            dao.create((Entity) task);
            modelIsChanged();
            System.out.println("Запись добавлена  в модель " + task.toString());

        }
    }

    public void addAllTask(List<Entity> entities1) {

        if (entities1 == null) {
            System.out.println("Задач пока нет");

        } else {

            for (Entity entity : entities1) {
                if (entity != null) {
                    checkTasks((Task) entity);
                    tasks.add((Task) entity);
                    setChanged();
                    notifyObservers();
                }
            }
        }
    }

    @Override
    public void addWatcher(TaskManagerView view) {
        addObserver(view);
    }


    private void checkTasks(Task task) {
        for (Task task1 : getTasks()) {
            if (task1.equals(task)) {
                throw new RuntimeException("a record already exists");
            }
        }
    }

    @Override
    public void deleteTask(Task taskToRemove) {
        JDBCDaoTask dao = new JDBCDaoTask();
        dao.delete((Entity) taskToRemove);
        tasks.remove(taskToRemove);
        modelIsChanged();
    }

    public void updateTask(Task taskToUpdate) {
        JDBCDaoTask dao = new JDBCDaoTask();
        dao.update((Entity) taskToUpdate);
        tasks.remove(searchTask(taskToUpdate));
        tasks.add(taskToUpdate);
        modelIsChanged();
    }

    //Assaignee
    public void addAssaignee(Assignee assignee) {
        checkAssignees(assignee);
        assignees.add(assignee);
        JDBCDaoAssignee dao = new JDBCDaoAssignee();
        dao.create((Entity) assignee);
        modelIsChanged();
        System.out.println("Запись добавлена в модель " + assignee.toString());
    }

    @Override
    public void addAllAssignee(List<Entity> assignees1) {
        if (assignees1 == null) {
            System.out.println("Исполнителей пока нет");

        } else {

            for (Entity entity : assignees1) {
                if (entity != null) {
                    checkAssignees((Assignee) entity);
                    assignees.add((Assignee) entity);
                    modelIsChanged();
                }
            }
        }
    }

    public void updateAssignee (Assignee assigneeToUpdate){
        JDBCDaoAssignee dao = new JDBCDaoAssignee();
        dao.update((Entity) assigneeToUpdate);
        assignees.remove(searchAssignee(assigneeToUpdate));
        assignees.add(assigneeToUpdate);
        modelIsChanged();
    }

    public void deleteAssignee(Assignee assigneeToRemove) {
        JDBCDaoAssignee dao = new JDBCDaoAssignee();
        dao.delete((Entity) assigneeToRemove);
        assignees.remove(assigneeToRemove);
        modelIsChanged();
    }

    private void checkAssignees(Assignee assignee) {
        for (Assignee assignee1 : getAssignees()) {
            if (assignee1.equals(assignee)) {
                throw new RuntimeException("A record already exists");

            }
        }
    }

    public void modelIsChanged() {
        setChanged();
        notifyObservers();
    }

    public Task searchTask(Task task) {
        Task foundTask = null;
        for (Task task1 : tasks) {
            if (task1.getId().equals(task.getId())) {
                foundTask = task1;
            }
        }
        return foundTask;
    }

    public Assignee searchAssignee(Assignee assignee) {
        Assignee foundAssignee = null;
        for (Assignee assignee1 : assignees) {
            if (assignee1.getId().equals(assignee.getId())) {
                foundAssignee = assignee1;
            }
        }
        return foundAssignee;
    }


}
