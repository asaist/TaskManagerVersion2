package common.entity;



public interface DataObject {
    enum String {CREATE, UPDATE, DELETE, UPLOAD}


    void setEntity(Object entity);

    Object getEntity();

    void setAction(String action);

    String getAction();
}
