package getman.ejb.test;

import java.rmi.Remote;

/**Created by Parfenov Artem on 15.02.2016.*/
public interface CalculatorBeanRemote extends Remote {
    /**Performs addition of two numbers*/
    public double add(double a, double b);
    /**Performs subtraction b number from a number
     * @param a double - number to subtract from
     * @param b double - number that subtracted from a*/
    public double subtract(double a, double b);
}

