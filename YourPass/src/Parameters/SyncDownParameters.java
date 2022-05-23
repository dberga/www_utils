package Parameters;

import java.util.LinkedList;
import java.util.List;

 public class SyncDownParameters { 
            public String user;
            public String pass;
            public List<String> data;
            
            public SyncDownParameters (String user, String pass, List data){
                this.user = user;
                this.pass = pass;
                this.data = data; 
            }
            public SyncDownParameters (){
                this.user = "";
                this.pass = "";
                this.data = new LinkedList<String>();
                this.data.add("");//lastpagesynced
            }
            
             public int getID(){
               
                return user.hashCode();
            }
        }

