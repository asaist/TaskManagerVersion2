package server.view;

import common.entity.AssigneeImpl;
import common.entity.TaskImpl;
import server.controller.TaskManagerController;
import common.entity.Assignee;
import common.entity.Task;
import server.model.TaskManagerModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Observable;
import java.util.Observer;

public class TaskManagerViewImpl implements TaskManagerView, Observer {

    private final TaskManagerController controller;
    private final TaskManagerModel model;

    private final JPanel assaigneeControlPanel;
    private final JPanel taskControlPanel;
    private final JPanel globalPanel;
    private final JPanel tasksViewPanel;
    private final JPanel assigneesViewPanel;
    private final EntityPresenter entityPresenter = new EntityPresenter();
    private final JFrame viewFrame;
    private final JTextField viewTextName;
    private final JTextField viewTextLastName;
    private final JTextField viewTextPost;
    private final JTextField viewTextConsole;
    private final JTextField viewTextTName;
    private final JTextField viewTextDescription;
    private final JTextField viewTextDeadlineYear;
    private final JTextField viewTextDeadlineMonth;
    private final JTextField viewTextDeadlineDay;
    private final JTextField viewTextDeadlineHour;
    private final JTextField viewTextPriority;
    private final JTextField viewTextStatus;
    private final JTextField viewTextSubTask;
    private final JButton addAssaigneeButton;
    private final JButton addTaskButton;
    private final String textViewFrame = "viewServer";
    private final String addAssaigneeButtonLable = "Add Assaignee";
    private final String addTaskButtonLable = "Add Task";
    private final String textName = "Name";
    private final String textLastName = "Last Name";
    private final String textConsole = "Console";
    private final String textPost = "Post";
    private final String texTName = "TaskName";
    private final String textDescription = "Description";
    private final String textDeadlineYear = "2017";
    private final String textDeadlineMonth = "12";
    private final String textDeadlineDay = "31";
    private final String textDeadlineHour = "23";
    private final String textPriority = "Priority";
    private final String textStatus = "Status";
    private final String textSubtasks = "Subtasks";


    public TaskManagerViewImpl(TaskManagerController controller, TaskManagerModel model) {
        this.controller = controller;
        this.model = model;

        viewFrame = new JFrame(textViewFrame);
        tasksViewPanel = new JPanel();
        assigneesViewPanel = new JPanel();
        globalPanel = new JPanel();
        assaigneeControlPanel = new JPanel();
        taskControlPanel = new JPanel();

        addAssaigneeButton = new JButton(addAssaigneeButtonLable);
        addTaskButton = new JButton(addTaskButtonLable);


        viewTextName = new JTextField(textName);
        viewTextLastName = new JTextField(textLastName);
        viewTextPost = new JTextField(textPost);
        viewTextConsole = new JTextField(textConsole);

        viewTextTName = new JTextField(texTName);
        viewTextDescription = new JTextField(textDescription);
        viewTextDeadlineYear = new JTextField(textDeadlineYear);
        viewTextDeadlineMonth = new JTextField(textDeadlineMonth);
        viewTextDeadlineDay = new JTextField(textDeadlineDay);
        viewTextDeadlineHour = new JTextField(textDeadlineHour);
        viewTextPriority = new JTextField(textPriority);
        viewTextStatus = new JTextField(textStatus);
        viewTextSubTask = new JTextField(textSubtasks);
    }

    public void createView() {


        viewFrame.pack();
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
        viewFrame.setVisible(true);

        globalPanel.setLayout(new BoxLayout(globalPanel, BoxLayout.Y_AXIS));
        Border border = BorderFactory.createLineBorder(Color.black);
        globalPanel.setBorder(border);
        tasksViewPanel.setLayout(new BoxLayout(tasksViewPanel, BoxLayout.Y_AXIS));
        tasksViewPanel.setBorder(border);

        assaigneeControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        assaigneeControlPanel.setSize(new Dimension(100, 100));
        assaigneeControlPanel.setBorder(border);

        assaigneeControlPanel.add(viewTextName);
        assaigneeControlPanel.add(viewTextLastName);
        assaigneeControlPanel.add(viewTextPost);
        assaigneeControlPanel.add(addAssaigneeButton);

        viewTextName.setSize(new Dimension(100, 100));
        viewTextLastName.setSize(new Dimension(100, 100));
        viewTextPost.setSize(new Dimension(100, 100));
        viewTextConsole.setSize(new Dimension(100, 100));
        addAssaigneeButton.setSize(new Dimension(100, 100));

        taskControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        taskControlPanel.setSize(new Dimension(100, 100));
        taskControlPanel.setBorder(border);

        taskControlPanel.add(viewTextTName);
        taskControlPanel.add(viewTextDescription);
        taskControlPanel.add(viewTextDeadlineYear);
        taskControlPanel.add(viewTextDeadlineMonth);
        taskControlPanel.add(viewTextDeadlineDay);
        taskControlPanel.add(viewTextDeadlineHour);
        taskControlPanel.add(viewTextPriority);
        taskControlPanel.add(viewTextStatus);
        taskControlPanel.add(viewTextSubTask);
        taskControlPanel.add(addTaskButton);

        viewTextTName.setSize(new Dimension(100, 100));
        viewTextDescription.setSize(new Dimension(100, 100));
        viewTextDeadlineYear.setSize(new Dimension(100, 100));
        viewTextDeadlineMonth.setSize(new Dimension(100, 100));
        viewTextDeadlineDay.setSize(new Dimension(100, 100));
        viewTextDeadlineHour.setSize(new Dimension(100, 100));
        viewTextPriority.setSize(new Dimension(100, 100));
        viewTextStatus.setSize(new Dimension(100, 100));
        viewTextSubTask.setSize(new Dimension(100, 100));
        addTaskButton.setSize(new Dimension(100, 100));

        globalPanel.add(assaigneeControlPanel);
        globalPanel.add(taskControlPanel);
        globalPanel.add(assigneesViewPanel);
        globalPanel.add(tasksViewPanel);
        globalPanel.add(viewTextConsole);
        viewFrame.add(globalPanel);


        addAssaigneeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.addAssignee(String.valueOf(viewTextName.getText()), String.valueOf(viewTextLastName.getText()), String.valueOf(viewTextPost.getText()));
                } catch (RuntimeException e1) {
                    updateViewTextConsole(e1.toString());
                }
            }
        }
        );
        addTaskButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {

                                                try {
                                                    controller.addTask(String.valueOf(viewTextTName.getText()), String.valueOf(viewTextDescription.getText()), String.valueOf(viewTextDeadlineYear.getText()), String.valueOf(viewTextDeadlineMonth.getText()), String.valueOf(viewTextDeadlineDay.getText()), String.valueOf(viewTextDeadlineHour.getText()), String.valueOf(viewTextPriority.getText()), String.valueOf(viewTextStatus.getText()), String.valueOf(viewTextSubTask.getText()));
                                                } catch (RuntimeException e1) {
                                                    updateViewTextConsole(e1.toString());

                                                }
                                            }
                                        }
        );

        displayModels(model);

    }


    public class EntityPresenter {

        public void displayTask(Task task) {
            Border border = BorderFactory.createLineBorder(Color.black);
            JTextField taskName = new JTextField(task.getTaskName());
            JTextField description = new JTextField(task.getDescription());
            JTextField deadlineYear = new JTextField(task.getDeadlineYear());
            JTextField deadlineMonth = new JTextField(task.getDeadlineMonth());
            JTextField deadlineDay = new JTextField(task.getDeadlineDay());
            JTextField deadlineHour = new JTextField(task.getDeadlineHour());
            JTextField priority = new JTextField(task.getPriority());
            JTextField status = new JTextField(task.getStatus());
            JTextField subtask = new JTextField(task.getSubtask());
            JPanel certainParentTaskPanel = new JPanel();
            JPanel certainTaskPanel = new JPanel();
            certainTaskPanel.setBorder(border);

            certainTaskPanel.setLayout(new GridLayout(3, 3));
            JButton removeButton = new JButton("Delete");
            JButton updateButton = new JButton("Update");
            certainTaskPanel.add(taskName);
            certainTaskPanel.add(description);
            certainTaskPanel.add(deadlineYear);
            certainTaskPanel.add(deadlineMonth);
            certainTaskPanel.add(deadlineDay);
            certainTaskPanel.add(deadlineHour);
            certainTaskPanel.add(priority);
            certainTaskPanel.add(status);
            certainTaskPanel.add(subtask);
            certainTaskPanel.add(updateButton);
            certainTaskPanel.add(removeButton);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.deleteTask(task);
                    } catch (RuntimeException e1) {
                        updateViewTextConsole(e1.toString());

                    }
                }
            });

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Task taskToUpdate = new TaskImpl(String.valueOf(taskName.getText()), String.valueOf(description.getText()), String.valueOf(deadlineYear.getText()), String.valueOf(deadlineMonth.getText()), String.valueOf(deadlineDay.getText()), String.valueOf(deadlineHour.getText()), String.valueOf(priority.getText()), String.valueOf(status.getText()), String.valueOf(subtask.getText()));
                        controller.updateTask(taskToUpdate);

                    } catch (RuntimeException e1) {
                        updateViewTextConsole(e1.toString());

                    }
                }
            });
            certainParentTaskPanel.add(certainTaskPanel);
            certainParentTaskPanel.setLayout(new GridLayout(1, 3));

            tasksViewPanel.add(certainParentTaskPanel);


        }

        public void displayAssignee(Assignee assignee) {
            Border border = BorderFactory.createLineBorder(Color.black);
            JTextField assigneeName = new JTextField(assignee.getName());
            JTextField lastName = new JTextField(assignee.getLastname());
            JTextField post = new JTextField(assignee.getPost());
            JPanel certainParentAssigneePanel = new JPanel();
            JPanel certainAssigneePanel = new JPanel();
            certainAssigneePanel.setBorder(border);

            certainAssigneePanel.setLayout(new GridLayout(3, 3));
            JButton removeButton = new JButton("Delete");
            JButton updateButton = new JButton("Update");
            certainAssigneePanel.add(assigneeName);
            certainAssigneePanel.add(lastName);
            certainAssigneePanel.add(post);
            certainAssigneePanel.add(updateButton);
            certainAssigneePanel.add(removeButton);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        controller.deleteAssignee(assignee);
                    } catch (RuntimeException e1) {
                        updateViewTextConsole(e1.toString());

                    }
                }
            });

            updateButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Assignee assigneeToUpdate = new AssigneeImpl(String.valueOf(assigneeName.getText()), String.valueOf(lastName.getText()), String.valueOf(post.getText()));
                        controller.updateAssignee(assigneeToUpdate);

                    } catch (RuntimeException e1) {
                        updateViewTextConsole(e1.toString());

                    }
                }
            });
            certainParentAssigneePanel.add(certainAssigneePanel);
            certainParentAssigneePanel.setLayout(new GridLayout(1, 3));

            assigneesViewPanel.add(certainParentAssigneePanel);


        }
    }



    @Override
    public void update(Observable o, Object arg) {
        TaskManagerModel model = (TaskManagerModel) o;
        displayModels(model);
    }

    public void updateViewTextConsole(String textConsole) {
        viewTextConsole.setText(textConsole);
    }


    public void displayModels(TaskManagerModel model) {

        assigneesViewPanel.removeAll();
        for (Assignee assignee : model.getAssignees()) {
            updateViewTextConsole(assignee.getName() + " " +
                    assignee.getLastname() + " " +
                    assignee.getPost()
            );
            entityPresenter.displayAssignee(assignee);
        }

        tasksViewPanel.removeAll();
        for (Task task : model.getTasks()) {
            updateViewTextConsole(task.getTaskName() + " " +
                    task.getDescription() + " " +
                    task.getDeadlineYear() + " " +
                    task.getDeadlineMonth() + " " +
                    task.getDeadlineDay() + " " +
                    task.getDeadlineHour() + " " +
                    task.getPriority() + " " +
                    task.getStatus() + " " +
                    task.getSubtask()
            );
            entityPresenter.displayTask(task);
        }

        viewFrame.pack();
    }

}
