package getman.ejb3.test;

import javax.annotation.security.PermitAll;
import javax.ejb.Remote;

/**
 * Created by Parfenov Artem on 19.02.2016.
 */
@PermitAll
@Remote
public interface CalculatorRemote3 {
    public double add(double a, double b);
}
