import aparfenov.ejb.stateful.requesthistory.ClientRequestHistoryBean;
import aparfenov.ejb.stateless.logger.LoggerByJdbcRequestBean;
import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.Logger;

import javax.ejb.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.transaction.*;

/**Receives and processes requests to the book store (is there a specified book present)
 * Created by Parfenov Artem on 04.07.2016.
 */
@MessageDriven(mappedName = "jms/aparfenov/my.2nd.test.queue")
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class BookRequestListener implements MessageListener {
    private Logger logger = EJBLogger.getLogger(getClass());
    @EJB
    private LoggerByJdbcRequestBean loggerJdbc;
    @EJB
    private ClientRequestHistoryBean clienthistory;

    public void onMessage(Message message) {
        try {
            processRequest(message);
        } catch (Exception e) {
            logger.error("Something goes wrong while processing the request to the book store", e);
        }
    }

    public void transitionalMethod(Message msg) throws JMSException {
        processRequest(msg);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    /**performs the processing of message with request for a specified book*/
    private void processRequest(Message msg) throws JMSException {
        logger.info("MDB: received message. CMT was started");
        if (msg instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) msg;
            String msgText = txtMsg.getText();
            logger.info("message text:" + msgText);
            loggerJdbc.log(msgText);
            try {
                clienthistory.addRequestToHistory(msgText);
            } catch (SystemException e) {
                logger.error("BMT broken due of SystemException", e);
                e.printStackTrace();
            } catch (NotSupportedException e) {
                logger.error("BMT broken due of NotSupportedException", e);
                e.printStackTrace();
            } catch (HeuristicRollbackException e) {
                logger.error("BMT broken due of HeuristicRollbackException", e);
                e.printStackTrace();
            } catch (HeuristicMixedException e) {
                logger.error("BMT broken due of HeuristicMixedException", e);
                e.printStackTrace();
            } catch (RollbackException e) {
                logger.error("BMT broken due of RollbackException", e);
                e.printStackTrace();
            }
        }
        logger.info("MDB: CMT was successfully finished");
    }
}
