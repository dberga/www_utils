
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBAccess {
    
            String URL = "jdbc:mysql://localhost:3306/yourpass";

            String dbuser = "root";
            String dbpass = "ascent";
            
            
            Connection sqlcon = null;
                Statement sqlstm ;
          public DBAccess() throws SQLException{
                    
              
          }
          public ResultSet execQuery(String query) throws SQLException{
                
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
              
              
              sqlcon = DriverManager.getConnection(this.URL,this.dbuser,this.dbpass);
               sqlstm = sqlcon.createStatement();

             
              return sqlstm.executeQuery(query);
          }
          
          public int execUpdate(String query) throws SQLException{
                
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
            }
              
              
              sqlcon = DriverManager.getConnection(this.URL,this.dbuser,this.dbpass);
               sqlstm = sqlcon.createStatement();

             
              return sqlstm.executeUpdate(query);
          }
}
