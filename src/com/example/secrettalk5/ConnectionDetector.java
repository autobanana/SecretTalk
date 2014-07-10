package com.example.secrettalk5;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

//			Toast.makeText(_context, "disconnection", Toast.LENGTH_LONG).show();

    		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(_context);
    		
    		alertDialogBuilder.setTitle("無法連接網路\n請先開啟無線上網或行動網路");
    		alertDialogBuilder.setCancelable(true);
    		alertDialogBuilder.setNegativeButton("確定",
	    		new DialogInterface.OnClickListener() {
	    			public void onClick(DialogInterface dialog,int id) {
	    				// if this button is clicked, just close
	    				// the dialog box and do nothing
	    				dialog.cancel();
	    			}
    			});	
    		AlertDialog alertDialog = alertDialogBuilder.create();
       	 
       		// show it
       		alertDialog.show();
    		

			
    	}
    }
    
}