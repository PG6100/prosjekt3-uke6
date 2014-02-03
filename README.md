### Øving uke 6, JMS forelesning  



NB! Få Glassfish 4 og JDK 7 først.

- Glassfish 4: http://download.java.net/glassfish/4.0/release/glassfish-4.0.zip
- JDK 7: http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html

---
**Start glassfish**

Gå til  katalogen $GLASSFISH_HOME/glassfish/bin/. Kjør ``startserv``.  


### JMS  

** Point-To-Point  **

*Oppsett av ``ConnectionFactory``*
* sett opp ConnectionFactory i glassfish admin console.
* gå til http://localhost:4848/, trykk på ``go to the Administration Console``.
* velg  "JMS Resources" => "Connection Factories".
    opprett en ny QueueConnectionFactory, med f.eks. jndi navn ``jms/QueueConnectionFactory``
* sett opp destinasjon ("JMS Resources" => "Destination Resources"). Opprett en ny Queue, jndi navn ``jms/Queue``, phys. destination name, "que.phys.destination.name"

I prosjektet finner dere pakke ``no.nith.pg6100.p2p``. Her er det kodeskjelett som dere må implementere.

Vår klient i denne øvingen er ikke lokal, dvs den kjører ikke inne i container. Sendere sender melding til destinasjon som "lever" i container, men klientene kjører uten container. Dermer er spørsmålet er, * hvordan hente vi ConnectionFactory?*
Vi kan slå opp i JNDI som vist under: 

    InitialContext ctx=new InitialContext();
    ctx.lookup(<jndi_navn>);

hent ``QueueConnectionFactory``

    QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup("jms/QueueConnectionFactory");

hent ``Queue``

    Destination destination = (Destination) context.lookup("jms/Queue");

eller

    Queue destination = (Queue) context.lookup("jms/Queue");


Her <jndi_navn> skal være samme som vi har opprettet i Glassfish admin console. For å hente destinasjon, må vi gjøre det samme. Disse to er såkalte ``Administered objects``, som må konfigureres på forhånd, ref forrige forelesning.



** Publish / Subscribe **

I prosjektet finner dere pakke ``no.nith.pg6100.pubsub``. Her er det kodeskjelett som dere skal implementere.

* opprett en ny ``TopicConnectionFactory``, jndi navn ``jms/TopicConnectionFactory``
* opprett en ny Topic, jndi navn ``jms/Topic``, phys. destination name, "top.phys.destination.name"
..*  
  

            
            


** Durable subscription **

I prosjektet finner dere pakke ``no.nith.pg6100.durable``. Her er det kodeskjelett som dere skal implementere.

NB! Husk å sette clientId (ref forelesning).

Enda en NB!. For at durable subscription skal funke, må dere bruke jms 2.0 siden glassfish 4 er Java EE 7 og dermed er det jms 2.0 api-et som gjelder. For å teste durable subscription, må dere endre pom.xml:

    <dependency>
        <groupId>javax.jms</groupId>
        <artifactId>jms-api</artifactId>
        <version>2.0</version>
    </dependency>
i stedet for gamle ``1.1-rev-1`` versjon som var der. For gradle er det vel ``javax.jms:jms-api:2.0`` som skal brukes.

Når du har testet en enkel variant av durable subscription, kan du stoppe klienten. Send så en melding til topic mens klienten er avkoblet. Start klienten. Den skal motta meldingen som ble sendt mens han var offline (dvs dette er hensikten med durable subscription). Hvilken ``DeliveryMode`` har du brukt?

---
***Felles til alle deløvingene***

Eksperimenter med forskjellige type melding. Prøv å sette TTL, endre ``ACKNOWLEDGEMENT mode``, mao alt som har med pålitelighet å gjøre -- det vi snakket om på forelesningen.
 
---

De som har gradle installert, kan bruke det istedet for maven. Byggeskriptet for gradle er lagt til. Undertegnede har testet med gradle 1.10.
