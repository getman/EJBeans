package getman.ejb.test;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

/**Created by Parfenov Artem on 15.02.2016.*/
public interface CalculatorRemote extends EJBObject {
    /**Performs addition of two numbers*/
    public double add(double a, double b)throws RemoteException;
    /**Performs subtraction b number from a numberr
     * @param a double - number to subtract from
     * @param b double - number that subtracted from a*/
    public double subtract(double a, double b) throws RemoteException;
}

