package labs.taskmanger.server.ejb.asssignee;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface ManagerAssigneeHome extends EJBHome {

    public ManagerAssignee create () throws RemoteException, CreateException;


}