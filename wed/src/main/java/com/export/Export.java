package com.export;

import com.datastax.driver.core.ColumnDefinitions;
/*import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

//import com.datastax.driver.core.Cluster;
//import com.datastax.driver.core.Session;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

//import cassandradb.com.Db;

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
	

	  CqlSession session = CqlSession.builder().build();

	  SimpleStatement statement = SimpleStatement.newInstance("COPY ALstom.DATA (id ,message, date_and_time) FROM '../data.csv' WITH DELIMITER = ',' AND HEADER = TRUE;");

	  session.execute(statement);

	  //session.execute("INSERT INTO DATA (id,message,date_and_time) VALUES("+uniqueID+","+"'"+ "god"+"'"+","+"'"+time+"'" +");");
	 // session.execute("COPY Alstom.DATA(id,date_and_time,message) TO "+ "'" +".."+"/"+"data.csv"+"'"+" WITH HEADER = TRUE;");
	  //COPY cycling.cyclist_name (id,lastname) 
	 // TO '../cyclist_lastname.csv' WITH HEADER = TRUE ;
   }
}*/
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Export implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        CqlSession session = CqlSession.builder().build();
        String selectQuery = "SELECT * FROM alstom.DATA;";
        ResultSet resultSet = session.execute(selectQuery);
        String csvFile = "D:\\data\\file.csv";

        try (FileWriter fileWriter = new FileWriter(csvFile)) {
            // Write the column names as the header of the CSV file
        	 com.datastax.oss.driver.api.core.cql.ColumnDefinitions columnDefinitions = resultSet.getColumnDefinitions();
        	 List<String> columnNames = new ArrayList<>();
             for (int i = 0; i < columnDefinitions.size(); i++) {
                 columnNames.add(columnDefinitions.get(i).toString());
             }
             fileWriter.append(String.join(",", columnNames));
             fileWriter.append("\n");
            for (Row row : resultSet) {
                String[] rowData = new String[resultSet.getColumnDefinitions().size()];
                for (int i = 0; i < resultSet.getColumnDefinitions().size(); i++) {
                    rowData[i] = row.getObject(i).toString();
                }
                fileWriter.append(String.join(",", rowData));
                fileWriter.append("\n");
            }
            System.out.println("Data exported successfully to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.close();
    }
}

