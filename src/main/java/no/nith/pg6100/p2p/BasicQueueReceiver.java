package no.nith.pg6100.p2p;

import javax.jms.*;
import javax.naming.InitialContext;

public class BasicQueueReceiver {

    public static void main(String[] args) {
        try {
            System.out.println("looking up connection factory...");
            InitialContext context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/QueueConnectionFactory");
            System.out.println("got queue connection factory: "+connectionFactory.getClass().getName());
            QueueConnection con = connectionFactory.createQueueConnection();
            try {
                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
                try {
                    MessageConsumer consumer = session.createConsumer((Destination) context.lookup("jms/Queue"));
                    System.out.println("Waiting for a message to arrive...");
                    con.start();
                    Message msg = consumer.receive();
                    System.out.println("received message.");
                    System.out.println(msg);
                } finally {
                    session.close();
                }
            } finally {
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
