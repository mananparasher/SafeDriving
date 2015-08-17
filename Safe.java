package com.example.safedrive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.test.suitebuilder.annotation.SmallTest;

public class Safe extends BroadcastReceiver 
{

	@Override
	public void onReceive(Context arg0, Intent data)
	{
	
		Bundle extras=data.getExtras();
		if(extras!=null)
		{
			
			Object [] pduarray=(Object[])extras.get("pdus");
			
			for(int i=0;i<pduarray.length;i++)
			{
			
				SmsMessage smsdata=SmsMessage.createFromPdu( (byte [])pduarray[i]);
				String number= smsdata.getOriginatingAddress();
				String message=smsdata.getMessageBody();
			
				Intent senddata=new Intent(arg0,MainActivity.class);
				senddata.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				senddata.putExtra( "Number",number);
				senddata.putExtra( "Message",message);
				
				arg0.startActivity(senddata);
				
			}
			
			
		}
		
		
		 
		

	}

}
