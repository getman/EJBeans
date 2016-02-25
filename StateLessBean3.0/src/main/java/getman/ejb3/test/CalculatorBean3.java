package getman.ejb3.test;

import javax.ejb.Stateless;

/**
 * Created by Parfenov Artem on 19.02.2016.
 */
@Stateless
public class CalculatorBean3 implements CalculatorRemote3 {
    public CalculatorBean3() {}

    public double add(double a, double b) {
        return (double) a + b;
    }
}

