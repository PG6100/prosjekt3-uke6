package no.nith.pg6100.p2p;

import javax.jms.*;
import javax.naming.InitialContext;

public class BasicQueueSender {
    public static void main( String[] args )    {
        try {
            InitialContext context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/QueueConnectionFactory");
            System.out.println("got queue connection factory: " + connectionFactory.getClass().getName());
            QueueConnection con = connectionFactory.createQueueConnection();
            try {
                Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
                try {
                    Destination destination = (Destination) context.lookup("jms/Queue");
                    MessageProducer producer = session.createProducer(destination);
                    TextMessage textMessage = session.createTextMessage();
                    textMessage.setText("Heia fisk frosk, melding fra basic queue sender!");
                    producer.send(textMessage);
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
