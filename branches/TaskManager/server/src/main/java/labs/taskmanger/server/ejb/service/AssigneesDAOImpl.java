package labs.taskmanger.server.ejb.service;

import labs.taskmanger.server.domain.Assignee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AssigneesDAOImpl implements AssigneesDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void addAssignee(Assignee assignee){
        entityManager.persist(assignee);

    }

    public void editAssignee(Assignee assignee){

        entityManager.merge(assignee);
    }

    public void deleteAssignee(int assigneeId){

        entityManager.remove(getAssignee(assigneeId));
    }

    public Assignee getAssignee(int assigneeId){

        return entityManager.find(Assignee.class, assigneeId);

    }

    public List<Assignee> getAllAssignee(){

        return entityManager.createNamedQuery("Assignee.getAll").getResultList();

    }
}