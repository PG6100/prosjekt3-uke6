package no.nith.pg6100;

import javax.jms.Connection;
import javax.jms.JMSException;

/**
 * Shutdown hook to close jms connections!
 */
public class ShutdownHook {

    public static void add(final Connection con) {
        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    public void run() {
                        try {
                            System.out.println("closing jms connection...");
                            con.close();
                        } catch (JMSException e) {
                            System.err.println("could not close connection from shutdown hook!");
                        }
                    }
                }
        );
    }
}
