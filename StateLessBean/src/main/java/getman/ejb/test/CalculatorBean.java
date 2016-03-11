package getman.ejb.test;

import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/** EJB component with business method
 * Created by Parfenov Artem on 15.02.2016.
 */
public class CalculatorBean implements SessionBean {

    private Logger logger = EJBLogger.getLogger(getClass());

    public double add(double a, double b) throws RemoteException, CreateException {
        System.out.println("system stateless add:" + (a + b));
        logger.trace("stateless add:" + (a + b));
        return (double) a + b;
    }

    public double subtract(double a, double b) throws RemoteException, CreateException {
        return (double) a - b;
    }

    // used for creating a reference to RemoteInterface
    public void ejbCreate () throws CreateException {
        logger.trace("ejbCreate");
    }

    public void setSessionContext(SessionContext sessionContext) throws EJBException, RemoteException {
        logger.trace("setSessionContext");
    }

    public void ejbRemove() throws EJBException, RemoteException {
        logger.trace("ejbRemove");
    }

    public void ejbActivate() throws EJBException, RemoteException {
        logger.trace("ejbActivate");
    }

    public void ejbPassivate() throws EJBException, RemoteException {
        logger.trace("ejbPassivate");
    }
}
