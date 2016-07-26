package getman.servlet3;

import javax.jms.*;

/**
 * Created by Parfenov Artem on 04.07.2016.
 */
public class RequestSender {
    //-------------------Fields--------------
    public static final String AUTHOR_ID_FIELD = "author_id";
    public static final String BOOK_ID_FIELD = "book_id";

    private ConnectionFactory connectionFactory;
    private Destination targetQueueName;

    //----------------Methods--------------
    /*public RequestSender(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }*/

    public RequestSender(ConnectionFactory connectionFactory, Destination targetQueueName) {
        this.connectionFactory = connectionFactory;
        this.targetQueueName = targetQueueName;
    }

    /**sends a request to the book store to find out if there is a specified book in it*/
    public void sendBookRequest(String bookName, String author) throws JMSException {
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination destinationQueue = session.createQueue(buildQueueName(queueManagerName, targetQueueName));
//        Destination destinationQueue = session.createQueue(targetQueueName);
        MessageProducer producer = session.createProducer(targetQueueName);

        TextMessage requestMsg = session.createTextMessage();
        requestMsg.setText("Request for book name: " + bookName + ", author name: " + author);
        requestMsg.setIntProperty(AUTHOR_ID_FIELD, 12);
        requestMsg.setIntProperty(BOOK_ID_FIELD, 10);

        int msgPriority = 3;
        //Время жизни сообщения, мс
        long processRequestExpirationTime = 10000;
        producer.send(requestMsg, DeliveryMode.NON_PERSISTENT, msgPriority, processRequestExpirationTime);
        connection.close();
    }

    private String buildQueueName(String queueManagerName, String queueName) {
        StringBuilder sessionConnString = new StringBuilder();
        sessionConnString.append("queue://");
        /*if (queueManager == null) {
            queueManager = KsddConfig.INSTANCE.getLocalQueueManagerName();
        }*/
        sessionConnString.append(queueManagerName);
        sessionConnString.append("/").append(queueName);
        addSessionOptions(sessionConnString);

        return sessionConnString.toString();
    }

    private void addSessionOptions(StringBuilder sessionConnString) {
        if (!sessionConnString.toString().contains("?")) {
            sessionConnString
                    .append("?CCSID=")
                    .append(1208).append("&targetClient=0");
        } else {
            sessionConnString
                    .append("&CCSID=")
                    .append(1208).append("&targetClient=0");
        }
    }
}
