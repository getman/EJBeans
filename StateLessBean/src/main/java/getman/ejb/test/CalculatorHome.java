package getman.ejb.test;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 19.02.2016.
 */
public interface CalculatorHome extends EJBHome {
    public CalculatorRemote create() throws RemoteException;
}
