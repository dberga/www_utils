package Communication;


import java.io.Serializable;
import java.util.List;

import Parameters.Page;
import java.util.LinkedList;
        

        public class Request implements Serializable{ 
            public String headtag;
            public String user;
            public String pass;
            public List<String> data;
            
            public void setUser(String user){
                this.user= user;
            }
            public void setPass(String pass){
                this.pass= pass;
            }
            public void setHead(String headtag){
                this.headtag = headtag;
            }
            public void setData(List<String> data){
                this.data = data;
            }
            public String getUser(){
                return this.user;
            }
            public String getPass(){
                return this.pass;
            }
            public String getHead(){
                return this.headtag;
            }
            public List<String> getData(){
                return this.data;
            }
            public Request(){
                this.headtag = "YourPass";
                this.user = "-2";
                this.pass = "nothing";
                this.data = new LinkedList<String>();
            }
        }
