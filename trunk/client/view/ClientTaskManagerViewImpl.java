package client.view;

import client.controller.ClientTaskManagerController;
import client.model.ClientTaskManagerModel;
import common.entity.TaskImpl;
import common.entity.Assignee;
import common.entity.Task;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Date;
import java.util.Observable;
import java.util.Observer;

public class ClientTaskManagerViewImpl implements ClientTaskManagerView, Observer {

    private final ClientTaskManagerController controller;
    private final ClientTaskManagerModel model;

    private final JPanel assaigneeControlPanel;
    private final JPanel taskControlPanel;
    private final JPanel globalPanel;
    private final JPanel tasksViewPanel;
    private final TaskPresenter taskPresenter = new TaskPresenter();

    //    private   JPanel TaskPanelButton;
    private final JFrame viewFrame;
    private final JTextField viewTextName;
    private final JTextField viewTextLastName;
    private final JTextField viewTextPost;
    private final JTextField viewTextConsole;
    private final JTextField viewTextTName;
    private final JTextField viewTextDescription;
    private final JTextField viewTextDeadline;
    private final JTextField viewTextPriority;
    private final JTextField viewTextStatus;
    private final JTextField viewTextSubTask;
    private final JButton addAssaigneeButton;
    private final JButton addTaskButton;
    private final JButton deleteAssigneeButton;
    private final JButton deleteTaskButton;
    private final String textViewFrame = "viewClient";
    private final String addAssaigneeButtonLable = "Add Assaignee";
    private final String addTaskButtonLable = "Add Task";
    private final String deleteAssigneeButtonLable = "Delete Assignee";
    private final String deleteTaskButtonLable = "Delete Task";
    private final String textName = "Name";
    private final String textLastName = "Last Name";
    private final String textConsole = "Console";
    private final String textPost = "Post";
    private final String texTName = "TaskName";
    private final String textDescription = "Description";
    private final String textDeadline = "2017:01:02";
    private final String textPriority = "Priority";
    private final String textStatus = "Status";
    private final String textSubtasks = "Subtasks";


    public ClientTaskManagerViewImpl(ClientTaskManagerController controller, ClientTaskManagerModel model) {
        this.controller = controller;
        this.model = model;

        viewFrame = new JFrame(textViewFrame);
        tasksViewPanel = new JPanel();
        globalPanel = new JPanel();
        assaigneeControlPanel = new JPanel();
        taskControlPanel = new JPanel();

        addAssaigneeButton = new JButton(addAssaigneeButtonLable);
        addTaskButton = new JButton(addTaskButtonLable);
        deleteAssigneeButton = new JButton(deleteAssigneeButtonLable);
        deleteTaskButton = new JButton(deleteTaskButtonLable);

        viewTextName = new JTextField(textName);
        viewTextLastName = new JTextField(textLastName);
        viewTextPost = new JTextField(textPost);
        viewTextConsole = new JTextField(textConsole);

        viewTextTName = new JTextField(texTName);
        viewTextDescription = new JTextField(textDescription);
        viewTextDeadline = new JTextField(textDeadline);
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
        assaigneeControlPanel.add(viewTextConsole);
        assaigneeControlPanel.add(addAssaigneeButton);
        assaigneeControlPanel.add(deleteAssigneeButton);

        viewTextName.setSize(new Dimension(100, 100));
        viewTextLastName.setSize(new Dimension(100, 100));
        viewTextPost.setSize(new Dimension(100, 100));
        viewTextConsole.setSize(new Dimension(100, 100));
        addAssaigneeButton.setSize(new Dimension(100, 100));
        deleteAssigneeButton.setSize(new Dimension(100, 100));

        taskControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        taskControlPanel.setSize(new Dimension(100, 100));
        taskControlPanel.setBorder(border);

        taskControlPanel.add(viewTextTName);
        taskControlPanel.add(viewTextDescription);
        taskControlPanel.add(viewTextDeadline);
        taskControlPanel.add(viewTextPriority);
        taskControlPanel.add(viewTextStatus);
        taskControlPanel.add(viewTextSubTask);
        taskControlPanel.add(addTaskButton);
        taskControlPanel.add(deleteTaskButton);

        viewTextTName.setSize(new Dimension(100, 100));
        viewTextDescription.setSize(new Dimension(100, 100));
        viewTextDeadline.setSize(new Dimension(100, 100));
        viewTextPriority.setSize(new Dimension(100, 100));
        viewTextStatus.setSize(new Dimension(100, 100));
        viewTextSubTask.setSize(new Dimension(100, 100));
        addTaskButton.setSize(new Dimension(100, 100));
        deleteTaskButton.setSize(new Dimension(100, 100));

        globalPanel.add(assaigneeControlPanel);
        globalPanel.add(taskControlPanel);
        globalPanel.add(tasksViewPanel);
        viewFrame.add(globalPanel);


        addAssaigneeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.addAssignee(String.valueOf(viewTextName.getText()), String.valueOf(viewTextLastName.getText()), String.valueOf(viewTextPost.getText()));
                } catch (RuntimeException e1) {
                    System.out.println(e1);
                }
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {


                                                try {
                                                    controller.addTask(String.valueOf(viewTextTName.getText()), String.valueOf(viewTextDescription.getText()), (String.valueOf(viewTextDeadline.getText())), String.valueOf(viewTextPriority.getText()), String.valueOf(viewTextStatus.getText()), String.valueOf(viewTextSubTask.getText()));
                                                } catch (RuntimeException e1) {
                                                    updateViewTextConsole(e1.toString());

                                                }
                                            }
                                        }
        );

        deleteAssigneeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    throw new RuntimeException("I do not know how to do this, but I'll soon learn");
                } catch (RuntimeException e1) {
                    System.out.println(e1);
                }
            }
        });

        deleteTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    throw new RuntimeException("I do not know how to do this, but I'll soon learn");
                } catch (RuntimeException e1) {
                    System.out.println(e1);
                }
            }
        });
        displayModels(model);

    }


    public class TaskPresenter {

        public void displayTask(Task task) {
            Border border = BorderFactory.createLineBorder(Color.black);
            JTextField taskName = new JTextField(task.getTaskName());
            JTextField description = new JTextField(task.getDescription());
            JTextField deadline = new JTextField(task.getDeadline().toString());
            JTextField priority = new JTextField(task.getPriority());
            JTextField status = new JTextField(task.getStatus());
            JPanel certainParentTaskPanel = new JPanel();
            JPanel certainTaskPanel = new JPanel();
            certainTaskPanel.setBorder(border);

            certainTaskPanel.setLayout(new GridLayout(3, 3));
            JButton removeButton = new JButton("Delete");
            JButton updateButton = new JButton("Update");//toString = имя кнопки /вызывать task.getId
            certainTaskPanel.add(taskName);
            certainTaskPanel.add(description);
            certainTaskPanel.add(deadline);
            certainTaskPanel.add(priority);
            certainTaskPanel.add(status);
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
                        Task taskToUpdate = new TaskImpl(String.valueOf(taskName.getText()), String.valueOf(description.getText()), String.valueOf(deadline.getText()), String.valueOf(priority.getText()), String.valueOf(status.getText()));
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
    }


    @Override
    public void update(Observable o, Object arg) {
        ClientTaskManagerModel model = (ClientTaskManagerModel) o;
        displayModels(model);
    }

    public void updateViewTextConsole(String textConsole) {
        viewTextConsole.setText(textConsole);
        System.out.println(textConsole);
    }


    public void displayModels(ClientTaskManagerModel model) {

        tasksViewPanel.removeAll();
        for (Assignee assignee : model.getAssignees()) {
            updateViewTextConsole(assignee.getName() + " " +
                    assignee.getLastName() + " " +
                    assignee.getPost()
            );

        }
        for (Task task : model.getTasks()) {
            updateViewTextConsole(task.getTaskName() + " " +
                    task.getDescription() + " " +
                    task.getPriority() + " " +
                    task.getStatus()
            );
            taskPresenter.displayTask(task);
        }

        viewFrame.pack();
    }

}
