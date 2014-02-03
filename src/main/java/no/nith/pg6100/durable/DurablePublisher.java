package no.nith.pg6100.durable;

import javax.jms.*;
import javax.naming.InitialContext;

public class DurablePublisher {
    public static void main( String[] args )    {
        try {
            InitialContext context = new InitialContext();
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/TopicConnectionFactory");
            System.out.println("got topic connection factory: " + connectionFactory.getClass().getName());
            TopicConnection con = connectionFactory.createTopicConnection();
            try {
                TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
                try {
                    Destination destination = (Destination) context.lookup("jms/Topic");
                    MessageProducer producer = session.createProducer(destination);
                    TextMessage textMessage = session.createTextMessage();
                    textMessage.setText("Heia fisk frosk , dette er en topic, meldingen sendes til en Durable Subcription!");
                    producer.send(textMessage, DeliveryMode.PERSISTENT,9,10000);
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
