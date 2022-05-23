package Communication;


import java.io.Serializable;
import java.util.List;

import Parameters.Page;
import java.util.LinkedList;
        

        public class Response implements Serializable{ 
            public String headtag;
            public String statustag;
            public String messagetag;
            public List<Page> data;
            
            public void setStatus(String statustag){
                this.statustag= statustag;
            }
            public void setMessage(String messagetag){
                this.messagetag= messagetag;
            }
            public void setHead(String headtag){
                this.headtag = headtag;
            }
            public void setData(List<Page> data){
                this.data = data;
            }
            public String getStatus(){
                return this.statustag;
            }
            public String getMessage(){
                return this.messagetag;
            }
            public String getHead(){
                return this.headtag;
            }
            public List<Page> getData(){
                return this.data;
            }
            public Response(){
                this.headtag = "YourPass";
                this.statustag = "-2";
                this.messagetag = "nothing";
                this.data = new LinkedList<Page>();
            }
        }
