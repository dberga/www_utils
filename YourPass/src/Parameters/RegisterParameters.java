package Parameters;

import java.util.LinkedList;
import java.util.List;

public class RegisterParameters{ 
            public String user;
            public String pass;
            public List<String> data;

              
            
            public RegisterParameters(String user, String pass,List data){
                 this.user = user;
                this.pass = pass;
                
                this.data = data;
            }
            public RegisterParameters(){
                 this.user = "";
                this.pass = "";
                this.data = new LinkedList<String>();
                this.data.add(""); //fistname
                this.data.add(""); //lastname
                this.data.add(""); //mail
            }
            public int getID(){
               
                return user.hashCode();
            }
            
            
        }

