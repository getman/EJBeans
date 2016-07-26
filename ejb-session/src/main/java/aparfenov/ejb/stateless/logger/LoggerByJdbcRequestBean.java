package aparfenov.ejb.stateless.logger;

import getman.ejb.logger.EJBLogger;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Parfenov Artem on 12.07.2016.
 */

@Stateless
@Local
//@TransactionManagement(TransactionManagementType.CONTAINER)
public class LoggerByJdbcRequestBean {
    @Resource(name = "jdbc/postgresql/postgres")
    private DataSource ds;

    private Logger logger = EJBLogger.getLogger(getClass());

    //---------------Methods--------------
    @PostConstruct
    public void init() {
        logger.info("Initialize LoggerByJdbcRequestBean");
    }

//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
//    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void log(String msgToLog) {
        logger.info("Stateless: CMT transaction continues");
        try {
            saveLogToDb(msgToLog);
        } catch (SQLException e) {
            logger.error("SQL exception thrown while logging into the DB", e);
        } catch (Exception e) {
            logger.error("Something goes wrong while logging into the DB", e);
            throw new EJBException(e);
        }
        logger.info("Stateless: finishing work in CMT transaction");
//        throw new RuntimeException();
    }

    private void saveLogToDb(String msgToLog) throws SQLException, NamingException {
        final String SQL_REQUEST = "insert into log(text) values('" + msgToLog + "')";
        Connection conn = ds.getConnection();
        Statement statement = conn.createStatement();
        statement.execute(SQL_REQUEST);
        statement.close();
        conn.close();
        InitialContext ic = new InitialContext();

        String keyWordToThrowRuntimeException = (String) ic.lookup("java:comp/env/keyWord");
        logger.info("key word is:" + keyWordToThrowRuntimeException);

        if (msgToLog.contains(keyWordToThrowRuntimeException)) {
            throw new RuntimeException(keyWordToThrowRuntimeException);
        }
        logger.info("log was successfully saved to the DB");
    }
}
