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
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.export.Export;

public class Subscriber {
   public static void main(String[] args) throws Exception {
      JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
      Connection connection = factory.createConnection("Ganagaraj", "admin");
      connection.start();
      Db db = new Db();
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      Destination destination = session.createTopic("Alstom");
      MessageConsumer subscriber = session.createConsumer(destination);
      
      
     /* JobDetail job1 = JobBuilder.newJob(Export.class).withIdentity("job1", "group1").build();  
      Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("2 * * * * ?")).build();  
      Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();  
      scheduler1.start();  
      scheduler1.scheduleJob(job1, trigger1); */
      
      
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