package no.nith.pg6100.durable;

import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;

public class DurablePublisher {

    public static void main(String[] args) {
        try {
            InitialContext context = new InitialContext();
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/TopicConnectionFactory");
            System.out.println("got topic connection factory: " + connectionFactory.getClass().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
