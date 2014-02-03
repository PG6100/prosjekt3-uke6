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
            //TODO kode for å motta melding fra queue "jms/Queue"
            // jndi oppslag for å hente destinasjon
            // opprett connection, opprett session
            // 1) vent til meldingen kommer, print ut meldingen.
            // 2) hvis 1) funker, endre koden til å lytte uendelig å printe ut etterhvert som meldingene kommer inn
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
