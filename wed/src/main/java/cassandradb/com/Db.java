package cassandradb.com;

import java.util.Date;

import com.datastax.driver.core.*;


public class Db {
public void cassandradb(String msg) {
String serverIp="127.0.0.1";
String keyspace = "alstom";

Cluster cluster = null;

cluster = Cluster.builder().withPort(9042).addContactPoint(serverIp).build();
Session session = cluster.connect();

session = cluster.connect(keyspace);

   Date date = new Date();
   long msec = date.getTime();
 

session.execute("INSERT INTO message (id,message) VALUES("+msec+","+"'"+ msg+"'" +");");


}

}
