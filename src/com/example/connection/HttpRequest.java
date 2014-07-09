package com.example.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;






import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.content.Context;
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
	
	public String POST(String url , List params){
		HttpPost post = new HttpPost(url);
		 try {
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			
			HttpResponse httpResponse = new DefaultHttpClient().execute(post);
				
			String strResult = EntityUtils.toString(httpResponse.getEntity());
			return strResult;
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return e.toString();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return e.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return e.toString();
		}
		
	}
	
	private Context getApplicationContext() {
		// TODO Auto-generated method stub
		return null;
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
