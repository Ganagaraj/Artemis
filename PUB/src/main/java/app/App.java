package app;

import java.util.concurrent.Flow.Publisher;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.utilitis.Utilitis;

public class App {
	
	public static void main(String[] args) {
 
	   try {  
	       JmsConnectionFactory factory = new JmsConnectionFactory("amqp://localhost:5672");
	        Connection connection = factory.createConnection("Ganagaraj", "admin");
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination destination = session.createTopic("Alstom");
	        MessageProducer publisher = session.createProducer(destination);
             Utilitis utility = new Utilitis();
             utility.setSession(session);
             utility.setPublisher(publisher);
	         JobDetail job1 = JobBuilder.newJob((Class<? extends Job>) Publisher.class).withIdentity("job1", "group1").build();  
	         Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1").withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();  
	         Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();  
	         scheduler1.start();  
	         scheduler1.scheduleJob(job1, trigger1);  
	            
	             
	          
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  

	}

}
