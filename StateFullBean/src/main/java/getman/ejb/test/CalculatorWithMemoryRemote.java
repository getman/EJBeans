package getman.ejb.test;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/** EJB Component remote interface
 * Created by Parfenov Artem on 25.02.2016.
 */
public interface CalculatorWithMemoryRemote extends EJBObject {
    /**summarize the two double values*/
    public double add(double a, double b) throws RemoteException;
    /**adds the specified double to the memory and returns the result*/
    public double addToMemory(double a) throws RemoteException;
}
