
import Communication.Response;

import Parameters.Page;
import Parameters.SyncDownParameters;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import net.sf.json.JSONSerializer;

import java.sql.*;

import java.util.LinkedList;
import java.util.List;
import net.sf.json.JSONObject;

 class MyHandlerSyncDown  {
        
        
        static void handle(HttpExchange t) throws IOException, SQLException {
                       
            //declarar valors request
            InputStream in;
            String jsonrequest = "";
            BufferedReader streamReader;
            StringBuilder responseStrBuilder;
            
            //declarar valors response
            Response response = new Response();
            JSONObject jsonResponseObject;
            byte[] bresponse;
            SyncDownParameters params = new SyncDownParameters();
            String jsonresponse = ""; 
 
            //DB
            DBAccess db = new DBAccess();
            
            
//////////////GET REQUEST
            in = t.getRequestBody();
            in.read();
            streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8")); 
            responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            
          
            jsonrequest = responseStrBuilder.toString();
            
   //jsonrequest = "{\"data\":[\"youtube.com\"],\"head\":\"YourPass\",\"user\":\"dber\",\"pass\":\"1234\"}";
            
            
            System.out.println("Request:"+jsonrequest);
            
//!////////////DESERIALIZE REQUEST (posar valors a SyncDownParameters)
            
             Gson gson = new Gson();  
             
             if(jsonrequest.equals("")){
                    response.setStatus("-99"); 
                    response.setMessage("No HTTP received");
             }else{
                params = gson.fromJson(jsonrequest, SyncDownParameters.class);
            
             
            
          
//!////////////FER SELECT A LA BASE DE DADES, SI EXISTEIX found = true, sino found = false
            boolean found = false;
            boolean synced = false;
            
            ResultSet rs = db.execQuery("SELECT * from users WHERE username='"+params.user+"' AND password='"+params.pass+"'");
            
            while ( rs.next() ) {
                found = true;
            }
            
            if(found){
                if(params.data.get(0).equals(""))
                    rs = db.execQuery("SELECT * from pages WHERE userID='"+params.getID()+"' AND timestamp >= 0");
                else
                    rs = db.execQuery("SELECT * from pages WHERE userID='"+params.getID()+"' AND timestamp > (SELECT timestamp from pages WHERE userID='"+params.getID()+"' AND domainname='"+params.data.get(0) +"') ");
                
                List<Page> listtodownload = new LinkedList<Page>();
                while ( rs.next() ) {
                    listtodownload.add(new Page(rs.getString("domainname"),rs.getString("domainuser"),rs.getString("domainpswd")));
                }
                
                
                synced = true;
                
                if(synced){
                    response.data = listtodownload;
                    
                }
                
                
            }else{
                synced = false;
            }
//////////////POSAR VALORS A Response
            
            if(found){
                if(!synced){
                    response.setStatus("-1"); 
                    response.setMessage("Already synced");
                }else{
                    response.setStatus("0"); 
                    response.setMessage("Synced successful");
                }
                
            }else{
                response.setStatus("1"); 
                response.setMessage("Wrong Credentials");
            }
             }
            
/////////////SERIALIZE RESPONSE        
            jsonResponseObject = (JSONObject) JSONSerializer.toJSON(response);
            jsonresponse = jsonResponseObject.toString();
            bresponse = jsonresponse.getBytes("UTF-8"); 

/////////////SEND RESPONSE
            
            t.sendResponseHeaders(200, bresponse.length); 
            OutputStream os = t.getResponseBody(); 
            os.write(bresponse); 
            os.close(); 
            in.close();
        }
    }

