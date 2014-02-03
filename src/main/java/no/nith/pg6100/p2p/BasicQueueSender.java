package no.nith.pg6100.p2p;

import javax.jms.*;
import javax.naming.InitialContext;

public class BasicQueueSender {
    public static void main( String[] args )    {
        try {
            System.out.println("Initializing queue sender...");
            InitialContext context = new InitialContext();
            QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/QueueConnectionFactory");
            System.out.println("got queue connection factory: " + connectionFactory.getClass().getName());
            //TODO kode for å sende til queue "jms/Queue"
            // jndi oppslag for å hente destinasjon
            // opprett connection, opprett session
            // lag en melding, f.eks text melding, og send til queue
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
