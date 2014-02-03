package no.nith.pg6100.durable;


import no.nith.pg6100.ShutdownHook;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.net.InetAddress;

public class DurableClient implements MessageListener {

    public static void main(String[] args) {
        try {
            new DurableClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws NamingException, JMSException {
        System.out.println("creating a topic subscriber ....");
        InitialContext context = new InitialContext();
        TopicConnectionFactory conFactory = (TopicConnectionFactory) context.lookup("jms/TopicConnectionFactory");
        TopicConnection con = conFactory.createTopicConnection();
        ShutdownHook.add(con);
        try {
            con.setClientID(InetAddress.getLocalHost().getHostName());
            TopicSession session = con.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber subscriber = session.createDurableSubscriber((Topic) context.lookup("jms/Topic"), "MyDurableSubscription");
            subscriber.setMessageListener(this);
            System.out.println("listening to incoming topic messages with durable subscription...");
            con.start();
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new JMSException(e.getMessage());
        }
    }

    public void onMessage(Message message) {
        System.out.println("Got message (in durable subscription)!");
        System.out.println(message);
    }
}
