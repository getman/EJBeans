package getman.test.ejb3.stateless.calc;

import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

/**
 * Created by Parfenov Artem on 19.02.2016.
 */
@Stateless
public class CalculatorBean3 implements CalculatorRemote3 {
    private Logger logger = EJBLogger.getLogger(getClass());

    public CalculatorBean3() {
        logger.trace("calculator constructed");
    }

    public double add(double a, double b) {
        logger.trace("calculator:" + ((double)a + b));
        return (double) a + b;
    }

    @PostConstruct
    public void afterConstuct() {
        logger.trace("after constructor");
    }

    @PreDestroy
    public void preDestroy() {
        logger.trace("before destroy");
    }

    public void remove() {
        logger.trace("remove is aquired");
    }
}

