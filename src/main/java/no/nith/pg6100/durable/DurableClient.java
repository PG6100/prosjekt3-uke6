package no.nith.pg6100.durable;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DurableClient {
    public static void main(String[] args) {
        try {
            new DurableClient().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void start() throws NamingException, JMSException {
        System.out.println("Starting a durable subscriber...");
        InitialContext context = new InitialContext();
        ConnectionFactory conFactory = (ConnectionFactory) context.lookup("jms/TopicConnectionFactory");
        //TODO
        // opprett connection
        // hent sessjon osv.
        // noe spesielt man må gjøre for durable subscription?
    }

}
