package getman.test.ejb3.stateful.calc;

import javax.ejb.Remote;
import javax.ejb.Remove;

/**
 * Created by Parfenov Artem on 10.03.2016.
 */
@Remote
public interface CalculatorWithMemoryRemote3 {
    public double addToMemory(double a);
    @Remove
    public void removeMe();
}
