package labs.taskmanger.server.modelServer;


import labs.taskmanger.common.entity.Assignee;
import labs.taskmanger.common.entity.Coloring;
import labs.taskmanger.common.entity.Entity;
import labs.taskmanger.common.entity.Task;
import labs.taskmanger.common.service.GenericDao;
import labs.taskmanger.server.viewServer.TaskManagerView;

import java.util.*;


public class TaskManagerModelImpl extends Observable implements TaskManagerModel {


    private List<Task> tasks = new ArrayList();
    private List<Coloring> colorings = new ArrayList();
    private List<Assignee> assignees = new ArrayList();
    private final GenericDao daoTask;
    private final GenericDao daoAssignee;


    @Inject
    public TaskManagerModelImpl(GenericDao <Task> daoTask, GenericDao <Assignee> daoAssidnee) {

        this.daoTask = daoTask;
        this.daoAssignee = daoAssidnee;
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
            daoTask.create((Entity) task);
            modelIsChanged();
            System.out.println("Запись добавлена  в модель " + task.toString());

        }
    }

    public void addAllTask() {

        List<Entity> allEntities = daoTask.readAll();
        if (allEntities == null) {
            System.out.println("Задач пока нет");

        } else {

            for (Entity entity : allEntities) {
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
        daoTask.delete((Entity) taskToRemove);
        tasks.remove(taskToRemove);
        modelIsChanged();
    }

    public void updateTask(Task taskToUpdate) {
        daoTask.update((Entity) taskToUpdate);
        tasks.remove(searchTask(taskToUpdate));
        tasks.add(taskToUpdate);
        modelIsChanged();
    }

    //Assaignee
    public void addAssaignee(Assignee assignee) {
        checkAssignees(assignee);
        assignees.add(assignee);
        daoAssignee.create((Entity) assignee);
        modelIsChanged();
        System.out.println("Запись добавлена в модель " + assignee.toString());
    }

    @Override
    public void addAllAssignee() {

        List<Entity> allAssignees = daoAssignee.readAll();
        if (allAssignees == null) {
            System.out.println("Исполнителей пока нет");

        } else {

            for (Entity entity : allAssignees) {
                if (entity != null) {
                    checkAssignees((Assignee) entity);
                    assignees.add((Assignee) entity);
                    modelIsChanged();
                }
            }
        }
    }

    public void updateAssignee (Assignee assigneeToUpdate){
        daoAssignee.update((Entity) assigneeToUpdate);
        assignees.remove(searchAssignee(assigneeToUpdate));
        assignees.add(assigneeToUpdate);
        modelIsChanged();
    }

    public void deleteAssignee(Assignee assigneeToRemove) {
        daoAssignee.delete((Entity) assigneeToRemove);
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
