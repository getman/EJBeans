package getman.ejb3.test;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Parfenov Artem on 19.02.2016.
 */
//@Stateless(name = "CalculatorRemote3", mappedName = "Map-CalculatorRemote3")
@Stateless(description = "my simple bean")
@Remote
public class CalculatorBean3 implements CalculatorRemote3 {
    public CalculatorBean3() {}

    public double add(double a, double b) {
        return (double) a + b;
    }
}

