
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


  class YourPassHandler implements HttpHandler{
      

      
    public void handle(HttpExchange t) throws IOException {
        String path = t.getRequestURI().getPath();
        
        String[] splitpath = path.split("/");
        try {
            if(splitpath[2].equals("login")){

                    MyHandlerLogin.handle(t);


            }else if(splitpath[2].equals("register")){
                 MyHandlerRegister.handle(t);

            }else if(splitpath[2].equals("syncup")){
                MyHandlerSyncUp.handle(t);

            }else if(splitpath[2].equals("syncdown")){
                MyHandlerSyncDown.handle(t);


            }else{


            }
        
        } catch (SQLException ex) {
            //System.out.println("SQL error");
                Logger.getLogger(YourPassHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
