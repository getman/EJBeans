package getman.test.ejb3.stateless.calc;

import javax.annotation.security.PermitAll;
import javax.ejb.Remote;
import javax.ejb.Remove;

/**
 * Created by Parfenov Artem on 19.02.2016.
 */
@Remote
public interface CalculatorRemote3 {
    public double add(double a, double b);
    @Remove
    public void remove();
}
