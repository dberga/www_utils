
import Parameters.RegisterParameters;
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

 class MyHandlerRegister  {
        
        
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
            RegisterParameters params = new RegisterParameters();
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
   //jsonrequest = "{\"data\":[\"Dani\",\"Castaño\", \"kasta.dani@gmail.com\"],\"head\":\"YourPass\",\"user\":\"kasta\",\"pass\":\"12345\"}";
            System.out.println("Request:"+jsonrequest);
            
//!////////////DESERIALIZE REQUEST (posar valors a RegisterParameters)
            
             Gson gson = new Gson();  
             

             if(jsonrequest.equals("")){
                    response.setStatus("-99"); 
                    response.setMessage("No HTTP received");
             }else{
                params = gson.fromJson(jsonrequest, RegisterParameters.class);
                
                System.out.println("parsed:"+params.user+"|"+params.pass+"|"+params.data.get(0)+"|"+params.data.get(1)+"|"+params.data.get(2));

                
             
            
            
          
//!////////////FER SELECT A LA BASE DE DADES, SI NO ESTA DUPLICAT , FER INSERT DEL USUARI
            boolean found = false;
            boolean registered = false;

            //SELECT
            ResultSet rs = db.execQuery("SELECT * from users WHERE username='"+params.user+"' AND password='"+params.pass+"'");
           
            
            
            while ( rs.next() ) {
                found = true;
            }
            
            
            if(!found){
                //INSERT
                db.execUpdate("INSERT INTO users (userID,username,password,LastName, FirstName, Email) VALUES ("+String.valueOf(params.getID())+",'"+params.user+"','"+params.pass+"','"+params.data.get(1) +"','"+params.data.get(0)+"','"+params.data.get(2)+"')");
           
                registered = true;
            }else{
                //NOTHING
                registered = false;
            }
//////////////POSAR VALORS A Response
            
            if(registered){
                response.setStatus("0"); 
                response.setMessage("Register Successful");
            }else{
                response.setStatus("1"); 
                response.setMessage("Duplicated Credentials");
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

