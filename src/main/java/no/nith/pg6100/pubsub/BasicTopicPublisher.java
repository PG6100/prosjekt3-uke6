package no.nith.pg6100.pubsub;

import javax.jms.*;
import javax.naming.InitialContext;

public class BasicTopicPublisher {

    public static void main( String[] args )    {
        try {
            InitialContext context = new InitialContext();
            TopicConnectionFactory connectionFactory = (TopicConnectionFactory) context.lookup("jms/TopicConnectionFactory");
            System.out.println("got queue connection factory: " + connectionFactory.getClass().getName());
            TopicConnection con = connectionFactory.createTopicConnection();
            try {
                TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE); // kan også bruke con.createSession()
                try {
                    Topic destination = (Topic) context.lookup("jms/Topic");
                    TopicPublisher publisher = session.createPublisher(destination);
                    TextMessage textMessage = session.createTextMessage();
                    textMessage.setText("Heia fisk frosk , dette er en melding til en mottaker/e som er interessert i jms/Topic!");
                    publisher.send(textMessage);
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
