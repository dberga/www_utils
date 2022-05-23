package Parameters;


import java.io.Serializable;



    public class Page implements Serializable{ 
            public String domain;
            public String user;
            public String pass;
            
            public String getDomain(){
                return this.domain;
            }
            public String getUser(){
                return this.user;
            }
            public String getPass(){
                return this.pass;
            }
            public void setDomain(String domain){
                
                this.domain = domain;
            }
            public void setPass(String pass){
                this.pass = pass;
            }
            public void setUser(String user){
                this.user = user;
            }
            public Page(){
                  this.domain = "";
                  this.pass = "";
                  this.user = "";
            }
            public Page(String domain, String pass, String user){
                  this.domain = domain;
                  this.pass = pass;
                  this.user = user;
            }
        }

