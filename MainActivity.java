package com.example.safedrive;

import java.util.Locale;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	static boolean b=false;

	TextToSpeech tts;
	String number;
	String message;
	String name;
Button b1,b2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
     b1=(Button)findViewById(R.id.button1start);
   
     b1.setOnClickListener(this);
     
     
     b2=(Button)findViewById(R.id.button2stop);
     
     b2.setOnClickListener(this);
     
     
     if(b==true)
     {
		  Intent receivedata=getIntent();
	        number=receivedata.getStringExtra("Number");
	       message=receivedata.getStringExtra("Message");
	       name=getContactName(number);
  tts=new TextToSpeech(this , new TextToSpeech.OnInitListener() 
 {
		
		@Override
		public void onInit(int arg0) 
		{
		tts.setLanguage(Locale.getDefault());
	tts.speak( name +"says " + message, TextToSpeech.QUEUE_FLUSH, null);
		
			
		}
	});
		
        
      
     }
  
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

	@Override
	public void onClick(View v)
	{
		switch(v.getId())
		{
		
		
		
		case R.id.button1start:
			b=true;
			break;
			
			
		case R.id.button2stop:
			b=false;
			
			break;
		
		
		
		
		
		
		
		
		}
		
	}

	
	
	
	
	public String getContactName(final String phoneNumber) 
    {  
        Uri uri;
        String[] projection;

        if (Build.VERSION.SDK_INT >= 5)
        {
            uri = Uri.parse("content://com.android.contacts/phone_lookup");
            projection = new String[] { "display_name" };
        }
        else
        { 
            uri = Uri.parse("content://contacts/phones/filter");
            projection = new String[] { "name" }; 
        } 

        uri = Uri.withAppendedPath(uri, Uri.encode(phoneNumber)); 
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null); 

        String contactName = "";

        if (cursor.moveToFirst()) 
        { 
            contactName = cursor.getString(0);
        } 
        
        else
        {
        contactName="Unknown";
        }
        cursor.close();
        cursor = null;

        return contactName; 
    }	
	
	
	
	
	
	
	
}
