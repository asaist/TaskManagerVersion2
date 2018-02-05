package common.service;

import common.entity.Task;

public interface FileWork {
    void fileWriter(Task task);

    void fileReader();

}
