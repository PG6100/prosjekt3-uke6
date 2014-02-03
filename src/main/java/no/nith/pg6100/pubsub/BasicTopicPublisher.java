package no.nith.pg6100.pubsub;

import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BasicTopicPublisher {

    public static void main(String[] args) throws NamingException {
        InitialContext context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/TopicConnectionFactory");
        System.out.println("got topic connection factory: " + connectionFactory.getClass().getName());

    }

}
