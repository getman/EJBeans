package getman.ejb.test;

import javax.ejb.EJBHome;
import java.rmi.RemoteException;

/** Home interface
 * Created by Parfenov Artem on 25.02.2016.
 */
public interface CalculatorWithMemoryHome extends EJBHome{
    public CalculatorWithMemoryRemote create() throws RemoteException;
}
