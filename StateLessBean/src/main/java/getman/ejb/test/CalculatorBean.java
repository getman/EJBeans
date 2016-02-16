package getman.ejb.test;

/**
 * Created by Parfenov Artem on 15.02.2016.
 */
public class CalculatorBean implements CalculatorBeanRemote {

    public double add(double a, double b) {
        return (double) a + b;
    }

    public double subtract(double a, double b) {
        return (double) a - b;
    }
}
