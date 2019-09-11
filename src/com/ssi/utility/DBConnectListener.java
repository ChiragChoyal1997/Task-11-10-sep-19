package com.ssi.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DBConnectListener implements ServletContextListener {
	Connection con;
    
    public void contextDestroyed(ServletContextEvent sce)  {
    	try{
        	con.close();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
    }
    public void contextInitialized(ServletContextEvent sce)  { 
    	 try{
        	 Class.forName("oracle.jdbc.driver.OracleDriver");
        	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","abc123","abc123");
        	 ServletContext context=sce.getServletContext();
        	 context.setAttribute("datacon", con);
         }catch(Exception e){
        	 
        	 e.printStackTrace();	
         }
    }
	
}
