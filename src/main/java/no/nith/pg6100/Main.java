package no.nith.pg6100;

public class Main {

    public static void main(String[] args) {
        System.out.println("Implementasjon av JMS applikasjon");
        System.out.println("1. Point-To-Point");
        System.out.println("for å kjøre 1. kan man gjøre det via IDE eller maven/gradle");
        System.out.println("mottaker: mvn clean package exec:java -Dexec.mainClass=\"no.nith.pg6100.p2p.BasicQueueReceiver\"");
        System.out.println("sender: mvn clean package exec:java -Dexec.mainClass=\"no.nith.pg6100.p2p.BasicQueueSender\"");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("2. Publish subscribe");
    }
}
