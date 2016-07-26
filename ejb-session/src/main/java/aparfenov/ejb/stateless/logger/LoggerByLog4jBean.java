package aparfenov.ejb.stateless.logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * Created by Parfenov Artem on 12.07.2016.
 */

@Stateless
@Local
@TransactionManagement(TransactionManagementType.BEAN)
public class LoggerByLog4jBean {

    //------------Methods------------
    public void log(String msgToLog) {

    }
}
