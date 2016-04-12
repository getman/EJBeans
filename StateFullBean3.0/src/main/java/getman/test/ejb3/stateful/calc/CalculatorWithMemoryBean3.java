package getman.test.ejb3.stateful.calc;

import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;

/**Created by Parfenov Artem on 10.03.2016.
 */
@Stateful(name = "CalculatorStatefulEJBName")
public class CalculatorWithMemoryBean3 implements CalculatorWithMemoryRemote3 {
    private transient Logger logger;

    private double memory = 0;

    public CalculatorWithMemoryBean3() {
        logger = EJBLogger.getLogger(getClass());
        logger.trace("statefull 3.0 constructed");
    }

    public double addToMemory(double a) {
        logger.trace("statefull 3.0, memory state: " + (double)(memory + a));
        return memory += a;
    }

    public void removeMe() {
        logger.trace("stateless 3.0 remove");
    }


    @PostConstruct
    public void afterConstructing() {
        logger.trace("stateless 3.0 after constructing");
    }

    @PostActivate
    public void afterActivate() {
        logger.trace("stateless 3.0 after activate");
    }

    @PreDestroy
    public void beforeDestroy() {
        logger.trace("stateless 3.0 before destroy");
    }

    @PrePassivate
    public void beforePassivate() {
        logger.trace("stateless 3.0 before passivate");
    }
}
