package no.nith.pg6100.pubsub;

import no.nith.pg6100.ShutdownHook;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BasicTopicSubscriber implements MessageListener {

    public static void main(String[] args) {
        try {
            new BasicTopicSubscriber().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws NamingException, JMSException {
        System.out.println("creating a topic subscriber ....");
        InitialContext context = new InitialContext();
        TopicConnectionFactory conFactory = (TopicConnectionFactory) context.lookup("jms/TopicConnectionFactory");
        TopicConnection con = conFactory.createTopicConnection(); // alt. conFactory.createConnection()
        ShutdownHook.add(con);
        try {
            TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE); // alt. con.createSession()
            Topic topic = (Topic) context.lookup("jms/Topic");
            TopicSubscriber consumer = session.createSubscriber(topic); // kan også bruke session.createConsumer()
            consumer.setMessageListener(this);
            System.out.println("listening to incoming topic messages...");
            con.start();
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public void onMessage(final Message message) {
        System.out.println("Message received on jms/Topic, printing it!");
        System.out.println(message);
    }
}
