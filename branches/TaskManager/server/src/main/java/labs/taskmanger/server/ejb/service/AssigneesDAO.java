package labs.taskmanger.server.ejb.service;

import labs.taskmanger.server.domain.Assignee;

import java.util.List;

public interface AssigneesDAO {

    void addAssignee(Assignee assignee);
    void editAssignee(Assignee assignee);
    void deleteAssignee(int assigneeId);
    Assignee getAssignee(int assigneeId);
    List<Assignee> getAllAssignee();
}