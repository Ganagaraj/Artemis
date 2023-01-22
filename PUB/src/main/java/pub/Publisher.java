package pub;

import java.io.Console;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;

public class Publisher {
   public static void main(String[] args) {
	   try {
      JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
      Connection connection = factory.createConnection("Ganagaraj", "admin");
      connection.start();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      Destination destination = session.createTopic("Alstom");
      MessageProducer publisher = session.createProducer(destination);

      Scanner input = new Scanner(System.in);
      String response;
      do {
         System.out.println("Enter message: ");
         response = input.nextLine();
         TextMessage msg = session.createTextMessage(response);
         publisher.send(msg);

      } while (!response.equalsIgnoreCase("Quit"));
      input.close();
      connection.close();
	   }
	   catch(Exception e) {
		   System.err.println(e);
	   }
   }
}
