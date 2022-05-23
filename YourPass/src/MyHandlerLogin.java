
import Parameters.LoginParameters;
import Communication.Response;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


import net.sf.json.JSONSerializer;

import java.sql.*;

import net.sf.json.JSONObject;

 class MyHandlerLogin  {
        
        
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
            LoginParameters params = new LoginParameters();
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
   //jsonrequest = "{\"data\":[],\"head\":\"YourPass\",\"user\":\"dber\",\"pass\":\"1234\"}";
            System.out.println("Request:"+jsonrequest);
            
//!////////////DESERIALIZE REQUEST (posar valors a LoginParameters)

            Gson gson = new Gson();  
             
             if(jsonrequest.equals("")){
                    response.setStatus("-99"); 
                    response.setMessage("No HTTP received");
                 
                 
                 
                 
             }else{
                params = gson.fromJson(jsonrequest, LoginParameters.class);
            
            System.out.println("parsed:"+params.user+"|"+params.pass);

            
          
//!////////////FER SELECT A LA BASE DE DADES, SI EXISTEIX found = true, sino found = false
            boolean found = false;
           

            ResultSet rs = db.execQuery("SELECT * from users WHERE username='"+params.user+"' AND password='"+params.pass+"'");
            
           
            while ( rs.next() ) {
                found = true;
            }
            
           
            
//////////////POSAR VALORS A Response
            
            if(found){
                response.setStatus("0"); 
                response.setMessage("Login Successful");
            }else{
                response.setStatus("1"); 
                response.setMessage("Wrong credentials");
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

