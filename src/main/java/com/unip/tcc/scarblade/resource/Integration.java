
package com.unip.tcc.scarblade.resource;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Integration {

	public void executeIntegration(String id) {

		try {
		  URL url = new URL("http://localhost:8080/scarblade-treinamento/api/v1/treinamento/treinar/"+ id);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();            
			connection.addRequestProperty("Request-Method","GET");      
			    
			  
			connection.setDoInput(true);    
			connection.setDoOutput(false);    
			connection.connect();    
			    
			if(connection.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED) {    
			    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));    
			    StringBuffer newData = new StringBuffer(10000);    
			    String s = "";    
			    while (null != ((s = br.readLine()))) {    
			        newData.append(s);    
			    }    
			    br.close();    
			    System.out.println(new String(newData));    
			} 	
	
	}catch(Exception e) {
		
	}
}
}
