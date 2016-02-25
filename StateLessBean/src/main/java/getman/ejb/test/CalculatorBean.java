package getman.ejb.test;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/** EJB component with business method
 * Created by Parfenov Artem on 15.02.2016.
 */
public class CalculatorBean implements SessionBean {

    public double add(double a, double b) throws RemoteException, CreateException {
        return (double) a + b;
    }

    public double subtract(double a, double b) throws RemoteException, CreateException {
        return (double) a - b;
    }

    // used for creating a reference to RemoteInterface
    public void ejbCreate () throws CreateException {
        System.out.println("ejbCreate");
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
        System.out.println("setSessionContext");
    }

    public void ejbRemove() throws EJBException, RemoteException {
        System.out.println("ejbRemove");
    }

    public void ejbActivate() throws EJBException, RemoteException {
        System.out.println("ejbActivate");
    }

    public void ejbPassivate() throws EJBException, RemoteException {
        System.out.println("ejbPassivate");
    }
}
