package sub;


import java.io.Console;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import cassandradb.com.*;
import org.apache.qpid.jms.JmsConnectionFactory;

public class Subscriber {
   public static void main(String[] args) throws Exception {
      JmsConnectionFactory factory = new JmsConnectionFactory("amqp://192.168.1.55");
      Connection connection = factory.createConnection("Ganagaraj", "admin");
      connection.start();
      Db db = new Db();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      Destination destination = session.createTopic("Alstom");
      MessageConsumer subscriber = session.createConsumer(destination);
      Console c = System.console();
      String response;
      do {      	
         Message msg = subscriber.receive();
         response = ((TextMessage) msg).getText();
         System.out.println("Received = "+response);
         db.cassandradb(response);

      } while (!response.equalsIgnoreCase("Quit"));

      connection.close();
   }
}