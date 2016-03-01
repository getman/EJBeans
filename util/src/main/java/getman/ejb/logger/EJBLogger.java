package getman.ejb.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Parfenov Artem on 29.02.2016.
 */
public class EJBLogger {

    public static Logger getLogger(Class<?> cl) {
        System.setProperty("_APP_NAME_", cl.getName());
        Logger logger = LogManager.getLogger(cl.getName());
        return logger;
    }
}
