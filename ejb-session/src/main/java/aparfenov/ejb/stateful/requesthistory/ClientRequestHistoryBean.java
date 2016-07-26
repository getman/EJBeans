package aparfenov.ejb.stateful.requesthistory;

import aparfenov.ejb.stateless.logger.LoggerByJdbcRequestBean;
import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Parfenov Artem on 12.07.2016.
 */
@Stateful
@Local
@TransactionManagement(TransactionManagementType.BEAN)
public class ClientRequestHistoryBean {
    @Resource
    private EJBContext ejbContext;

    @EJB
    private LoggerByJdbcRequestBean loggerJdbc;

    private Logger logger = EJBLogger.getLogger(getClass());
    private List<String> requests = new ArrayList<String>();

    //-------------Methods--------------
    public void addRequestToHistory(String clientRequest) throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        UserTransaction ut = ejbContext.getUserTransaction();
        ut.begin();
        requests.add(clientRequest);
        logger.info("new client request in the history");
        StringBuilder totalRequests = new StringBuilder();
        for (String nextRequest: requests) {
            totalRequests.append("\n");
            totalRequests.append(nextRequest);
        }
        logger.info("Total requests: " + totalRequests.toString());
        if (clientRequest.contains("BMTUzurpator")) {
            //set transaction state to broken
            ut.rollback();
        } else {
            ut.commit();
        }
    }
}
