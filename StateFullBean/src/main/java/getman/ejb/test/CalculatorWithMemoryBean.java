package getman.ejb.test;

import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.Logger;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;

/**
 * Created by Parfenov Artem on 25.02.2016.
 */
public class CalculatorWithMemoryBean implements SessionBean{
    private Logger logger = EJBLogger.getLogger(getClass());

    private double memory = 0;

    /**summarize the two double values*/
    public double add(double a, double b) throws RemoteException {
        return (double) a + b;
    }
    /**adds the specified double to the memory and returns the result*/
    public double addToMemory(double a) throws RemoteException {
        logger.trace("statefull 2.0, memory state is " + (double)(memory + a));
        return memory += a;
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
