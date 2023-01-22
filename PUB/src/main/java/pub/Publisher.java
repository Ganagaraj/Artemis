package pub;

import java.io.Console;
import java.util.Date;
import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.utilitis.Utilitis;

public class Publisher implements Job{
   public void execute(JobExecutionContext context) throws JobExecutionException  {
	   try {
      Utilitis utility = new Utilitis();
         String response = "Artemis --->>> Hello user! Time is " + new Date();
         TextMessage msg = utility.getSession().createTextMessage(response);
         utility.getPublisher().send(msg);
     // input.close();
      //connection.close();
	   }
	   catch(Exception e) {
		   System.err.println(e);
	   }
   }
}
