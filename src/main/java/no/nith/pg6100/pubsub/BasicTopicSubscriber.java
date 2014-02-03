package no.nith.pg6100.pubsub;

import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BasicTopicSubscriber {

    public static void main(String[] args) {
        try {
            new BasicTopicSubscriber().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws NamingException {
        System.out.println("creating a topic subscriber ....");
        InitialContext context = new InitialContext();
        ConnectionFactory conFactory = (ConnectionFactory) context.lookup("jms/TopicConnectionFactory");

        //TODO
        // her må dere gjøre akkurat det samme som med queue med unntak av at dette blir asynkron
        // husk å starte connection
        // denne klassen må ikke avslutte seg selv, og vente til meldingene kommer,
        // man kan gjøre løkke while(true) {Thread.sleep(1000);} for at tråden skal blokere
    }


    public void onMessage(final Message message) {

    }
}
