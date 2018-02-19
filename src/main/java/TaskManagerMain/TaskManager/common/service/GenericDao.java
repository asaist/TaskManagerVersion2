package TaskManagerMain.TaskManager.common.service;

import TaskManagerMain.TaskManager.common.entity.Entity;

import java.io.IOException;
import java.util.List;

public interface GenericDao<T extends Entity> {
    /**
     * Persist the newInstance object into database
     */
    Integer create(T newInstance);

    /**
     * Retrieve an object that was previously persisted to the database using
     * the indicated id as primary key
     */
    Entity read(Integer id);

    /**
     * Save changes made to a persistent object.
     */
    void update(T transientObject);

    /**
     * Remove an object from persistent storage in the database
     */
    void delete(T persistentObject);

    List<Entity> readAll();

    void checkFile();
}

