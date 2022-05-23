package Parameters;

import java.util.LinkedList;
import java.util.List;

public class SyncUpParameters { 
            public String user;
            public String pass;
            public List<Page> data;
           
            
            public SyncUpParameters (String user, String pass,List<Page> data){
                this.user = user;
                this.pass = pass;
                this.data = data; 
            }
            public SyncUpParameters (){
                this.user = "";
                this.pass = "";
                this.data = new LinkedList<Page>(); 
            }
            
             public int getID(){
               
                return user.hashCode();
            }
            
        }


