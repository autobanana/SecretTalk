package com.example.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpRequest {
	
	public String GET(String url)
	{	
		InputStream inputStream = null;
		String result;
		try{
			// create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            
            //make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            
            //receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
			
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "fail";
            
            return result;
			
		}
		catch(Exception ex){
			Log.e("GET", ex.toString());
			return ex.toString();
		}
		
		
	}
	
	//Convert InputStream to String
	private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
 
    }

}
