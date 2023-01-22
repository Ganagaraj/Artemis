package com.export;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import cassandradb.com.Db;

public class Export implements Job{
   public void execute(JobExecutionContext context) throws JobExecutionException {
	   String serverIp="127.0.0.1";
	   String keyspace = "alstom";

	   Cluster cluster = null;

	   cluster = Cluster.builder().withPort(9042).addContactPoint(serverIp).build();
	   //session = cluster.connect();

	   Session session = cluster.connect();

	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd'T'HH:mm:ss");  
	  LocalDateTime now = LocalDateTime.now(); 
	  //System.out.println(dtf.format(now)+" "+msg);
	  String time = dtf.format(now);
	  String uniqueID = UUID.randomUUID().toString();
	  System.out.println("COPY Alstom.DATA(id,date_and_time,message) TO "+ "'" +".."+"/"+"data.csv"+"'"+" WITH HEADER = TRUE;");
	  //session.execute("INSERT INTO DATA (id,message,date_and_time) VALUES("+uniqueID+","+"'"+ "god"+"'"+","+"'"+time+"'" +");");
	  session.execute("COPY Alstom.DATA(id,date_and_time,message) TO "+ "'" +".."+"/"+"data.csv"+"'"+" WITH HEADER = TRUE;");
	  //COPY cycling.cyclist_name (id,lastname) 
	 // TO '../cyclist_lastname.csv' WITH HEADER = TRUE ;
   }
}
