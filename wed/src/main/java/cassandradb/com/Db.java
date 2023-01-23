package cassandradb.com;

import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.time.LocalDateTime;
import com.datastax.driver.core.*;


public class Db {

public void cassandradb(String msg) {
	
	try {
String serverIp="127.0.0.1";
String keyspace = "Alstom";

Cluster cluster = null;

cluster = Cluster.builder().withPort(9042).addContactPoint(serverIp).build();
//session = cluster.connect();

Session session = cluster.connect(keyspace);

DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd'T'HH:mm:ss");  
LocalDateTime now = LocalDateTime.now(); 
//System.out.println(dtf.format(now)+" "+msg);
String time = dtf.format(now);
String uniqueID = UUID.randomUUID().toString();

//System.out.println("INSERT INTO message (id,message,time_and_date) VALUES("+"'"+uniqueID+"'"+","+"'"+ msg+"'"+","+"'"+time+"'" +");");
session.execute("INSERT INTO DATA (id,message,date_and_time) VALUES("+uniqueID+","+"'"+ msg+"'"+","+"'"+time+"'" +");");
//System.out.println("COPY Alstom.DATA(id,date_and_time,message) TO "+ "'" +".."+"/"+"data.csv"+"'"+" WITH HEADER = TRUE;");

//session.execute("COPY Alstom.DATA(id,date_and_time,message) TO "+ "'" +".."+"/"+"data.csv"+"'"+" WITH HEADER = TRUE;");
	}
	catch(Exception e) {
		System.err.println(e);
	}
	

}

}
