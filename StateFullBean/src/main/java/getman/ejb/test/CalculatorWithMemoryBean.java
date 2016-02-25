package getman.ejb.test;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 25.02.2016.
 */
public class CalculatorWithMemoryBean implements SessionBean{
    private double memory = 0;

    /**summarize the two double values*/
    public double add(double a, double b) throws RemoteException {
        return (double) a + b;
    }
    /**adds the specified double to the memory and returns the result*/
    public double addToMemory(double a) throws RemoteException {
        return memory += a;
    }

    // used for creating a reference to RemoteInterface
    public void ejbCreate () throws CreateException {
        System.out.println("ejbCreate");
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {

    }

    public void ejbRemove() throws EJBException, RemoteException {

    }

    public void ejbActivate() throws EJBException, RemoteException {

    }

    public void ejbPassivate() throws EJBException, RemoteException {

    }
}
