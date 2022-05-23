package Parameters;

public class LoginParameters{ 
            public String user;
            public String pass;
           
            
            public LoginParameters(String user, String pass){
                this.user = user;
                this.pass = pass; 
            }
            
            public LoginParameters(){
                this.user = "";
                this.pass = ""; 
            }
             public int getID(){
               
                return user.hashCode();
            }
        }
