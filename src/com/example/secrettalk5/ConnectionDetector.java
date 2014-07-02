package com.example.secrettalk5;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
 
public class ConnectionDetector {
     
    private Context _context;
     
    public ConnectionDetector(Context context){
        this._context = context;
    }
 
    public boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
          if (connectivity != null) 
          {
              NetworkInfo[] info = connectivity.getAllNetworkInfo();
              if (info != null) 
                  for (int i = 0; i < info.length; i++) 
                      if (info[i].getState() == NetworkInfo.State.CONNECTED)
                      {
                          return true;
                      }
 
          }
          return false;
    }
    
    
    public void showConnction(){
    	
    	if(!isConnectingToInternet()){
    		Toast.makeText(_context, "Disconnction", Toast.LENGTH_LONG).show();
    	}else{
//    		Toast.makeText(_context, "Connction", Toast.LENGTH_LONG).show();
    	}
    	
    	
    	
    }
    
}