
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;

 

public class YourPassWS {
        
        
   
    
    //main server test (starts the server)
    public static void main(String[] args) throws IOException { 
       
       try {
           
       
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0); 
        server.createContext("/yourpass", new YourPassHandler()); //crea un context
        
        server.setExecutor(null); // creates a default executor 
        server.start(); 
        System.out.println("SERVER ADDRESS: http://localhost:"+String.valueOf(8000));
        
        }catch (IOException e1){
            
        }
    }
    
    
    
    
 
    
    
  
    
}

